
package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IAnswerService;
import org.example.oopdefaultkgb.Interface.Service.IQuestionService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.AnswerService;
import org.example.oopdefaultkgb.Service.QuestionService;
import org.example.oopdefaultkgb.Service.QuizService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "ShowQuizServlet", value = "/ShowQuizServlet")
public class ShowQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        IQuizService quizService = null;
        IQuestionService questionService = null;
        IAnswerService answerService = null;
        try {
            quizService = new QuizService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            questionService = new QuestionService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            answerService = new AnswerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int userId = Integer.parseInt(request.getParameter("userId"));
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        System.out.println(request.getParameter("userId"));
        System.out.println(request.getParameter("quizId"));
        ArrayList<Question> questions = null;
        try {
            quizService.getAllQuestions(quizId);
            for (int i = 0; i < questions.size(); i++) {
                System.out.println(questions.get(i).getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

