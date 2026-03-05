package person.view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.controller.RegisterPsihoterapeutController;

public class RegisterView extends Stage {

    private final TextField tfIme = new TextField();
    private final TextField tfPrezime = new TextField();
    private final TextField tfJmbg = new TextField();
    private final DatePicker dpDatumRodjenja = new DatePicker();
    private final TextField tfPrebivaliste = new TextField();
    private final TextField tfTelefon = new TextField();
    private final TextField tfEmail = new TextField();
    private final ComboBox<String> cbPol = new ComboBox<>();
    private final CheckBox chkPsiholog = new CheckBox("Da li je psiholog");
    private final TextField tfKorisnickoIme = new TextField();
    private final PasswordField pfLozinka = new PasswordField();
    private final PasswordField pfPotvrdaLozinke = new PasswordField();
    private final Button btnRegister = new Button("Registracija");
    private final Button btnCancel = new Button("Odustani");

    public RegisterView() {
        super.setTitle("Registracija psihoterapeuta");

        cbPol.setItems(FXCollections.observableArrayList("Muški", "Ženski"));
        cbPol.setPromptText("Izaberite pol");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        int row = 0;
        gridPane.add(new Label("Ime:"), 0, row);
        gridPane.add(tfIme, 1, row++);

        gridPane.add(new Label("Prezime:"), 0, row);
        gridPane.add(tfPrezime, 1, row++);

        gridPane.add(new Label("JMBG:"), 0, row);
        gridPane.add(tfJmbg, 1, row++);

        gridPane.add(new Label("Datum rođenja:"), 0, row);
        gridPane.add(dpDatumRodjenja, 1, row++);

        gridPane.add(new Label("Prebivalište:"), 0, row);
        gridPane.add(tfPrebivaliste, 1, row++);

        gridPane.add(new Label("Telefon:"), 0, row);
        gridPane.add(tfTelefon, 1, row++);

        gridPane.add(new Label("Email:"), 0, row);
        gridPane.add(tfEmail, 1, row++);

        gridPane.add(new Label("Pol:"), 0, row);
        gridPane.add(cbPol, 1, row++);

        gridPane.add(chkPsiholog, 1, row++);

        gridPane.add(new Label("Korisničko ime:"), 0, row);
        gridPane.add(tfKorisnickoIme, 1, row++);

        gridPane.add(new Label("Lozinka:"), 0, row);
        gridPane.add(pfLozinka, 1, row++);

        gridPane.add(new Label("Potvrda lozinke:"), 0, row);
        gridPane.add(pfPotvrdaLozinke, 1, row++);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnRegister, btnCancel);

        btnRegister.setOnAction(new RegisterPsihoterapeutController(
                tfIme, tfPrezime, tfJmbg, dpDatumRodjenja, tfPrebivaliste,
                tfTelefon, tfEmail, cbPol, chkPsiholog, tfKorisnickoIme,
                pfLozinka, pfPotvrdaLozinke, this));

        btnCancel.setOnAction(e -> {
            this.close();
            Stage loginStage = new LoginView();
            loginStage.show();
        });

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("Registracija novog psihoterapeuta");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        vbox.getChildren().addAll(titleLabel, gridPane, buttonBox);

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 500, 600);
        super.setScene(scene);
        super.centerOnScreen();
    }
}