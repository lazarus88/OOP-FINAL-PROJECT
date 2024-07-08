package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.User;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IFriendService {
    List<Friend> getFriends(int userId) throws SQLException;

    boolean deleteFriends(int userId, int friendId) throws SQLException;

    boolean AcceptFriends(int userId, int friendId) throws SQLException;

    boolean checkIfUserIsFriend(int userId, int friendId) throws SQLException;

    List<User> getFriendUsers(int userId) throws SQLException;
}
