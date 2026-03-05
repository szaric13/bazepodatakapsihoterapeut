package person.model;

import java.time.LocalDate;

public class Prijava {
    private int id_prijave;
    private int id_klijenta;
    private int id_psihoterapeuta;
    private LocalDate datum_prijave;
    private String problem_opis;
    private String komunikacioni_kanal;

    private Klijent klijent;
    private Psihoterapeut psihoterapeut;

    public Prijava() {
    }

    public Prijava(int id_prijave, int id_klijenta, int id_psihoterapeuta, LocalDate datum_prijave,
                   String problem_opis, String komunikacioni_kanal) {
        this.id_prijave = id_prijave;
        this.id_klijenta = id_klijenta;
        this.id_psihoterapeuta = id_psihoterapeuta;
        this.datum_prijave = datum_prijave;
        this.problem_opis = problem_opis;
        this.komunikacioni_kanal = komunikacioni_kanal;
    }

    public int getId_prijave() {
        return id_prijave;
    }

    public void setId_prijave(int id_prijave) {
        this.id_prijave = id_prijave;
    }

    public int getId_klijenta() {
        return id_klijenta;
    }

    public void setId_klijenta(int id_klijenta) {
        this.id_klijenta = id_klijenta;
    }

    public int getId_psihoterapeuta() {
        return id_psihoterapeuta;
    }

    public void setId_psihoterapeuta(int id_psihoterapeuta) {
        this.id_psihoterapeuta = id_psihoterapeuta;
    }

    public LocalDate getDatum_prijave() {
        return datum_prijave;
    }

    public void setDatum_prijave(LocalDate datum_prijave) {
        this.datum_prijave = datum_prijave;
    }

    public String getProblem_opis() {
        return problem_opis;
    }

    public void setProblem_opis(String problem_opis) {
        this.problem_opis = problem_opis;
    }

    public String getKomunikacioni_kanal() {
        return komunikacioni_kanal;
    }

    public void setKomunikacioni_kanal(String komunikacioni_kanal) {
        this.komunikacioni_kanal = komunikacioni_kanal;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Psihoterapeut getPsihoterapeut() {
        return psihoterapeut;
    }

    public void setPsihoterapeut(Psihoterapeut psihoterapeut) {
        this.psihoterapeut = psihoterapeut;
    }
}