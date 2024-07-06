package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IUserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository extends BaseRepository implements IUserRepository {
    public UserRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public boolean addUser(User user) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO User(FullName, UserName, hashPassword, CreatedAt, Role)" +
                "VALUES(%s, %s, %s, %s, %s, %s)", user.fullName, user.userName, user.hashPassword, user.createdAt.toString(), user.createdAt, user.Role);
        return statement.execute(query);

    }

    @Override
    public boolean updateStatus(int userId, String status) {
        return false;
    }

    @Override
    public User getUser(int userId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM User WHERE Id = %d", userId);
        ResultSet res = statement.executeQuery(query);
        if(!res.next()) return null;
        User user = new User(res.getInt(1), res.getString(2),res.getString(3), res.getString(4),
                res.getDate(5), res.getDate(6), res.getInt(7), res.getString(8));
        return user;
    }
    @Override
    public User getUser(String userName) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM User WHERE UserName = %s", userName);
        ResultSet res = statement.executeQuery(query);
        if(!res.next()) return null;
        User user = new User(res.getInt(1), res.getString(2),res.getString(3), res.getString(4),
                res.getDate(5), res.getDate(6), res.getInt(7), res.getString(8));
        return user;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }

    @Override
    public boolean updateIUserFullName(int userId, String fullName) {
        return false;
    }
}
