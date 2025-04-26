import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import javax.servlet.http.*;
import java.io.*;

public class RegisterServletTest {

    @Test
    public void testSuccessfulRegistration() throws Exception {
        // Mock objects
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // Behavior
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("password123");
        when(response.getWriter()).thenReturn(writer);

        // Test servlet
        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        // Validate
        writer.flush(); // Important
        verify(response).setStatus(HttpServletResponse.SC_OK);
        assertTrue(stringWriter.toString().contains("Registration successful for testuser"));
    }

    @Test
    public void testMissingParameters() throws Exception {
        // Mock objects
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // Behavior
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);

        // Test servlet
        RegisterServlet servlet = new RegisterServlet();
        servlet.doPost(request, response);

        // Validate
        writer.flush(); // Important
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        assertTrue(stringWriter.toString().contains("Missing username or password."));
    }
}
