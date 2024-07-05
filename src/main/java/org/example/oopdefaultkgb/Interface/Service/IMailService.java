package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Mail;

import java.sql.SQLException;
import java.util.List;

public interface IMailService {
    List<Mail> getMails(int userId, int MailTypeId) throws SQLException;
    boolean sendFriendRequest(int userIdFrom, int userIdTo) throws SQLException;
    boolean sendChallengeRequest(int userIdFrom, int userIdTo, String quizName) throws SQLException;
    boolean sendNote(int userIdFrom, int userIdTo, String note) throws SQLException;
    boolean acceptFriendRequest(int userId, int userIdFrom) throws SQLException;
    boolean acceptChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException;
    boolean rejectFriendRequest(int userId, int userIdFrom) throws SQLException;
    boolean rejectChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException;
}
