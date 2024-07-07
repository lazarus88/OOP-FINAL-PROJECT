package com.example;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.FriendService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OtherUserProfileServlet", value = "/other-user-profile-servlet")
public class OtherUserProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the path parameter from the request
        int userId = Integer.parseInt(request.getParameter("userId"));
        int otherUserId = Integer.parseInt(request.getParameter("otherUserId"));

        try {
            IFriendService friendService = new FriendService();
            //userService.
            boolean isFriend = friendService.checkIfUserIsFriend(userId, otherUserId);
            System.out.println(isFriend + " " + userId + " " + otherUserId);
            request.setAttribute("isFriend", isFriend);
        } catch (SQLException e) {
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