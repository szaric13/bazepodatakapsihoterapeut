package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.Beleska;


import java.sql.Date;
import java.util.List;


public class BeleskaTable extends TableView<Beleska> {

    public BeleskaTable(List<Beleska> beleske) {
        super();

        TableColumn<Beleska, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_beleske"));
        idColumn.setPrefWidth(50);

        TableColumn<Beleska, Integer> seansaColumn = new TableColumn<>("ID seanse");
        seansaColumn.setCellValueFactory(new PropertyValueFactory<>("ID_seanse"));
        seansaColumn.setPrefWidth(80);

        TableColumn<Beleska, Date> datumColumn = new TableColumn<>("Datum unosa");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum_unosa"));
        datumColumn.setPrefWidth(120);

        TableColumn<Beleska, String> sadrzajColumn = new TableColumn<>("Sadržaj beleške");
        sadrzajColumn.setCellValueFactory(new PropertyValueFactory<>("sadrzaj"));
        sadrzajColumn.setPrefWidth(350);

        this.getColumns().addAll(idColumn, seansaColumn, datumColumn, sadrzajColumn);

        this.setItems(FXCollections.observableArrayList(beleske));
    }

    public void updateItems(List<Beleska> beleske) {
        this.setItems(FXCollections.observableArrayList(beleske));
    }
}