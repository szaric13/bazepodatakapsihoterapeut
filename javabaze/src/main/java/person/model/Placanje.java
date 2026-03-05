package person.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Placanje {
    private int idPlacanja;
    private int idKlijenta;
    private Integer idSeanse;
    private Integer idRezultata;
    private String svrha;
    private int idValute;
    private BigDecimal iznos;
    private String nacinPlacanja;
    private int brojRate;
    private BigDecimal procenatPrveRate;
    private LocalDate rokDrugeRate;
    private BigDecimal provizija;
    private LocalDate datumPlacanja;

    private String imeKlijenta;
    private String prezimeKlijenta;
    private String skraceniNazivValute;
    private String puniNazivValute;
    private LocalDate datumSeanse;
    private String nazivTesta;

    public Placanje() {}

    public Placanje(int idPlacanja, int idKlijenta, Integer idSeanse, Integer idRezultata,
                    String svrha, int idValute, BigDecimal iznos, String nacinPlacanja,
                    int brojRate, BigDecimal procenatPrveRate, LocalDate rokDrugeRate,
                    BigDecimal provizija, LocalDate datumPlacanja) {
        this.idPlacanja = idPlacanja;
        this.idKlijenta = idKlijenta;
        this.idSeanse = idSeanse;
        this.idRezultata = idRezultata;
        this.svrha = svrha;
        this.idValute = idValute;
        this.iznos = iznos;
        this.nacinPlacanja = nacinPlacanja;
        this.brojRate = brojRate;
        this.procenatPrveRate = procenatPrveRate;
        this.rokDrugeRate = rokDrugeRate;
        this.provizija = provizija;
        this.datumPlacanja = datumPlacanja;
    }

    public int getIdPlacanja() { return idPlacanja; }
    public void setIdPlacanja(int idPlacanja) { this.idPlacanja = idPlacanja; }

    public int getIdKlijenta() { return idKlijenta; }
    public void setIdKlijenta(int idKlijenta) { this.idKlijenta = idKlijenta; }

    public Integer getIdSeanse() { return idSeanse; }
    public void setIdSeanse(Integer idSeanse) { this.idSeanse = idSeanse; }

    public Integer getIdRezultata() { return idRezultata; }
    public void setIdRezultata(Integer idRezultata) { this.idRezultata = idRezultata; }

    public String getSvrha() { return svrha; }
    public void setSvrha(String svrha) { this.svrha = svrha; }

    public int getIdValute() { return idValute; }
    public void setIdValute(int idValute) { this.idValute = idValute; }

    public BigDecimal getIznos() { return iznos; }
    public void setIznos(BigDecimal iznos) { this.iznos = iznos; }

    public String getNacinPlacanja() { return nacinPlacanja; }
    public void setNacinPlacanja(String nacinPlacanja) { this.nacinPlacanja = nacinPlacanja; }

    public int getBrojRate() { return brojRate; }
    public void setBrojRate(int brojRate) { this.brojRate = brojRate; }

    public BigDecimal getProcenatPrveRate() { return procenatPrveRate; }
    public void setProcenatPrveRate(BigDecimal procenatPrveRate) { this.procenatPrveRate = procenatPrveRate; }

    public LocalDate getRokDrugeRate() { return rokDrugeRate; }
    public void setRokDrugeRate(LocalDate rokDrugeRate) { this.rokDrugeRate = rokDrugeRate; }

    public BigDecimal getProvizija() { return provizija; }
    public void setProvizija(BigDecimal provizija) { this.provizija = provizija; }

    public LocalDate getDatumPlacanja() { return datumPlacanja; }
    public void setDatumPlacanja(LocalDate datumPlacanja) { this.datumPlacanja = datumPlacanja; }

    public String getImeKlijenta() { return imeKlijenta; }
    public void setImeKlijenta(String imeKlijenta) { this.imeKlijenta = imeKlijenta; }

    public String getPrezimeKlijenta() { return prezimeKlijenta; }
    public void setPrezimeKlijenta(String prezimeKlijenta) { this.prezimeKlijenta = prezimeKlijenta; }

    public String getSkraceniNazivValute() { return skraceniNazivValute; }
    public void setSkraceniNazivValute(String skraceniNazivValute) { this.skraceniNazivValute = skraceniNazivValute; }

    public String getPuniNazivValute() { return puniNazivValute; }
    public void setPuniNazivValute(String puniNazivValute) { this.puniNazivValute = puniNazivValute; }

    public LocalDate getDatumSeanse() { return datumSeanse; }
    public void setDatumSeanse(LocalDate datumSeanse) { this.datumSeanse = datumSeanse; }

    public String getNazivTesta() { return nazivTesta; }
    public void setNazivTesta(String nazivTesta) { this.nazivTesta = nazivTesta; }

    public String getImePrezimeKlijenta() {
        return imeKlijenta + " " + prezimeKlijenta;
    }
}