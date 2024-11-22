package org.example.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.example.models.Evenement;
import org.example.services.EvenementServices;
import org.example.services.ExcelExporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class affichageEventController implements Initializable {
    @FXML
    private TableColumn<Evenement, String> NomEvent;
    @FXML
    private GridPane calendarGrid;

    @FXML
    private TableColumn<Evenement, String> cate;

    @FXML
    private TableColumn<Evenement, Date> dateEvent;

    @FXML
    private TableColumn<Evenement, Integer> id_event;

    @FXML
    private TableColumn<Evenement, String> lieuEvent;

    @FXML
    private TableColumn<Evenement, Integer> nbr_max;
    private Map<LocalDate, String> eventData = new HashMap<>();

    @FXML
    private TableView<Evenement> tableEvent;
    public void showevent() throws SQLException {
        EvenementServices eve = new EvenementServices();
        List<Evenement> list = eve.recupere();
        ObservableList<Evenement> evelist = FXCollections.observableArrayList(list);
        id_event.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomEvent.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        lieuEvent.setCellValueFactory(new PropertyValueFactory<>("lieu_event"));
        dateEvent.setCellValueFactory(new PropertyValueFactory<>("date_event"));
        nbr_max.setCellValueFactory(new PropertyValueFactory<>("max_places_event"));
        cate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCate().getNom()));
        tableEvent.setItems(evelist);
        for (Evenement event : list) {
            eventData.put(event.getDate_event().toLocalDate(), event.getNom_event());
        }
        populateCalendar();

    }
    @FXML
    void addEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutEvent.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }

    @FXML
    void returnbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewadmin.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }
    @FXML
    void Export(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("events.xlsx");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            ExcelExporter.exportToExcel(tableEvent, filePath);
        }
    }

    @FXML
    void PDFE(ActionEvent event) {
       /* try {
            // Spécifiez le chemin du fichier PDF
            String filePath = "event.pdf"; // Chemin du fichier PDF généré
            PdfWriter pdfWriter = new PdfWriter("event.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            // Ouvrir le document
            pdfDocument.getDocumentInfo().setAuthor("Auteur");
            pdfDocument.getDocumentInfo().setTitle("Liste des Sponsors");
            pdfDocument.getDocumentInfo().setSubject("Liste des sponsors en format PDF");

            // Ajoutez le titre du document
            Document document = new Document(pdfDocument); // Création de l'objet Document

            Paragraph title = new Paragraph("Liste des Sponsors");
            title.setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Parcourez les sponsors et ajoutez leurs informations dans le document
            for (Evenement evenement : tableEvent.getItems()) {
                Paragraph sponsorInfo = new Paragraph("ID: " + evenement.getId()+
                        ", Nom: " + evenement.getNom_event() +
                        ", Date: " + evenement.getDate_event() +
                        ", categorie: " + evenement.getCate().getNom()+
                        ", nbr_max: " + evenement.getMax_places_event()+
                        ", lieu: " + evenement.getLieu_event());
                document.add(sponsorInfo);
            }

            // Fermez le document
            document.close(); // Fermeture du document

            // Ouvrir automatiquement le fichier PDF après sa génération
            File file = new File(filePath);
            if (file.exists()) { // Vérifier si le fichier existe
                Desktop.getDesktop().open(file); // Ouvrir le fichier PDF avec l'application par défaut
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Affichez un message d'erreur en cas de problème lors de la génération du PDF
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Une erreur s'est produite lors de l'exportation du PDF.");
            alert.showAndWait();
        }
*/
    }
    private void populateCalendar() {
        calendarGrid.getChildren().clear();
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            calendarGrid.add(dayLabel, i, 0);
        }
        YearMonth currentMonth = YearMonth.now();
        LocalDate firstDayOfMonth = currentMonth.atDay(1);
        int numRows = 6;
        int numCols = 7;
        LocalDate currentDate = firstDayOfMonth;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Label dayLabel = new Label(Integer.toString(currentDate.getDayOfMonth()));
                calendarGrid.add(dayLabel, col, row + 1);
                if (eventData.containsKey(currentDate)) {
                    String event = eventData.get(currentDate);
                    Label eventIndicator = new Label("•");
                    eventIndicator.setStyle("-fx-text-fill: red; -fx-font-size: 50px;");
                    calendarGrid.add(eventIndicator, col, row + 1);
                }
                currentDate = currentDate.plusDays(1);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/detailevent.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                detailEventController d = Loader.getController();
                d.setData(tableEvent.getSelectionModel().getSelectedItem().getId(), "" + tableEvent.getSelectionModel().getSelectedItem().getNom_event(), tableEvent.getSelectionModel().getSelectedItem().getDate_event(), tableEvent.getSelectionModel().getSelectedItem().getCategorie_id(), tableEvent.getSelectionModel().getSelectedItem().getMax_places_event(), tableEvent.getSelectionModel().getSelectedItem().getLieu_event());
                Parent p = Loader.getRoot();

                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        });
        try {
            showevent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
