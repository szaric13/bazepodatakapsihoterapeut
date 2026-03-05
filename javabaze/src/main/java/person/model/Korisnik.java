package person.model;

public class Korisnik {
    private int id_korisnika;
    private int id_psihoterapeuta;
    private String korisnicko_ime;
    private String lozinka;
    private String ime;
    private String prezime;

    public Korisnik() {
    }

    public Korisnik(int id_korisnika, int id_psihoterapeuta, String korisnicko_ime, String lozinka, String ime, String prezime) {
        this.id_korisnika = id_korisnika;
        this.id_psihoterapeuta = id_psihoterapeuta;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
    }

    public int getId_psihoterapeuta() {
        return id_psihoterapeuta;
    }

    public void setId_psihoterapeuta(int id_psihoterapeuta) {
        this.id_psihoterapeuta = id_psihoterapeuta;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}