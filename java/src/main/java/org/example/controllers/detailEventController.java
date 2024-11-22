package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.models.CategorieEvent;
import org.example.models.Evenement;
import org.example.services.CategorieEventService;
import org.example.services.EvenementServices;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class detailEventController implements Initializable {
    @FXML
private ComboBox<ajoutEventController.catecombo> Catecombo;

    @FXML
    private DatePicker Date_Event;

    @FXML
    private TextField Lieu_Event;

    @FXML
    private TextField Nbr_event;

    @FXML
    private TextField NomEvent;
    @FXML
    private TextField idEvent;

    @FXML
    void DeleteEvent(ActionEvent event) throws Exception {
        EvenementServices ev= new EvenementServices();
        Date date = Date.valueOf(Date_Event.getValue());
        Evenement evt=new Evenement(Integer.parseInt(idEvent.getText()),Catecombo.getValue().getId(),Integer.parseInt(Nbr_event.getText()),NomEvent.getText(),Lieu_Event.getText(), date );
        ev.delete(evt);
        returnbtn(event);
    }
    @FXML
    void returnbtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageEvent.fxml"));
        Parent signInRoot = loader.load();
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();

    }
    @FXML
    void updateEvent(ActionEvent event) throws Exception {
        EvenementServices ev= new EvenementServices();
        Date date = Date.valueOf(Date_Event.getValue());
        Evenement evt=new Evenement(Integer.parseInt(idEvent.getText()),Catecombo.getValue().getId(),Integer.parseInt(Nbr_event.getText()),NomEvent.getText(),Lieu_Event.getText(), date );
        ev.update(evt);
        returnbtn(event);


    }
    public void setData(int id , String nom, Date date, int catid,int nbr,String lieu) {

        idEvent.setText(String.valueOf(id));
        Nbr_event.setText(String.valueOf(nbr));
        NomEvent.setText(nom);
        Lieu_Event.setText(lieu);
        Date_Event.setValue(date.toLocalDate());
        Catecombo.setVisibleRowCount(catid);



    }
    private CategorieEventService cs= new CategorieEventService();
    void fillcateComboBox()
    {

        List<CategorieEvent> list = cs.recupere();
        ObservableList<ajoutEventController.catecombo> list2 = FXCollections.observableArrayList();
        for (CategorieEvent c : list)
        {
            list2.add(new ajoutEventController.catecombo(c.getId(),c.getNom()));
        }
        Catecombo.setItems(list2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillcateComboBox();
    }
}
