package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.UserService;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CreationServlet", value = "/CreationServlet")
public class CreationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        IUserService sercive = null;
        try {
            sercive = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //AccountManager accountManager = (AccountManager) sc.getAttribute("AMG");
        String usr = request.getParameter("name");
        String psw = request.getParameter("pass");
        User curUser = null;
        try {
            curUser = sercive.getProfile(usr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(curUser != null){
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/inUse.jsp");
            rd.forward(request,response);
        }
        try {
            if (sercive.addUser(usr,psw,null))
            {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Profile.jsp");
                rd.forward(request,response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/inUse.jsp");
                rd.forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}