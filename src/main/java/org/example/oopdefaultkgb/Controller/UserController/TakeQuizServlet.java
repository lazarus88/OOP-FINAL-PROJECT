package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Answer;
import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.Interface.Repository.IAnswerRepository;
import org.example.oopdefaultkgb.Interface.Repository.IQuestionRepository;
import org.example.oopdefaultkgb.Interface.Service.IAnswerService;
import org.example.oopdefaultkgb.Interface.Service.IQuestionService;
import org.example.oopdefaultkgb.Repository.AnswerRepository;
import org.example.oopdefaultkgb.Repository.QuestionRepository;
import org.example.oopdefaultkgb.Service.AnswerService;
import org.example.oopdefaultkgb.Service.QuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TakeQuizServlet", value = "/take-quiz-servlet")
public class TakeQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quizId = req.getSession().getAttribute("quizId") != null ? (int)req.getSession().getAttribute("quizId")
                : Integer.parseInt(req.getParameter("quizId"));
        req.setAttribute("quizId", quizId);
        int userId = (int)req.getSession().getAttribute("userId");
        req.setAttribute("userId", userId);
        System.out.println(quizId + " quizId");
        try {
            IQuestionService questionService = new QuestionService();
            IAnswerService answerService = new AnswerService();
            Map<Integer, List<Answer>> answerMap = new HashMap<>();
            List<Question> questionList = questionService.getAllQuestions(quizId);
            for(Question question : questionList) {
                System.out.println(answerService.getAllAnswers(question.id));
                answerMap.put(question.id, answerService.getAllAnswers(question.id));
            }
            req.setAttribute("questions", questionList);
            System.out.println(questionList);
            req.setAttribute("answers", answerMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/TakeQuiz.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
