package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.QuizService;
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


@WebServlet(name = "CreateQuizServlet", value = "/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        IQuizService quizService = null;
        try {
            quizService = new QuizService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("userId",request.getParameter("userId"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateQuiz.jsp");
        dispatcher.forward(request, response);
    }
}