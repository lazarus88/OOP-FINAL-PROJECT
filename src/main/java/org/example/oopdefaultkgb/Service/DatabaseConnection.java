package org.example.oopdefaultkgb.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final Connection conn;

    public DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "password");
    }

    public Connection getConnection(){
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection dbc = new DatabaseConnection();
    }
}
