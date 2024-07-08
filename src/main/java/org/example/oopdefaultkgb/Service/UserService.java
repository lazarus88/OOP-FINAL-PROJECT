package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IAchievementRepository;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
import org.example.oopdefaultkgb.Interface.Repository.IUserRepository;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Repository.AchievementRepository;
import org.example.oopdefaultkgb.Repository.FriendRepository;
import org.example.oopdefaultkgb.Repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    public IUserRepository userRepository;
    public IFriendRepository friendRepository;
    public IAchievementRepository achievementRepository;

    public UserService() throws SQLException, ClassNotFoundException {
        userRepository = new UserRepository();
        friendRepository = new FriendRepository();
        achievementRepository = new AchievementRepository();
    }
    @Override
    public User getProfileById(int userId) throws SQLException {
        return userRepository.getUser(userId);
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

    @Override
    public boolean checkPassword(String userName, String password) {
        try {
            User curUser = userRepository.getUser(userName);
            return curUser.getHashPassword().equals(password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean addUser(User user) throws SQLException {
        return userRepository.addUser(user);
    }

    @Override
    public boolean addUser(String userName, String hashPassword, String role) throws SQLException {
        return userRepository.addUser(userName, hashPassword, role);
    }
}
