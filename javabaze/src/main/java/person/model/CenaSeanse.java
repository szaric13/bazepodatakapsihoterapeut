package person.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CenaSeanse {
    private int id_cene;
    private BigDecimal iznos_po_satu;
    private LocalDate datum_promene;

    public CenaSeanse() {
    }

    public CenaSeanse(int id_cene, BigDecimal iznos_po_satu, LocalDate datum_promene) {
        this.id_cene = id_cene;
        this.iznos_po_satu = iznos_po_satu;
        this.datum_promene = datum_promene;
    }

    public int getId_cene() {
        return id_cene;
    }

    public void setId_cene(int id_cene) {
        this.id_cene = id_cene;
    }

    public BigDecimal getIznos_po_satu() {
        return iznos_po_satu;
    }

    public void setIznos_po_satu(BigDecimal iznos_po_satu) {
        this.iznos_po_satu = iznos_po_satu;
    }

    public LocalDate getDatum_promene() {
        return datum_promene;
    }

    public void setDatum_promene(LocalDate datum_promene) {
        this.datum_promene = datum_promene;
    }
}
