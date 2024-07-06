package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    User getProfileById(int userId) throws SQLException;

    User getProfile(String userName) throws SQLException;

    List<Friend> getFriends(int userId) throws SQLException;
    List<Achievement> getAchievements(int userId) throws SQLException;

    List<HistoryQuiz> getHistories(int userId);

    boolean checkPassword(String userName, String password);

    boolean addUser(User user) throws SQLException;

    boolean addUser(String userName, String hashPassword, String role) throws SQLException;
}
