package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Answer;
import org.example.oopdefaultkgb.Interface.Repository.IAnswerRepository;
import org.example.oopdefaultkgb.Interface.Service.IAnswerService;
import org.example.oopdefaultkgb.Repository.AnswerRepository;

import java.sql.SQLException;
import java.util.List;

public class AnswerService  implements IAnswerService {
    public IAnswerRepository answerRepository;
    public AnswerService( ) throws SQLException, ClassNotFoundException {
        answerRepository = new AnswerRepository();
    }
    @Override
    public List<Answer> getAllAnswers(int questionId) throws SQLException {
        return answerRepository.getAllAnswers(questionId);
    }
    @Override
    public boolean deleteAnswer(int answerId)throws SQLException{
        return answerRepository.deleteAnswer(answerId);
    }
    @Override
    public boolean addAnswer(int questionId, String answer, boolean isCorrect) throws SQLException {
        return answerRepository.addAnswer(questionId,answer,isCorrect);
    }
}
