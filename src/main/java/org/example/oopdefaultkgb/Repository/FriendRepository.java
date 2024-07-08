package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FriendRepository extends  BaseRepository implements IFriendRepository {
    public FriendRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public List<Friend> getFriends(int userId) throws SQLException {
        var resultList = new ArrayList<Friend>();
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Friend WHERE SenderUserId = %d OR ReceiverUserId = %d  ORDER BY InvitedAt DESC", userId, userId);
        ResultSet result = statement.executeQuery(query);
        while(result.next())
            resultList.add(new Friend(
                    result.getInt(1),
                    result.getInt(2),
                    result.getInt(3),
                    result.getObject(4, LocalDateTime.class),
                    result.getString(5)
            ));
        return resultList;
    }
    @Override
    public Friend getFriend(int userId, int friendUserId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Friend WHERE ((SenderUserId = %d AND ReceiverUserId = %d) OR (SenderUserId = %d AND ReceiverUserId = %d)) ", userId, friendUserId, friendUserId, userId);
        ResultSet result = statement.executeQuery(query);
           if(result.next()) {
               return new Friend(
                       result.getInt(1),
                       result.getInt(2),
                       result.getInt(3),
                       result.getObject(4, LocalDateTime.class),
                       result.getString(5));
           }
           return null;
    }
    @Override
    public boolean deleteFriends(int userId, int friendId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Friend SET Status = 'DELETED' WHERE ((SenderUserId = %d AND ReceiverUserId = %d) OR (SenderUserId = %d AND ReceiverUserId = %d))  AND Status = 'ACTIVE' ", userId, friendId,friendId,userId);
        return statement.execute(query);
    }

    @Override
    public boolean AcceptFriends(int userId, int friendId, LocalDateTime InvitedAt) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Friend(SenderUserId,ReceiverUserId,InvitedAt,Status) vales(%d,%d,%s,'ACTIVE') ", friendId, userId, InvitedAt.toString());
        return statement.execute(query); //not null
    }




}
