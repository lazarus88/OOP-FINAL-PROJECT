package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.Interface.Repository.IMailRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;


public class MailRepository extends BaseRepository implements IMailRepository {

    private final String CHALLENGE_REQUEST_SEND = " თქვენ გაუგზავნეთ {%s} ქვიზის Challenge {%s} - ს";
    private final String CHALLENGE_REQUEST_RECEIVE = " {%s} - მა გამომიგზავნათ {%s} ქვიზის Challenge";
    private final int FRIEND_REQUEST_ID = 1;
    private final int CHALLENGE_REQUEST_ID = 2;
    private final int NOTE_ID = 3;

    private final String SENT = "sent";


    public MailRepository() throws SQLException {
        super();
    }

    @Override
    public boolean sendFriendRequest(int userIdFrom, int userIdTo) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId MailTypeId, CreatedAt, status" +
                "VALUES(%d, %d, %s, %d, %s, %s", userIdFrom, userIdTo, FRIEND_REQUEST_ID, LocalDateTime.now(), SENT);
        return statement.execute(query);
    }

    @Override
    public boolean sendChallengeRequest(int userIdFrom, int userIdTo, String quizName) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId, message, MailTypeId, CreatedAt, status" +
                "VALUES(%d, %d, %s, %d, %s, %s", userIdFrom, userIdTo, quizName, CHALLENGE_REQUEST_ID, LocalDateTime.now(), SENT);
        return statement.execute(query);
    }

    @Override
    public boolean sendNote(int userIdFrom, int userIdTo, String note) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId, message, MailTypeId, CreatedAt, status" +
                "VALUES(%d, %d, %s, %d, %s, %s", userIdFrom, userIdTo, note, NOTE_ID, LocalDateTime.now(), SENT);
        return statement.execute(query);
    }

    @Override
    public boolean acceptFriendRequest(int userId, int userIdFrom) throws SQLException {
        Statement statement =ConnectionString.createStatement();
    String query = String.format("UPDATE Mail SET Status = 'ACCEPTED' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d)", userIdFrom, userId, FRIEND_REQUEST_ID);
        return statement.execute(query);
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
