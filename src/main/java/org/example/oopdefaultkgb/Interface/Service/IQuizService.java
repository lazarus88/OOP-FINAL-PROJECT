package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.EntityDTO.Quiz;

import java.sql.SQLException;
import java.util.List;

public interface IQuizService  {

    boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isOneVsMultiple, boolean isImmediate, boolean isPracticeEnable, int quizTypeId) throws SQLException;

    boolean deleteQuiz(int quizId) throws SQLException;

    List<Question> getAllQuestions(int quizID) throws SQLException;

    boolean deleteAllQuestions(int quizID)  throws SQLException;

    Quiz getQuiz(int CreatorUserId, String quizName) throws SQLException;

    List<Quiz> getPopularQuizList() throws SQLException;

}
