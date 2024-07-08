package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Friend;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface IFriendRepository {
    List<Friend> getFriends(int userId) throws SQLException;


    Friend getFriend(int userId, int friendUserId) throws SQLException;

    boolean deleteFriends(int userId, int friendId) throws SQLException;

    boolean AcceptFriends(int userId, int friendId) throws SQLException;
}
