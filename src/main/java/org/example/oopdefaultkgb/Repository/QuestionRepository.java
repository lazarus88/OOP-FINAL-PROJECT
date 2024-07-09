package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.Interface.Repository.IQuestionRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository  extends  BaseRepository implements IQuestionRepository {
    public QuestionRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public boolean addQuestion(int quizId, String question, int questionTypeId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Question(quizId,question,questionTypeId,status) values(%d,'%s',%d,'ACTIVE') ",quizId ,question,questionTypeId);
        return statement.execute(query); //not null
    }
    @Override
    public boolean deleteQuestion(int questionId) throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Question SET Status = 'DELETED' WHERE id = %d  AND Status = 'ACTIVE' ",questionId);
        return statement.execute(query); //not null
    }
    @Override
    public boolean editQuestion(int questionId, String question) throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Question SET Question = %s WHERE id = %d  AND Status = 'ACTIVE' ",question,questionId);
        return statement.execute(query); //not null
    }
    @Override
    public List<Question> getAllQuestions(int quizId) throws SQLException{
        var resultList = new ArrayList<Question>();
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Question WHERE  QuizId = %d ORDER BY id ",  quizId);
        ResultSet result = statement.executeQuery(query);

        while(result.next())
            resultList.add(new Question(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5)
            ));
        return resultList;
    }
    @Override
    public  boolean deleteAllQuestions(int quizID) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Question SET Status = 'DELETED' WHERE QuizId = %d  AND Status = 'ACTIVE' ",quizID);
        return statement.execute(query); //not null
    }
    @Override
    public Question getQuestion(int questionId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Question WHERE UserName = %s", questionId);
        ResultSet res = statement.executeQuery(query);
        if(!res.next()) return null;
        Question question = new Question(res.getInt(1), res.getInt(2),res.getString(3), res.getInt(4),
                res.getString(5));
        return question;
    }
    @Override
    public Question getQuestion(int quizId, String questionS) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Question WHERE (quizId = %d and question = '%s')", quizId,questionS);
        ResultSet res = statement.executeQuery(query);
        if(!res.next()) return null;
        Question question = new Question(res.getInt(1), res.getInt(2),res.getString(3), res.getInt(4),
                res.getString(5));
        return question;
    }
}
