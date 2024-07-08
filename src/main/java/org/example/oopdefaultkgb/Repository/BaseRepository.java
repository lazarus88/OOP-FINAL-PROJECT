package org.example.oopdefaultkgb.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    Connection ConnectionString;

    public BaseRepository() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.ConnectionString = DriverManager.getConnection("jdbc:mysql://localhost:3306/kgb", "root", "ROOTpASSWORD1!");
    }

    public Connection getBaseRepository(){

        return ConnectionString;
    }
}
