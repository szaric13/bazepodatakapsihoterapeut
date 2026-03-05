package person.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import person.model.utility.JDBCUtils;

public class MainView extends Stage {

    private final BorderPane root = new BorderPane();
    private final Button btnPregledPsihoterapeuta = new Button("Pregled psihoterapeuta");
    private final Button btnOdjava = new Button("Odjava");

    public MainView() {
        super.setTitle("Novi Početak - Savetovalište");

        MenuBar menuBar = createMenuBar();

        VBox centerContent = createCenterContent();

        HBox bottomBar = createBottomBar();

        root.setTop(menuBar);
        root.setCenter(centerContent);
        root.setBottom(bottomBar);

        setupButtons();

        super.setScene(new Scene(this.root, 800, 600));
        super.centerOnScreen();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Fajl");
        MenuItem miOdjava = new MenuItem("Odjava");
        MenuItem miIzlaz = new MenuItem("Izlaz");
        menuFile.getItems().addAll(miOdjava, miIzlaz);

        Menu menuPsihoterapeuti = new Menu("Psihoterapeuti");
        MenuItem miPregled = new MenuItem("Pregled psihoterapeuta");
        menuPsihoterapeuti.getItems().add(miPregled);

        menuBar.getMenus().addAll(menuFile, menuPsihoterapeuti);

        miOdjava.setOnAction(e -> handleOdjava());
        miIzlaz.setOnAction(e -> System.exit(0));
        miPregled.setOnAction(e -> handlePregledPsihoterapeuta());

        return menuBar;
    }

    private VBox createCenterContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(50));
        content.setAlignment(Pos.CENTER);

        Label lblDobrodosli = new Label("Dobrodošli u informacioni sistem Savetovališta 'Novi Početak'");
        lblDobrodosli.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label lblOpis = new Label("Izaberite opciju iz menija ili koristite dugmad ispod za navigaciju.");

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnPregledPsihoterapeuta);

        content.getChildren().addAll(lblDobrodosli, lblOpis, buttonBox);

        return content;
    }

    private HBox createBottomBar() {
        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(10));
        bottomBar.setAlignment(Pos.CENTER_RIGHT);

        bottomBar.getChildren().add(btnOdjava);

        return bottomBar;
    }

    private void setupButtons() {
        btnPregledPsihoterapeuta.setOnAction(e -> handlePregledPsihoterapeuta());
        btnOdjava.setOnAction(e -> handleOdjava());
    }

    private void handlePregledPsihoterapeuta() {
        PsychotherapistBrowseView psychotherapistBrowseView = new PsychotherapistBrowseView();
        psychotherapistBrowseView.show();
    }

    private void handleOdjava() {
        this.close();
        LoginView loginView = new LoginView();
        loginView.show();
    }
}