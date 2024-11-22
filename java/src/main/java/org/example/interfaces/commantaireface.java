package org.example.interfaces;

import org.example.models.CategorieEvent;
import org.example.models.Commantaire;
import org.example.models.Publication;

import java.sql.SQLException;
import java.util.List;

public interface commantaireface<T> {
    void add(T t) throws SQLException, RuntimeException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<Commantaire > recupere ();
    Publication getpubbyid(int id);

}