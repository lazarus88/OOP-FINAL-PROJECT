package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Mail;

import java.sql.SQLException;
import java.util.List;

public interface IMailRepository {
     boolean sendFriendRequest(int userIdFrom, int userIdTo, String userNameFrom) throws SQLException;
     boolean sendChallengeRequest(int userIdFrom, int userIdTo, int quizId);
     boolean sendNote(int userIdFrom, int userIdTo, String note);
     boolean acceptFriendRequest(int userId, int userIdFrom);
     boolean acceptChallengeRequest(int userId, int userIdFrom, int quizId);
     boolean rejectFriendRequest(int userId, int userIdFrom);
    boolean rejectChallengeRequest(int userId, int userIdFrom, int quizId);
    List<Mail> getFriendRequests(int userId);
     List<Mail> getChallengeRequests(int userId);
     List<Mail> getNotes(int userId);

}
