import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserProfile", value = "/user-profile")
public class UserProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Example of setting user profile data
        String username = "JohnDoe";
        int age = 30;
        String email = "john.doe@example.com";

        // Set user profile data as request attributes
        request.setAttribute("username", username);
        request.setAttribute("age", age);
        request.setAttribute("email", email);

        // Forward the request to Profile.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("Profile.jsp");
        dispatcher.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("Profile.jsp").forward(request, response);
    }
}
