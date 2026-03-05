package person.model;


public class Psihoterapeut {
    private int id_psihoterapeuta;
    private int id_osobe;
    private boolean je_psiholog;
    private int id_sertifikata;

    public Psihoterapeut() {
    }

    public Psihoterapeut(int id_psihoterapeuta, int id_osobe, boolean je_psiholog, int id_sertifikata) {
        this.id_psihoterapeuta = id_psihoterapeuta;
        this.id_osobe = id_osobe;
        this.je_psiholog = je_psiholog;
        this.id_sertifikata = id_sertifikata;
    }

    public int getId_psihoterapeuta() {
        return id_psihoterapeuta;
    }

    public void setId_psihoterapeuta(int id_psihoterapeuta) {
        this.id_psihoterapeuta = id_psihoterapeuta;
    }

    public int getId_osobe() {
        return id_osobe;
    }

    public void setId_osobe(int id_osobe) {
        this.id_osobe = id_osobe;
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
}