package com.lab221.lab221asm6.servlets;

import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.repositories.UserRepository;
import com.lab221.lab221asm6.services.UserService;
import com.lab221.lab221asm6.utils.AuthUtil;
import com.lab221.lab221asm6.utils.Constants;
import com.lab221.lab221asm6.utils.DatabaseConnection;
import com.lab221.lab221asm6.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "securityQuestionServlet", value = "/security-question")
public class SecurityQuestionServlet extends HttpServlet {

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * Init SecurityQuestionServlet.
     */
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        userService = new UserService(new UserRepository(connection));
    }

    /**
     * Handle render security question page.
     * @param request the request
     * @param response the response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!AuthUtil.isAuthenticated(request, response)) {
            return;
        }

        HttpSession session = request.getSession();
        UserModel currentUser = (UserModel) session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY);

        request.setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, currentUser);
        request.getRequestDispatcher("security-question.jsp").forward(request, response);
    }

    /**
     * Handle update security questions & answers.
     * @param request the request
     * @param response the response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserModel currentUser = (UserModel) session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY);
        if (currentUser == null) {
            request.setAttribute("errorMessage", "Please login to enter the system.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String question1 = request.getParameter("question1");
        String answer1 = request.getParameter("answer1");
        String question2 = request.getParameter("question2");
        String answer2 = request.getParameter("answer2");
        String question3 = request.getParameter("question3");
        String answer3 = request.getParameter("answer3");

        if (StringUtil.isBlank(question1)) {
            request.setAttribute("errorMessage", "Please fill as least 1 question & answer.");
            request.getRequestDispatcher("security-question.jsp").forward(request, response);
            return;
        }

        if (StringUtil.isBlank(answer1)) {
            request.setAttribute("errorMessage", "Answer can not be empty.");
            request.getRequestDispatcher("security-question.jsp").forward(request, response);
            return;
        }

        if (!StringUtil.isBlank(question2) && StringUtil.isBlank(answer2)) {
            request.setAttribute("errorMessage", "Answer can not be empty.");
            request.getRequestDispatcher("security-question.jsp").forward(request, response);
            return;
        }

        if (!StringUtil.isBlank(question3) && StringUtil.isBlank(answer3)) {
            request.setAttribute("errorMessage", "Answer can not be empty.");
            request.getRequestDispatcher("security-question.jsp").forward(request, response);
            return;
        }

        List<String> questions = Arrays.asList(question1, question2, question3);
        List<String> answers = Arrays.asList(answer1, answer2, answer3);
        userService.updateSecurityQuestionForFirstLogin(currentUser.getId(), questions, answers);

        if (currentUser.isFirstTimeLogin()) {
            currentUser.setHasToChangePassword(true);
            currentUser.setFirstTimeLogin(false);
            session.setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, currentUser);
            response.sendRedirect("/change-password");
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
