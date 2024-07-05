package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
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

    boolean addQuiz(int creatorUserId, String quizName, boolean isRandom, boolean isImmediate, boolean isPracticeEnable) throws SQLException{
        return quizRepository.addQuiz(creatorUserId,quizName,isRandom,isImmediate,isPracticeEnable);
    }
    boolean deleteQuiz(int quizId) throws SQLException{
        return quizRepository.deleteQuiz(quizId);
    }
    ArrayList<Question> getAllQuestions(int quizID) throws SQLException  {
        return questionRepository.getAllQuestions(quizID);
    }

    boolean deleteAllQuestions(int quizID)  throws SQLException{
        return questionRepository.deleteAllQuestions(quizID);
    }



}
