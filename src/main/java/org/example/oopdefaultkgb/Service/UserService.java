package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IAchievementRepository;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
import org.example.oopdefaultkgb.Interface.Repository.IUserRepository;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Repository.FriendRepository;
import org.example.oopdefaultkgb.Repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    public IUserRepository userRepository;
    public IFriendRepository friendRepository;
    public IAchievementRepository achievementRepository;
    public UserService( ) throws SQLException, ClassNotFoundException {
        userRepository = new UserRepository(new DatabaseConnection().getConnection());
        friendRepository = new FriendRepository(new DatabaseConnection().getConnection());
    }
    @Override
    public User getProfile(String userName) throws SQLException {
        return userRepository.getUser(userName);
    }

    @Override
    public List<Friend> getFriends(int userId) throws SQLException {
        return friendRepository.getFriends(userId);
    }

    @Override
    public List<Achievement> getAchievements(int userId) throws SQLException {
        return achievementRepository.getAchievements(userId);
    }

    @Override
    public List<HistoryQuiz> getHistories(int userId) {
        return List.of();
    }


}
