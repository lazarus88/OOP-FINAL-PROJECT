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
    private  final String FriendRequest = "თქვენ გამოგიგზავნათ მეგობრობის მოთხოვნა {%s}";
    private final int FRIEND_REQUEST_ID = 1;
    private final String SEND_REQUEST = "sent";


    public MailRepository() throws SQLException {
        super();
    }

    @Override
    public boolean sendFriendRequest(int userIdFrom, int userIdTo, String userNameFrom) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String message = String.format(FriendRequest, userNameFrom);
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId, message, MailTypeId, CreatedAt, status" +
                "VALUES(%d, %d, %s, %d, %s, %s", userIdFrom, userIdTo, message, FRIEND_REQUEST_ID, LocalDateTime.now(), SEND_REQUEST );
        return  statement.execute(query);
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
