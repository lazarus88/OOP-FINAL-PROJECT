package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    User getProfile(String userName) throws SQLException;

    List<Friend> getFriends(int userId) throws SQLException;
    List<Achievement> getAchievements(int userId) throws SQLException;
}
