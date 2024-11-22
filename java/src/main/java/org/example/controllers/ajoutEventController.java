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
import org.example.models.Evenement;
import org.example.services.CategorieEventService;
import org.example.services.EvenementServices;
import javafx.scene.control.Alert;
import org.example.utils.MyDatabase;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ajoutEventController implements Initializable {
    Connection cnx= MyDatabase.getInstance().getCnx();

    @FXML
    private ComboBox<catecombo> Catecombo;

    @FXML
    private DatePicker Date_Event;

    @FXML
    private TextField Lieu_Event;

    @FXML
    private TextField Nbr_event;
    EvenementServices eve =new EvenementServices();
    private CategorieEventService cs= new CategorieEventService();
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
    private TextField NomEvent;
    @FXML
    void ajoutEvent(ActionEvent event) throws Exception {
        Date date = Date.valueOf(Date_Event.getValue());
        Evenement newEvent = new Evenement(Catecombo.getValue().getId(),Integer.parseInt(Nbr_event.getText()),NomEvent.getText(),Lieu_Event.getText(), date );
        String nbrEventText = Nbr_event.getText().trim();

        try {
        String nomevent = NomEvent.getText().trim();
        if (nomevent.isEmpty() || nomevent.matches("^\\d+$")) {
            throw new IllegalArgumentException("Le nom de l'evenement ne peut pas être vide ou un nombre.");
        }





            int nbrEvent = Integer.parseInt(nbrEventText);
            if (nbrEvent <= 0) {
                throw new IllegalArgumentException("Le nombre d'événements doit être un entier positif.");
            }

            eve.add(newEvent);
            sendNotificationEmailToAllUsers(newEvent);

            returnbtn(event);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue : " + e.getMessage());
        }


    }




    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillcateComboBox();
    }

    public static class catecombo {
        private final int id;
        private final String displayValue;

        public catecombo(int id, String displayValue) {
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
    void fillcateComboBox()
    {

        List<CategorieEvent> list = cs.recupere();
        ObservableList<catecombo> list2 = FXCollections.observableArrayList();
        for (CategorieEvent c : list)
        {
            list2.add(new catecombo(c.getId(),c.getNom()));
        }
        Catecombo.setItems(list2);
    }
    private void sendNotificationEmailToAllUsers(Evenement event) {
        try {

            List<String> userEmails = getUserEmailsFromDatabase();

            // SMTP server properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Sender's email credentials
            String username = "wjbwastake@gmail.com";
            String password = "xxop vupk etcd ukmg";
            javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            // Create session


            // Iterate through each user's email address and send email
            for (String recipient : userEmails) {
                sendEmail(session, username, recipient, "New event added: " + event.getNom_event());
            }

            System.out.println("Notification emails sent successfully.");

        } catch (SQLException | MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private void sendEmail(javax.mail.Session session, String from, String to, String content) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("New Event Notification");
        message.setText(content);
        Transport.send(message);
    }
    private List<String> getUserEmailsFromDatabase() throws SQLException {
        List<String> userEmails = new ArrayList<>();

        // Establish database connection

        try {

            Statement st = cnx.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT email FROM user");

            // Fetch user emails from the result set
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                userEmails.add(email);

            }
            st.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userEmails;
    }
}
