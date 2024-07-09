package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Answer;
import org.example.oopdefaultkgb.Interface.Repository.IAnswerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository  extends BaseRepository implements IAnswerRepository {
    public AnswerRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public List<Answer> getAllAnswers(int questionId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Answer WHERE QuestionId = %d ORDER BY Id", questionId);
        ResultSet result = statement.executeQuery(query);
        List<Answer> resultList = new ArrayList<Answer>();
        if(!result.next()) return null;
        while(result.next())
            resultList.add(new Answer(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getBoolean(4),
                    result.getString(5)
            ));
        return resultList;
    }
    @Override
    public boolean deleteAnswer(int answerId)throws SQLException{
        Statement statement = ConnectionString.createStatement();
        String query = String.format("UPDATE Answer SET Status = 'DELETED' WHERE AnswerId = %d  AND Status = 'ACTIVE' ",answerId);
        return statement.execute(query);
    }

    @Override
    public boolean addAnswer(int questionId, String answer, boolean isCorrect) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Answer(QuestionId,Answer,IsCorrect,status) values(%d,'%s',%b,'ACTIVE') ",questionId ,answer,isCorrect);
        return statement.execute(query); //not null
    }
}
