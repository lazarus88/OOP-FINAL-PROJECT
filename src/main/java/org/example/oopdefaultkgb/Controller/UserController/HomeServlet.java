package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.*;
import org.example.oopdefaultkgb.Interface.Repository.IHistoryRepository;
import org.example.oopdefaultkgb.Interface.Service.IHistoryService;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Repository.HistoryRepository;
import org.example.oopdefaultkgb.Service.HistoryService;
import org.example.oopdefaultkgb.Service.MailService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            IUserService userService = new UserService();
            IQuizService quizService = new QuizService();
            IMailService mailService = new MailService();
            IHistoryService historyService = new HistoryService();
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userService.getProfileById(userId);
            List<Mail> mails = mailService.getMails(user.id, -1);
            List<User> otherUsers = new ArrayList<>();
            for(Mail mail: mails){
                otherUsers.add(userService.getProfileById(mail.getSenderUserId()));
            }
            request.setAttribute("senderUsers", otherUsers);
            request.setAttribute("senderUsers", otherUsers);
            List<Achievement> achievements = userService.getAchievements(userId);
            request.setAttribute("achievements", achievements);
            List<Quiz> popularQuiz = quizService.getPopularQuizList();
            Map<HistoryQuiz, Quiz> recentQuiz = historyService.getRecentQuizList(userId);
            request.setAttribute("mails", mails);
            request.setAttribute("userId",user.id);
            request.setAttribute("currentUser", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/HomePage.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
