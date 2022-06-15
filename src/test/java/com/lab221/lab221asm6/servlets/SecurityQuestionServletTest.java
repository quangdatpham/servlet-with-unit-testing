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
import java.sql.Connection;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SecurityQuestionServletTest {

    private SecurityQuestionServlet testee;
    private UserModel currentUser;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private HttpSession session;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        Connection connection = DatabaseConnection.getConnection();
        userService = new UserService(new UserRepository(connection));
        testee = new SecurityQuestionServlet();
        testee.init();
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
        userService.create(currentUser);
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(currentUser);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @AfterEach
    public void cleanUp() {
        userService.delete(currentUser.getId());
    }

    @Test
    void test_doGet_notLoggedIn_renderLoginPage() throws ServletException, IOException {
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(null);
        testee.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("login.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doGet_loggedIn_renderSecurityQuestionPage() throws ServletException, IOException {
        testee.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("security-question.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_notLoggedIn_renderLoginPage() throws ServletException, IOException {
        when(session.getAttribute(Constants.CURRENT_USER_ATTRIBUTE_KEY)).thenReturn(null);
        testee.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher("login.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_missingFirstQuestion_renderSecurityQuestionPage() throws ServletException, IOException {
        testee.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher("security-question.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_firstQuestionAndAnswerProvided_renderSecurityQuestionPage() throws ServletException, IOException {
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        testee.doPost(request, response);
        verify(response, times(1)).sendRedirect("/home");
    }

    @Test
    void test_doPost_missingFirstAnswer_renderSecurityQuestionPage() throws ServletException, IOException {
        when(request.getParameter("question1")).thenReturn("A question");
        testee.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher("security-question.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_firstQAProvidedAndFirstTimeLogin_renderChangePassword() throws ServletException, IOException {
        currentUser.setFirstTimeLogin(true);
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        testee.doPost(request, response);
        verify(response, times(1)).sendRedirect("/change-password");
    }

    @Test
    void test_doPost_secondQuestionProvidedWithoutAnswer_renderSecurityQuestionPage() throws ServletException, IOException {
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        when(request.getParameter("question2")).thenReturn("A question");
        when(request.getParameter("answer2")).thenReturn("");
        testee.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher("security-question.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_secondQuestionAndAnswerProvided_renderSecurityQuestionPage() throws ServletException, IOException {
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        when(request.getParameter("question2")).thenReturn("A question");
        when(request.getParameter("answer2")).thenReturn("An answer");
        testee.doPost(request, response);
        verify(response, times(1)).sendRedirect("/home");
    }

    @Test
    void test_doPost_secondQAProvidedAndFirstTimeLogin_renderChangePassword() throws ServletException, IOException {
        currentUser.setFirstTimeLogin(true);
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        when(request.getParameter("question2")).thenReturn("A question");
        when(request.getParameter("answer2")).thenReturn("An answer");
        testee.doPost(request, response);
        verify(response, times(1)).sendRedirect("/change-password");
    }

    @Test
    void test_doPost_thirdQuestionProvidedWithoutAnswer_renderSecurityQuestionPage() throws ServletException, IOException {
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        when(request.getParameter("question2")).thenReturn("A question");
        when(request.getParameter("answer2")).thenReturn("An answer");
        when(request.getParameter("question3")).thenReturn("A question");
        when(request.getParameter("answer3")).thenReturn("");
        testee.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher("security-question.jsp");
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void test_doPost_thirdQuestionAndAnswerProvided_renderSecurityQuestionPage() throws ServletException, IOException {
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        when(request.getParameter("question2")).thenReturn("A question");
        when(request.getParameter("answer2")).thenReturn("An answer");
        when(request.getParameter("question3")).thenReturn("A question");
        when(request.getParameter("answer3")).thenReturn("An answer");
        testee.doPost(request, response);
        verify(response, times(1)).sendRedirect("/home");
    }

    @Test
    void test_doPost_thirdQAProvidedAndFirstTimeLogin_renderChangePassword() throws ServletException, IOException {
        currentUser.setFirstTimeLogin(true);
        when(request.getParameter("question1")).thenReturn("A question");
        when(request.getParameter("answer1")).thenReturn("An answer");
        when(request.getParameter("question2")).thenReturn("A question");
        when(request.getParameter("answer2")).thenReturn("An answer");
        when(request.getParameter("question3")).thenReturn("A question");
        when(request.getParameter("answer3")).thenReturn("An answer");
        testee.doPost(request, response);
        verify(response, times(1)).sendRedirect("/change-password");
    }
}
