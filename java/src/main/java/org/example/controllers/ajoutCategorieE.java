package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.CategorieEvent;
import org.example.services.CategorieEventService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ajoutCategorieE implements Initializable {



    @FXML
    private TextField descrcate;

    @FXML
    private TextField id_cate;

    @FXML
    private TextField nomcate;
     CategorieEventService cs= new CategorieEventService();

    @FXML
    void addcate(ActionEvent event) throws Exception {
        cs.add(new CategorieEvent(nomcate.getText(),descrcate.getText()));
        returnbtn(event);
    }

    @FXML
    void returnbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageCategorieE.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
