package org.example.oopdefaultkgb.Controller.UserController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.oopdefaultkgb.EntityDTO.Answer;
import org.example.oopdefaultkgb.EntityDTO.Question;
import org.example.oopdefaultkgb.Interface.Service.IAnswerService;
import org.example.oopdefaultkgb.Service.AnswerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/submit-quiz-servlet")
public class SubmitQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the correct answers from the database or from a pre-defined map
        // Track the number of correct answers
        int correctCount = 0;
        List<Question> questions = (List<Question>) request.getSession().getAttribute("questions");
        IAnswerService answerService;
        try {
            answerService = new AnswerService();
            for (Question question : questions) {
                Answer answer = answerService.getCorrectAnswer(question.id);
                String submittedAnswerIdStr = request.getParameter("question_" + question.id);
                if (submittedAnswerIdStr != null) {
                    int submittedAnswerId = Integer.parseInt(submittedAnswerIdStr);
                    if (submittedAnswerId == answer.id) {
                        correctCount++;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("you answered " + correctCount + " questions correctly");
        request.setAttribute("correctCount", correctCount);
        request.setAttribute("totalQuestions", questions.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/SubmitQuiz.jsp");
        dispatcher.forward(request, response);
    }
}
