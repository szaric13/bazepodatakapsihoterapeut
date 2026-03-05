package person.model.utility;

import person.model.Beleska;
import person.model.Placanje;
import person.model.*;


import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class JDBCUtils {

    public static Connection connection = null;

    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("", "root");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novipocetakfinal", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<PsihoterapeutInfo> selectAllPsihoterapeutiProsireno() {
        List<PsihoterapeutInfo> psihoterapeuti = new ArrayList<>();


        if (connection == null) {
            connect();
        }

        String query = "SELECT o.*, p.id_psihoterapeuta, p.je_psiholog, p.id_sertifikata, " +
                "op.naziv_oblasti, s.datum_sertifikacije " +
                "FROM osoba o " +
                "JOIN psihoterapeut p ON o.id_osobe = p.id_osobe " +
                "LEFT JOIN sertifikat s ON p.id_sertifikata = s.id_sertifikata " +
                "LEFT JOIN oblast_psihoterapije op ON s.id_oblasti_psihoterapije = op.id_oblasti_psihoterapije";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_osobe = resultSet.getInt("id_osobe");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String jmbg = resultSet.getString("jmbg");
                LocalDate datum_rodjenja = resultSet.getObject("datum_rodjenja", LocalDate.class);
                String prebivaliste = resultSet.getString("prebivaliste");
                String telefon = resultSet.getString("telefon");
                String email = resultSet.getString("email");
                char pol = resultSet.getString("pol").charAt(0);

                int id_psihoterapeuta = resultSet.getInt("id_psihoterapeuta");
                boolean je_psiholog = resultSet.getBoolean("je_psiholog");
                int id_sertifikata = resultSet.getInt("id_sertifikata");
                String oblast_psihoterapije = resultSet.getString("naziv_oblasti");
                LocalDate datum_sertifikacije = resultSet.getObject("datum_sertifikacije", LocalDate.class);

                PsihoterapeutInfo psihoterapeut = new PsihoterapeutInfo(
                        id_osobe, ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email, pol,
                        id_psihoterapeuta, je_psiholog, id_sertifikata, oblast_psihoterapije, datum_sertifikacije
                );
                psihoterapeuti.add(psihoterapeut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return psihoterapeuti;
    }


    public static List<PsihoterapeutInfo> searchPsihoterapeutiSaOblasti(String imePretraga, String prezimePretraga, String oblastPretraga) {

        String query = "SELECT o.*, p.id_psihoterapeuta, p.je_psiholog, p.id_sertifikata, " +
                "op.naziv_oblasti, s.datum_sertifikacije " +
                "FROM osoba o " +
                "JOIN psihoterapeut p ON o.id_osobe = p.id_osobe " +
                "LEFT JOIN sertifikat s ON p.id_sertifikata = s.id_sertifikata " +
                "LEFT JOIN oblast_psihoterapije op ON s.id_oblasti_psihoterapije = op.id_oblasti_psihoterapije " +
                "WHERE (? = '' OR LOWER(o.ime) LIKE CONCAT('%', LOWER(?), '%')) " +
                "AND (? = '' OR LOWER(o.prezime) LIKE CONCAT('%', LOWER(?), '%')) " +
                "AND (? = '' OR LOWER(op.naziv_oblasti) LIKE CONCAT('%', LOWER(?), '%'))";

        List<PsihoterapeutInfo> filtrirani = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, imePretraga);
            statement.setString(2, imePretraga);
            statement.setString(3, prezimePretraga);
            statement.setString(4, prezimePretraga);
            statement.setString(5, oblastPretraga);
            statement.setString(6, oblastPretraga);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_osobe = resultSet.getInt("id_osobe");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");
                String jmbg = resultSet.getString("jmbg");
                LocalDate datum_rodjenja = resultSet.getObject("datum_rodjenja", LocalDate.class);
                String prebivaliste = resultSet.getString("prebivaliste");
                String telefon = resultSet.getString("telefon");
                String email = resultSet.getString("email");
                char pol = resultSet.getString("pol").charAt(0);

                int id_psihoterapeuta = resultSet.getInt("id_psihoterapeuta");
                boolean je_psiholog = resultSet.getBoolean("je_psiholog");
                int id_sertifikata = resultSet.getInt("id_sertifikata");
                String oblast_psihoterapije = resultSet.getString("naziv_oblasti");
                LocalDate datum_sertifikacije = resultSet.getObject("datum_sertifikacije", LocalDate.class);

                PsihoterapeutInfo psihoterapeut = new PsihoterapeutInfo(
                        id_osobe, ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email, pol,
                        id_psihoterapeuta, je_psiholog, id_sertifikata, oblast_psihoterapije, datum_sertifikacije
                );
                filtrirani.add(psihoterapeut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return filtrirani;
    }


    public static Korisnik loginKorisnik(String username, String password) {
        String query = "SELECT * FROM korisnik WHERE korisnicko_ime = ? AND lozinka = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id_korisnika = resultSet.getInt("id_korisnika");
                int id_psihoterapeuta = resultSet.getInt("id_psihoterapeuta");
                String korisnicko_ime = resultSet.getString("korisnicko_ime");
                String lozinka = resultSet.getString("lozinka");
                String ime = resultSet.getString("ime");
                String prezime = resultSet.getString("prezime");

                return new Korisnik(id_korisnika, id_psihoterapeuta, korisnicko_ime, lozinka, ime, prezime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static void registerPsihoterapeut(String ime, String prezime, String jmbg, LocalDate datumRodjenja,
                                             String prebivaliste, String telefon, String email, char pol,
                                             boolean jePsiholog, String korisnickoIme, String lozinka) {
        try {

            connection.setAutoCommit(false);


            String insertOsobaQuery = "INSERT INTO osoba (ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email, pol) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement osobaStatement = connection.prepareStatement(insertOsobaQuery, Statement.RETURN_GENERATED_KEYS);
            osobaStatement.setString(1, ime);
            osobaStatement.setString(2, prezime);
            osobaStatement.setString(3, jmbg);
            osobaStatement.setDate(4, Date.valueOf(datumRodjenja));
            osobaStatement.setString(5, prebivaliste);
            osobaStatement.setString(6, telefon);
            osobaStatement.setString(7, email);
            osobaStatement.setString(8, String.valueOf(pol));

            osobaStatement.executeUpdate();


            ResultSet generatedKeys = osobaStatement.getGeneratedKeys();
            int idOsobe = 0;
            if (generatedKeys.next()) {
                idOsobe = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Kreiranje osobe nije uspelo, nije dobijen ID.");
            }


            int defaultOblastId = 1;
            int defaultCentarId = 1;
            LocalDate today = LocalDate.now();

            String insertSertifikatQuery = "INSERT INTO sertifikat (id_osobe, id_oblasti_psihoterapije, datum_sertifikacije, id_centra) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement sertifikatStatement = connection.prepareStatement(insertSertifikatQuery, Statement.RETURN_GENERATED_KEYS);
            sertifikatStatement.setInt(1, idOsobe);
            sertifikatStatement.setInt(2, defaultOblastId);
            sertifikatStatement.setDate(3, Date.valueOf(today));
            sertifikatStatement.setInt(4, defaultCentarId);

            sertifikatStatement.executeUpdate();


            generatedKeys = sertifikatStatement.getGeneratedKeys();
            int idSertifikata = 0;
            if (generatedKeys.next()) {
                idSertifikata = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Kreiranje sertifikata nije uspelo, nije dobijen ID.");
            }


            String insertPsihoterapeutQuery = "INSERT INTO psihoterapeut (id_osobe, je_psiholog, id_sertifikata) VALUES (?, ?, ?)";

            PreparedStatement psihoterapeutStatement = connection.prepareStatement(insertPsihoterapeutQuery, Statement.RETURN_GENERATED_KEYS);
            psihoterapeutStatement.setInt(1, idOsobe);
            psihoterapeutStatement.setBoolean(2, jePsiholog);
            psihoterapeutStatement.setInt(3, idSertifikata);

            psihoterapeutStatement.executeUpdate();


            generatedKeys = psihoterapeutStatement.getGeneratedKeys();
            int idPsihoterapeuta = 0;
            if (generatedKeys.next()) {
                idPsihoterapeuta = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Kreiranje psihoterapeuta nije uspelo, nije dobijen ID.");
            }


            String insertKorisnikQuery = "INSERT INTO korisnik (id_psihoterapeuta, korisnicko_ime, lozinka, ime, prezime) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement korisnikStatement = connection.prepareStatement(insertKorisnikQuery);
            korisnikStatement.setInt(1, idPsihoterapeuta);
            korisnikStatement.setString(2, korisnickoIme);
            korisnikStatement.setString(3, lozinka);
            korisnikStatement.setString(4, ime);
            korisnikStatement.setString(5, prezime);

            korisnikStatement.executeUpdate();


            connection.commit();

        } catch (SQLException e) {
            try {

                connection.rollback();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Greška prilikom poništavanja transakcije: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("Greška prilikom registracije: " + e.getMessage());
        } finally {
            try {

                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Greška prilikom vraćanja autoCommit-a: " + e.getMessage());
            }
        }
    }


    public static List<OblastPsihoterapije> selectAllOblastiPsihoterapije() {
        List<OblastPsihoterapije> oblasti = new ArrayList<>();
        String query = "SELECT * FROM oblast_psihoterapije";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_oblasti_psihoterapije");
                String naziv = resultSet.getString("naziv_oblasti");
                String opis = resultSet.getString("opis");

                OblastPsihoterapije oblast = new OblastPsihoterapije(id, naziv, opis);
                oblasti.add(oblast);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oblasti;
    }


    public static List<CentarZaObuku> selectAllCentri() {
        List<CentarZaObuku> centri = new ArrayList<>();
        String query = "SELECT * FROM centar_za_obuku";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_centra");
                String naziv = resultSet.getString("naziv");
                String email = resultSet.getString("email");
                String telefon = resultSet.getString("telefon");
                String ulica = resultSet.getString("ulica");
                String broj = resultSet.getString("broj");
                String opstina = resultSet.getString("opstina");

                CentarZaObuku centar = new CentarZaObuku(id, naziv, email, telefon, ulica, broj, opstina);
                centri.add(centar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return centri;
    }
    public static List<Prijava> selectPrijaveByPsihoterapeut(int idPsihoterapeuta) {
        List<Prijava> prijave = new ArrayList<>();
        String query = "SELECT p.*, o.ime, o.prezime, o.datum_rodjenja, o.pol, o.email " +
                "FROM Prijava p " +
                "INNER JOIN Klijent k ON p.ID_klijenta = k.ID_klijenta " +
                "INNER JOIN Osoba o ON k.ID_osobe = o.ID_osobe " +
                "WHERE p.ID_psihoterapeuta = ? " +
                "ORDER BY p.datum_prijave DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idPsihoterapeuta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Prijava prijava = new Prijava();
                prijava.setId_prijave(rs.getInt("ID_prijave"));
                prijava.setId_klijenta(rs.getInt("ID_klijenta"));
                prijava.setId_psihoterapeuta(rs.getInt("ID_psihoterapeuta"));
                prijava.setDatum_prijave(rs.getDate("datum_prijave").toLocalDate());
                prijava.setProblem_opis(rs.getString("problem_opis"));
                prijava.setKomunikacioni_kanal(rs.getString("komunikacioni_kanal"));

                Klijent klijent = new Klijent();
                klijent.setId_klijenta(rs.getInt("ID_klijenta"));

                Osoba osoba = new Osoba();
                osoba.setIme(rs.getString("ime"));
                osoba.setPrezime(rs.getString("prezime"));
                osoba.setEmail(rs.getString("email"));

                klijent.setOsoba(osoba);
                prijava.setKlijent(klijent);

                prijave.add(prijava);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prijave;
    }


    public static List<Seansa> selectPastSeanseByPsihoterapeut(int idPsihoterapeuta) {
        List<Seansa> seanse = new ArrayList<>();
        String query = "SELECT s.* " +
                "FROM Seansa s " +
                "INNER JOIN Prijava p ON s.ID_prijave = p.ID_prijave " +
                "WHERE p.ID_psihoterapeuta = ? AND s.datum < CURDATE() " +
                "ORDER BY s.datum DESC, s.vreme_pocetka DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idPsihoterapeuta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Seansa seansa = new Seansa(
                        rs.getInt("ID_seanse"),
                        rs.getInt("ID_prijave"),
                        rs.getDate("datum"),
                        rs.getTime("vreme_pocetka"),
                        rs.getInt("trajanje_minuta"),
                        rs.getBoolean("je_prva_seansa"),
                        rs.getInt("ID_cene")
                );
                seanse.add(seansa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return seanse;
    }


    public static List<Seansa> selectFutureSeanseByPsihoterapeut(int idPsihoterapeuta) {
        List<Seansa> seanse = new ArrayList<>();
        String query = "SELECT s.* " +
                "FROM Seansa s " +
                "INNER JOIN Prijava p ON s.ID_prijave = p.ID_prijave " +
                "WHERE p.ID_psihoterapeuta = ? AND (s.datum > CURDATE() OR " +
                "(s.datum = CURDATE() AND s.vreme_pocetka > CURTIME())) " +
                "ORDER BY s.datum ASC, s.vreme_pocetka ASC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idPsihoterapeuta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Seansa seansa = new Seansa(
                        rs.getInt("ID_seanse"),
                        rs.getInt("ID_prijave"),
                        rs.getDate("datum"),
                        rs.getTime("vreme_pocetka"),
                        rs.getInt("trajanje_minuta"),
                        rs.getBoolean("je_prva_seansa"),
                        rs.getInt("ID_cene")
                );
                seanse.add(seansa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return seanse;
    }


    public static List<TestRezultat> selectTestRezultatiBySeansa(int idSeanse) {
        List<TestRezultat> rezultati = new ArrayList<>();
        String query = "SELECT tr.* FROM Test_rezultat tr WHERE tr.ID_seanse = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idSeanse);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TestRezultat rezultat = new TestRezultat(
                        rs.getInt("ID_rezultata"),
                        rs.getInt("ID_testa"),
                        rs.getInt("ID_seanse"),
                        rs.getInt("ID_klijenta"),
                        rs.getInt("ID_psihoterapeuta"),
                        rs.getString("rezultat"),
                        rs.getDate("datum_testiranja")
                );
                rezultati.add(rezultat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rezultati;
    }


    public static List<Beleska> selectBeleskeBySeansa(int idSeanse) {
        List<Beleska> beleske = new ArrayList<>();
        String query = "SELECT b.* FROM Beleska b WHERE b.ID_seanse = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idSeanse);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Beleska beleska = new Beleska(
                        rs.getInt("ID_beleske"),
                        rs.getInt("ID_seanse"),
                        rs.getString("sadrzaj"),
                        rs.getDate("datum_unosa")
                );
                beleske.add(beleska);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return beleske;
    }


    public static Seansa selectSeansaById(int idSeanse) {
        String query = "SELECT s.* FROM Seansa s WHERE s.ID_seanse = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idSeanse);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Seansa(
                        rs.getInt("ID_seanse"),
                        rs.getInt("ID_prijave"),
                        rs.getDate("datum"),
                        rs.getTime("vreme_pocetka"),
                        rs.getInt("trajanje_minuta"),
                        rs.getBoolean("je_prva_seansa"),
                        rs.getInt("ID_cene")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public static Klijent selectKlijentByPrijava(int idPrijave) {
        String query = "SELECT k.*, o.ime, o.prezime, o.jmbg, o.datum_rodjenja, o.prebivaliste, o.telefon, o.email, o.pol " +
                "FROM Prijava p " +
                "INNER JOIN Klijent k ON p.ID_klijenta = k.ID_klijenta " +
                "INNER JOIN Osoba o ON k.ID_osobe = o.ID_osobe " +
                "WHERE p.ID_prijave = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idPrijave);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Klijent klijent = new Klijent();
                klijent.setId_klijenta(rs.getInt("ID_klijenta"));
                klijent.setId_osobe(rs.getInt("ID_osobe"));
                klijent.setRanije_isao_psihoterapeutu(rs.getBoolean("ranije_isao_psihoterapeutu"));

                Osoba osoba = new Osoba();
                osoba.setId_osobe(rs.getInt("ID_osobe"));
                osoba.setIme(rs.getString("ime"));
                osoba.setPrezime(rs.getString("prezime"));
                osoba.setJmbg(rs.getString("jmbg"));
                osoba.setDatum_rodjenja(rs.getDate("datum_rodjenja").toLocalDate());
                osoba.setPrebivaliste(rs.getString("prebivaliste"));
                osoba.setTelefon(rs.getString("telefon"));
                osoba.setEmail(rs.getString("email"));
                osoba.setPol(rs.getString("pol").charAt(0));

                klijent.setOsoba(osoba);

                return klijent;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static PsiholoskiTest selectPsiholoskiTestById(int idTesta) {
        String query = "SELECT pt.* FROM Psiholoski_test pt WHERE pt.ID_testa = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idTesta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new PsiholoskiTest(
                        rs.getInt("ID_testa"),
                        rs.getString("naziv"),
                        rs.getString("oblast"),
                        rs.getBigDecimal("cena")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static PsihoterapeutInfo selectCurrentPsihoterapeut(int idKorisnika) {
        String query = "SELECT o.*, pt.ID_psihoterapeuta, pt.je_psiholog, s.ID_sertifikata, s.datum_sertifikacije, op.naziv_oblasti " +
                "FROM Korisnik k " +
                "INNER JOIN Psihoterapeut pt ON k.ID_psihoterapeuta = pt.ID_psihoterapeuta " +
                "INNER JOIN Osoba o ON pt.ID_osobe = o.ID_osobe " +
                "INNER JOIN Sertifikat s ON pt.ID_sertifikata = s.ID_sertifikata " +
                "INNER JOIN Oblast_psihoterapije op ON s.ID_oblasti_psihoterapije = op.ID_oblasti_psihoterapije " +
                "WHERE k.ID_korisnika = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idKorisnika);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LocalDate datumRodjenja = rs.getDate("datum_rodjenja").toLocalDate();
                LocalDate datumSertifikacije = rs.getDate("datum_sertifikacije").toLocalDate();

                return new PsihoterapeutInfo(
                        rs.getInt("ID_osobe"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("JMBG"),
                        datumRodjenja,
                        rs.getString("prebivaliste"),
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getString("pol").charAt(0),
                        rs.getInt("ID_psihoterapeuta"),
                        rs.getBoolean("je_psiholog"),
                        rs.getInt("ID_sertifikata"),
                        rs.getString("naziv_oblasti"),
                        datumSertifikacije
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public static List<Placanje> selectPlacanjaZaPsihoterapeuta(int idPsihoterapeuta) {
        String query = "SELECT p.*, " +
                "o.ime, o.prezime, " +
                "v.skraceni_naziv, v.puni_naziv, " +
                "s.datum as datum_seanse, " +
                "pt.naziv as naziv_testa " +
                "FROM Placanje p " +
                "INNER JOIN Klijent k ON p.ID_klijenta = k.ID_klijenta " +
                "INNER JOIN Osoba o ON k.ID_osobe = o.ID_osobe " +
                "INNER JOIN Valuta v ON p.ID_valute = v.ID_valute " +
                "LEFT JOIN Seansa s ON p.ID_seanse = s.ID_seanse " +
                "LEFT JOIN Test_rezultat tr ON p.ID_rezultata = tr.ID_rezultata " +
                "LEFT JOIN Psiholoski_test pt ON tr.ID_testa = pt.ID_testa " +
                "WHERE (p.svrha = 'seansa' AND s.ID_prijave IN " +
                "  (SELECT pr.ID_prijave FROM Prijava pr WHERE pr.ID_psihoterapeuta = ?)) " +
                "OR (p.svrha = 'test' AND tr.ID_psihoterapeuta = ?) " +
                "ORDER BY p.datum_placanja DESC";

        List<Placanje> placanja = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idPsihoterapeuta);
            pstmt.setInt(2, idPsihoterapeuta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Placanje placanje = new Placanje();
                placanje.setIdPlacanja(rs.getInt("ID_placanja"));
                placanje.setIdKlijenta(rs.getInt("ID_klijenta"));

                if (rs.getObject("ID_seanse") != null) {
                    placanje.setIdSeanse(rs.getInt("ID_seanse"));
                }
                if (rs.getObject("ID_rezultata") != null) {
                    placanje.setIdRezultata(rs.getInt("ID_rezultata"));
                }

                placanje.setSvrha(rs.getString("svrha"));
                placanje.setIdValute(rs.getInt("ID_valute"));
                placanje.setIznos(rs.getBigDecimal("iznos"));
                placanje.setNacinPlacanja(rs.getString("nacin_placanja"));
                placanje.setBrojRate(rs.getInt("broj_rate"));

                if (rs.getBigDecimal("procenat_prve_rate") != null) {
                    placanje.setProcenatPrveRate(rs.getBigDecimal("procenat_prve_rate"));
                }
                if (rs.getDate("rok_druge_rate") != null) {
                    placanje.setRokDrugeRate(rs.getDate("rok_druge_rate").toLocalDate());
                }
                if (rs.getBigDecimal("provizija") != null) {
                    placanje.setProvizija(rs.getBigDecimal("provizija"));
                }

                placanje.setDatumPlacanja(rs.getDate("datum_placanja").toLocalDate());

                placanje.setImeKlijenta(rs.getString("ime"));
                placanje.setPrezimeKlijenta(rs.getString("prezime"));
                placanje.setSkraceniNazivValute(rs.getString("skraceni_naziv"));
                placanje.setPuniNazivValute(rs.getString("puni_naziv"));

                if (rs.getDate("datum_seanse") != null) {
                    placanje.setDatumSeanse(rs.getDate("datum_seanse").toLocalDate());
                }
                if (rs.getString("naziv_testa") != null) {
                    placanje.setNazivTesta(rs.getString("naziv_testa"));
                }

                placanja.add(placanje);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return placanja;
    }


    public static List<Placanje> selectPlacanjaZaKlijenta(int idKlijenta) {
        String query = "SELECT p.*, " +
                "o.ime, o.prezime, " +
                "v.skraceni_naziv, v.puni_naziv, " +
                "s.datum as datum_seanse, " +
                "pt.naziv as naziv_testa " +
                "FROM Placanje p " +
                "INNER JOIN Klijent k ON p.ID_klijenta = k.ID_klijenta " +
                "INNER JOIN Osoba o ON k.ID_osobe = o.ID_osobe " +
                "INNER JOIN Valuta v ON p.ID_valute = v.ID_valute " +
                "LEFT JOIN Seansa s ON p.ID_seanse = s.ID_seanse " +
                "LEFT JOIN Test_rezultat tr ON p.ID_rezultata = tr.ID_rezultata " +
                "LEFT JOIN Psiholoski_test pt ON tr.ID_testa = pt.ID_testa " +
                "WHERE p.ID_klijenta = ? " +
                "ORDER BY p.datum_placanja DESC";

        List<Placanje> placanja = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idKlijenta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Placanje placanje = mapResultSetToPlacanje(rs);
                placanja.add(placanje);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return placanja;
    }


    public static BigDecimal selectDugovanjaZaKlijenta(int idKlijenta) {
        String query = "SELECT SUM(CASE " +
                "WHEN p.broj_rate = 2 AND p.rok_druge_rate > CURDATE() THEN " +
                "  p.iznos * (100 - COALESCE(p.procenat_prve_rate, 0)) / 100 " +
                "ELSE 0 END) as dugovanje " +
                "FROM Placanje p " +
                "WHERE p.ID_klijenta = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idKlijenta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                BigDecimal dugovanje = rs.getBigDecimal("dugovanje");
                return dugovanje != null ? dugovanje : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return BigDecimal.ZERO;
    }


    public static List<Map<String, Object>> selectPlacanjaIDugovanja(int idPsihoterapeuta) {
        String query = "SELECT DISTINCT " +
                "k.ID_klijenta, " +
                "o.ime as ime_klijenta, " +
                "o.prezime as prezime_klijenta " +
                "FROM Klijent k " +
                "JOIN Osoba o ON k.ID_osobe = o.ID_osobe " +
                "JOIN Placanje p ON k.ID_klijenta = p.ID_klijenta " +
                "LEFT JOIN Seansa s ON p.ID_seanse = s.ID_seanse " +
                "LEFT JOIN Prijava pr ON s.ID_prijave = pr.ID_prijave " +
                "LEFT JOIN Test_rezultat tr ON p.ID_rezultata = tr.ID_rezultata " +
                "WHERE (pr.ID_psihoterapeuta = ? OR tr.ID_psihoterapeuta = ?) " +
                "ORDER BY o.prezime, o.ime";

        List<Map<String, Object>> rezultat = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, idPsihoterapeuta);
            pstmt.setInt(2, idPsihoterapeuta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> klijentInfo = new HashMap<>();
                int idKlijenta = rs.getInt("ID_klijenta");

                klijentInfo.put("idKlijenta", idKlijenta);
                klijentInfo.put("imeKlijenta", rs.getString("ime_klijenta"));
                klijentInfo.put("prezimeKlijenta", rs.getString("prezime_klijenta"));


                List<Placanje> placanja = selectPlacanjaZaKlijenta(idKlijenta);
                klijentInfo.put("placanja", placanja);
                klijentInfo.put("dugovanje", selectDugovanjaZaKlijenta(idKlijenta));

                rezultat.add(klijentInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rezultat;
    }


    private static Placanje mapResultSetToPlacanje(ResultSet rs) throws SQLException {
        Placanje placanje = new Placanje();
        placanje.setIdPlacanja(rs.getInt("ID_placanja"));
        placanje.setIdKlijenta(rs.getInt("ID_klijenta"));

        if (rs.getObject("ID_seanse") != null) {
            placanje.setIdSeanse(rs.getInt("ID_seanse"));
        }
        if (rs.getObject("ID_rezultata") != null) {
            placanje.setIdRezultata(rs.getInt("ID_rezultata"));
        }

        placanje.setSvrha(rs.getString("svrha"));
        placanje.setIdValute(rs.getInt("ID_valute"));
        placanje.setIznos(rs.getBigDecimal("iznos"));
        placanje.setNacinPlacanja(rs.getString("nacin_placanja"));
        placanje.setBrojRate(rs.getInt("broj_rate"));

        if (rs.getBigDecimal("procenat_prve_rate") != null) {
            placanje.setProcenatPrveRate(rs.getBigDecimal("procenat_prve_rate"));
        }
        if (rs.getDate("rok_druge_rate") != null) {
            placanje.setRokDrugeRate(rs.getDate("rok_druge_rate").toLocalDate());
        }
        if (rs.getBigDecimal("provizija") != null) {
            placanje.setProvizija(rs.getBigDecimal("provizija"));
        }

        placanje.setDatumPlacanja(rs.getDate("datum_placanja").toLocalDate());


        placanje.setImeKlijenta(rs.getString("ime"));
        placanje.setPrezimeKlijenta(rs.getString("prezime"));
        placanje.setSkraceniNazivValute(rs.getString("skraceni_naziv"));
        placanje.setPuniNazivValute(rs.getString("puni_naziv"));

        if (rs.getDate("datum_seanse") != null) {
            placanje.setDatumSeanse(rs.getDate("datum_seanse").toLocalDate());
        }
        if (rs.getString("naziv_testa") != null) {
            placanje.setNazivTesta(rs.getString("naziv_testa"));
        }

        return placanje;
    }
    private JDBCUtils() {

    }
}