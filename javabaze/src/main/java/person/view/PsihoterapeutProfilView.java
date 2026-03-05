package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import person.model.PsihoterapeutInfo;
import person.model.Prijava;
import person.model.Seansa;
import person.model.utility.JDBCUtils;

import java.util.List;


public class PsihoterapeutProfilView extends Stage {

    private final BorderPane root = new BorderPane();
    private final PsihoterapeutInfo psihoterapeut;

    private PrijavaTable tvPrijave;
    private SeansaTable tvProsleSeanse;
    private SeansaTable tvBuduceSeanse;

    private List<Prijava> prijave;
    private List<Seansa> prosleSeanse;
    private List<Seansa> buduceSeanse;

    public PsihoterapeutProfilView(PsihoterapeutInfo psihoterapeut) {
        this.psihoterapeut = psihoterapeut;

        super.setTitle("Profil psihoterapeuta - " + psihoterapeut.getIme() + " " + psihoterapeut.getPrezime());


        loadData();


        createInterface();

        super.setScene(new Scene(root, 1200, 800));
        super.centerOnScreen();
    }

    private void loadData() {
        prijave = JDBCUtils.selectPrijaveByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
        prosleSeanse = JDBCUtils.selectPastSeanseByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
        buduceSeanse = JDBCUtils.selectFutureSeanseByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
    }

    private void createInterface() {

        VBox headerPane = createHeaderPane();


        TabPane tabPane = createTabPane();


        HBox buttonPane = createButtonPane();

        root.setTop(headerPane);
        root.setCenter(tabPane);
        root.setBottom(buttonPane);
    }

