package com.lab221.lab221asm6.utils;

import com.lab221.lab221asm6.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthUtil {

    /**
     * Check authentication.
     * @param request the request
     * @param response the response
     * @return true if authenticated
     */
    public static boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserModel currentUser = (UserModel) session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY);

        if (currentUser == null) {
            request.setAttribute("errorMessage", "Please login to enter the system.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return false;
        }

        if (currentUser.isFirstTimeLogin()) {
            request.getRequestDispatcher("security-question.jsp").forward(request, response);
            return false;
        }

        if (currentUser.isHasToChangePassword()) {
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return false;
        }

        return true;
    }
}
