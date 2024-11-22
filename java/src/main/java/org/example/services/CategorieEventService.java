package org.example.services;

import org.example.models.CategorieEvent;
import org.example.interfaces.categorieface;
import org.example.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieEventService implements categorieface<CategorieEvent> {
    Connection cnx = MyDatabase.getInstance().getCnx();

    @Override
    public void add(CategorieEvent categorieEvent) throws SQLException, RuntimeException {
        String req = "INSERT INTO `categorie_event`(`nom`, `description_categorie_event`) VALUES ('" + categorieEvent.getNom() + "','" + categorieEvent.getDescription_categorie_event() + "')";
        try (Statement st = cnx.createStatement()) {
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("Category Added successfully!");
    }


    @Override
    public void update(CategorieEvent categorieEvent) throws SQLException {
        String req = "UPDATE `categorie_event` SET `nom`=?, `description_categorie_event`=? WHERE id=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, categorieEvent.getNom());
        pre.setString(2, categorieEvent.getDescription_categorie_event());
        pre.setInt(3, categorieEvent.getId());

        pre.executeUpdate();
    }


    @Override
    public void delete(CategorieEvent categorieEvent) throws SQLException {
        String req = " delete from categorie_event where id=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, categorieEvent.getId());
        pre.executeUpdate();
    }

    @Override
    public List<CategorieEvent> recupere() {
        List<CategorieEvent> CategorieEvent = new ArrayList<>();
        try {

            String req = "SELECT * FROM categorie_event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                CategorieEvent e = new CategorieEvent();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setDescription_categorie_event(rs.getString(3));



                CategorieEvent.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return CategorieEvent;

    }
    @Override

    public CategorieEvent getcategoriebyid(int id_cate) {

        CategorieEvent categorie = new CategorieEvent();
        try {
            String req = "SELECT * FROM categorie_event WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, id_cate);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                categorie.setNom(rs.getString(2));
            }

        } catch (Exception e) {
            System.out.println("Failed to get user by id");
            e.printStackTrace();
        }
        return categorie;
    }


}

