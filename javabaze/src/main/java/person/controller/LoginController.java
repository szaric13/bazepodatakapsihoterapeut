package person.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import person.model.Korisnik;
import person.model.utility.JDBCUtils;
import person.view.MainView;

public class LoginController implements EventHandler<ActionEvent> {

    private final TextField tfKorisnickoIme;
    private final PasswordField pfLozinka;
    private final Stage currentStage;

    public LoginController(TextField tfKorisnickoIme, PasswordField pfLozinka, Stage currentStage) {
        this.tfKorisnickoIme = tfKorisnickoIme;
        this.pfLozinka = pfLozinka;
        this.currentStage = currentStage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String korisnickoIme = tfKorisnickoIme.getText().trim();
        String lozinka = pfLozinka.getText();


        if (korisnickoIme.isEmpty() || lozinka.isEmpty()) {
            showError("Korisničko ime i lozinka su obavezni!");
            return;
        }

        try {

            Korisnik korisnik = JDBCUtils.loginKorisnik(korisnickoIme, lozinka);

            if (korisnik != null) {

                currentStage.close();


                MainView mainView = new MainView();
                mainView.show();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Prijava uspešna");
                alert.setHeaderText(null);
                alert.setContentText("Dobrodošli, " + korisnik.getIme() + " " + korisnik.getPrezime() + "!");
                alert.showAndWait();
            } else {

                showError("Pogrešno korisničko ime ili lozinka!");
            }
        } catch (Exception e) {
            showError("Greška prilikom prijave: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greška");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}