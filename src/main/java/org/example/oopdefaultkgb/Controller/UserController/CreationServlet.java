package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;


@WebServlet(name = "CreationServlet", value = "/CreationServlet")
public class CreationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //ServletContext sc = request.getServletContext();
        IUserService sercive = null;
        try {
            sercive = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //AccountManager accountManager = (AccountManager) sc.getAttribute("AMG");
        String fullName = request.getParameter("fullName");
        String usr = request.getParameter("name");
        String psw = request.getParameter("pass");
        User curUser = null;
        try {
            curUser = sercive.getProfile(usr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(curUser != null){
            System.out.println(curUser.id);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/inUse.jsp");
            rd.forward(request,response);
        }
        try {
            if (sercive.addUser(usr,psw,fullName,null))
            {
                System.out.println(1111);
                //request.setAttribute("userId",curUser.id);
                curUser = sercive.getProfile(usr);
                if(curUser != null) {
                    request.setAttribute("curUser",curUser);
                    RequestDispatcher rd = request.getRequestDispatcher("user-profile-servlet");
                    rd.forward(request, response);
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/ErrorPage.jsp");
                    rd.forward(request, response);
                }
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