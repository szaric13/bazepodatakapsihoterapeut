package person.model;

public class OblastPsihoterapije {
    private int id_oblasti_psihoterapije;
    private String naziv_oblasti;
    private String opis;

    public OblastPsihoterapije() {
    }

    public OblastPsihoterapije(int id_oblasti_psihoterapije, String naziv_oblasti, String opis) {
        this.id_oblasti_psihoterapije = id_oblasti_psihoterapije;
        this.naziv_oblasti = naziv_oblasti;
        this.opis = opis;
    }

    public int getId_oblasti_psihoterapije() {
        return id_oblasti_psihoterapije;
    }

    public void setId_oblasti_psihoterapije(int id_oblasti_psihoterapije) {
        this.id_oblasti_psihoterapije = id_oblasti_psihoterapije;
    }

    public String getNaziv_oblasti() {
        return naziv_oblasti;
    }

    public void setNaziv_oblasti(String naziv_oblasti) {
        this.naziv_oblasti = naziv_oblasti;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv_oblasti;
    }
}