package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import person.model.Prijava;
import person.model.Klijent;
import person.model.Seansa;
import person.model.utility.JDBCUtils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


public class SeansaTable extends TableView<Seansa> {

    public SeansaTable(List<Seansa> seanse) {
        super();

        TableColumn<Seansa, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID_seanse"));
        idColumn.setPrefWidth(50);

        TableColumn<Seansa, String> imeKlijentaColumn = new TableColumn<>("Ime klijenta");
        imeKlijentaColumn.setCellValueFactory(cellData -> {
            Seansa seansa = cellData.getValue();
            if (seansa != null) {
                Klijent klijent = JDBCUtils.selectKlijentByPrijava(seansa.getID_prijave());
                if (klijent != null && klijent.getOsoba() != null) {
                    return javafx.beans.binding.Bindings.createStringBinding(
                            () -> klijent.getOsoba().getIme());
                }
            }
            return javafx.beans.binding.Bindings.createStringBinding(() -> "N/A");
        });
        imeKlijentaColumn.setPrefWidth(120);

        TableColumn<Seansa, String> prezimeKlijentaColumn = new TableColumn<>("Prezime klijenta");
        prezimeKlijentaColumn.setCellValueFactory(cellData -> {
            Seansa seansa = cellData.getValue();
            if (seansa != null) {
                Klijent klijent = JDBCUtils.selectKlijentByPrijava(seansa.getID_prijave());
                if (klijent != null && klijent.getOsoba() != null) {
                    return javafx.beans.binding.Bindings.createStringBinding(
                            () -> klijent.getOsoba().getPrezime());
                }
            }
            return javafx.beans.binding.Bindings.createStringBinding(() -> "N/A");
        });
        prezimeKlijentaColumn.setPrefWidth(150);

        TableColumn<Seansa, Date> datumColumn = new TableColumn<>("Datum");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumColumn.setPrefWidth(120);

        TableColumn<Seansa, Time> vremeColumn = new TableColumn<>("Vreme početka");
        vremeColumn.setCellValueFactory(new PropertyValueFactory<>("vreme_pocetka"));
        vremeColumn.setPrefWidth(120);

        TableColumn<Seansa, Integer> trajanjeColumn = new TableColumn<>("Trajanje (min)");
        trajanjeColumn.setCellValueFactory(new PropertyValueFactory<>("trajanje_minuta"));
        trajanjeColumn.setPrefWidth(100);

        TableColumn<Seansa, String> prvaSeanasColumn = new TableColumn<>("Prva seansa");
        prvaSeanasColumn.setCellValueFactory(cellData -> {
            boolean jePrva = cellData.getValue().isJe_prva_seansa();
            return javafx.beans.binding.Bindings.createStringBinding(() -> jePrva ? "Da" : "Ne");
        });
        prvaSeanasColumn.setPrefWidth(80);

        TableColumn<Seansa, Integer> prijavaColumn = new TableColumn<>("ID prijave");
        prijavaColumn.setCellValueFactory(new PropertyValueFactory<>("ID_prijave"));
        prijavaColumn.setPrefWidth(80);

        this.getColumns().addAll(idColumn, imeKlijentaColumn, prezimeKlijentaColumn,
                datumColumn, vremeColumn, trajanjeColumn, prvaSeanasColumn, prijavaColumn);

        this.setItems(FXCollections.observableArrayList(seanse));

        this.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
    }

    public void updateItems(List<Seansa> seanse) {
        this.setItems(FXCollections.observableArrayList(seanse));
    }
}