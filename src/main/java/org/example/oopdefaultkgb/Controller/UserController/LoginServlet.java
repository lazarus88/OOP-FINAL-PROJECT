package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.*;
import org.example.oopdefaultkgb.Interface.Repository.IAchievementRepository;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
import org.example.oopdefaultkgb.Interface.Service.*;
import org.example.oopdefaultkgb.Repository.AchievementRepository;
import org.example.oopdefaultkgb.Service.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        IUserService sercive;
        IMailService mailService;
        List<Mail> mails;
        try {
            sercive = new UserService();
            mailService = new MailService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String usr = request.getParameter("name");
        String psw = request.getParameter("pass");
        System.out.println(usr + psw);
        User curUser;
        try {
            curUser = sercive.getProfile(usr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(curUser == null){
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/illegalPassword.jsp");
            rd.forward(request,response);
        }
        else if (sercive.checkPassword(usr,psw))
        {
            try {
                User curUser1 = sercive.getProfile(usr);
                if(curUser1.Status.equals("DELETED")) {
                    request.setAttribute("error", "User Is Deleted By Admin");
                    request.getRequestDispatcher("WEB-INF/illegalPassword.jsp").forward(request,response);
                    return;
                }
                if(curUser1.Status.equals("BLOCKED")) {
                    request.setAttribute("error", "User Is Blocked By Admin");
                    request.getRequestDispatcher("WEB-INF/illegalPassword.jsp").forward(request,response);
                    return;
                }
                if(curUser1.Role.equals("ADMIN")) {
                    request.setAttribute("currentAdminUser", curUser1);
                    request.getRequestDispatcher("WEB-INF/AdminUserProfile.jsp").forward(request,response);
                    return;
                }
                mails = mailService.getMails(curUser1.id, -1);
                IUserService userService1 = new UserService();
                List<User> otherUsers = new ArrayList<>();
                for(Mail mail: mails){
                    otherUsers.add(userService1.getProfileById(mail.getSenderUserId()));
                }
                List<Achievement> achievements;
                achievements = userService1.getAchievements(curUser1.id);
                IQuizService quizService = new QuizService();
                IHistoryService historyService = new HistoryService();
                Map<HistoryQuiz, Quiz> historyQuiz = historyService.getRecentQuizList(curUser1.id);
                request.setAttribute("historyQuiz", historyQuiz);
                List<Quiz> popularQuiz;
                popularQuiz = quizService.getPopularQuizList();
                List<Quiz> recentlyCreated = quizService.getActiveQuizs();
                request.setAttribute("recentlyCreated", recentlyCreated);;
                request.setAttribute("quizList", popularQuiz);
                request.setAttribute("achievements", achievements);
                request.setAttribute("senderUsers", otherUsers);
                request.setAttribute("mails", mails);
                request.setAttribute("userId",curUser1.id);
                request.setAttribute("currentUser", curUser1);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/HomePage.jsp");
                rd.forward(request,response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }       }
        else
        {
            request.setAttribute("error", "Incorrect Credentials");
            request.getRequestDispatcher("WEB-INF/illegalPassword.jsp").forward(request,response);
        }
    }
}