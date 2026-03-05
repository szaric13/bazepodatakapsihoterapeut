package person.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.Prijava;

import java.time.LocalDate;
import java.util.List;


public class PrijavaTable extends TableView<Prijava> {

    public PrijavaTable(List<Prijava> prijave) {
        super();

        TableColumn<Prijava, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_prijave"));
        idColumn.setPrefWidth(50);

        TableColumn<Prijava, String> imeKlijentaColumn = new TableColumn<>("Ime klijenta");
        imeKlijentaColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getKlijent() != null && cellData.getValue().getKlijent().getOsoba() != null) {
                return javafx.beans.binding.Bindings.createStringBinding(
                        () -> cellData.getValue().getKlijent().getOsoba().getIme());
            }
            return javafx.beans.binding.Bindings.createStringBinding(() -> "");
        });
        imeKlijentaColumn.setPrefWidth(120);

        TableColumn<Prijava, String> prezimeKlijentaColumn = new TableColumn<>("Prezime klijenta");
        prezimeKlijentaColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getKlijent() != null && cellData.getValue().getKlijent().getOsoba() != null) {
                return javafx.beans.binding.Bindings.createStringBinding(
                        () -> cellData.getValue().getKlijent().getOsoba().getPrezime());
            }
            return javafx.beans.binding.Bindings.createStringBinding(() -> "");
        });
        prezimeKlijentaColumn.setPrefWidth(150);

        TableColumn<Prijava, LocalDate> datumColumn = new TableColumn<>("Datum prijave");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum_prijave"));
        datumColumn.setPrefWidth(120);

        TableColumn<Prijava, String> kanalColumn = new TableColumn<>("Način kontakta");
        kanalColumn.setCellValueFactory(new PropertyValueFactory<>("komunikacioni_kanal"));
        kanalColumn.setPrefWidth(120);

        TableColumn<Prijava, String> opisColumn = new TableColumn<>("Opis problema");
        opisColumn.setCellValueFactory(new PropertyValueFactory<>("problem_opis"));
        opisColumn.setPrefWidth(250);

        this.getColumns().addAll(idColumn, imeKlijentaColumn, prezimeKlijentaColumn, datumColumn, kanalColumn, opisColumn);

        this.setItems(FXCollections.observableArrayList(prijave));
    }

    public void updateItems(List<Prijava> prijave) {
        this.setItems(FXCollections.observableArrayList(prijave));
    }}

