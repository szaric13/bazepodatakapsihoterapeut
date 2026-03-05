package person.model;

public class Klijent {
    private int id_klijenta;
    private int id_osobe;
    private boolean ranije_isao_psihoterapeutu;

    private Osoba osoba;

    public Klijent() {
    }

    public Klijent(int id_klijenta, int id_osobe, boolean ranije_isao_psihoterapeutu) {
        this.id_klijenta = id_klijenta;
        this.id_osobe = id_osobe;
        this.ranije_isao_psihoterapeutu = ranije_isao_psihoterapeutu;
    }

    public int getId_klijenta() {
        return id_klijenta;
    }

    public void setId_klijenta(int id_klijenta) {
        this.id_klijenta = id_klijenta;
    }

    public int getId_osobe() {
        return id_osobe;
    }

    public void setId_osobe(int id_osobe) {
        this.id_osobe = id_osobe;
    }

    public boolean isRanije_isao_psihoterapeutu() {
        return ranije_isao_psihoterapeutu;
    }

    public void setRanije_isao_psihoterapeutu(boolean ranije_isao_psihoterapeutu) {
        this.ranije_isao_psihoterapeutu = ranije_isao_psihoterapeutu;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }
}