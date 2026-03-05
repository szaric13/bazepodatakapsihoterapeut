package person.model;

import java.sql.Date;
import java.sql.Time;


public class Seansa {
    private int ID_seanse;
    private int ID_prijave;
    private Date datum;
    private Time vreme_pocetka;
    private int trajanje_minuta;
    private boolean je_prva_seansa;
    private int ID_cene;

    public Seansa(int ID_seanse, int ID_prijave, Date datum, Time vreme_pocetka,
                  int trajanje_minuta, boolean je_prva_seansa, int ID_cene) {
        this.ID_seanse = ID_seanse;
        this.ID_prijave = ID_prijave;
        this.datum = datum;
        this.vreme_pocetka = vreme_pocetka;
        this.trajanje_minuta = trajanje_minuta;
        this.je_prva_seansa = je_prva_seansa;
        this.ID_cene = ID_cene;
    }

    public Seansa(int ID_prijave, Date datum, Time vreme_pocetka,
                  int trajanje_minuta, boolean je_prva_seansa, int ID_cene) {
        this.ID_prijave = ID_prijave;
        this.datum = datum;
        this.vreme_pocetka = vreme_pocetka;
        this.trajanje_minuta = trajanje_minuta;
        this.je_prva_seansa = je_prva_seansa;
        this.ID_cene = ID_cene;
    }


    public Seansa() {
    }


    public int getID_seanse() {
        return ID_seanse;
    }

    public void setID_seanse(int ID_seanse) {
        this.ID_seanse = ID_seanse;
    }

    public int getID_prijave() {
        return ID_prijave;
    }

    public void setID_prijave(int ID_prijave) {
        this.ID_prijave = ID_prijave;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme_pocetka() {
        return vreme_pocetka;
    }

    public void setVreme_pocetka(Time vreme_pocetka) {
        this.vreme_pocetka = vreme_pocetka;
    }

    public int getTrajanje_minuta() {
        return trajanje_minuta;
    }

    public void setTrajanje_minuta(int trajanje_minuta) {
        this.trajanje_minuta = trajanje_minuta;
    }

    public boolean isJe_prva_seansa() {
        return je_prva_seansa;
    }

    public void setJe_prva_seansa(boolean je_prva_seansa) {
        this.je_prva_seansa = je_prva_seansa;
    }

    public int getID_cene() {
        return ID_cene;
    }

    public void setID_cene(int ID_cene) {
        this.ID_cene = ID_cene;
    }

    @Override
    public String toString() {
        return "Seansa{" +
                "ID_seanse=" + ID_seanse +
                ", datum=" + datum +
                ", vreme_pocetka=" + vreme_pocetka +
                ", trajanje_minuta=" + trajanje_minuta +
                ", je_prva_seansa=" + je_prva_seansa +
                '}';
    }
}