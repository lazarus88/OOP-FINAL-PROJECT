package org.example.oopdefaultkgb.Repository;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseRepository {
    Connection ConnectionString;

    public BaseRepository(Connection connectionString) throws SQLException {
        ConnectionString = ConnectionString;
    }
}
