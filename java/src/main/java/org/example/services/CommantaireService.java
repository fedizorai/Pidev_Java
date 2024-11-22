package org.example.services;

import org.example.interfaces.categorieface;
import org.example.interfaces.commantaireface;
import org.example.models.CategorieEvent;
import org.example.models.Commantaire;
import org.example.models.Publication;
import org.example.utils.MyDatabase;

import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommantaireService implements commantaireface<Commantaire> {
    Connection cnx = MyDatabase.getInstance().getCnx();

    @Override
    public void add(Commantaire commantaire) throws SQLException {
        String query = "INSERT INTO commentaire ( pubid, contenucom, datecom, name) VALUES ( ?, ?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commantaire.getIdpub());
            statement.setString(2, commantaire.getContenucom());
            statement.setDate(3, commantaire.getDatecomm());
            statement.setString(4, commantaire.getName());

            statement.executeUpdate();
        }
    }


    @Override
    public void update(Commantaire commantaire) throws SQLException {
        String query = "UPDATE commentaire SET  pubid=?, contenucom=?, datecom=?, name=? WHERE id=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commantaire.getIdpub());
            statement.setString(2, commantaire.getContenucom());
            statement.setDate(3, commantaire.getDatecomm());
            statement.setString(4, commantaire.getName());
            statement.setInt(5, commantaire.getIdcomm());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Update failed, no rows affected.");
            } else {
                System.out.println("Update successful.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating record: " + e.getMessage());
            throw e; // Re-throw the exception to propagate it
        }
    }

    @Override
    public void delete(Commantaire commantaire) throws SQLException {
        String query = "DELETE FROM commentaire WHERE id=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, commantaire.getIdcomm());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("Delete failed, no rows affected.");
            } else {
                System.out.println("Delete successful.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting record: " + e.getMessage());
            throw e; // Re-throw the exception to propagate it
        }
    }

    @Override
    public List<Commantaire> recupere() {
        List<Commantaire> Commantaire = new ArrayList<>();
        commantaireface face= new CommantaireService() ;

        try {
            String req = "SELECT * FROM commentaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commantaire e = new Commantaire();
                e.setIdcomm(rs.getInt(1));
                e.setContenucom(rs.getString(3));
                e.setDatecomm(rs.getDate(4));
                e.setName(rs.getString(5));
                e.setPub_id(face.getpubbyid(e.getIdpub()));


                Commantaire.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Commantaire;
    }


    @Override
    public Publication getpubbyid(int id) {
        Publication Publication = new Publication();
        try {
            String req = "SELECT * FROM publication WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                Publication.setUsername(rs.getString(4));
            }

        } catch (Exception e) {
            System.out.println("Failed to get user by id");
            e.printStackTrace();
        }
        return Publication;    }
}
