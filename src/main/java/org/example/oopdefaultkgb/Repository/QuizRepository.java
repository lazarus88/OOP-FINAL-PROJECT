package org.example.oopdefaultkgb.Repository;


import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.Interface.Repository.IQuizRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public  class QuizRepository extends  BaseRepository implements IQuizRepository {
    public QuizRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isOneVsMultiple,boolean isImmediate, boolean isPracticeEnable,int quizTypeId) throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Quiz(creatorUserId,QuizName,isRandom,isOneVsMultiple,isImmediate,isPracticeEnable,quizTypeId,status) values(%d,'%S',%b,%b,%b,%b,%d,'ACTIVE') ",creatorUserId ,quizName,isRandom,isOneVsMultiple,isImmediate,isPracticeEnable,quizTypeId );
        return statement.execute(query); //not null
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
        if(result.next()) {
            return new Quiz(result.getInt(1),
                            result.getString(2),
                            result.getInt(3),
                            result.getBoolean(4),
                            result.getBoolean(5),
                            result.getBoolean(6),
                            result.getBoolean(7),
                            result.getString(8));
        }
        return null;
    }


}
