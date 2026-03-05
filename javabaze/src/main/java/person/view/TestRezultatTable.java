package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.TestRezultat;
import person.model.PsiholoskiTest;

import java.util.Date;
import java.util.List;


public class TestRezultatTable extends TableView<TestRezultat> {

    public TestRezultatTable(List<TestRezultat> rezultati) {
        super();

        TableColumn<TestRezultat, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_rezultata"));
        idColumn.setPrefWidth(50);

        TableColumn<TestRezultat, String> nazivTestaColumn = new TableColumn<>("Naziv testa");
        nazivTestaColumn.setCellValueFactory(cellData -> {

            return javafx.beans.binding.Bindings.createStringBinding(
                    () -> "Test #" + cellData.getValue().getID_testa());
        });
        nazivTestaColumn.setPrefWidth(180);

        TableColumn<TestRezultat, Integer> seansaColumn = new TableColumn<>("ID seanse");
        seansaColumn.setCellValueFactory(new PropertyValueFactory<>("ID_seanse"));
        seansaColumn.setPrefWidth(80);

        TableColumn<TestRezultat, Integer> klijentColumn = new TableColumn<>("ID klijenta");
        klijentColumn.setCellValueFactory(new PropertyValueFactory<>("ID_klijenta"));
        klijentColumn.setPrefWidth(80);

        TableColumn<TestRezultat, Date> datumColumn = new TableColumn<>("Datum testiranja");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum_testiranja"));
        datumColumn.setPrefWidth(120);

        TableColumn<TestRezultat, String> rezultatColumn = new TableColumn<>("Rezultat");
        rezultatColumn.setCellValueFactory(new PropertyValueFactory<>("rezultat"));
        rezultatColumn.setPrefWidth(250);

        this.getColumns().addAll(idColumn, nazivTestaColumn, seansaColumn, klijentColumn, datumColumn, rezultatColumn);

        this.setItems(FXCollections.observableArrayList(rezultati));
    }

    public void updateItems(List<TestRezultat> rezultati) {
        this.setItems(FXCollections.observableArrayList(rezultati));
    }
}