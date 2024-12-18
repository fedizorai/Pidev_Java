package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.Evenement;
import org.example.models.Publication;
import org.example.services.PublicationService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ajoutPublicationController {
    @FXML
    private TextField Contenup;

    @FXML
    private DatePicker Datep;

    @FXML
    private TextField Idp;

    @FXML
    private TextField Likep;

    @FXML
    private TextField Useridp;

    @FXML
    private TextField Usernamep;
    PublicationService pub = new PublicationService();
    @FXML
    void addbtn(ActionEvent event) throws Exception {
        Date date = Date.valueOf(Datep.getValue());
        Publication newEvent = new Publication(Integer.parseInt(Useridp.getText()),Integer.parseInt(Likep.getText()),Contenup.getText(),Usernamep.getText(), date );
        pub.add(newEvent);
        returnbtn(event);
    }

    @FXML
    void returnbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePublication.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }
}
