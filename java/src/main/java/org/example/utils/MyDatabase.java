package org.example.utils;
            import java.sql.Connection;
            import java.sql.DriverManager;
            import java.sql.SQLException;

public class MyDatabase {
    static final String URL = "jdbc:mysql://localhost:3306/culturevibes";
    static final String USER = "root";
    static final String PASSWORD = "";

    //var
    private Connection cnx;
    //1
    static MyDatabase instance;

    //const
    //2
    public MyDatabase() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Connection getCnx() {
        return cnx;
    }

    //3
    public static MyDatabase getInstance() {
        if (instance == null)
            instance = new MyDatabase();
        System.out.println("Connected!");

        return instance;
    }

}
