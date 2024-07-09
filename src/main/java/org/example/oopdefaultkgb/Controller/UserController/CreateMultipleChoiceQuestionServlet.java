
    package org.example.oopdefaultkgb.Controller.UserController;

import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.EntityDTO.Quiz;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Service.IAnswerService;
import org.example.oopdefaultkgb.Interface.Service.IQuestionService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Interface.Service.IUserService;
import org.example.oopdefaultkgb.Service.AnswerService;
import org.example.oopdefaultkgb.Service.QuestionService;
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
            IQuestionService questionService = null;
            IAnswerService answerService = null;
            try {
                quizService = new QuizService();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                questionService = new QuestionService();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                answerService = new AnswerService();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            int nQuestion = Integer.parseInt(request.getParameter("Nquestion"));
            //Quiz quiz = null;
            int cor = Integer.parseInt(request.getParameter("correctAnswer"));
            System.out.println(cor + "answer");
            boolean ans1 = false;
            boolean ans2 = false;
            boolean ans3 = false;
            boolean ans4 = false;
            if(cor == 1){
                ans1 = true;
            }else if (cor==2){
                ans2=true;
            }else if(cor ==3){
                ans3 =true;
            }else if(cor==4){
                ans4 =true;
            }
            String questionS = request.getParameter("question");
            Quiz quiz =  (Quiz)session.getAttribute("quiz");
            try {
                questionService.addQuestion(quiz.getId(),questionS,0);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Question question = questionService.getQuestion(quiz.getId(),questionS);
                answerService.addAnswer(question.getId(),request.getParameter("option1"),ans1);
                answerService.addAnswer(question.getId(),request.getParameter("option2"),ans2);
                answerService.addAnswer(question.getId(),request.getParameter("option3"),ans3);
                answerService.addAnswer(question.getId(),request.getParameter("option4"),ans4);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(nQuestion);
            System.out.println(quiz.getId());
            int numQuestions = (int)session.getAttribute("numQuestions");
            if(nQuestion > numQuestions){
                request.setAttribute("userId",quiz.getCreatorUserId());
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EndQuiz.jsp");
                dispatcher.forward(request, response);
            }
            request.setAttribute("Nquestion",nQuestion);
            request.setAttribute("quiz",quiz);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateMultipleChoiceQuestion.jsp");
            dispatcher.forward(request, response);

        }
    }