    private VBox createHeaderPane() {
        VBox headerPane = new VBox(10);
        headerPane.setPadding(new Insets(20));
        headerPane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 0 0 1 0;");

        Label lblNaslov = new Label("Profil psihoterapeuta");
        lblNaslov.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(15);
        infoGrid.setVgap(8);

        infoGrid.add(new Label("Ime i prezime:"), 0, 0);
        infoGrid.add(new Label(psihoterapeut.getIme() + " " + psihoterapeut.getPrezime()), 1, 0);

        infoGrid.add(new Label("Email:"), 0, 1);
        infoGrid.add(new Label(psihoterapeut.getEmail()), 1, 1);

        infoGrid.add(new Label("Telefon:"), 0, 2);
        infoGrid.add(new Label(psihoterapeut.getTelefon()), 1, 2);


        infoGrid.add(new Label("Oblast:"), 2, 0);


        infoGrid.add(new Label("Psiholog:"), 2, 1);
        infoGrid.add(new Label(psihoterapeut.isJe_psiholog() ? "Da" : "Ne"), 3, 1);

        infoGrid.add(new Label("Datum sertifikacije:"), 2, 2);
        infoGrid.add(new Label(psihoterapeut.getDatum_sertifikacije().toString()), 3, 2);

        headerPane.getChildren().addAll(lblNaslov, infoGrid);

        return headerPane;
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();

        Tab tabPrijave = new Tab("Prijave klijenata");
        tabPrijave.setClosable(false);
        tvPrijave = new PrijavaTable(prijave);

        VBox prijaveBox = new VBox(10);
        prijaveBox.setPadding(new Insets(10));

        Label lblPrijave = new Label("Ukupno prijava: " + prijave.size());
        lblPrijave.setStyle("-fx-font-weight: bold;");

        Button btnRefreshPrijave = new Button("Osvezi podatke");
        btnRefreshPrijave.setOnAction(e -> refreshPrijave());

        HBox prijaveHeader = new HBox(10);
        prijaveHeader.setAlignment(Pos.CENTER_LEFT);
        prijaveHeader.getChildren().addAll(lblPrijave, btnRefreshPrijave);

        prijaveBox.getChildren().addAll(prijaveHeader, tvPrijave);
        tabPrijave.setContent(prijaveBox);

        Tab tabProsleSeanse = new Tab("Prošle seanse");
        tabProsleSeanse.setClosable(false);
        tvProsleSeanse = new SeansaTable(prosleSeanse);

        VBox prosleBox = new VBox(10);
        prosleBox.setPadding(new Insets(10));

        Label lblProsle = new Label("Ukupno prošlih seansi: " + prosleSeanse.size());
        lblProsle.setStyle("-fx-font-weight: bold;");

        Button btnRefreshProsle = new Button("Osvezi podatke");
        btnRefreshProsle.setOnAction(e -> refreshProsleSeanse());

        Button btnDetaljiProsle = new Button("Prikaži detalje");
        btnDetaljiProsle.setOnAction(e -> prikaziDetaljeSeanse(tvProsleSeanse.getSelectionModel().getSelectedItem()));

        HBox prosleHeader = new HBox(10);
        prosleHeader.setAlignment(Pos.CENTER_LEFT);
        prosleHeader.getChildren().addAll(lblProsle, btnRefreshProsle, btnDetaljiProsle);

        prosleBox.getChildren().addAll(prosleHeader, tvProsleSeanse);
        tabProsleSeanse.setContent(prosleBox);

        Tab tabBuduceSeanse = new Tab("Buduće seanse");
        tabBuduceSeanse.setClosable(false);
        tvBuduceSeanse = new SeansaTable(buduceSeanse);

        VBox buduceBox = new VBox(10);
        buduceBox.setPadding(new Insets(10));

        Label lblBuduce = new Label("Ukupno budućih seansi: " + buduceSeanse.size());
        lblBuduce.setStyle("-fx-font-weight: bold;");

        Button btnRefreshBuduce = new Button("Osvezi podatke");
        btnRefreshBuduce.setOnAction(e -> refreshBuduceSeanse());

        Button btnDetaljiBuduce = new Button("Prikaži detalje");
        btnDetaljiBuduce.setOnAction(e -> prikaziDetaljeSeanse(tvBuduceSeanse.getSelectionModel().getSelectedItem()));

        HBox buduceHeader = new HBox(10);
        buduceHeader.setAlignment(Pos.CENTER_LEFT);
        buduceHeader.getChildren().addAll(lblBuduce, btnRefreshBuduce, btnDetaljiBuduce);

        buduceBox.getChildren().addAll(buduceHeader, tvBuduceSeanse);
        tabBuduceSeanse.setContent(buduceBox);

        tabPane.getTabs().addAll(tabPrijave, tabProsleSeanse, tabBuduceSeanse);

        return tabPane;
    }

    private HBox createButtonPane() {
        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(15));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        Button btnPlacanja = new Button("Pregled plaćanja");
        btnPlacanja.setOnAction(e -> otvoriPregledPlacanja());

        Button btnZatvori = new Button("Zatvori");
        btnZatvori.setOnAction(e -> this.close());

        buttonPane.getChildren().addAll(btnPlacanja, btnZatvori);

        return buttonPane;
    }

    private void refreshPrijave() {
        prijave = JDBCUtils.selectPrijaveByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
        tvPrijave.updateItems(prijave);
    }

    private void refreshProsleSeanse() {
        prosleSeanse = JDBCUtils.selectPastSeanseByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
        tvProsleSeanse.updateItems(prosleSeanse);
    }

    private void refreshBuduceSeanse() {
        buduceSeanse = JDBCUtils.selectFutureSeanseByPsihoterapeut(psihoterapeut.getId_psihoterapeuta());
        tvBuduceSeanse.updateItems(buduceSeanse);
    }

    private void prikaziDetaljeSeanse(Seansa seansa) {
        if (seansa != null) {
            SeansaDetaljiView detaljiView = new SeansaDetaljiView(seansa);
            detaljiView.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Nema selektovane seanse");
            alert.setContentText("Molimo selektujte seansu za prikaz detalja.");
            alert.showAndWait();
        }
    }


    private void otvoriPregledPlacanja() {
        PlacanjeView placanjeView = new PlacanjeView(psihoterapeut.getId_psihoterapeuta());
        placanjeView.show();
    }
}