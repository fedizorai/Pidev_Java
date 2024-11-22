package org.example.interfaces;
import java.sql.SQLException;
import java.util.List;

public interface CRUD <T>{
    void add(T t) throws SQLException, RuntimeException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    List<T> recupere ();
}