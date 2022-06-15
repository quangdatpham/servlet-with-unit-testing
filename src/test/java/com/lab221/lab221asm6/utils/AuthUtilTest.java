package com.lab221.lab221asm6.utils;

import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.repositories.UserRepository;
import com.lab221.lab221asm6.services.UserService;
import com.lab221.lab221asm6.servlets.SecurityQuestionServlet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthUtilTest {

    private UserModel currentUser;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        Connection connection = DatabaseConnection.getConnection();
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
    void test_isAuthenticated_notLoggedIn_renderLoginPage() throws ServletException, IOException {
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(null);
        boolean result = AuthUtil.isAuthenticated(request, response);
        assertFalse(result);
        verify(request, times(1)).getRequestDispatcher("login.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_isAuthenticated_loggedIn_returnTrue() throws ServletException, IOException {
        boolean result = AuthUtil.isAuthenticated(request, response);
        assertTrue(result);
    }

    @Test
    void test_isAuthenticated_firstTimeLogin_renderSecurityQuestionPage() throws ServletException, IOException {
        currentUser.setFirstTimeLogin(true);
        boolean result = AuthUtil.isAuthenticated(request, response);
        assertFalse(result);
        verify(request, times(1)).getRequestDispatcher("security-question.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_isAuthenticated_hasToChangePassword_renderChangePasswordPage() throws ServletException, IOException {
        currentUser.setHasToChangePassword(true);
        boolean result = AuthUtil.isAuthenticated(request, response);
        assertFalse(result);
        verify(request, times(1)).getRequestDispatcher("change-password.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
