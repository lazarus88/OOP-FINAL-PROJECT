package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Mail;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IMailService;
import org.example.oopdefaultkgb.Service.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MailServlet", value = "/mail-servlet")
public class MailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        try {
            IMailService mailService = new MailService();
            List<Mail> fMails = mailService.getMails(Integer.parseInt(userId), 1);
            List<Mail> cMails = mailService.getMails(Integer.parseInt(userId), 2);
            List<Mail> nMails = mailService.getMails(Integer.parseInt(userId), 3);
            req.setAttribute("friendRequests",fMails);
            req.setAttribute("challengeRequests",cMails);
            req.setAttribute("notes",nMails);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("WEB-INF/Mail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
