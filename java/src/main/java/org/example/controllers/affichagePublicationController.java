package org.example.controllers;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.models.CategorieEvent;
import org.example.models.Publication;
import org.example.services.CategorieEventService;
import org.example.services.ExcelExporter;
import org.example.services.PublicationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class affichagePublicationController implements Initializable {
    @FXML
    private TableColumn<Publication, String> Username_Pub;

    @FXML
    private TableColumn<Publication, String> contenu;

    @FXML
    private TableColumn<Publication, Date> date_pub;

    @FXML
    private TableColumn<Publication, Integer> id_pub;

    @FXML
    private TableColumn<Publication, Integer> likes;

    @FXML
    private TableView<Publication> tablePub;

    @FXML
    private TableColumn<Publication, Integer> user_id;

    public void showpub() throws SQLException {
        PublicationService pub = new PublicationService();
        List<Publication> list = pub.recupere();
        ObservableList<Publication> publist = FXCollections.observableArrayList(list);
        user_id.setCellValueFactory(new PropertyValueFactory<>("userid"));
        likes.setCellValueFactory(new PropertyValueFactory<>("like"));
        id_pub.setCellValueFactory(new PropertyValueFactory<>("id"));
        date_pub.setCellValueFactory(new PropertyValueFactory<>("datepub"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        Username_Pub.setCellValueFactory(new PropertyValueFactory<>("username"));
        tablePub.setItems(publist);

    }
    @FXML
    void addp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutpublication.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }
    @FXML
    void ExportP(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("events.xlsx");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            ExcelExporter.exportToExcel(tablePub, filePath);
        }
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablePub.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/detailPublication.fxml"));

                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();

                    Logger.getLogger(detailPublictaionController.class.getName()).log(Level.SEVERE, null, ex);
                }

                detailPublictaionController detail = Loader.getController();
                detail.setData(tablePub.getSelectionModel().getSelectedItem().getId(),tablePub.getSelectionModel().getSelectedItem().getUserid(),tablePub.getSelectionModel().getSelectedItem().getLike(),tablePub.getSelectionModel().getSelectedItem().getContenu(),tablePub.getSelectionModel().getSelectedItem().getUsername(),tablePub.getSelectionModel().getSelectedItem().getDatepub());
                Parent p = Loader.getRoot();
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }


        });


        try {
            showpub();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
