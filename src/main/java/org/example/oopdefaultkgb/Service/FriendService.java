package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
import org.example.oopdefaultkgb.Interface.Repository.IUserRepository;
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Repository.FriendRepository;
import org.example.oopdefaultkgb.Repository.UserRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FriendService implements IFriendService {
    public IFriendRepository friendRepository;
    public IUserRepository userRepository;
    public FriendService( ) throws SQLException, ClassNotFoundException {
        friendRepository = new FriendRepository();
        userRepository = new UserRepository();
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
    public boolean AcceptFriends(int userId, int friendId) throws SQLException {
        return friendRepository.AcceptFriends(userId,friendId);
    }
    @Override
    public boolean checkIfUserIsFriend(int userId, int friendId) throws SQLException {
        return friendRepository.getFriend(userId, friendId) != null ? true : false;
    }
    @Override
    public List<User> getFriendUsers(int userId) throws SQLException {
        List<Friend> friendList = friendRepository.getFriends(userId);
        List<User> userFriendList = new ArrayList<>();
        for (Friend friend : friendList) {
            int friendUserId = friend.receiverUserId == userId ? friend.senderUserId : friend.receiverUserId;
            userFriendList.add(userRepository.getUser(friendUserId));
        }
        return userFriendList;
    }
}
