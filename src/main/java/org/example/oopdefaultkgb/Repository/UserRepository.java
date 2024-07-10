package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IUserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends BaseRepository implements IUserRepository {
    public UserRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public boolean addUser(User user) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO User(FullName, UserName, hashPassword,Status, CreatedAt, Role)" +
                "VALUES(%s, %s, %s, %s,'ACTIVE', SYSDATE(), %s)", user.fullName, user.userName, user.hashPassword, user.Role);
        return statement.execute(query);

    }
    @Override
    public boolean addUser(String userName, String hashPassword,String fullName ,String role) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO User(FullName, UserName, hashPassword,Status, Role)" +
                "VALUES('%s', '%s', '%s', 'ACTIVE', '%s')", fullName, userName, hashPassword, role);
        statement.execute(query);
        return true;

    }

    @Override
    public boolean updateStatus(int userId, String status) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE User SET Status = '%s' WHERE Id = %d", status, userId);
        return statement.execute(query);
    }

    @Override
    public User getUser(int userId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM User WHERE Id = %d", userId);
        ResultSet res = statement.executeQuery(query);
        if(!res.next()) return null;
        User user = new User(res.getInt(1), res.getString(2),res.getString(3), res.getString(4),
                res.getString(5),res.getObject(6, LocalDateTime.class), res.getObject(7, LocalDateTime.class), res.getLong(8), res.getString(9));
        return user;
    }
    @Override
    public User getUser(String userName) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM User WHERE UserName = '%s'", userName);
        ResultSet res = statement.executeQuery(query);
        while(res.next()) {
            return new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
                    res.getString(5), res.getObject(6, LocalDateTime.class), res.getObject(7, LocalDateTime.class), res.getLong(8), res.getString(9));

        }
        return null;
    }

    @Override
    public boolean promoteUser(int userId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE User SET Role = 'ADMIN' WHERE Id = %d", userId);
        return statement.execute(query);
    }

    @Override
    public boolean updateIUserFullName(int userId, String fullName) {
        return false;
    }

    @Override
    public List<User> getAllActiveUser() throws SQLException {
        Statement statement = ConnectionString.createStatement();
        List<User> answer = new ArrayList<>();
        String query = String.format("SELECT * FROM User WHERE Status = 'ACTIVE'");
        ResultSet res = statement.executeQuery(query);
        while(res.next())
            answer.add(new User(res.getInt(1), res.getString(2),res.getString(3), res.getString(4),
                res.getString(5),res.getObject(6, LocalDateTime.class), res.getObject(7, LocalDateTime.class), res.getLong(8), res.getString(9)));
        return answer;
    }
}
