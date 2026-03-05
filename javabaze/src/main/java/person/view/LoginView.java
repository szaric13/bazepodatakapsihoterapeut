package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.controller.LoginController;
import person.model.utility.JDBCUtils;

public class LoginView extends Stage {

    private final TextField tfKorisnickoIme = new TextField();
    private final PasswordField pfLozinka = new PasswordField();
    private final Button btnLogin = new Button("Prijava");
    private final Button btnRegister = new Button("Registracija");

    public LoginView() {
        super.setTitle("Prijava na sistem");

        JDBCUtils.connect();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(new Label("Korisničko ime:"), 0, 0);
        gridPane.add(tfKorisnickoIme, 1, 0);
        gridPane.add(new Label("Lozinka:"), 0, 1);
        gridPane.add(pfLozinka, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnLogin, btnRegister);

        btnLogin.setOnAction(new LoginController(tfKorisnickoIme, pfLozinka, this));
        btnRegister.setOnAction(e -> {
            this.close();
            RegisterView registerView = new RegisterView();
            registerView.show();
        });

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Prijava psihoterapeuta");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        vbox.getChildren().addAll(titleLabel, gridPane, buttonBox);

        Scene scene = new Scene(vbox, 400, 300);
        super.setScene(scene);
        super.setResizable(false);
        super.centerOnScreen();
    }
}