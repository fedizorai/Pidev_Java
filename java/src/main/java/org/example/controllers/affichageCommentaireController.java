package org.example.controllers;

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
import org.example.models.Commantaire;
import org.example.models.Evenement;
import org.example.services.CommantaireService;
import org.example.services.EvenementServices;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class affichageCommentaireController implements Initializable {
    @FXML
    private TableColumn<Commantaire, Date> Datec;

    @FXML
    private TableColumn<Commantaire, String> contenuC;

    @FXML
    private TableColumn<Commantaire, Integer> idco;

    @FXML
    private TableColumn<Commantaire, String> namec;

    @FXML
    private TableColumn<Commantaire, String> pubc;

    @FXML
    private TableView<Commantaire> tableCo;
    public void showcomm() throws SQLException {
        CommantaireService eve = new CommantaireService();
        List<Commantaire> list = eve.recupere();
        ObservableList<Commantaire> evelist = FXCollections.observableArrayList(list);
        idco.setCellValueFactory(new PropertyValueFactory<>("idcomm"));
        namec.setCellValueFactory(new PropertyValueFactory<>("name"));
        contenuC.setCellValueFactory(new PropertyValueFactory<>("contenucom"));
        Datec.setCellValueFactory(new PropertyValueFactory<>("datecomm"));
        pubc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPub_id().getUsername()));
        tableCo.setItems(evelist);

    }
    @FXML
    void addbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajoutCommantaire.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableCo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("/detailCommentaire.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                detailCommentaireController d = Loader.getController();
                d.setData(tableCo.getSelectionModel().getSelectedItem().getIdcomm(), "" + tableCo.getSelectionModel().getSelectedItem().getName(), tableCo.getSelectionModel().getSelectedItem().getDatecomm(), tableCo.getSelectionModel().getSelectedItem().getIdpub(), tableCo.getSelectionModel().getSelectedItem().getContenucom());
                Parent p = Loader.getRoot();

                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        });


        try {
            showcomm();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
