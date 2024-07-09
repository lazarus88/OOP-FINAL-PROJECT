package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.Interface.Repository.IQuestionRepository;
import org.example.oopdefaultkgb.Interface.Service.IQuestionService;
import org.example.oopdefaultkgb.Repository.QuestionRepository;

import java.sql.SQLException;

public class QuestionService implements IQuestionService {
    public IQuestionRepository questionRepository;
    public QuestionService( ) throws SQLException, ClassNotFoundException {
        questionRepository = new QuestionRepository();
    }
    @Override
    public boolean addQuestion(int quizId, String question, int questionTypeId) throws SQLException {
        return questionRepository.addQuestion(quizId,question,questionTypeId);
    }
    @Override
    public boolean deleteQuestion(int questionId) throws SQLException{
        return questionRepository.deleteQuestion(questionId);
    }
    @Override
    public boolean editQuestion(int questionId, String question) throws SQLException {
        return questionRepository.editQuestion(questionId,question);
    }
    @Override
    public Question getQuestion(int questionId)  throws SQLException{
        return questionRepository.getQuestion(questionId);
    }
    @Override
    public Question getQuestion(int quizId, String question) throws SQLException {
        return questionRepository.getQuestion(quizId,question);
    }

}
