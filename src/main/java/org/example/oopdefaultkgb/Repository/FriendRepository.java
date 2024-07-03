package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String query = String.format("SELECT * FROM Friend WHERE SenderUserId = %d OR ReceiverUserId = %d ORDER BY InvitedAt DESC", userId, userId);
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


}
