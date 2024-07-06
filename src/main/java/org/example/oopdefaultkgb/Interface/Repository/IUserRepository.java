package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.User;

import java.sql.SQLException;

public interface IUserRepository {
    boolean addUser(User user) throws SQLException;

    boolean addUser(String userName, String hashPassword, String role) throws SQLException;

    boolean updateStatus(int userId, String status);

    User getUser(int userId) throws SQLException;

    User getUser(String userName) throws SQLException;

    boolean deleteUser(int userId);
    boolean updateIUserFullName(int userId, String fullName);
}
