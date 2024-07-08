package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserProfileServlet", value = "/user-profile-servlet")
public class UserProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        User currUser = new User(1,"ლაზარე ფაჩუაშვილი", "Lazarus21", null,"ACTIVE", LocalDateTime.now(),null,0,"CUSTOMER");
        User currUser = (User)request.getAttribute("currentUser");
        try {
            IUserService userService = new UserService();
     //       List<Achievement>  achievementList = userService.getAchievements(currUser.id);
      //      List<HistoryQuiz> historyQuizList = userService.getHistories(currUser.id);
            List<Friend> friendList = userService.getFriends(currUser.id);
            List<User> userFriendList = new ArrayList<>();
            for (Friend friend : friendList) {
                int userId = friend.receiverUserId == currUser.id ? friend.senderUserId : friend.receiverUserId;
                userFriendList.add(userService.getProfileById(userId));
            }
            request.setAttribute("friendList" ,userFriendList);
       //     request.setAttribute("historyQuizList" ,historyQuizList);
        //    request.setAttribute("achievementList" ,achievementList);
            //request.setAttribute("favouriteQuizList" ,);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //request.setAttribute("currentUser",currUser);
        // სრული სახელი, იუზერნეიმი, რეგისტრაცისს, თარიღი, როლი
        request.getRequestDispatcher("WEB-INF/Profile.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //User currUser = new User(1,"ლაზარე ფაჩუაშვილი", "Lazarus21", null,"ACTIVE", LocalDateTime.now(),null,0,"CUSTOMER");
        User currUser = (User)request.getAttribute("currentUser");
        try {
            IUserService userService = new UserService();
            //       List<Achievement>  achievementList = userService.getAchievements(currUser.id);
            //      List<HistoryQuiz> historyQuizList = userService.getHistories(currUser.id);
            List<Friend> friendList = userService.getFriends(currUser.id);
            List<User> userFriendList = new ArrayList<>();
            for (Friend friend : friendList) {
                int userId = friend.receiverUserId == currUser.id ? friend.senderUserId : friend.receiverUserId;
                userFriendList.add(userService.getProfileById(userId));
            }
            request.setAttribute("friendList" ,userFriendList);
            //     request.setAttribute("historyQuizList" ,historyQuizList);
            //    request.setAttribute("achievementList" ,achievementList);
            //request.setAttribute("favouriteQuizList" ,);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //request.setAttribute("currentUser",currUser);
        // სრული სახელი, იუზერნეიმი, რეგისტრაცისს, თარიღი, როლი
        request.getRequestDispatcher("WEB-INF/Profile.jsp").forward(request, response);
    }
}
