package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IAnnouncmentService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.AnnouncmentService;
import org.example.oopdefaultkgb.Service.QuizService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet(name = "AdminActionServlet", value = "/AdminActionServlet")
public class AdminActionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String errorMessage = null;
        try {
            IAnnouncmentService announcementService = new AnnouncmentService();
            IUserService userService = new UserService();
            IQuizService quizService = new QuizService();
            int activeUserCount = userService.getActiveUsers();
            int activeQuizCount = quizService.getActiveQuizs();
            switch (action) {
                case "promoteUser":
                    User user = userService.getProfile(username);
                    if (user == null)
                        errorMessage = "This action cannot be performed: User Not Found.";
                    userService.PromoteUser(user.id);
                    break;
                case "removeUser":
                    user = userService.getProfile(username);
                    if (user == null)
                        errorMessage = "This action cannot be performed: User Not Found.";
                    userService.DeleteUser(user.id);
                    break;
                case "createAnnouncement":
                    String announcementMessage = request.getParameter("message");
                    int adminId = Integer.parseInt(request.getParameter("adminId"));
                    announcementService.addAnnouncment(adminId, announcementMessage);
                    break;
                case "removeQuiz":
                    int quizId = Integer.parseInt(request.getParameter("quizId"));
                    quizService.deleteQuiz(quizId);
                    break;
                default:
                    errorMessage = "Action Completed.";
            }
        } catch (Exception e) {
            errorMessage = "This action cannot be performed: " + e.getMessage();
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
        }
        request.getRequestDispatcher("WEB-INF/AdminUserProfile.jsp").forward(request, response);
    }
}
