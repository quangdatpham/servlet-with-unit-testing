package com.lab221.lab221asm6.servlets;

import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.utils.AuthUtil;
import com.lab221.lab221asm6.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

    /**
     * Handle render profile page.
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
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    /**
     * destroy.
     */
    public void destroy() {
    }
}
