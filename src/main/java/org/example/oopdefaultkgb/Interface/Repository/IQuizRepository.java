package org.example.oopdefaultkgb.Interface.Repository;

import java.sql.SQLException;

public interface IQuizRepository {

    boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isImmediate, boolean isPracticeEnable) throws SQLException;

    boolean deleteQuiz(int quizId) throws SQLException;
}
