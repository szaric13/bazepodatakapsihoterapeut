package person.model;

import java.math.BigDecimal;

public class PsiholoskiTest {
    private int ID_testa;
    private String naziv;
    private String oblast;
    private BigDecimal cena;

    public PsiholoskiTest(int ID_testa, String naziv, String oblast, BigDecimal cena) {
        this.ID_testa = ID_testa;
        this.naziv = naziv;
        this.oblast = oblast;
        this.cena = cena;
    }

    public PsiholoskiTest(String naziv, String oblast, BigDecimal cena) {
        this.naziv = naziv;
        this.oblast = oblast;
        this.cena = cena;
    }

    public PsiholoskiTest() {
    }


    public int getID_testa() {
        return ID_testa;
    }

    public void setID_testa(int ID_testa) {
        this.ID_testa = ID_testa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Psiholoski_test{" +
                "ID_testa=" + ID_testa +
                ", naziv='" + naziv + '\'' +
                ", oblast='" + oblast + '\'' +
                ", cena=" + cena +
                '}';
    }
}
