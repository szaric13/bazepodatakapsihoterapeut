package person.view;


import person.model.Placanje;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import person.model.utility.JDBCUtils;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PlacanjeView extends Stage {
    private PlacanjeTable placanjeTable;
    private int idPsihoterapeuta;
    private TabPane tabPane;

    public PlacanjeView(int idPsihoterapeuta) {
        this.idPsihoterapeuta = idPsihoterapeuta;
        initializeComponents();
        setupEventHandlers();
    }

    private void initializeComponents() {
        setTitle("Pregled plaćanja");
        setMaximized(true);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        tabPane = new TabPane();

        placanjeTable = new PlacanjeTable(idPsihoterapeuta);
        Tab tab1 = new Tab("Sva plaćanja", placanjeTable);
        tab1.setClosable(false);
        tabPane.getTabs().add(tab1);

        VBox detaljniPanel = createDetailedPanel();
        Tab tab2 = new Tab("Detaljan pregled po klijentima", detaljniPanel);
        tab2.setClosable(false);
        tabPane.getTabs().add(tab2);

        VBox.setVgrow(tabPane, Priority.ALWAYS);
        root.getChildren().add(tabPane);

        HBox bottomPanel = new HBox(10);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.setPadding(new Insets(10));

        Button btnDetaljiPlacanja = new Button("Detalji plaćanja");
        btnDetaljiPlacanja.setOnAction(e -> showPlacanjeDetails());
        bottomPanel.getChildren().add(btnDetaljiPlacanja);

        Button btnClose = new Button("Zatvori");
        btnClose.setOnAction(e -> close());
        bottomPanel.getChildren().add(btnClose);

        root.getChildren().add(bottomPanel);

        Scene scene = new Scene(root, 1200, 800);
        setScene(scene);
    }

    private void setupEventHandlers() {

        placanjeTable.getTable().setRowFactory(tv -> {
            TableRow<PlacanjeTable.PlacanjeRow> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    showPlacanjeDetails();
                }
            });
            return row;
        });
    }

    private VBox createDetailedPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));

        TableView<KlijentRow> tableKlijenti = new TableView<>();
        tableKlijenti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<KlijentRow, String> colKlijent = new TableColumn<>("Klijent");
        colKlijent.setCellValueFactory(cellData -> cellData.getValue().klijentProperty());
        colKlijent.setPrefWidth(200);

        TableColumn<KlijentRow, String> colBrojPlacanja = new TableColumn<>("Broj plaćanja");
        colBrojPlacanja.setCellValueFactory(cellData -> cellData.getValue().brojPlacanjaProperty());
        colBrojPlacanja.setPrefWidth(120);

        TableColumn<KlijentRow, String> colUkupno = new TableColumn<>("Ukupno plaćeno");
        colUkupno.setCellValueFactory(cellData -> cellData.getValue().ukupnoProperty());
        colUkupno.setPrefWidth(150);

        TableColumn<KlijentRow, String> colDugovanje = new TableColumn<>("Dugovanje");
        colDugovanje.setCellValueFactory(cellData -> cellData.getValue().dugovanjeProperty());
        colDugovanje.setPrefWidth(150);

        TableColumn<KlijentRow, String> colPoslednje = new TableColumn<>("Poslednje plaćanje");
        colPoslednje.setCellValueFactory(cellData -> cellData.getValue().poslednjeProperty());
        colPoslednje.setPrefWidth(150);

        tableKlijenti.getColumns().addAll(colKlijent, colBrojPlacanja, colUkupno, colDugovanje, colPoslednje);

        ObservableList<KlijentRow> dataKlijenti = FXCollections.observableArrayList();
        loadDetailedData(dataKlijenti);
        tableKlijenti.setItems(dataKlijenti);

        VBox.setVgrow(tableKlijenti, Priority.ALWAYS);
        panel.getChildren().add(tableKlijenti);

        HBox buttonPanel = new HBox(10);
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPadding(new Insets(10));

        Button btnPrikaziPlacanja = new Button("Prikaži plaćanja klijenta");
        btnPrikaziPlacanja.setOnAction(e -> {
            KlijentRow selectedRow = tableKlijenti.getSelectionModel().getSelectedItem();
            if (selectedRow != null) {
                String klijent = selectedRow.getKlijent();
                showClientPayments(klijent);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setHeaderText("Molimo izaberite klijenta.");
                alert.showAndWait();
            }
        });
        buttonPanel.getChildren().add(btnPrikaziPlacanja);

        Button btnRefreshDetailed = new Button("Osveži");
        btnRefreshDetailed.setOnAction(e -> loadDetailedData(dataKlijenti));
        buttonPanel.getChildren().add(btnRefreshDetailed);

        panel.getChildren().add(buttonPanel);

        return panel;
    }

    private void loadDetailedData(ObservableList<KlijentRow> data) {
        data.clear();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try {
            List<Map<String, Object>> klijentiInfo = JDBCUtils.selectPlacanjaIDugovanja(idPsihoterapeuta);

            for (Map<String, Object> info : klijentiInfo) {
                KlijentRow row = new KlijentRow();

                String ime = (String) info.get("imeKlijenta");
                String prezime = (String) info.get("prezimeKlijenta");
                row.setKlijent(ime + " " + prezime);

                @SuppressWarnings("unchecked")
                List<Placanje> placanja = (List<Placanje>) info.get("placanja");
                row.setBrojPlacanja(String.valueOf(placanja.size()));

                BigDecimal ukupno = BigDecimal.ZERO;
                Placanje poslednje = null;

                for (Placanje p : placanja) {
                    ukupno = ukupno.add(p.getIznos());
                    if (poslednje == null || p.getDatumPlacanja().isAfter(poslednje.getDatumPlacanja())) {
                        poslednje = p;
                    }
                }

                row.setUkupno(ukupno.toString() + " RSD");

                BigDecimal dugovanje = (BigDecimal) info.get("dugovanje");
                // Fix for the "0E" issue - use toPlainString() instead of toString()
                String dugovanjeStr = (dugovanje != null) ? dugovanje.toPlainString() : "0.00";
                row.setDugovanje(dugovanjeStr + " RSD");

                row.setPoslednje(poslednje != null ? poslednje.getDatumPlacanja().format(dateFormatter) : "");

                data.add(row);
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška pri učitavanju detaljnih podataka");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void showPlacanjeDetails() {
        Placanje selected = placanjeTable.getSelectedPlacanje();
        if (selected != null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Detalji plaćanja");
            alert.setHeaderText("Informacije o plaćanju ID: " + selected.getIdPlacanja());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            StringBuilder details = new StringBuilder();
            details.append("Klijent: ").append(selected.getImePrezimeKlijenta()).append("\n");
            details.append("Svrha: ").append(selected.getSvrha().equals("seansa") ? "Seansa" : "Test").append("\n");
            details.append("Iznos: ").append(selected.getIznos()).append(" ").append(selected.getSkraceniNazivValute()).append("\n");
            details.append("Način plaćanja: ").append(selected.getNacinPlacanja().equals("gotovina") ? "Gotovina" : "Kartica").append("\n");
            details.append("Datum plaćanja: ").append(selected.getDatumPlacanja().format(dateFormatter)).append("\n");

            if (selected.getBrojRate() > 1) {
                details.append("Broj rata: ").append(selected.getBrojRate()).append("\n");
                if (selected.getProcenatPrveRate() != null) {
                    details.append("Procenat prve rate: ").append(selected.getProcenatPrveRate()).append("%\n");
                }
                if (selected.getRokDrugeRate() != null) {
                    details.append("Rok druge rate: ").append(selected.getRokDrugeRate().format(dateFormatter)).append("\n");
                }
            }

            if (selected.getProvizija() != null) {
                details.append("Provizija: ").append(selected.getProvizija()).append("%\n");
            }

            if (selected.getSvrha().equals("seansa") && selected.getDatumSeanse() != null) {
                details.append("Datum seanse: ").append(selected.getDatumSeanse().format(dateFormatter)).append("\n");
            } else if (selected.getSvrha().equals("test") && selected.getNazivTesta() != null) {
                details.append("Test: ").append(selected.getNazivTesta()).append("\n");
            }

            alert.setContentText(details.toString());
            alert.getDialogPane().setPrefWidth(400);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Molimo izaberite plaćanje.");
            alert.showAndWait();
        }
    }

    private void showClientPayments(String klijent) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(this);
        dialog.setTitle("Plaćanja klijenta: " + klijent);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        TableView<ClientPaymentRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ClientPaymentRow, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<ClientPaymentRow, String> colSvrha = new TableColumn<>("Svrha");
        colSvrha.setCellValueFactory(cellData -> cellData.getValue().svrhaProperty());

        TableColumn<ClientPaymentRow, String> colIznos = new TableColumn<>("Iznos");
        colIznos.setCellValueFactory(cellData -> cellData.getValue().iznosProperty());

        TableColumn<ClientPaymentRow, String> colDatum = new TableColumn<>("Datum plaćanja");
        colDatum.setCellValueFactory(cellData -> cellData.getValue().datumProperty());

        TableColumn<ClientPaymentRow, String> colNacin = new TableColumn<>("Način plaćanja");
        colNacin.setCellValueFactory(cellData -> cellData.getValue().nacinProperty());

        table.getColumns().addAll(colId, colSvrha, colIznos, colDatum, colNacin);

        ObservableList<ClientPaymentRow> data = FXCollections.observableArrayList();

        try {
            List<Placanje> svaPlacanja = JDBCUtils.selectPlacanjaZaPsihoterapeuta(idPsihoterapeuta);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            for (Placanje p : svaPlacanja) {
                if (p.getImePrezimeKlijenta().equals(klijent)) {
                    ClientPaymentRow row = new ClientPaymentRow();
                    row.setId(String.valueOf(p.getIdPlacanja()));
                    row.setSvrha(p.getSvrha().equals("seansa") ? "Seansa" : "Test");
                    row.setIznos(p.getIznos().toString() + " " + p.getSkraceniNazivValute());
                    row.setDatum(p.getDatumPlacanja().format(dateFormatter));
                    row.setNacin(p.getNacinPlacanja().equals("gotovina") ? "Gotovina" : "Kartica");
                    data.add(row);
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška pri učitavanju podataka");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        table.setItems(data);
        VBox.setVgrow(table, Priority.ALWAYS);
        root.getChildren().add(table);

        HBox closePanel = new HBox();
        closePanel.setAlignment(Pos.CENTER);
        closePanel.setPadding(new Insets(10));

        Button btnClose = new Button("Zatvori");
        btnClose.setOnAction(e -> dialog.close());
        closePanel.getChildren().add(btnClose);

        root.getChildren().add(closePanel);

        Scene scene = new Scene(root, 800, 600);
        dialog.setScene(scene);
        dialog.show();
    }


    public static class KlijentRow {
        private final SimpleStringProperty klijent = new SimpleStringProperty();
        private final SimpleStringProperty brojPlacanja = new SimpleStringProperty();
        private final SimpleStringProperty ukupno = new SimpleStringProperty();
        private final SimpleStringProperty dugovanje = new SimpleStringProperty();
        private final SimpleStringProperty poslednje = new SimpleStringProperty();

        public String getKlijent() { return klijent.get(); }
        public void setKlijent(String value) { klijent.set(value); }
        public SimpleStringProperty klijentProperty() { return klijent; }

        public String getBrojPlacanja() { return brojPlacanja.get(); }
        public void setBrojPlacanja(String value) { brojPlacanja.set(value); }
        public SimpleStringProperty brojPlacanjaProperty() { return brojPlacanja; }

        public String getUkupno() { return ukupno.get(); }
        public void setUkupno(String value) { ukupno.set(value); }
        public SimpleStringProperty ukupnoProperty() { return ukupno; }

        public String getDugovanje() { return dugovanje.get(); }
        public void setDugovanje(String value) { dugovanje.set(value); }
        public SimpleStringProperty dugovanjeProperty() { return dugovanje; }

        public String getPoslednje() { return poslednje.get(); }
        public void setPoslednje(String value) { poslednje.set(value); }
        public SimpleStringProperty poslednjeProperty() { return poslednje; }
    }

    public static class ClientPaymentRow {
        private final SimpleStringProperty id = new SimpleStringProperty();
        private final SimpleStringProperty svrha = new SimpleStringProperty();
        private final SimpleStringProperty iznos = new SimpleStringProperty();
        private final SimpleStringProperty datum = new SimpleStringProperty();
        private final SimpleStringProperty nacin = new SimpleStringProperty();

        public String getId() { return id.get(); }
        public void setId(String value) { id.set(value); }
        public SimpleStringProperty idProperty() { return id; }

        public String getSvrha() { return svrha.get(); }
        public void setSvrha(String value) { svrha.set(value); }
        public SimpleStringProperty svrhaProperty() { return svrha; }

        public String getIznos() { return iznos.get(); }
        public void setIznos(String value) { iznos.set(value); }
        public SimpleStringProperty iznosProperty() { return iznos; }

        public String getDatum() { return datum.get(); }
        public void setDatum(String value) { datum.set(value); }
        public SimpleStringProperty datumProperty() { return datum; }

        public String getNacin() { return nacin.get(); }
        public void setNacin(String value) { nacin.set(value); }
        public SimpleStringProperty nacinProperty() { return nacin; }
    }
}