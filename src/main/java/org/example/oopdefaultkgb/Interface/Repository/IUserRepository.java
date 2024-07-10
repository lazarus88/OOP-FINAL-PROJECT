package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IUserRepository {
    boolean addUser(User user) throws SQLException;


    boolean addUser(String userName, String hashPassword, String fullName, String role) throws SQLException;

    boolean updateStatus(int userId, String status) throws SQLException;

    User getUser(int userId) throws SQLException;

    User getUser(String userName) throws SQLException;


    boolean promoteUser(int userId) throws SQLException;

    boolean updateIUserFullName(int userId, String fullName);

    List<User> getAllActiveUser() throws SQLException;
}
