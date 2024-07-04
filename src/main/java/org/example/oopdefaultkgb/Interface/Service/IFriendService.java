package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Friend;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IFriendService {
    List<Friend> getFriends(int userId) throws SQLException;

    boolean deleteFriends(int userId, int friendId) throws SQLException;

    boolean AcceptFriends(int userId, int friendId, Date InvitedAt) throws SQLException;
}
