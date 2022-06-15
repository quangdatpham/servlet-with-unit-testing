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

@WebServlet(name = "changePasswordServlet", value = "/change-password")
public class ChangePasswordServlet extends HttpServlet {

    /**
     * The user service.
     */
    private UserService userService;

    /**
     * Init ChangePasswordServlet.
     */
    public void init() {
        Connection connection = DatabaseConnection.getConnection();
        userService = new UserService(new UserRepository(connection));
    }

    /**
     * Handle render change-password page.
     * @param request the request
     * @param response the response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!AuthUtil.isAuthenticated(request, response)) {
            return;
        }
        request.getRequestDispatcher("change-password.jsp").forward(request, response);
    }

    /**
     * Handle update password.
     * @param request the request
     * @param response the response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserModel currentUser = (UserModel) session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY);

        if (currentUser == null) {
            response.sendRedirect("/login");
            return;
        }

        String currentPassword = request.getParameter("current-password");
        String newPassword = request.getParameter("new-password");

        if (StringUtil.isBlank(currentPassword) || StringUtil.isBlank(newPassword)) {
            request.setAttribute("errorMessage", "Password can not be blank.");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }

        UserModel userModel = userService.comparePassword(currentUser.getUsername(), currentPassword);
        if (userModel == null) {
            request.setAttribute("errorMessage", "Password is incorrect.");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }

        userService.changePassword(newPassword, userModel.getId());
        session.setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, null);
        response.sendRedirect("/login");
    }

    /**
     * destroy.
     */
    public void destroy() {
    }
}
