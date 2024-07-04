package org.example.oopdefaultkgb.Repository;

import lombok.NonNull;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRepository extends  BaseRepository implements IFriendRepository {
    public FriendRepository(Connection connectionString) throws SQLException {
        super(connectionString);
    }
    @Override
    public List<Friend> getFriends(int userId) throws SQLException {
        var resultList = new ArrayList<Friend>();
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Friend WHERE SenderUserId = %d OR ReceiverUserId = %d and status = 'ACTIVE' ORDER BY InvitedAt DESC", userId, userId);
        ResultSet result = statement.executeQuery(query);
        if(!result.next()) return null;
        while(result.next())
            resultList.add(new Friend(
                    result.getInt(1),
                    result.getInt(2),
                    result.getInt(3),
                    result.getDate(4),
                    result.getString(5)
            ));
        return resultList;
    }

    @Override
    public boolean deleteFriends(int userId, int friendId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Friend SET Status = 'DELETED' WHERE ((SenderUserId = %d AND ReceiverUserId = %d) OR (SenderUserId = %d AND ReceiverUserId = %d))  AND Status = 'ACTIVE' ", userId, friendId,friendId,userId);
        return statement.execute(query);
    }

    @Override
    public boolean AcceptFriends(int userId, int friendId, Date InvitedAt) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Friend(SenderUserId,ReceiverUserId,InvitedAt,Status) vales(%d,%d,%s,'ACTIVE') ", friendId, userId, InvitedAt.toString());
        return statement.execute(query); //not null
    }




}
