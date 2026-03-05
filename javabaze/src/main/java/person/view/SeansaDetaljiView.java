package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import person.model.Beleska;
import person.model.*;
import person.model.utility.JDBCUtils;

import java.util.List;


public class SeansaDetaljiView extends Stage {

    private final BorderPane root = new BorderPane();
    private final Seansa seansa;

    private Klijent klijent;
    private List<TestRezultat> testRezultati;
    private List<Beleska> beleske;


    private TestRezultatTable tvTestovi;
    private BeleskaTable tvBeleske;

    public SeansaDetaljiView(Seansa seansa) {
        this.seansa = seansa;

        super.setTitle("Detalji seanse #" + seansa.getID_seanse());


        loadData();


        createInterface();

        super.setScene(new Scene(root, 1000, 700));
        super.centerOnScreen();
    }

    private void loadData() {

        klijent = JDBCUtils.selectKlijentByPrijava(seansa.getID_prijave());


        testRezultati = JDBCUtils.selectTestRezultatiBySeansa(seansa.getID_seanse());


        beleske = JDBCUtils.selectBeleskeBySeansa(seansa.getID_seanse());
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
        VBox headerPane = new VBox(15);
        headerPane.setPadding(new Insets(20));
        headerPane.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 0 0 1 0;");

        Label lblNaslov = new Label("Detalji seanse");
        lblNaslov.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        GridPane seansaGrid = new GridPane();
        seansaGrid.setHgap(20);
        seansaGrid.setVgap(10);

        Label lblSeansaInfo = new Label("Informacije o seansi:");
        lblSeansaInfo.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        seansaGrid.add(new Label("ID seanse:"), 0, 0);
        seansaGrid.add(new Label(String.valueOf(seansa.getID_seanse())), 1, 0);

        seansaGrid.add(new Label("Datum:"), 0, 1);
        seansaGrid.add(new Label(seansa.getDatum().toString()), 1, 1);

        seansaGrid.add(new Label("Vreme početka:"), 0, 2);
        seansaGrid.add(new Label(seansa.getVreme_pocetka().toString()), 1, 2);

        seansaGrid.add(new Label("Trajanje:"), 2, 0);
        seansaGrid.add(new Label(seansa.getTrajanje_minuta() + " minuta"), 3, 0);

        seansaGrid.add(new Label("Prva seansa:"), 2, 1);
        seansaGrid.add(new Label(seansa.isJe_prva_seansa() ? "Da" : "Ne"), 3, 1);


        Separator separator = new Separator();

        GridPane klijentGrid = new GridPane();
        klijentGrid.setHgap(20);
        klijentGrid.setVgap(10);

        Label lblKlijentInfo = new Label("Informacije o klijentu:");
        lblKlijentInfo.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        if (klijent != null && klijent.getOsoba() != null) {
            klijentGrid.add(new Label("Ime i prezime:"), 0, 0);
            klijentGrid.add(new Label(klijent.getOsoba().getIme() + " " + klijent.getOsoba().getPrezime()), 1, 0);

            klijentGrid.add(new Label("Email:"), 0, 1);
            klijentGrid.add(new Label(klijent.getOsoba().getEmail() != null ? klijent.getOsoba().getEmail() : "N/A"), 1, 1);

            klijentGrid.add(new Label("Telefon:"), 2, 0);
            klijentGrid.add(new Label(klijent.getOsoba().getTelefon() != null ? klijent.getOsoba().getTelefon() : "N/A"), 3, 0);

            klijentGrid.add(new Label("Ranije kod psihoterapeuta:"), 2, 1);
            klijentGrid.add(new Label(klijent.isRanije_isao_psihoterapeutu() ? "Da" : "Ne"), 3, 1);
        } else {
            klijentGrid.add(new Label("Podaci o klijentu nisu dostupni"), 0, 0);
        }

        headerPane.getChildren().addAll(lblNaslov, lblSeansaInfo, seansaGrid, separator, lblKlijentInfo, klijentGrid);

        return headerPane;
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();

        Tab tabTestovi = new Tab("Psihološki testovi");
        tabTestovi.setClosable(false);

        VBox testoviBox = new VBox(10);
        testoviBox.setPadding(new Insets(15));

        Label lblTestovi = new Label("Broj testova: " + testRezultati.size());
        lblTestovi.setStyle("-fx-font-weight: bold;");

        tvTestovi = new TestRezultatTable(testRezultati);

        Button btnRefreshTestovi = new Button("Osveži podatke");
        btnRefreshTestovi.setOnAction(e -> refreshTestovi());

        Button btnDetaljiTesta = new Button("Detalji testa");
        btnDetaljiTesta.setOnAction(e -> prikaziDetaljeTest());

        HBox testoviHeader = new HBox(10);
        testoviHeader.setAlignment(Pos.CENTER_LEFT);
        testoviHeader.getChildren().addAll(lblTestovi, btnRefreshTestovi, btnDetaljiTesta);

        testoviBox.getChildren().addAll(testoviHeader, tvTestovi);
        tabTestovi.setContent(testoviBox);

        Tab tabBeleske = new Tab("Beleške");
        tabBeleske.setClosable(false);

        VBox beleskeBox = new VBox(10);
        beleskeBox.setPadding(new Insets(15));

        Label lblBeleske = new Label("Broj beležaka: " + beleske.size());
        lblBeleske.setStyle("-fx-font-weight: bold;");

        tvBeleske = new BeleskaTable(beleske);

        Button btnRefreshBeleske = new Button("Osveži podatke");
        btnRefreshBeleske.setOnAction(e -> refreshBeleske());


        HBox beleskeHeader = new HBox(10);
        beleskeHeader.setAlignment(Pos.CENTER_LEFT);
        beleskeHeader.getChildren().addAll(lblBeleske, btnRefreshBeleske);

        beleskeBox.getChildren().addAll(beleskeHeader, tvBeleske);
        tabBeleske.setContent(beleskeBox);


        tabPane.getTabs().addAll(tabTestovi, tabBeleske);

        return tabPane;
    }

