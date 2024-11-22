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
import org.example.models.CategorieEvent;
import org.example.models.Commantaire;
import org.example.models.Evenement;
import org.example.models.Publication;
import org.example.services.CommantaireService;
import org.example.services.PublicationService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ajoutCommentaireController implements Initializable {
    @FXML
    private TextField ContenuC;

    @FXML
    private DatePicker DateC;

    @FXML
    private TextField IdC;

    @FXML
    private TextField NameC;

    @FXML
    private ComboBox<CommCombo> comboC;
CommantaireService co=new CommantaireService();
    @FXML
    void addbtn(ActionEvent event) throws Exception {
        Date date = Date.valueOf(DateC.getValue());
        Commantaire newEvent = new Commantaire(comboC.getValue().getId(),ContenuC.getText(),NameC.getText(), date );
        co.add(newEvent);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillcateComboBox();
    }

    public static class CommCombo {
        private final int id;
        private final String displayValue;

        public CommCombo(int id, String displayValue) {
            this.id = id;
            this.displayValue = displayValue;
        }

        public int getId() {
            return id;
        }
        @Override
        public String toString() {
            return displayValue;
        }
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
}
