package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.models.Commantaire;
import org.example.models.Publication;
import org.example.services.CommantaireService;
import org.example.services.PublicationService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class detailCommentaireController implements Initializable {
    @FXML
    private TextField ContenuC;

    @FXML
    private DatePicker DateC;

    @FXML
    private TextField IdC;

    @FXML
    private TextField NameC;

    @FXML
    private ComboBox<ajoutCommentaireController.CommCombo> comboC;
    CommantaireService co=new CommantaireService();
    @FXML
    void deletebtn(ActionEvent event) throws Exception {
        Date date = Date.valueOf(DateC.getValue());
        Commantaire newEvent = new Commantaire(Integer.parseInt(IdC.getText()),comboC.getValue().getId(),ContenuC.getText(),NameC.getText(), date );
        co.delete(newEvent);
        returnbtn(event);
    }

    @FXML
    void returnbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageCommantaire.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }

    @FXML
    void updatebtn(ActionEvent event) throws Exception {
        Date date = Date.valueOf(DateC.getValue());
        Commantaire newEvent = new Commantaire(Integer.parseInt(IdC.getText()),comboC.getValue().getId(),ContenuC.getText(),NameC.getText(), date );
        co.update(newEvent);
        returnbtn(event);
    }
    public void setData(int id , String nom, Date date, int catid, String lieu) {

        IdC.setText(String.valueOf(id));

        NameC.setText(nom);
        ContenuC.setText(lieu);
        DateC.setValue(date.toLocalDate());
        comboC.setVisibleRowCount(catid);



    }
    PublicationService cs = new PublicationService();
    void fillcateComboBox()
    {

        List<Publication> list = cs.recupere();
        ObservableList<ajoutCommentaireController.CommCombo> list2 = FXCollections.observableArrayList();
        for (Publication c : list)
        {
            list2.add(new ajoutCommentaireController.CommCombo(c.getId(),c.getUsername()));
        }
        comboC.setItems(list2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillcateComboBox();
    }
}
