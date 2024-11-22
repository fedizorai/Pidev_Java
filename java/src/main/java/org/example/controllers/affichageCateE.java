package org.example.controllers;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.models.CategorieEvent;
import org.example.models.Evenement;
import org.example.services.CategorieEventService;
import org.example.services.EvenementServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class affichageCateE implements Initializable {
    @FXML
    private TableColumn<CategorieEvent, String> Nomcate;
    @FXML
    private TableView<CategorieEvent> tablecate;
    @FXML
    private TableColumn<CategorieEvent, String> descCate;

    @FXML
    private TableColumn<CategorieEvent, Integer> id_cate;

    @FXML
    void addbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutCategorieE.fxml"));
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
    public void showcate() throws SQLException {
        CategorieEventService eve = new CategorieEventService();
        List<CategorieEvent> list = eve.recupere();
        ObservableList<CategorieEvent> evelist = FXCollections.observableArrayList(list);
        id_cate.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nomcate.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descCate.setCellValueFactory(new PropertyValueFactory<>("description_categorie_event"));
        tablecate.setItems(evelist);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablecate.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/detailCategorieE.fxml"));

                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();

                    Logger.getLogger(detailCategorieE.class.getName()).log(Level.SEVERE, null, ex);
                }

                detailCategorieE detail = Loader.getController();
                detail.setData(tablecate.getSelectionModel().getSelectedItem().getId(),""+tablecate.getSelectionModel().getSelectedItem().getDescription_categorie_event(),""+tablecate.getSelectionModel().getSelectedItem().getNom());
                Parent p = Loader.getRoot();
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }


        });


        try {
            showcate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
