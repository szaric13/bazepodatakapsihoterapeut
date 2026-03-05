package person.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import person.model.Psihoterapeut;
import person.model.PsihoterapeutInfo;
import person.model.base.Server;
import person.model.utility.JDBCUtils;
import person.view.LoginView;
import person.view.MainView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RegisterPsihoterapeutController implements EventHandler<ActionEvent> {

    private final TextField tfIme;
    private final TextField tfPrezime;
    private final TextField tfJmbg;
    private final DatePicker dpDatumRodjenja;
    private final TextField tfPrebivaliste;
    private final TextField tfTelefon;
    private final TextField tfEmail;
    private final ComboBox<String> cbPol;
    private final CheckBox chkPsiholog;
    private final TextField tfKorisnickoIme;
    private final PasswordField pfLozinka;
    private final PasswordField pfPotvrdaLozinke;
    private final Stage currentStage;

    public RegisterPsihoterapeutController(TextField tfIme, TextField tfPrezime, TextField tfJmbg,
                                           DatePicker dpDatumRodjenja, TextField tfPrebivaliste,
                                           TextField tfTelefon, TextField tfEmail, ComboBox<String> cbPol,
                                           CheckBox chkPsiholog, TextField tfKorisnickoIme,
                                           PasswordField pfLozinka, PasswordField pfPotvrdaLozinke,
                                           Stage currentStage) {
        this.tfIme = tfIme;
        this.tfPrezime = tfPrezime;
        this.tfJmbg = tfJmbg;
        this.dpDatumRodjenja = dpDatumRodjenja;
        this.tfPrebivaliste = tfPrebivaliste;
        this.tfTelefon = tfTelefon;
        this.tfEmail = tfEmail;
        this.cbPol = cbPol;
        this.chkPsiholog = chkPsiholog;
        this.tfKorisnickoIme = tfKorisnickoIme;
        this.pfLozinka = pfLozinka;
        this.pfPotvrdaLozinke = pfPotvrdaLozinke;
        this.currentStage = currentStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        if (!validateForm()) {
            return;
        }

        String ime = tfIme.getText().trim();
        String prezime = tfPrezime.getText().trim();
        String jmbg = tfJmbg.getText().trim();
        LocalDate datumRodjenja = dpDatumRodjenja.getValue();
        String prebivaliste = tfPrebivaliste.getText().trim();
        String telefon = tfTelefon.getText().trim();
        String email = tfEmail.getText().trim();
        char pol = cbPol.getValue().charAt(0);
        boolean jePsiholog = chkPsiholog.isSelected();
        String korisnickoIme = tfKorisnickoIme.getText().trim();
        String lozinka = pfLozinka.getText();

        try {

            JDBCUtils.registerPsihoterapeut(ime, prezime, jmbg, datumRodjenja, prebivaliste,
                    telefon, email, pol, jePsiholog, korisnickoIme, lozinka);


            List<PsihoterapeutInfo> psihoterapeuti = JDBCUtils.selectAllPsihoterapeutiProsireno();
            Server.SERVER.setPsihoterapeuti(psihoterapeuti);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registracija uspešna");
            alert.setHeaderText(null);
            alert.setContentText("Uspešno ste registrovani. Možete se prijaviti sa Vašim korisničkim imenom i lozinkom.");
            alert.showAndWait();


            currentStage.close();
            Stage loginStage = new LoginView();
            loginStage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText(null);
            alert.setContentText("Došlo je do greške prilikom registracije: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private boolean validateForm() {

        if (tfIme.getText().trim().isEmpty() ||
                tfPrezime.getText().trim().isEmpty() ||
                tfJmbg.getText().trim().isEmpty() ||
                dpDatumRodjenja.getValue() == null ||
                tfPrebivaliste.getText().trim().isEmpty() ||
                tfTelefon.getText().trim().isEmpty() ||
                tfEmail.getText().trim().isEmpty() ||
                cbPol.getValue() == null ||
                tfKorisnickoIme.getText().trim().isEmpty() ||
                pfLozinka.getText().isEmpty() ||
                pfPotvrdaLozinke.getText().isEmpty()) {

            showError("Sva polja su obavezna.");
            return false;
        }


        String jmbg = tfJmbg.getText().trim();
        if (jmbg.length() != 13 || !jmbg.matches("\\d+")) {
            showError("JMBG mora imati tačno 13 cifara.");
            return false;
        }


        String email = tfEmail.getText().trim();
        if (!email.contains("@") || !email.contains(".")) {
            showError("Unesite validnu email adresu.");
            return false;
        }


        String lozinka = pfLozinka.getText();
        String potvrdaLozinke = pfPotvrdaLozinke.getText();
        if (!lozinka.equals(potvrdaLozinke)) {
            showError("Lozinke se ne poklapaju.");
            return false;
        }


        if (lozinka.length() < 6) {
            showError("Lozinka mora imati najmanje 6 karaktera.");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greška");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}