package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import person.model.PsihoterapeutInfo;
import person.model.Seansa;
import person.model.utility.JDBCUtils;

import java.util.List;


public class SeansaSelectionView extends Stage {

    private final BorderPane root = new BorderPane();
    private final PsihoterapeutInfo psihoterapeut;

    private SeansaTable tvSeanse;
    private List<Seansa> sveSeanse;

    public SeansaSelectionView(PsihoterapeutInfo psihoterapeut) {
        this.psihoterapeut = psihoterapeut;

        super.setTitle("Odabir seanse - " + psihoterapeut.getIme() + " " + psihoterapeut.getPrezime());
        super.initModality(Modality.APPLICATION_MODAL);

        loadData();

        createInterface();


        super.setScene(new Scene(root, 900, 600));
        super.centerOnScreen();
    }

    private void loadData() {

        List<Seansa> prosleSeanse = JDBCUtils.selectPastSeanseByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
        List<Seansa> buduceSeanse = JDBCUtils.selectFutureSeanseByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());

        sveSeanse = new java.util.ArrayList<>();
        sveSeanse.addAll(prosleSeanse);
        sveSeanse.addAll(buduceSeanse);


        sveSeanse.sort((s1, s2) -> s1.getDatum().compareTo(s2.getDatum()));
    }

    private void createInterface() {

        VBox headerPane = createHeaderPane();

        tvSeanse = new SeansaTable(sveSeanse);

        HBox buttonPane = createButtonPane();

        root.setTop(headerPane);
        root.setCenter(tvSeanse);
        root.setBottom(buttonPane);
    }

    private VBox createHeaderPane() {
        VBox headerPane = new VBox(10);
        headerPane.setPadding(new Insets(20));
        headerPane.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 0 0 1 0;");

        Label lblNaslov = new Label("Odabir seanse za prikaz detalja");
        lblNaslov.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label lblPsihoterapeut = new Label("Psihoterapeut: " + psihoterapeut.getIme() + " " + psihoterapeut.getPrezime());
        lblPsihoterapeut.setStyle("-fx-font-size: 14;");

        Label lblInfo = new Label("Ukupno seansi: " + sveSeanse.size());
        lblInfo.setStyle("-fx-font-size: 12; -fx-text-fill: #666;");

        Label lblInstrukcije = new Label("Dvaput kliknite na seansu ili koristite dugme 'Prikaži detalje' za prikaz detaljnih informacija.");
        lblInstrukcije.setStyle("-fx-font-size: 11; -fx-text-fill: #666; -fx-font-style: italic;");

        headerPane.getChildren().addAll(lblNaslov, lblPsihoterapeut, lblInfo, lblInstrukcije);

        return headerPane;
    }

    private HBox createButtonPane() {
        HBox buttonPane = new HBox(15);
        buttonPane.setPadding(new Insets(15));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        Button btnPrikaziDetalje = new Button("Prikaži detalje");
        btnPrikaziDetalje.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        btnPrikaziDetalje.setOnAction(e -> prikaziDetaljeSeanse());

        Button btnOsvezi = new Button("Osveži podatke");
        btnOsvezi.setOnAction(e -> osvezPodatke());

        Button btnZatvori = new Button("Zatvori");
        btnZatvori.setOnAction(e -> this.close());

        buttonPane.getChildren().addAll(btnPrikaziDetalje, btnOsvezi, btnZatvori);

        tvSeanse.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                prikaziDetaljeSeanse();
            }
        });

        return buttonPane;
    }

    private void prikaziDetaljeSeanse() {
        Seansa selectedSeansa = tvSeanse.getSelectionModel().getSelectedItem();
        if (selectedSeansa != null) {
            SeansaDetaljiView detaljiView = new SeansaDetaljiView(selectedSeansa);
            detaljiView.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Nema selektovane seanse");
            alert.setContentText("Molimo selektujte seansu za prikaz detalja.");
            alert.showAndWait();
        }
    }

    private void osvezPodatke() {
        loadData();
        tvSeanse.updateItems(sveSeanse);
    }
}