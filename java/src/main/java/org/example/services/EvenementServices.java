package org.example.services;

import org.example.models.Evenement;
import org.example.interfaces.CRUD;
import org.example.interfaces.categorieface;
import org.example.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementServices implements CRUD<Evenement> {

    Connection cnx= MyDatabase.getInstance().getCnx();


    @Override
    public void add(Evenement evenement) throws SQLException, RuntimeException {
        String req = "INSERT INTO `evenement`(`categorie_id`, `nom_event`, `max_places_event`, `date_event`, `lieu_event`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, evenement.getCategorie_id());
            st.setString(2, evenement.getNom_event());
            st.setInt(3, evenement.getMax_places_event());
            st.setDate(4, evenement.getDate_event());
            st.setString(5, evenement.getLieu_event());
            try {
                st.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("Event Added successfully!");
    }


    @Override
    public void update(Evenement evenement) throws SQLException {
        String req = "UPDATE evenement SET categorie_id=?, nom_event=?, max_places_event=?, date_event=?, lieu_event=? WHERE id=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, evenement.getCategorie_id());
        pre.setString(2, evenement.getNom_event());
        pre.setInt(3, evenement.getMax_places_event());
        pre.setDate(4, evenement.getDate_event());
        pre.setString(5, evenement.getLieu_event());
        pre.setInt(6, evenement.getId());

        pre.executeUpdate();
    }


    @Override
    public void delete(Evenement evenement) throws SQLException {
        String req = " delete from evenement where id=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1,evenement.getId());
        pre.executeUpdate();
    }

    @Override
    public List<Evenement> recupere() {
        List<Evenement> Evenement = new ArrayList<>();
       categorieface face= new CategorieEventService() ;

        try {

            String req = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setCategorie_id(rs.getInt(2));
                e.setCate(face.getcategoriebyid(e.getCategorie_id()));
                e.setNom_event(rs.getString(3));
                e.setMax_places_event(rs.getInt(4));

                e.setDate_event(rs.getDate(5));

                e.setLieu_event(rs.getString(6));


                Evenement.add(e);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return Evenement;

    }
}
