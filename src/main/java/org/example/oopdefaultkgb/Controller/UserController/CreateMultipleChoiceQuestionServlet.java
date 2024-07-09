
    package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Quiz;
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
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;


    @WebServlet(name = "CreateMultipleChoiceQuestionServlet", value = "/CreateMultipleChoiceQuestionServlet")
    public class CreateMultipleChoiceQuestionServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            HttpSession session = request.getSession();
            IQuizService quizService = null;
            try {
                quizService = new QuizService();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            int nQuestion = Integer.parseInt(request.getParameter("Nquestion"));
            //Quiz quiz = null;
            Quiz quiz =  (Quiz)session.getAttribute("quiz");
            System.out.println(nQuestion);
            System.out.println(quiz.getId());
            if(nQuestion > 2){
                request.setAttribute("userId",quiz.getCreatorUserId());
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/HomePage.jsp");
                dispatcher.forward(request, response);
            }
            request.setAttribute("Nquestion",nQuestion);
            request.setAttribute("quiz",quiz);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateMultipleChoiceQuestion.jsp");
            dispatcher.forward(request, response);

        }
    }

