package org.example.oopdefaultkgb.Repository;


import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.Interface.Repository.IQuizRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public  class QuizRepository extends  BaseRepository implements IQuizRepository {
    public QuizRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isOneVsMultiple,boolean isImmediate, boolean isPracticeEnable,int quizTypeId) throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Quiz(creatorUserId,QuizName,isRandom,isOneVsMultiple,isImmediate,isPracticeEnable,quizTypeId,status,CreatedAt) values(%d,'%S',%b,%b,%b,%b,%d,'ACTIVE','%s') ",creatorUserId ,quizName,isRandom,isOneVsMultiple,isImmediate,isPracticeEnable,quizTypeId,LocalDateTime.now());
        return statement.execute(query); //not null
    }
    @Override
    public List<Quiz> getPopularQuizList() throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * from Quiz  ORDER BY takenCount Desc");
        ResultSet result = statement.executeQuery(query);
        List<Quiz> quizList = new ArrayList<>();
        while (result.next()) {
            quizList.add(new Quiz(result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getBoolean(4),
                    result.getBoolean(5),
                    result.getBoolean(6),
                    result.getBoolean(7),
                    result.getInt(8),
                    result.getString(9),
                    result.getInt(10),
                    result.getObject(11, LocalDateTime.class)));
        }
        return quizList;
    }

    @Override
    public Quiz getQuizById(int quizId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * from Quiz  WHERE id = %d", quizId);
        ResultSet result = statement.executeQuery(query);
        if(result.next()) {
            return new Quiz(result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getBoolean(4),
                    result.getBoolean(5),
                    result.getBoolean(6),
                    result.getBoolean(7),
                    result.getInt(8),
                    result.getString(9),
                    result.getInt(10),
                    result.getObject(11, LocalDateTime.class));
        }
        return null;
    }

    @Override
    public List<Quiz> getAllActiveQuizs() throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * from Quiz  WHERE Status = 'ACTIVE' ORDER BY CreatedAt DESC");
        ResultSet result = statement.executeQuery(query);
        List<Quiz> quizList = new ArrayList<>();
        while (result.next()) {
            quizList.add(new Quiz(result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getBoolean(4),
                    result.getBoolean(5),
                    result.getBoolean(6),
                    result.getBoolean(7),
                    result.getInt(8),
                    result.getString(9),
                    result.getInt(10),
                    result.getObject(11, LocalDateTime.class)));
        }
        return quizList;
    }

    @Override
    public boolean deleteQuiz(int quizId) throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Quiz SET Status = 'DELETED' WHERE id = %d  AND Status = 'ACTIVE' ",quizId);
        return statement.execute(query); //not null
    }
    @Override
    public Quiz getQuiz(String quizName, int creatorUserId) throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * from Quiz  WHERE (QuizName = '%s' and CreatorUserId = %d)",quizName,creatorUserId);
        ResultSet result = statement.executeQuery(query);
        System.out.println("esaa");
        if(result.next()) {
            return new Quiz(result.getInt(1),
                            result.getString(2),
                            result.getInt(3),
                            result.getBoolean(4),
                            result.getBoolean(5),
                            result.getBoolean(6),
                            result.getBoolean(7),
                            result.getInt(8),
                            result.getString(9),
                            result.getInt(10),
                            result.getObject(11, LocalDateTime.class));
        }
        return null;
    }
    @Override
  public boolean updateQuizCounter(int quizId) throws SQLException {
      Statement statement = ConnectionString.createStatement();
      int updatedCounter = getQuizById(quizId).takenCount + 1;
      String query = String.format("UPDATE Quiz SET takenCount = %d WHERE id = %d  AND Status = 'ACTIVE' ",updatedCounter,quizId);
      return statement.execute(query);
  }

}
