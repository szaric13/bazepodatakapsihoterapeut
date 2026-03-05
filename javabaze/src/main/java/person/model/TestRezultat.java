package person.model;

import java.util.Date;

public class TestRezultat {
    private int ID_rezultata;
    private int ID_testa;
    private int ID_seanse;
    private int ID_klijenta;
    private int ID_psihoterapeuta;
    private String rezultat;
    private Date datum_testiranja;

    public TestRezultat(int ID_rezultata, int ID_testa, int ID_seanse, int ID_klijenta,
                         int ID_psihoterapeuta, String rezultat, Date datum_testiranja) {
        this.ID_rezultata = ID_rezultata;
        this.ID_testa = ID_testa;
        this.ID_seanse = ID_seanse;
        this.ID_klijenta = ID_klijenta;
        this.ID_psihoterapeuta = ID_psihoterapeuta;
        this.rezultat = rezultat;
        this.datum_testiranja = datum_testiranja;
    }

    public TestRezultat(int ID_testa, int ID_seanse, int ID_klijenta,
                         int ID_psihoterapeuta, String rezultat, Date datum_testiranja) {
        this.ID_testa = ID_testa;
        this.ID_seanse = ID_seanse;
        this.ID_klijenta = ID_klijenta;
        this.ID_psihoterapeuta = ID_psihoterapeuta;
        this.rezultat = rezultat;
        this.datum_testiranja = datum_testiranja;
    }

    public TestRezultat() {
    }


    public int getID_rezultata() {
        return ID_rezultata;
    }

    public void setID_rezultata(int ID_rezultata) {
        this.ID_rezultata = ID_rezultata;
    }

    public int getID_testa() {
        return ID_testa;
    }

    public void setID_testa(int ID_testa) {
        this.ID_testa = ID_testa;
    }

    public int getID_seanse() {
        return ID_seanse;
    }

    public void setID_seanse(int ID_seanse) {
        this.ID_seanse = ID_seanse;
    }

    public int getID_klijenta() {
        return ID_klijenta;
    }

    public void setID_klijenta(int ID_klijenta) {
        this.ID_klijenta = ID_klijenta;
    }

    public int getID_psihoterapeuta() {
        return ID_psihoterapeuta;
    }

    public void setID_psihoterapeuta(int ID_psihoterapeuta) {
        this.ID_psihoterapeuta = ID_psihoterapeuta;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public Date getDatum_testiranja() {
        return datum_testiranja;
    }

    public void setDatum_testiranja(Date datum_testiranja) {
        this.datum_testiranja = datum_testiranja;
    }

    @Override
    public String toString() {
        return "Test_rezultat{" +
                "ID_rezultata=" + ID_rezultata +
                ", ID_testa=" + ID_testa +
                ", ID_seanse=" + ID_seanse +
                ", datum_testiranja=" + datum_testiranja +
                ", rezultat='" + rezultat + '\'' +
                '}';
    }
}
