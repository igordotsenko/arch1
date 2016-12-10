package arch1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/")
public class CookieCounter extends HttpServlet {
    private static final String COOKIE_COUNTER_NAME = "cookie_counter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cookieCounter = 0;
        resp.setContentType("text/html");

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_COUNTER_NAME.equals(cookie.getName())) {
                    cookieCounter = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        resp.getWriter().println("<h1>Cookie counter = " + cookieCounter + "</hi>");
        cookieCounter += 1;
        Cookie cookie = new Cookie(COOKIE_COUNTER_NAME, "" + cookieCounter);
        resp.addCookie(cookie);
    }
}
