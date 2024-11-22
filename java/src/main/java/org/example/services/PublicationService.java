package org.example.services;

import org.example.interfaces.CRUD;
import org.example.interfaces.categorieface;
import org.example.models.Evenement;
import org.example.models.Publication;
import org.example.utils.MyDatabase;

import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PublicationService implements CRUD<Publication> {
    Connection cnx = MyDatabase.getInstance().getCnx();

    @Override
    public void add(Publication publication) throws SQLException, RuntimeException {
        String req = "INSERT INTO `publication`(`contenupub`, `datepub`, `username`, `userid`, `likes`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setString(1,publication.getContenu() );
            st.setDate(2, publication.getDatepub());

            st.setString(3,publication.getUsername());
            st.setInt(4, publication.getUserid());
            st.setInt(5, publication.getLike());
            try {
                st.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("publication Added successfully!");

    }

    @Override
    public void update(Publication publication) throws SQLException {
        String req = "UPDATE `publication` SET `contenupub`=?,`datepub`=?,`username`=?,`userid`=?,`likes`=? WHERE `id`=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1,publication.getContenu() );
        pre.setDate(2, publication.getDatepub());

        pre.setString(3,publication.getUsername());
        pre.setInt(4, publication.getUserid());
        pre.setInt(5, publication.getLike());
        pre.setInt(6, publication.getId());

        pre.executeUpdate();
    }

    @Override
    public void delete(Publication publication) throws SQLException {
        String req = " delete from publication where id=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setInt(1, publication.getId());
        pre.executeUpdate();
    }

    @Override
    public List<Publication> recupere() {
        List<Publication> publications = new ArrayList<>();

        try {
            String req = "SELECT * FROM publication";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Publication e = new Publication();
                e.setId(rs.getInt(1));
                e.setContenu(rs.getString(2));

                e.setDatepub(rs.getDate(3));
                e.setUsername(rs.getString(4));

                e.setUserid(rs.getInt(5));
                e.setLike(rs.getInt(6));
                publications.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return publications;
    }

}
