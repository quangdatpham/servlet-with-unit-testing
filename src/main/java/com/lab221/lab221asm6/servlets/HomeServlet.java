package com.lab221.lab221asm6.servlets;

import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.utils.Constants;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "homeServlet", value = {"/", "/home", "/hello-servlet"})
public class HomeServlet extends HttpServlet {

    /**
     * Handle render home page.
     * @param request the request
     * @param response the response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserModel currentUser = (UserModel) session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY);

        if (currentUser != null && currentUser.isFirstTimeLogin()) {
            request.getRequestDispatcher("security-question.jsp").forward(request, response);
            return;
        }

        if (currentUser != null && currentUser.isHasToChangePassword()) {
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }

        request.setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, currentUser);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    /**
     * destroy.
     */
    public void destroy() {
    }
}
