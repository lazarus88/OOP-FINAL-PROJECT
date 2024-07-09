package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.Interface.Repository.IMailRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MailRepository extends BaseRepository implements IMailRepository {
    private final int FRIEND_REQUEST_ID = 0;
    private final int CHALLENGE_REQUEST_ID = 1;
    private final int NOTE_ID = 2;

    private final String SENT = "SENT";
    private final String ACCEPTED = "ACCEPTED";
    private final String REJECTED = "REJECTED";
    private final String CANCELLED = "CANCELLED";


    public MailRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public boolean sendFriendRequest(int userIdFrom, int userIdTo) throws SQLException {
        Statement statement = ConnectionString.createStatement();
//        String testQuery = String.format("SELECT * FROM Mail WHERE(SenderUserId = %d AND ReceiverUserId = %d AND status = '%s')",
//                userIdFrom, userIdTo, SENT);
//        ResultSet res = statement.executeQuery(testQuery);
//        if(res.next())
//            return false;
//        System.out.println("im here");
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId, MailTypeId, CreatedAt, status)" +
                "VALUES(%d, %d, %d, '%s', '%s')", userIdFrom, userIdTo, FRIEND_REQUEST_ID, LocalDateTime.now(), SENT);
        return statement.execute(query);
    }

    @Override
    public boolean sendChallengeRequest(int userIdFrom, int userIdTo, String quizName) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId, message, MailTypeId, CreatedAt, status)" +
                "VALUES(%d, %d, '%s', %d, '%s', '%s')", userIdFrom, userIdTo, quizName, CHALLENGE_REQUEST_ID, LocalDateTime.now(), SENT);
        return statement.execute(query);
    }

    @Override
    public boolean sendNote(int userIdFrom, int userIdTo, String note) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("INSERT INTO  Mail(SenderUserId, ReceiverUserId, message, MailTypeId, CreatedAt, status)" +
                "VALUES(%d, %d, '%s', %d, '%s', '%s')", userIdFrom, userIdTo, note, NOTE_ID, LocalDateTime.now(), SENT);
        return statement.execute(query);
    }

    @Override
    public boolean acceptFriendRequest(int userId, int userIdFrom) throws SQLException {
        Statement statement =ConnectionString.createStatement();
    String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s')", ACCEPTED,userIdFrom, userId, FRIEND_REQUEST_ID, SENT);
        return statement.execute(query);
}

    @Override
    public boolean acceptChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException {
        Statement statement =ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s' AND message = '%s')", ACCEPTED, userIdFrom, userId, CHALLENGE_REQUEST_ID, SENT, quizName);
        return statement.execute(query);
    }

    @Override
    public boolean rejectFriendRequest(int userId, int userIdFrom) throws SQLException {
        Statement statement =ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s')", REJECTED,userIdFrom, userId, FRIEND_REQUEST_ID, SENT);
        return statement.execute(query);
    }

    @Override
    public boolean rejectChallengeRequest(int userId, int userIdFrom, String quizName) throws SQLException {
        Statement statement =ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s' AND message = '%s')",REJECTED, userIdFrom, userId, CHALLENGE_REQUEST_ID, SENT, quizName);
        return statement.execute(query);
    }

    @Override
    public List<Mail> getMails(int userId, int mailTypeId) throws SQLException {
        var MailList = new ArrayList<Mail>();
        Statement statement =ConnectionString.createStatement();
        String query;
        if(mailTypeId == -1){
            query = String.format("SELECT * FROM Mail WHERE (ReceiverUserId = %d AND Status = '%s')  ORDER BY CreatedAt DESC", userId, SENT);
        }
         else query = String.format("SELECT * FROM Mail WHERE (ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s')  ORDER BY CreatedAt DESC", userId, mailTypeId, SENT);
        ResultSet res = statement.executeQuery(query);
        while(res.next())
              MailList.add(new Mail(
                      res.getInt(1), res.getInt(2),
                      res.getInt(3), res.getString(4),
                      res.getInt(5), res.getObject(6, LocalDateTime.class),
                      res.getString(7)));
        return MailList;
    }

    @Override
    public String getFriendRequestStatus(int userIdFrom, int userIdTo) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Mail WHERE(SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d) ORDER BY CreatedAt DESC", userIdFrom, userIdTo, FRIEND_REQUEST_ID );
        ResultSet res = statement.executeQuery(query);
        if(res.next())
            return res.getString(7);
        return "";
    }

    @Override
    public boolean receivedFriendRequest(int userIdFrom, int userId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Mail WHERE(SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s')", userIdFrom, userId, FRIEND_REQUEST_ID, SENT );
        ResultSet res = statement.executeQuery(query);
        if(res.next()){
            System.out.println("received friend request");
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelFriendRequest(int userIdFrom, int userIdTo) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s')",CANCELLED, userIdFrom, userIdTo, FRIEND_REQUEST_ID, SENT);
        return statement.execute(query);
    }

    @Override
    public boolean cancelChallengeRequest(int userIdFrom, int userIdTo, String quizName) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Mail SET Status = '%s' WHERE (SenderUserId = %d AND ReceiverUserId = %d AND MailTypeId = %d AND Status = '%s' AND Message = '%s')",CANCELLED, userIdFrom, userIdTo, CHALLENGE_REQUEST_ID, SENT, quizName);
        return statement.execute(query);
    }

}


