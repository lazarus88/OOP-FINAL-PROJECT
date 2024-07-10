package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Service.FriendService;
import org.example.oopdefaultkgb.Service.MailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ReceiveMailServlet", value = "/receive-mail-servlet")
public class ReceiveMailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mailTypeId = Integer.parseInt(req.getParameter("mailTypeId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int senderUserId = Integer.parseInt(req.getParameter("mailSenderId"));
        System.out.println("received");
        String action = req.getParameter("action");
        try {
            IMailService mailService = new MailService();
            IFriendService friendService = new FriendService();
            if(mailTypeId == 0){
            if(action.equals("accept") ) {
                mailService.acceptFriendRequest(userId, senderUserId);
                friendService.AcceptFriends(userId, senderUserId);
            }
            else mailService.rejectFriendRequest(userId, senderUserId);
            }
            else if(mailTypeId == 1){
                String quizName = req.getParameter("message");
                if(action.equals("accept") )
                    mailService.acceptChallengeRequest(userId, senderUserId, quizName);
                else mailService.rejectChallengeRequest(userId, senderUserId,quizName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher rd = req.getRequestDispatcher("HomeServlet");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
