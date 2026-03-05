package person;

import javafx.application.Application;
import javafx.stage.Stage;
import person.model.utility.JDBCUtils;
import person.view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            JDBCUtils.connect();

            LoginView loginView = new LoginView();
            loginView.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        try {
            if (JDBCUtils.connection != null && !JDBCUtils.connection.isClosed()) {
                JDBCUtils.connection.close();
                System.out.println("Konekcija sa bazom zatvorena.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}