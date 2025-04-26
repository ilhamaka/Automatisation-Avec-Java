import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HelloServletTest {
    @Test
    public void testHelloServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        HelloServlet servlet = new HelloServlet();
        servlet.doGet(request,response);

        verify(response).getWriter();
        }
}