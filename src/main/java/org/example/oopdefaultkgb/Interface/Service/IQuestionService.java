package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Question;

import java.sql.SQLException;

public interface IQuestionService {
    boolean addQuestion(int quizId, String question, int questionTypeId) throws SQLException;

    boolean deleteQuestion(int questionId) throws SQLException;

    boolean editQuestion(int questionId, String question) throws SQLException;

    Question getQuestion(int questionId) throws SQLException;
}
