package person.model;

import java.time.LocalDate;

public class Osoba {
    private int id_osobe;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datum_rodjenja;
    private String prebivaliste;
    private String telefon;
    private String email;
    private char pol;

    public Osoba() {
    }

    public Osoba(int id_osobe, String ime, String prezime, String jmbg, LocalDate datum_rodjenja,
                 String prebivaliste, String telefon, String email, char pol) {
        this.id_osobe = id_osobe;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datum_rodjenja = datum_rodjenja;
        this.prebivaliste = prebivaliste;
        this.telefon = telefon;
        this.email = email;
        this.pol = pol;
    }

    public int getId_osobe() {
        return id_osobe;
    }

    public void setId_osobe(int id_osobe) {
        this.id_osobe = id_osobe;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(LocalDate datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getPrebivaliste() {
        return prebivaliste;
    }

    public void setPrebivaliste(String prebivaliste) {
        this.prebivaliste = prebivaliste;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getPol() {
        return pol;
    }

    public void setPol(char pol) {
        this.pol = pol;
    }
}