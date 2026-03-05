package person.model;

public class CentarZaObuku {
    private int id_centra;
    private String naziv;
    private String email;
    private String telefon;
    private String ulica;
    private String broj;
    private String opstina;

    public CentarZaObuku() {
    }

    public CentarZaObuku(int id_centra, String naziv, String email, String telefon, String ulica, String broj, String opstina) {
        this.id_centra = id_centra;
        this.naziv = naziv;
        this.email = email;
        this.telefon = telefon;
        this.ulica = ulica;
        this.broj = broj;
        this.opstina = opstina;
    }

    public int getId_centra() {
        return id_centra;
    }

    public void setId_centra(int id_centra) {
        this.id_centra = id_centra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    @Override
    public String toString() {
        return naziv;
    }
}