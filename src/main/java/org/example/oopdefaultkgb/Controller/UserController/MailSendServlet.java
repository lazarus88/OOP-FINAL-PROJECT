package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Enum.MailTypeEnum;
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.FriendService;
import org.example.oopdefaultkgb.Service.MailService;
import org.example.oopdefaultkgb.Service.QuizService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MailSendServlet", value = "/mail-send-servlet")
public class MailSendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        try {
//            IMailService mailService = new MailService();
//            List<Mail> fMails = mailService.getMails(Integer.parseInt(userId), 1);
//            List<Mail> cMails = mailService.getMails(Integer.parseInt(userId), 2);
//            List<Mail> nMails = mailService.getMails(Integer.parseInt(userId), 3);
//            req.setAttribute("friendRequests",fMails);
//            req.setAttribute("challengeRequests",cMails);
//            req.setAttribute("notes",nMails);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        req.getRequestDispatcher("WEB-INF/Mail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int mailTypeId = Integer.parseInt(req.getParameter("mailTypeId"));
       int userIdFrom = Integer.parseInt(req.getParameter("userId"));
       int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
       String action = req.getParameter("action");
        System.out.println("request from " + userIdFrom + "MailTypeId "+mailTypeId +" ord " + MailTypeEnum.FriendRequest.ordinal());
        try {
            IMailService mailService = new MailService();
            IUserService userService = new UserService();
            IFriendService friendService = new FriendService();
            User otherUser =userService.getProfileById(otherUserId);
            req.setAttribute("otherUser", otherUser);
            if(mailTypeId == MailTypeEnum.FriendRequest.ordinal()) {
                if(action.equals("sendFriendRequest"))
                mailService.sendFriendRequest(userIdFrom, otherUserId);
                else if(action.equals("cancelFriendRequest"))
                    mailService.cancelFriendRequest(userIdFrom, otherUserId);
                else if(action.equals("acceptFriendRequest")) {
                    mailService.acceptFriendRequest(userIdFrom, otherUserId);
                    friendService.AcceptFriends(userIdFrom, otherUserId);
                }
                else if(action.equals("rejectFriendRequest"))
                    mailService.rejectFriendRequest(userIdFrom, otherUserId);
                else if(action.equals("deleteFriend")){
                    System.out.println("deleted");
                    friendService.deleteFriends(userIdFrom, otherUserId);
                }
            }
             else if(mailTypeId == MailTypeEnum.ChallengeRequest.ordinal()) {
                int quizId = Integer.parseInt(req.getParameter("quizId"));
                IQuizService quizService = new QuizService();
                Quiz quiz = quizService.getQuizById(quizId);
                mailService.sendChallengeRequest(userIdFrom, otherUser.id, quiz.quizName);
                req.setAttribute("userId", userIdFrom);
                req.setAttribute("quizId", quizId);
                req.getRequestDispatcher("submit-quiz-servlet").forward(req, resp);
            }
             else {
                  String note = req.getParameter("message");
                 mailService.sendNote(userIdFrom, otherUser.id, note);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("other-user-profile-servlet").forward(req, resp);

    }
}
