package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forward")
public class ForwardingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the path parameter from the request
        String path = request.getParameter("path");

        // Validate and construct the target path
        if (path != null && !path.trim().isEmpty()) {
            String targetPath = "/WEB-INF/" + path + ".jsp";

            // Forward the request to the target JSP
            request.getRequestDispatcher(targetPath).forward(request, response);
        } else {
            // Handle the case where no path is provided or it's invalid
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing path parameter.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Typically, doPost would call doGet to handle both types of requests
        doGet(request, response);
    }
}
