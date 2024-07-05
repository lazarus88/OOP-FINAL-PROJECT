package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IMailRepository;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Repository.MailRepository;

import java.sql.SQLException;
import java.util.List;

public class MailService implements IMailService {
    private IMailRepository mailRepository;
    public MailService() throws SQLException {
        mailRepository = new MailRepository();
    }

    @Override
    public List<Mail> getMails(int userId, int MailTypeId) throws SQLException {
        return mailRepository.getMails(userId, MailTypeId);
    }

    @Override
    public boolean sendFriendRequest(int userIdFrom, int userIdTo) throws SQLException {
        return mailRepository.sendFriendRequest(userIdFrom, userIdTo);
    }

    @Override
    public boolean sendChallengeRequest(int userIdFrom, int userIdTo, String quizName) throws SQLException {
        return mailRepository.sendChallengeRequest(userIdFrom, userIdTo, quizName);
    }

    @Override
    public boolean sendNote(int userIdFrom, int userIdTo, String note) throws SQLException {
        return mailRepository.sendNote(userIdFrom, userIdTo,note);
    }

    @Override
    public boolean acceptFriendRequest(int userId, int userIdFrom) throws SQLException {
        return mailRepository.acceptFriendRequest(userId, userIdFrom);
    }

    @Override
    public boolean acceptChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException {
        return mailRepository.acceptChallengeRequest(userId,userIdFrom,quizName);
    }

    @Override
    public boolean rejectFriendRequest(int userId, int userIdFrom) throws SQLException {
        return mailRepository.rejectFriendRequest(userId,userIdFrom);
    }

    @Override
    public boolean rejectChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException {
        return mailRepository.rejectChallengeRequest(userId,userIdFrom,quizName);
    }
}
