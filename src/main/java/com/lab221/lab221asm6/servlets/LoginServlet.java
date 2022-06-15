package com.lab221.lab221asm6.servlets;

import com.lab221.lab221asm6.exceptions.UserLockedException;
import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.repositories.UserRepository;
import com.lab221.lab221asm6.services.UserService;
import com.lab221.lab221asm6.utils.Constants;
import com.lab221.lab221asm6.utils.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * Init servlet.
     */
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        userService = new UserService(new UserRepository(connection));
    }

    /**
     * Handle login page.
     * @param request the request
     * @param response the response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserModel currentUser = (UserModel) session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY);
        if (currentUser != null) {
            response.sendRedirect("/home");
            return;
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handle login logic.
     * @param request the request
     * @param response the response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isBlank() || password.isBlank()) {
            request.setAttribute("errorMessage", "Username or password is empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        UserModel currentUser;
        try {
            currentUser = userService.comparePassword(username, password);
            if (currentUser == null) {
                request.setAttribute("errorMessage", "Username or password is incorrect.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } catch (UserLockedException exception) {
            request.setAttribute("errorMessage", "This account has been locked. Please contact call center for help.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }


        HttpSession session = request.getSession();
        currentUser.setPassword("");
        session.setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, currentUser);

        if (currentUser.isFirstTimeLogin()) {
            response.sendRedirect("/security-question");
            return;
        }

        response.sendRedirect("/home");
    }

    /**
     * destroy.
     */
    public void destroy() {
    }
}
