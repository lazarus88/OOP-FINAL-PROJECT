package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
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


@WebServlet(name = "CreateQuestionsServlet", value = "/CreateQuestionsServlet")
public class CreateQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        IQuizService quizService = null;
        try {
            quizService = new QuizService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int numQuestions = Integer.parseInt(request.getParameter("numQuestions"));
        String isRandomS = request.getParameter("isRandom");
        boolean isRandom = true;
        if(isRandomS == null) {
            isRandom = false;
        }
        String isOnePageS = request.getParameter("isOnePage");
        boolean isOnePage = true;
        if(isOnePageS == null) {
            isOnePage = false;
        }
        String isImmediateS = request.getParameter("isImmediate");
        boolean isImmediate = true;
        if(isImmediateS == null) {
            isImmediate = false;
        }
        String isPracticeEnabledS = request.getParameter("isPracticeEnabled");
        boolean isPracticeEnabled = true;
        if(isPracticeEnabledS == null) {
            isPracticeEnabled = false;
        }
        int userId = Integer.parseInt(request.getParameter("userId"));
        String quizName = request.getParameter("quizName");
            try {
                quizService.addQuiz(userId, quizName, isRandom, isOnePage, isImmediate, isPracticeEnabled, 0);
                Quiz quiz = quizService.getQuiz(userId,quizName);
                System.out.println("gadavdivar");
                request.setAttribute("Nquestion",1);
                session.setAttribute("numQuestions",numQuestions);
                request.setAttribute("quiz",quiz);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateMultipleChoiceQuestion.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}