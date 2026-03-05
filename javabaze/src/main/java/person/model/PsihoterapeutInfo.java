package person.model;

import java.time.LocalDate;

public class PsihoterapeutInfo extends Osoba {
    private int id_psihoterapeuta;
    private boolean je_psiholog;
    private int id_sertifikata;
    private String oblast_psihoterapije;
    private LocalDate datum_sertifikacije;

    public PsihoterapeutInfo(int id_osobe, String ime, String prezime, String jmbg, LocalDate datum_rodjenja, String prebivaliste, String telefon, String email, char pol) {
        super(id_osobe, ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email, pol);

        this.id_psihoterapeuta = 0;
        this.je_psiholog = false;
        this.id_sertifikata = 0;
        this.oblast_psihoterapije = "";
        this.datum_sertifikacije = null;
    }

    public PsihoterapeutInfo(int id_osobe, String ime, String prezime, String jmbg, LocalDate datum_rodjenja,
                             String prebivaliste, String telefon, String email, char pol,
                             int id_psihoterapeuta, boolean je_psiholog, int id_sertifikata,
                             String oblast_psihoterapije, LocalDate datum_sertifikacije) {
        super(id_osobe, ime, prezime, jmbg, datum_rodjenja, prebivaliste, telefon, email, pol);
        this.id_psihoterapeuta = id_psihoterapeuta;
        this.je_psiholog = je_psiholog;
        this.id_sertifikata = id_sertifikata;
        this.oblast_psihoterapije = oblast_psihoterapije;
        this.datum_sertifikacije = datum_sertifikacije;
    }

    public int getId_psihoterapeuta() {
        return id_psihoterapeuta;
    }

    public void setId_psihoterapeuta(int id_psihoterapeuta) {
        this.id_psihoterapeuta = id_psihoterapeuta;
    }

    public boolean getJe_psiholog() {
        return je_psiholog;
    }

    public boolean isJe_psiholog() {
        return je_psiholog;
    }

    public void setJe_psiholog(boolean je_psiholog) {
        this.je_psiholog = je_psiholog;
    }

    public int getId_sertifikata() {
        return id_sertifikata;
    }

    public void setId_sertifikata(int id_sertifikata) {
        this.id_sertifikata = id_sertifikata;
    }

    public String getOblast_psihoterapije() {
        return oblast_psihoterapije;
    }

    public void setOblast_psihoterapije(String oblast_psihoterapije) {
        this.oblast_psihoterapije = oblast_psihoterapije;
    }

    public LocalDate getDatum_sertifikacije() {
        return datum_sertifikacije;
    }

    public void setDatum_sertifikacije(LocalDate datum_sertifikacije) {
        this.datum_sertifikacije = datum_sertifikacije;
    }
}