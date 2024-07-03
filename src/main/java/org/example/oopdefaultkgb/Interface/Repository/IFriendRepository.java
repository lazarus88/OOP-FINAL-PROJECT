package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Friend;

import java.sql.SQLException;
import java.util.List;

public interface IFriendRepository {
    List<Friend> getFriends(int userId) throws SQLException;
}