    private HBox createButtonPane() {
        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(15));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);



        Button btnZatvori = new Button("Zatvori");
        btnZatvori.setOnAction(e -> this.close());

        buttonPane.getChildren().addAll(btnZatvori);

        return buttonPane;
    }

    private void refreshTestovi() {
        testRezultati = JDBCUtils.selectTestRezultatiBySeansa(seansa.getID_seanse());
        tvTestovi.updateItems(testRezultati);
    }

    private void refreshBeleske() {
        beleske = JDBCUtils.selectBeleskeBySeansa(seansa.getID_seanse());
        tvBeleske.updateItems(beleske);
    }

    private void prikaziDetaljeTest() {
        TestRezultat selectedTest = tvTestovi.getSelectionModel().getSelectedItem();
        if (selectedTest != null) {

            PsiholoskiTest test = JDBCUtils.selectPsiholoskiTestById(selectedTest.getID_testa());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Detalji testa");
            alert.setHeaderText("Test: " + (test != null ? test.getNaziv() : "Nepoznat test"));

            String content = "ID rezultata: " + selectedTest.getID_rezultata() + "\n";
            content += "Datum testiranja: " + selectedTest.getDatum_testiranja() + "\n";
            if (test != null) {
                content += "Oblast: " + test.getOblast() + "\n";
                content += "Cena: " + test.getCena() + " RSD\n";
            }
            content += "\nRezultat:\n" + selectedTest.getRezultat();

            alert.setContentText(content);
            alert.getDialogPane().setPrefWidth(400);
            alert.showAndWait();
        } else {
            showWarning("Nema selektovanog testa", "Molimo selektujte test za prikaz detalja.");
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