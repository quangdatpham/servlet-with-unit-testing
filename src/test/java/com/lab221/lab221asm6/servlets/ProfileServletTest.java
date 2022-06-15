package com.lab221.lab221asm6.servlets;

import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ProfileServletTest {

    private ProfileServlet testee;
    private UserModel currentUser;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        testee = new ProfileServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        currentUser = new UserModel();
        currentUser.setUsername("dat");
        currentUser.setFirstTimeLogin(false);
        currentUser.setHasToChangePassword(false);
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(currentUser);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    void test_doGet_loggedIn_renderProfilePage() throws ServletException, IOException {
        testee.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("profile.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doGet_notLoggedIn_renderLoginPage() throws ServletException, IOException {
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(null);
        testee.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("login.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
