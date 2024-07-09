package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.QuizService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            IUserService userService = new UserService();
            IQuizService quizService = new QuizService();

            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userService.getProfileById(userId);
            List<Achievement> achievements = userService.getAchievements(userId);
            List<Quiz> popularQuiz = quizService.getPopularQuizList();
            //List<Quiz> recentQuiz = quizService.getRecentQuizList();

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateMultipleChoiceQuestion.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
