package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.Interface.Repository.IMailRepository;

import java.util.List;

public class MailRepository implements IMailRepository {

    @Override
    public boolean sendFriendRequest(int userIdFrom, int userIdTo) {
        return false;
    }

    @Override
    public boolean sendChallengeRequest(int userIdFrom, int userIdTo, int quizId) {
        return false;
    }

    @Override
    public boolean sendNote(int userIdFrom, int userIdTo, String note) {
        return false;
    }

    @Override
    public boolean acceptFriendRequest(int userId, int userIdFrom) {
        return false;
    }

    @Override
    public boolean acceptChallengeRequest(int userId, int userIdFrom, int quizId) {
        return false;
    }

    @Override
    public boolean rejectFriendRequest(int userId, int userIdFrom) {
        return false;
    }

    @Override
    public boolean rejectChallengeRequest(int userId, int userIdFrom, int quizId) {
        return false;
    }

    @Override
    public List<Mail> getFriendRequests(int userId) {
        return null;
    }

    @Override
    public List<Mail> getChallengeRequests(int userId) {
        return null;
    }

    @Override
    public List<Mail> getNotes(int userId) {
        return null;
    }
}
