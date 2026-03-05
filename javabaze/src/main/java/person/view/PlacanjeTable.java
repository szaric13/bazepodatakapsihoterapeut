package person.view;


import person.model.Placanje;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import person.model.utility.JDBCUtils;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PlacanjeTable extends VBox {
    private TableView<PlacanjeRow> tablePlacanja;
    private ObservableList<PlacanjeRow> allData;
    private FilteredList<PlacanjeRow> filteredData;
    private Label lblUkupno;
    private Label lblDugovanje;
    private ComboBox<String> cmbFilter;
    private TextField txtPretraga;
    private int idPsihoterapeuta;
    private List<Placanje> svaPlacanja;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public PlacanjeTable(int idPsihoterapeuta) {
        this.idPsihoterapeuta = idPsihoterapeuta;
        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        setSpacing(10);
        setPadding(new Insets(10));

        HBox topPanel = new HBox(10);
        topPanel.setAlignment(Pos.CENTER_LEFT);
        topPanel.setPadding(new Insets(5));

        topPanel.getChildren().add(new Label("Filter:"));
        cmbFilter = new ComboBox<>(FXCollections.observableArrayList(
                "Svi", "Seanse", "Testovi", "Gotovina", "Kartica"));
        cmbFilter.setValue("Svi");
        cmbFilter.setOnAction(e -> applyFilter());
        topPanel.getChildren().add(cmbFilter);

        topPanel.getChildren().add(new Label("Pretraga:"));
        txtPretraga = new TextField();
        txtPretraga.setPrefWidth(200);
        txtPretraga.setOnAction(e -> applyFilter());
        topPanel.getChildren().add(txtPretraga);

        Button btnPretraga = new Button("Pretraži");
        btnPretraga.setOnAction(e -> applyFilter());
        topPanel.getChildren().add(btnPretraga);

        Button btnRefresh = new Button("Osveži");
        btnRefresh.setOnAction(e -> loadData());
        topPanel.getChildren().add(btnRefresh);

        getChildren().add(topPanel);

        createTable();
        VBox.setVgrow(tablePlacanja, Priority.ALWAYS);
        getChildren().add(tablePlacanja);

        HBox bottomPanel = new HBox(50);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.setPadding(new Insets(10));

        lblUkupno = new Label("Ukupno plaćeno: 0.00 RSD");
        lblUkupno.setFont(Font.font(null, FontWeight.BOLD, 12));
        bottomPanel.getChildren().add(lblUkupno);

        lblDugovanje = new Label("Ukupno dugovanje: 0.00 RSD");
        lblDugovanje.setFont(Font.font(null, FontWeight.BOLD, 12));
        lblDugovanje.setTextFill(Color.RED);
        bottomPanel.getChildren().add(lblDugovanje);

        getChildren().add(bottomPanel);
    }

    @SuppressWarnings("unchecked")
    private void createTable() {
        tablePlacanja = new TableView<>();
        tablePlacanja.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        TableColumn<PlacanjeRow, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colId.setPrefWidth(50);

        TableColumn<PlacanjeRow, String> colKlijent = new TableColumn<>("Klijent");
        colKlijent.setCellValueFactory(cellData -> cellData.getValue().klijentProperty());
        colKlijent.setPrefWidth(150);

        TableColumn<PlacanjeRow, String> colSvrha = new TableColumn<>("Svrha");
        colSvrha.setCellValueFactory(cellData -> cellData.getValue().svrhaProperty());
        colSvrha.setPrefWidth(80);

        TableColumn<PlacanjeRow, String> colIznos = new TableColumn<>("Iznos");
        colIznos.setCellValueFactory(cellData -> cellData.getValue().iznosProperty());
        colIznos.setPrefWidth(100);

        TableColumn<PlacanjeRow, String> colValuta = new TableColumn<>("Valuta");
        colValuta.setCellValueFactory(cellData -> cellData.getValue().valutaProperty());
        colValuta.setPrefWidth(60);

        TableColumn<PlacanjeRow, String> colNacin = new TableColumn<>("Način plaćanja");
        colNacin.setCellValueFactory(cellData -> cellData.getValue().nacinProperty());
        colNacin.setPrefWidth(120);

        TableColumn<PlacanjeRow, String> colBrRate = new TableColumn<>("Br. rata");
        colBrRate.setCellValueFactory(cellData -> cellData.getValue().brRateProperty());
        colBrRate.setPrefWidth(70);

        TableColumn<PlacanjeRow, String> colProcenat = new TableColumn<>("% 1. rate");
        colProcenat.setCellValueFactory(cellData -> cellData.getValue().procenatProperty());
        colProcenat.setPrefWidth(80);

        TableColumn<PlacanjeRow, String> colRok = new TableColumn<>("Rok 2. rate");
        colRok.setCellValueFactory(cellData -> cellData.getValue().rokProperty());
        colRok.setPrefWidth(100);

        TableColumn<PlacanjeRow, String> colProvizija = new TableColumn<>("Provizija");
        colProvizija.setCellValueFactory(cellData -> cellData.getValue().provizijaProperty());
        colProvizija.setPrefWidth(80);

        TableColumn<PlacanjeRow, String> colDatumPlacanja = new TableColumn<>("Datum plaćanja");
        colDatumPlacanja.setCellValueFactory(cellData -> cellData.getValue().datumPlacanjaProperty());
        colDatumPlacanja.setPrefWidth(120);

        TableColumn<PlacanjeRow, String> colDatumSeanse = new TableColumn<>("Datum seanse/Test");
        colDatumSeanse.setCellValueFactory(cellData -> cellData.getValue().datumSeanseProperty());
        colDatumSeanse.setPrefWidth(150);

        tablePlacanja.getColumns().addAll(colId, colKlijent, colSvrha, colIznos, colValuta,
                colNacin, colBrRate, colProcenat, colRok, colProvizija, colDatumPlacanja, colDatumSeanse);


        allData = FXCollections.observableArrayList();
        filteredData = new FilteredList<>(allData);
        tablePlacanja.setItems(filteredData);
    }

    public void loadData() {
        allData.clear();

        try {
            svaPlacanja = JDBCUtils.selectPlacanjaZaPsihoterapeuta(idPsihoterapeuta);
            populateTable(svaPlacanja);
            calculateTotalDebt();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška pri učitavanju podataka");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void populateTable(List<Placanje> placanja) {
        allData.clear();
        BigDecimal ukupno = BigDecimal.ZERO;

        for (Placanje p : placanja) {
            PlacanjeRow row = new PlacanjeRow();
            row.setId(String.valueOf(p.getIdPlacanja()));
            row.setKlijent(p.getImePrezimeKlijenta());
            row.setSvrha(p.getSvrha().equals("seansa") ? "Seansa" : "Test");
            row.setIznos(p.getIznos().toString());
            row.setValuta(p.getSkraceniNazivValute());
            row.setNacin(p.getNacinPlacanja().equals("gotovina") ? "Gotovina" : "Kartica");
            row.setBrRate(String.valueOf(p.getBrojRate()));
            row.setProcenat(p.getProcenatPrveRate() != null ? p.getProcenatPrveRate().toString() + "%" : "");
            row.setRok(p.getRokDrugeRate() != null ? p.getRokDrugeRate().format(dateFormatter) : "");
            row.setProvizija(p.getProvizija() != null ? p.getProvizija().toString() + "%" : "");
            row.setDatumPlacanja(p.getDatumPlacanja().format(dateFormatter));

            if (p.getSvrha().equals("seansa") && p.getDatumSeanse() != null) {
                row.setDatumSeanse("Seansa: " + p.getDatumSeanse().format(dateFormatter));
            } else if (p.getSvrha().equals("test") && p.getNazivTesta() != null) {
                row.setDatumSeanse("Test: " + p.getNazivTesta());
            } else {
                row.setDatumSeanse("");
            }

            allData.add(row);
            ukupno = ukupno.add(p.getIznos());
        }

        lblUkupno.setText("Ukupno plaćeno: " + ukupno.toString() + " RSD");
    }

    private void calculateTotalDebt() {
        try {
            List<Map<String, Object>> klijentiInfo = JDBCUtils.selectPlacanjaIDugovanja(idPsihoterapeuta);
            BigDecimal ukupnoDugovanje = BigDecimal.ZERO;

            for (Map<String, Object> info : klijentiInfo) {
                BigDecimal dugovanje = (BigDecimal) info.get("dugovanje");
                if (dugovanje != null) {
                    ukupnoDugovanje = ukupnoDugovanje.add(dugovanje);
                }
            }

            lblDugovanje.setText("Ukupno dugovanje: " + ukupnoDugovanje.toString() + " RSD");

        } catch (Exception e) {
            lblDugovanje.setText("Ukupno dugovanje: Greška pri izračunu");
        }
    }

    private void applyFilter() {
        String filter = cmbFilter.getValue();
        String pretraga = txtPretraga.getText().toLowerCase().trim();

        Predicate<PlacanjeRow> filterPredicate = row -> {

            boolean matchesFilter = true;
            if (!filter.equals("Svi")) {
                switch (filter) {
                    case "Seanse":
                        matchesFilter = row.getSvrha().equals("Seansa");
                        break;
                    case "Testovi":
                        matchesFilter = row.getSvrha().equals("Test");
                        break;
                    case "Gotovina":
                        matchesFilter = row.getNacin().equals("Gotovina");
                        break;
                    case "Kartica":
                        matchesFilter = row.getNacin().equals("Kartica");
                        break;
                }
            }


            boolean matchesSearch = true;
            if (!pretraga.isEmpty()) {
                matchesSearch = row.getId().toLowerCase().contains(pretraga) ||
                        row.getKlijent().toLowerCase().contains(pretraga) ||
                        row.getSvrha().toLowerCase().contains(pretraga) ||
                        row.getIznos().toLowerCase().contains(pretraga) ||
                        row.getNacin().toLowerCase().contains(pretraga) ||
                        row.getDatumPlacanja().toLowerCase().contains(pretraga) ||
                        row.getDatumSeanse().toLowerCase().contains(pretraga);
            }

            return matchesFilter && matchesSearch;
        };

        filteredData.setPredicate(filterPredicate);
    }

    public TableView<PlacanjeRow> getTable() {
        return tablePlacanja;
    }

    public Placanje getSelectedPlacanje() {
        PlacanjeRow selectedRow = tablePlacanja.getSelectionModel().getSelectedItem();
        if (selectedRow != null) {
            int idPlacanja = Integer.parseInt(selectedRow.getId());

            for (Placanje p : svaPlacanja) {
                if (p.getIdPlacanja() == idPlacanja) {
                    return p;
                }
            }
        }
        return null;
    }


    public static class PlacanjeRow {
        private final SimpleStringProperty id = new SimpleStringProperty();
        private final SimpleStringProperty klijent = new SimpleStringProperty();
        private final SimpleStringProperty svrha = new SimpleStringProperty();
        private final SimpleStringProperty iznos = new SimpleStringProperty();
        private final SimpleStringProperty valuta = new SimpleStringProperty();
        private final SimpleStringProperty nacin = new SimpleStringProperty();
        private final SimpleStringProperty brRate = new SimpleStringProperty();
        private final SimpleStringProperty procenat = new SimpleStringProperty();
        private final SimpleStringProperty rok = new SimpleStringProperty();
        private final SimpleStringProperty provizija = new SimpleStringProperty();
        private final SimpleStringProperty datumPlacanja = new SimpleStringProperty();
        private final SimpleStringProperty datumSeanse = new SimpleStringProperty();


        public String getId() { return id.get(); }
        public void setId(String value) { id.set(value); }
        public SimpleStringProperty idProperty() { return id; }

        public String getKlijent() { return klijent.get(); }
        public void setKlijent(String value) { klijent.set(value); }
        public SimpleStringProperty klijentProperty() { return klijent; }

        public String getSvrha() { return svrha.get(); }
        public void setSvrha(String value) { svrha.set(value); }
        public SimpleStringProperty svrhaProperty() { return svrha; }

        public String getIznos() { return iznos.get(); }
        public void setIznos(String value) { iznos.set(value); }
        public SimpleStringProperty iznosProperty() { return iznos; }

        public String getValuta() { return valuta.get(); }
        public void setValuta(String value) { valuta.set(value); }
        public SimpleStringProperty valutaProperty() { return valuta; }

        public String getNacin() { return nacin.get(); }
        public void setNacin(String value) { nacin.set(value); }
        public SimpleStringProperty nacinProperty() { return nacin; }

        public String getBrRate() { return brRate.get(); }
        public void setBrRate(String value) { brRate.set(value); }
        public SimpleStringProperty brRateProperty() { return brRate; }

        public String getProcenat() { return procenat.get(); }
        public void setProcenat(String value) { procenat.set(value); }
        public SimpleStringProperty procenatProperty() { return procenat; }

        public String getRok() { return rok.get(); }
        public void setRok(String value) { rok.set(value); }
        public SimpleStringProperty rokProperty() { return rok; }

        public String getProvizija() { return provizija.get(); }
        public void setProvizija(String value) { provizija.set(value); }
        public SimpleStringProperty provizijaProperty() { return provizija; }

        public String getDatumPlacanja() { return datumPlacanja.get(); }
        public void setDatumPlacanja(String value) { datumPlacanja.set(value); }
        public SimpleStringProperty datumPlacanjaProperty() { return datumPlacanja; }

        public String getDatumSeanse() { return datumSeanse.get(); }
        public void setDatumSeanse(String value) { datumSeanse.set(value); }
        public SimpleStringProperty datumSeanseProperty() { return datumSeanse; }
    }
}