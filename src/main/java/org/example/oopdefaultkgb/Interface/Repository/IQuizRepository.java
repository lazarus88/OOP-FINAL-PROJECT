package org.example.oopdefaultkgb.Interface.Repository;

import java.sql.SQLException;

public interface IQuizRepository {



    boolean deleteQuiz(int quizId) throws SQLException;

    boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isOneVsMultiple, boolean isImmediate, boolean isPracticeEnable, int quizTypeId) throws SQLException;
}
