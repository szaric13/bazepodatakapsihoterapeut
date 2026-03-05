package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import person.controller.SearchPsihoterapeutController;
import person.model.PsihoterapeutInfo;
import person.model.base.Server;
import person.model.utility.JDBCUtils;

import java.util.List;

public class PsychotherapistBrowseView extends Stage {

    private final BorderPane root = new BorderPane();

    private final TextField tfIme = new TextField();
    private final TextField tfPrezime = new TextField();
    private final TextField tfOblast = new TextField();
    private final Button btnPretraga = new Button("Pretraži");
    private final Button btnReset = new Button("Resetuj");

    private PsihoterapeutTable tvPsihoterapeuti;

    public PsychotherapistBrowseView() {
        super.setTitle("Pregled psihoterapeuta");

        List<PsihoterapeutInfo> psihoterapeuti = JDBCUtils.selectAllPsihoterapeutiProsireno();
        Server.SERVER.setPsihoterapeuti(psihoterapeuti);
        this.tvPsihoterapeuti = new PsihoterapeutTable(psihoterapeuti);

        GridPane filterPane = createFilterPane();

        HBox buttonBox = createButtonBox();

        root.setTop(createHeaderPane());
        root.setCenter(tvPsihoterapeuti);
        root.setBottom(buttonBox);
        root.setLeft(filterPane);

        setupEventHandlers();

        super.setScene(new Scene(root, 1200, 700));
        super.centerOnScreen();
    }

    private HBox createHeaderPane() {
        HBox headerPane = new HBox();
        headerPane.setPadding(new Insets(15));
        headerPane.setAlignment(Pos.CENTER);

        Label lblHeader = new Label("Pregled psihoterapeuta");
        lblHeader.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        headerPane.getChildren().add(lblHeader);
        return headerPane;
    }

    private GridPane createFilterPane() {
        GridPane filterPane = new GridPane();
        filterPane.setPadding(new Insets(15));
        filterPane.setHgap(10);
        filterPane.setVgap(10);
        filterPane.setPrefWidth(250);

        Label lblFilter = new Label("Filteri za pretragu");
        lblFilter.setStyle("-fx-font-weight: bold;");

        filterPane.add(lblFilter, 0, 0, 2, 1);
        filterPane.add(new Label("Ime:"), 0, 1);
        filterPane.add(tfIme, 1, 1);
        filterPane.add(new Label("Prezime:"), 0, 2);
        filterPane.add(tfPrezime, 1, 2);
        filterPane.add(new Label("Oblast:"), 0, 3);
        filterPane.add(tfOblast, 1, 3);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(btnPretraga, btnReset);
        filterPane.add(buttonBox, 0, 4, 2, 1);

        return filterPane;
    }

    private HBox createButtonBox() {
        HBox buttonBox = new HBox(15);
        buttonBox.setPadding(new Insets(15));
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        Button btnPrikaziProfil = new Button("Prikaži profil");
        btnPrikaziProfil.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        btnPrikaziProfil.setOnAction(e -> prikaziProfil());

        Button btnDetaljiSeanse = new Button("Detalji seanse");
        btnDetaljiSeanse.setOnAction(e -> prikaziDetaljeSeanse());

        Button btnZatvori = new Button("Zatvori");
        btnZatvori.setOnAction(e -> this.close());

        buttonBox.getChildren().addAll(btnPrikaziProfil, btnDetaljiSeanse, btnZatvori);
        return buttonBox;
    }

    private void setupEventHandlers() {

        btnPretraga.setOnAction(new SearchPsihoterapeutController(tfIme, tfPrezime, tfOblast, tvPsihoterapeuti));


        btnReset.setOnAction(e -> {
            tfIme.clear();
            tfPrezime.clear();
            tfOblast.clear();

            List<PsihoterapeutInfo> psihoterapeuti = JDBCUtils.selectAllPsihoterapeutiProsireno();
            Server.SERVER.setPsihoterapeuti(psihoterapeuti);
            tvPsihoterapeuti.setItems(javafx.collections.FXCollections.observableArrayList(psihoterapeuti));
        });


        tvPsihoterapeuti.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                prikaziProfil();
            }
        });
    }

    private void prikaziProfil() {
        PsihoterapeutInfo selected = tvPsihoterapeuti.getSelectionModel().getSelectedItem();
        if (selected != null) {
            PsihoterapeutProfilView profilView = new PsihoterapeutProfilView(selected);
            profilView.show();
        } else {
            showWarning("Nema selektovanog psihoterapeuta",
                    "Molimo selektujte psihoterapeuta iz tabele za prikaz profila.");
        }
    }

    private void prikaziDetaljeSeanse() {
        PsihoterapeutInfo selected = tvPsihoterapeuti.getSelectionModel().getSelectedItem();
        if (selected != null) {

            SeansaSelectionView seansaSelection = new SeansaSelectionView(selected);
            seansaSelection.show();
        } else {
            showWarning("Nema selektovanog psihoterapeuta",
                    "Molimo selektujte psihoterapeuta iz tabele za prikaz seansi.");
        }
    }

    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozorenje");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}