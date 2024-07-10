package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Answer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IAnswerRepository {


    List<Answer> getAllAnswers(int questionId) throws SQLException;

    boolean deleteAnswer(int answerId)throws SQLException;


    boolean addAnswer(int questionId, String answer, boolean isCorrect) throws SQLException;
    Answer getCorrectAnswer(int questionId) throws SQLException;
}
