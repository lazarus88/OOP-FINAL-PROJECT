
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
            System.out.println(1);
            int nQuestion = Integer.parseInt(request.getParameter("Nquestion"));
            System.out.println(nQuestion);
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
            int type = Integer.parseInt(request.getParameter("type"));
            //Quiz quiz = null;
            String questionS = request.getParameter("question");
            Quiz quiz =  (Quiz)session.getAttribute("quiz");
            System.out.println(request.getParameter("questionType"));
            boolean ans1 = true;
            boolean ans2 = true;
            boolean ans3 = true;
            boolean ans4 = true;
            String conS = null;
            if(type == 1) {
                int cor = Integer.parseInt(request.getParameter("correctAnswer"));
                System.out.println(cor + "answer");

                if (cor != 1) {
                    ans1 = false;
                }
                if (cor != 2) {
                    ans2 = false;
                }
                if (cor != 3) {
                    ans3 = false;
                }
                if (cor != 4) {
                    ans4 = false;
                }
                conS = "option";
            } else if (type == 0) {
                conS = "textCorrectAnswer";
            }else if (type == 2) {
                conS = "fillCorrectAnswer";
            }else if (type == 3) {
                conS = "picCorrectAnswer";
            }

            try {
                System.out.println(quiz.getId());
                questionService.addQuestion(quiz.getId(),questionS,type);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Question question = questionService.getQuestion(quiz.getId(),questionS);
                String s1 = request.getParameter(conS + "1");
                if(s1 != "") {
                    answerService.addAnswer(question.getId(), s1, ans1);
                }
                String s2 = request.getParameter(conS + "2");
                if(s2 != "") {
                    answerService.addAnswer(question.getId(), s2, ans2);
                }
                String s3 = request.getParameter(conS + "3");
                if(s3 != "") {
                    answerService.addAnswer(question.getId(), s3, ans3);
                }
                String s4 = request.getParameter(conS + "4");
                if(s4 != "") {
                    answerService.addAnswer(question.getId(), s4, ans4);
                }
                System.out.println("s1" + s1);
                System.out.println("s2" + s2);
                System.out.println("s3" + s3);
                System.out.println("s4" + s4);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(nQuestion);
            System.out.println(quiz.getId());
            int numQuestions = (int)session.getAttribute("numQuestions");
            if(nQuestion > numQuestions){
                System.out.println("numqeues" + nQuestion +" " +numQuestions);
                request.setAttribute("userId",quiz.getCreatorUserId());
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EndQuiz.jsp");
                dispatcher.forward(request, response);
            }else {
                request.setAttribute("Nquestion", nQuestion);
                request.setAttribute("quiz", quiz);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/CreateMultipleChoiceQuestion.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

