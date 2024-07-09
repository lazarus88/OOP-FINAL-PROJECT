package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.Interface.Repository.IHistoryRepository;
import org.example.oopdefaultkgb.Interface.Repository.IQuizRepository;
import org.example.oopdefaultkgb.Interface.Service.IHistoryService;
import org.example.oopdefaultkgb.Repository.HistoryRepository;
import org.example.oopdefaultkgb.Repository.QuizRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryService implements IHistoryService {
    private IHistoryRepository historyRepository;
    private IQuizRepository quizRepository;
    public HistoryService() throws SQLException, ClassNotFoundException {
        historyRepository = new HistoryRepository();
        quizRepository = new QuizRepository();
    }
    @Override
    public Map<HistoryQuiz, Quiz> getRecentQuizList(int userId) throws SQLException {
        List<HistoryQuiz> userQuiz = historyRepository.getRecentQuizList(userId);
        Map<HistoryQuiz, Quiz> mp = new HashMap<>();
        for(HistoryQuiz hq : userQuiz)
            mp.put(hq, quizRepository.getQuizById(hq.quizId));
        return mp;
    }
}
