package person.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import person.model.Osoba;
import person.model.PsihoterapeutInfo;
import person.model.utility.JDBCUtils;
import person.view.PsihoterapeutTable;

import java.util.List;

public class SearchPsihoterapeutController implements EventHandler<ActionEvent> {

    private final TextField tfIme;
    private final TextField tfPrezime;
    private final TextField tfOblast;
    private final TableView<PsihoterapeutInfo> tvPsihoterapeuti;

    public SearchPsihoterapeutController(TextField tfIme, TextField tfPrezime, TextField tfOblast,
                                         PsihoterapeutTable tvPsihoterapeuti) {
        this.tfIme = tfIme;
        this.tfPrezime = tfPrezime;
        this.tfOblast = tfOblast;
        this.tvPsihoterapeuti = tvPsihoterapeuti;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String ime = this.tfIme.getText().trim();
        String prezime = this.tfPrezime.getText().trim();
        String oblast = this.tfOblast.getText().trim();

        List<PsihoterapeutInfo> psihoterapeuti = JDBCUtils.searchPsihoterapeutiSaOblasti(ime, prezime, oblast);
        this.tvPsihoterapeuti.setItems(FXCollections.observableArrayList(psihoterapeuti));
    }
}