package com.lab221.lab221asm6.servlets;

import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.repositories.UserRepository;
import com.lab221.lab221asm6.services.UserService;
import com.lab221.lab221asm6.utils.Constants;
import com.lab221.lab221asm6.utils.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LogoutServletTest {

    private LogoutServlet testee;
    private UserModel currentUser;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        testee = new LogoutServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        currentUser = new UserModel();
        currentUser.setId(9999);
        currentUser.setUsername("unittester");
        currentUser.setPassword("unittester@1234");
        currentUser.setFullname("Unit Tester");
        currentUser.setFirstTimeLogin(false);
        currentUser.setHasToChangePassword(false);
        currentUser.setLocked(false);
        currentUser.setAttempt(0);
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(currentUser);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    void test_doGet_notLoggedIn_renderHomePage() throws ServletException, IOException {
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(null);
        testee.doGet(request, response);
        verify(session, times(1)).setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, null);
        verify(response, times(1)).sendRedirect("/login");
    }

    @Test
    void test_doGet_loggedIn_renderHomePage() throws ServletException, IOException {
        testee.doGet(request, response);
        verify(session, times(1)).setAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY, null);
        verify(response, times(1)).sendRedirect("/login");
    }
}
