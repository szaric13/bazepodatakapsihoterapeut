package person.model.base;

import person.model.Psihoterapeut;
import person.model.OblastPsihoterapije;
import person.model.CentarZaObuku;
import person.model.PsihoterapeutInfo;
import person.model.utility.JDBCUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Server {

    public static final Server SERVER = new Server();

    private final List<PsihoterapeutInfo> psihoterapeuti = new ArrayList<>();
    private final List<Psihoterapeut> psihoterapeuti1 = new ArrayList<>();
    private final List<OblastPsihoterapije> oblasti = new ArrayList<>();
    private final List<CentarZaObuku> centri = new ArrayList<>();

    private Server() {
        this.setPsihoterapeuti(JDBCUtils.selectAllPsihoterapeutiProsireno());
        this.setOblasti(JDBCUtils.selectAllOblastiPsihoterapije());
        this.setCentri(JDBCUtils.selectAllCentri());
    }

    public List<PsihoterapeutInfo> getPsihoterapeuti() {
        return psihoterapeuti;
    }

    public void setPsihoterapeuti(Collection<PsihoterapeutInfo> psihoterapeuti) {
        this.psihoterapeuti.clear();
        this.psihoterapeuti.addAll(psihoterapeuti);
    }
    public void setPsihoterapeuti1(Collection<Psihoterapeut> psihoterapeuti1) {
        this.psihoterapeuti1.clear();
        this.psihoterapeuti1.addAll(psihoterapeuti1);
    }
    public List<OblastPsihoterapije> getOblasti() {
        return oblasti;
    }

    public void setOblasti(Collection<OblastPsihoterapije> oblasti) {
        this.oblasti.clear();
        this.oblasti.addAll(oblasti);
    }

    public List<CentarZaObuku> getCentri() {
        return centri;
    }

    public void setCentri(Collection<CentarZaObuku> centri) {
        this.centri.clear();
        this.centri.addAll(centri);
    }
}