package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IMailRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MailRepository extends BaseRepository implements IMailRepository {
    private final int FRIEND_REQUEST_ID = 1;
    private final int CHALLENGE_REQUEST_ID = 2;
    private final int NOTE_ID = 3;

    private final String SENT = "SENT";
    private final String ACCEPTED = "ACCEPTED";
    private final String REJECTED = "REJECTED";


    public MailRepository() throws SQLException, ClassNotFoundException {
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
    String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = %s)", ACCEPTED,userIdFrom, userId, FRIEND_REQUEST_ID, SENT);
        return statement.execute(query);
}

    @Override
    public boolean acceptChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException {
        Statement statement =ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = %s AND message = %s)", ACCEPTED, userIdFrom, userId, CHALLENGE_REQUEST_ID, SENT, quizName);
        return statement.execute(query);
    }

    @Override
    public boolean rejectFriendRequest(int userId, int userIdFrom) throws SQLException {
        Statement statement =ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = %s)", REJECTED,userIdFrom, userId, FRIEND_REQUEST_ID, SENT);
        return statement.execute(query);
    }

    @Override
    public boolean rejectChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException {
        Statement statement =ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = %s AND message = %s)",REJECTED, userIdFrom, userId, CHALLENGE_REQUEST_ID, SENT, quizName);
        return statement.execute(query);
    }

    @Override
    public List<Mail> getMails(int userId, int mailTypeId) throws SQLException {
        var MailList = new ArrayList<Mail>();
        Statement statement =ConnectionString.createStatement();
        String query;
        if(mailTypeId == 0)
            query = String.format("SELECT * FROM Mail WHERE (ReceiverUserId = %d AND Status = '%s')", userId, SENT);
        else
            query = String.format("SELECT * FROM Mail WHERE (ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s')", userId, mailTypeId, SENT);
        ResultSet res = statement.executeQuery(query);
        while(res.next())
              MailList.add(new Mail(
                      res.getInt(1), res.getInt(2),
                      res.getInt(3), res.getString(4),
                      res.getInt(5), res.getObject(6, LocalDateTime.class),
                      res.getString(7)));
        return MailList;
    }

}
