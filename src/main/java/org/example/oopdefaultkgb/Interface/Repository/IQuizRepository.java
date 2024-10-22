package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Quiz;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public interface IQuizRepository {


    boolean deleteQuiz(int quizId) throws SQLException;

    boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isOneVsMultiple, boolean isImmediate, boolean isPracticeEnable, int quizTypeId) throws SQLException;


    public Quiz getQuiz(String quizName, int creatorUserId) throws SQLException;

    List<Quiz> getPopularQuizList() throws SQLException;

    Quiz getQuizById(int quizId) throws SQLException;

    List<Quiz> getAllActiveQuizs() throws SQLException;

    boolean updateQuizCounter(int quizId) throws SQLException;
}
