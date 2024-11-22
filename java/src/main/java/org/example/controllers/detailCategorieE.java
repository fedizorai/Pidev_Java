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

public class detailCategorieE implements Initializable {
    @FXML
    private TextField descrcate;

    @FXML
    private TextField id_cate;

    @FXML
    private TextField nomcate;

    @FXML
    void deletecate(ActionEvent event) throws Exception {
        CategorieEventService ca= new CategorieEventService();
        CategorieEvent cat=new CategorieEvent(Integer.parseInt(id_cate.getText()),descrcate.getText(),nomcate.getText());
        ca.delete(cat);
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

    @FXML
    void updatebtn(ActionEvent event) throws Exception {
        CategorieEventService ca= new CategorieEventService();
        CategorieEvent cat=new CategorieEvent(Integer.parseInt(id_cate.getText()),descrcate.getText(),nomcate.getText());
        ca.update(cat);
        returnbtn(event);

    }
    public void setData(int id ,String type,String waa){
        descrcate.setText( type );
        nomcate.setText( waa );

        id_cate.setText(String.valueOf(id));

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
