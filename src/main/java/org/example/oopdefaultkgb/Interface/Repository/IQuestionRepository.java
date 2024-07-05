package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Question;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IQuestionRepository {
    boolean addQuestion(int quizId, String question, int questionTypeId) throws SQLException;

    boolean deleteQuestion(int questionId) throws SQLException;

    boolean editQuestion(int questionId, String question) throws SQLException;

    ArrayList<Question> getAllQuestions(int quizID) throws SQLException;

    boolean deleteAllQuestions(int quizID) throws SQLException;

    Question getQuestion(int questionId) throws SQLException;
}
