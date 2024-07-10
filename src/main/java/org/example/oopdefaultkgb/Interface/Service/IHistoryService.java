package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.Quiz;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IHistoryService {

    Map<HistoryQuiz, Quiz> getRecentQuizList(int userId) throws SQLException;

    boolean addHistory(int userId, int quizId, int score) throws SQLException;
}
