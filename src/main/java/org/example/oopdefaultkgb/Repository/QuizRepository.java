package org.example.oopdefaultkgb.Repository;


import org.example.oopdefaultkgb.Interface.Repository.IQuizRepository;

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


}
