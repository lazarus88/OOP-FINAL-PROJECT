package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.Interface.Service.IAnnouncmentService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Service.AnnouncmentService;
import org.example.oopdefaultkgb.Service.QuizService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;


@WebServlet(name = "AdminActionsServlet", value = "/AdminActionsServlet")
public class AdminActionsServlet extends HttpServlet {

    @Override
    // AdminActionServlet.java

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        try {
            IAnnouncmentService announcmentService = new AnnouncmentService();

        switch (action) {
            case "createAnnouncement":
                String announcementMessage = request.getParameter("announcementMessage");
                announcmentService.addAnnouncment(adminId, announcementMessage);
                break;
            case "removeUser":
                String[] userIds = request.getParameterValues("userIds");
                //adminService.RemoveUser(userIds);
                break;
            case "removeQuiz":
                String[] quizIds = request.getParameterValues("quizIds");
                //adminService.RemoveQuiz(quizIds);
                break;
            case "promoteUser":
                String[] promotedUserIds = request.getParameterValues("userIds");
                //adminService.PromoteUser(userIds);
                break;
            default:
                // Handle unsupported action
                break;
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Optionally, retrieve statistics and set them as attributes for JSP to display
        int numberOfUsers = 4; // Retrieve from database
        int numberOfQuizzes = 3;// Retrieve from database
                request.setAttribute("numberOfUsers", numberOfUsers);
        request.setAttribute("numberOfQuizzes", numberOfQuizzes);

        // Forward to JSP for displaying statistics
        request.getRequestDispatcher("/AdminUserProfile.jsp").forward(request, response);
    }
}
