package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IFriendRepository;
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.FriendService;
import org.example.oopdefaultkgb.Service.MailService;
import org.example.oopdefaultkgb.Service.UserService;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                mails = mailService.getMails(curUser1.id, -1);
                IUserService userService1 = new UserService();
                List<User> otherUsers = new ArrayList<>();
                for(Mail mail: mails){
                    otherUsers.add(userService1.getProfileById(mail.getSenderUserId()));
                }
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
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/illegalPassword.jsp");
            rd.forward(request,response);
        }
    }
}