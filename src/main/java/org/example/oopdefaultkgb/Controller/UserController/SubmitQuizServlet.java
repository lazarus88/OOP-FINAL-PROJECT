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
import org.example.oopdefaultkgb.Interface.Service.IFriendService;
import org.example.oopdefaultkgb.Interface.Service.IHistoryService;
import org.example.oopdefaultkgb.Interface.Service.IQuizService;
import org.example.oopdefaultkgb.Service.AnswerService;
import org.example.oopdefaultkgb.Service.FriendService;
import org.example.oopdefaultkgb.Service.HistoryService;
import org.example.oopdefaultkgb.Service.QuizService;

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
        int quizId = (int)request.getSession().getAttribute("quizId");
        int userId = (int)request.getSession().getAttribute("userId");
        request.setAttribute("userId", userId);
        try {
            IQuizService quizService = new QuizService();
            IFriendService friendService = new FriendService();
            request.setAttribute("friendList",friendService.getFriendUsers(userId));
            System.out.println(userId + " friend " + friendService.getFriendUsers(userId).get(0));
            request.setAttribute("quiz", quizService.getQuizById(quizId));;
            quizService.updateQuizCounter(quizId);
            answerService = new AnswerService();
            IHistoryService historyService = new HistoryService();

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
            historyService.addHistory(userId,quizId, (correctCount/questions.size())*100);
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
