package person.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import person.model.PsihoterapeutInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PsihoterapeutTable extends TableView<PsihoterapeutInfo> {

    public PsihoterapeutTable(List<PsihoterapeutInfo> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<PsihoterapeutInfo, Integer> tcId = new TableColumn<>("ID");
        TableColumn<PsihoterapeutInfo, String> tcIme = new TableColumn<>("Ime");
        TableColumn<PsihoterapeutInfo, String> tcPrezime = new TableColumn<>("Prezime");
        TableColumn<PsihoterapeutInfo, String> tcEmail = new TableColumn<>("Email");
        TableColumn<PsihoterapeutInfo, String> tcTelefon = new TableColumn<>("Telefon");
        TableColumn<PsihoterapeutInfo, String> tcJmbg = new TableColumn<>("JMBG");
        TableColumn<PsihoterapeutInfo, String> tcOblast = new TableColumn<>("Oblast psihoterapije");
        TableColumn<PsihoterapeutInfo, Boolean> tcPsiholog = new TableColumn<>("Psiholog");
        TableColumn<PsihoterapeutInfo, LocalDate> tcDatumSertifikacije = new TableColumn<>("Datum sertifikacije");

        tcId.setCellValueFactory(new PropertyValueFactory<>("id_psihoterapeuta"));
        tcIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        tcPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        tcJmbg.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
        tcOblast.setCellValueFactory(new PropertyValueFactory<>("oblast_psihoterapije"));

        tcPsiholog.setCellValueFactory(new PropertyValueFactory<>("je_psiholog"));

        tcDatumSertifikacije.setCellValueFactory(new PropertyValueFactory<>("datum_sertifikacije"));


        tcPsiholog.setCellFactory(column -> {
            return new TableCell<PsihoterapeutInfo, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item ? "Da" : "Ne");
                    }
                }
            };
        });

        tcDatumSertifikacije.setCellFactory(column -> {
            return new TableCell<PsihoterapeutInfo, LocalDate>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(formatter.format(item));
                    }
                }
            };
        });

        super.getColumns().add(tcId);
        super.getColumns().add(tcIme);
        super.getColumns().add(tcPrezime);
        super.getColumns().add(tcEmail);
        super.getColumns().add(tcTelefon);
        super.getColumns().add(tcJmbg);
        super.getColumns().add(tcOblast);
        super.getColumns().add(tcPsiholog);
        super.getColumns().add(tcDatumSertifikacije);


        tcId.setPrefWidth(50);
        tcIme.setPrefWidth(120);
        tcPrezime.setPrefWidth(120);
        tcEmail.setPrefWidth(180);
        tcTelefon.setPrefWidth(120);
        tcJmbg.setPrefWidth(130);
        tcOblast.setPrefWidth(150);
        tcPsiholog.setPrefWidth(80);
        tcDatumSertifikacije.setPrefWidth(150);
    }


    public void setItems(List<PsihoterapeutInfo> items) {
        super.setItems(FXCollections.observableArrayList(items));
    }
}