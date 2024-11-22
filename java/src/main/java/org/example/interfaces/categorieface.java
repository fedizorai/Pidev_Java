package org.example.interfaces;
import org.example.models.CategorieEvent;

import java.sql.SQLException;
import java.util.List;

public interface categorieface<T> {
    void add(T t) throws SQLException, RuntimeException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<CategorieEvent> recupere ();
    CategorieEvent getcategoriebyid(int id_cate);

}