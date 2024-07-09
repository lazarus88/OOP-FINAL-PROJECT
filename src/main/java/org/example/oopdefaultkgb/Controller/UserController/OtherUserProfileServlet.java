package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.FriendService;
import org.example.oopdefaultkgb.Service.MailService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OtherUserProfileServlet", value = "/other-user-profile-servlet")
public class OtherUserProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the path parameter from the request
        int userId  = Integer.parseInt(request.getParameter("userId")) ;
        String otherUsername;
        if(request.getParameter("otherUsername") != null) {
            otherUsername = (request.getParameter("otherUsername"));
            System.out.println(otherUsername + " param");

        } else {
            otherUsername =((User)request.getAttribute("otherUser")).userName;
            System.out.println(otherUsername + " attr");
        }
        try {
            IUserService userService = new UserService();
            IFriendService friendService = new FriendService();
            IMailService mailService = new MailService();
            User otherUser = userService.getProfile(otherUsername);
            String friendRequestStatus = mailService.getFriendRequestStatus(userId, otherUser.id);
            if (mailService.receivedFriendRequest(otherUser.id, userId)) {
                System.out.println("received");
                request.setAttribute("receivedFriendRequest", true);
            }
            if (friendRequestStatus.equals("SENT"))
                request.setAttribute("alreadySent", true);
            request.setAttribute("otherUser", otherUser);
            boolean isFriend = friendService.checkIfUserIsFriend(userId, otherUser.id);
            System.out.println(isFriend + " " + userId + " " + otherUser.id);
            request.setAttribute("isFriend", isFriend);
            request.setAttribute("userId", userId);
            List<Achievement> otherUserAchievementList = userService.getAchievements(otherUser.id);
            request.setAttribute("otherUserAchievementList", otherUserAchievementList);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
            // Forward the request to the target JSP
            request.getRequestDispatcher("WEB-INF/OtherUserProfile.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Typically, doPost would call doGet to handle both types of requests
        doGet(request, response);
    }
}
