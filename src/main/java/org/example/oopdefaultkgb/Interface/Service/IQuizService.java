package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Question;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IQuizService  {

    boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isOneVsMultiple, boolean isImmediate, boolean isPracticeEnable, int quizTypeId) throws SQLException;

    boolean deleteQuiz(int quizId) throws SQLException;

    ArrayList<Question> getAllQuestions(int quizID) throws SQLException;

    boolean deleteAllQuestions(int quizID)  throws SQLException;
}
