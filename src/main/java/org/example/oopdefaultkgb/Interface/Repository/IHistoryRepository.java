package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.Quiz;

import java.sql.SQLException;
import java.util.List;

public interface IHistoryRepository {

    List<HistoryQuiz> getRecentQuizList(int userId) throws SQLException;
}

