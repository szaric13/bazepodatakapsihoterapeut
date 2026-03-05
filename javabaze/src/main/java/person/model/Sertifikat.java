package person.model;

import java.time.LocalDate;

public class Sertifikat {
    private int id_sertifikata;
    private int id_osobe;
    private int id_oblasti_psihoterapije;
    private LocalDate datum_sertifikacije;
    private int id_centra;

    private String naziv_oblasti;
    private String naziv_centra;

    public Sertifikat() {
    }

    public Sertifikat(int id_sertifikata, int id_osobe, int id_oblasti_psihoterapije,
                      LocalDate datum_sertifikacije, int id_centra, String naziv_oblasti, String naziv_centra) {
        this.id_sertifikata = id_sertifikata;
        this.id_osobe = id_osobe;
        this.id_oblasti_psihoterapije = id_oblasti_psihoterapije;
        this.datum_sertifikacije = datum_sertifikacije;
        this.id_centra = id_centra;
        this.naziv_oblasti = naziv_oblasti;
        this.naziv_centra = naziv_centra;
    }

    public int getId_sertifikata() {
        return id_sertifikata;
    }

    public void setId_sertifikata(int id_sertifikata) {
        this.id_sertifikata = id_sertifikata;
    }

    public int getId_osobe() {
        return id_osobe;
    }

    public void setId_osobe(int id_osobe) {
        this.id_osobe = id_osobe;
    }

    public int getId_oblasti_psihoterapije() {
        return id_oblasti_psihoterapije;
    }

    public void setId_oblasti_psihoterapije(int id_oblasti_psihoterapije) {
        this.id_oblasti_psihoterapije = id_oblasti_psihoterapije;
    }

    public LocalDate getDatum_sertifikacije() {
        return datum_sertifikacije;
    }

    public void setDatum_sertifikacije(LocalDate datum_sertifikacije) {
        this.datum_sertifikacije = datum_sertifikacije;
    }

    public int getId_centra() {
        return id_centra;
    }

    public void setId_centra(int id_centra) {
        this.id_centra = id_centra;
    }

    public String getNaziv_oblasti() {
        return naziv_oblasti;
    }

    public void setNaziv_oblasti(String naziv_oblasti) {
        this.naziv_oblasti = naziv_oblasti;
    }

    public String getNaziv_centra() {
        return naziv_centra;
    }

    public void setNaziv_centra(String naziv_centra) {
        this.naziv_centra = naziv_centra;
    }
}