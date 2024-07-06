package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Repository.FriendRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class FriendService implements IFriendService {
    public IFriendRepository friendRepository;

    public FriendService( ) throws SQLException, ClassNotFoundException {
        friendRepository = new FriendRepository();
    }

    @Override
    public List<Friend> getFriends(int userId) throws SQLException {
        return friendRepository.getFriends(userId);
    }

    @Override
    public boolean deleteFriends(int userId, int friendId) throws SQLException {
        return friendRepository.deleteFriends(userId,friendId);
    }

    @Override
    public boolean AcceptFriends(int userId, int friendId, LocalDateTime InvitedAt) throws SQLException {
        return friendRepository.AcceptFriends(userId,friendId,InvitedAt);
    }
}
