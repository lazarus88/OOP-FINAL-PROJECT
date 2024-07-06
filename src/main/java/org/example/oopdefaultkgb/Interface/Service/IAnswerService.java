package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Answer;

import java.sql.SQLException;
import java.util.List;

public interface IAnswerService {
    List<Answer> getAllAnswers(int questionId) throws SQLException;

    boolean deleteAnswer(int answerId)throws SQLException;

    boolean addAnswer(int questionId, String answer, boolean isCorrect) throws SQLException;
}
