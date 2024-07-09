package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.Interface.Repository.IQuestionRepository;
import org.example.oopdefaultkgb.Interface.Repository.IQuizRepository;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Repository.QuestionRepository;
import org.example.oopdefaultkgb.Repository.QuizRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizService implements IQuizService {

    public IQuizRepository quizRepository;
    public IQuestionRepository questionRepository;

    public QuizService( ) throws SQLException, ClassNotFoundException {
        quizRepository = new QuizRepository();
        questionRepository = new QuestionRepository();
    }
    @Override
    public boolean addQuiz(int creatorUserId, String quizName, boolean isRandom,boolean isOneVsMultiple, boolean isImmediate, boolean isPracticeEnable,int quizTypeId) throws SQLException{
        return quizRepository.addQuiz(creatorUserId,quizName,isRandom,isOneVsMultiple,isImmediate,isPracticeEnable,quizTypeId);
    }
    @Override
    public boolean deleteQuiz(int quizId) throws SQLException{
        return quizRepository.deleteQuiz(quizId);
    }
    @Override
    public ArrayList<Question> getAllQuestions(int quizID) throws SQLException  {
        return questionRepository.getAllQuestions(quizID);
    }
    @Override
    public boolean deleteAllQuestions(int quizID)  throws SQLException{
        return questionRepository.deleteAllQuestions(quizID);
    }
    @Override
    public Quiz getQuiz(int CreatorUserId, String quizName)throws SQLException{
        return quizRepository.getQuiz(quizName,CreatorUserId);
    }
    @Override
    public List<Quiz> getPopularQuizList() throws SQLException {
        return quizRepository.getPopularQuizList();
    }


}
