package org.example.oopdefaultkgb.UserController;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserProfile", value = "/user-profile")
public class UserProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User currUser = (User) request.getSession().getAttribute("currentUser");
        try {
            // friend list
            {
                IUserService userService = new UserService();
                List<Friend> friends = userService.getFriends(currUser.id);
                request.setAttribute("friendList", friends);
            }

            // TODO: Achivement
            // TODO: History
            // TODO: FavouriteQuiz
            request.getRequestDispatcher("ProfileJSPs/profile.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
