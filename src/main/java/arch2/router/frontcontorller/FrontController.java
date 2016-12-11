package arch2.router.frontcontorller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arch2.router.RouteNotFoundException;
import arch2.router.Router;


@WebServlet("/")
public class FrontController extends HttpServlet {
    private Router router;

    @Override
    public void init() throws ServletException {
        try {
            router = new Router(getServletContext().getRealPath("routes.conf"));
        } catch (FileNotFoundException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String webAppPath = getServletContext().getContextPath();
            String templateUrlString = req.getRequestURI().replace(webAppPath, "");
            resp.getOutputStream().print(router.getRoute(URI.create(templateUrlString)).getTemplateName());
        } catch (RouteNotFoundException e) {
            resp.getOutputStream().print("<h2>"+e.getMessage()+"</h2>");
        }
    }
}
