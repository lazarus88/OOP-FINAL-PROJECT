package org.example.oopdefaultkgb.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    Connection ConnectionString;

    public BaseRepository() throws SQLException {
        this.ConnectionString = DriverManager.getConnection("jdbc:mysql://localhost:3306/kgb", "root", "rootPassword1!");
    }

    public Connection getBaseRepository(){
        return ConnectionString;
    }
}
