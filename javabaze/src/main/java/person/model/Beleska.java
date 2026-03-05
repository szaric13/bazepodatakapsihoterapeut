package person.model;

import java.sql.Date;


public class Beleska {
    private int ID_beleske;
    private int ID_seanse;
    private String sadrzaj;
    private Date datum_unosa;


    public Beleska(int ID_beleske, int ID_seanse, String sadrzaj, Date datum_unosa) {
        this.ID_beleske = ID_beleske;
        this.ID_seanse = ID_seanse;
        this.sadrzaj = sadrzaj;
        this.datum_unosa = datum_unosa;
    }

    public Beleska(int ID_seanse, String sadrzaj, Date datum_unosa) {
        this.ID_seanse = ID_seanse;
        this.sadrzaj = sadrzaj;
        this.datum_unosa = datum_unosa;
    }

    public Beleska() {
    }


    @Override
    public String toString() {
        return "Beleska{" +
                "ID_beleske=" + ID_beleske +
                ", ID_seanse=" + ID_seanse +
                ", datum_unosa=" + datum_unosa +
                ", sadrzaj='" + (sadrzaj != null && sadrzaj.length() > 20
                ? sadrzaj.substring(0, 20) + "..."
                : sadrzaj) + '\'' +
                '}';
    }

    public int getID_beleske() {
        return ID_beleske;
    }

    public void setID_beleske(int ID_beleske) {
        this.ID_beleske = ID_beleske;
    }

    public int getID_seanse() {
        return ID_seanse;
    }

    public void setID_seanse(int ID_seanse) {
        this.ID_seanse = ID_seanse;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Date getDatum_unosa() {
        return datum_unosa;
    }

    public void setDatum_unosa(Date datum_unosa) {
        this.datum_unosa = datum_unosa;
    }
}