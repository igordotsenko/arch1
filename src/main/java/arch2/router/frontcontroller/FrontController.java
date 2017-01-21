package arch2.router.frontcontroller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arch2.router.RouteNotFoundException;
import arch2.router.Router;


@WebServlet("/")
public class FrontController extends HttpServlet {
    private Router router;
    // TODO move to configs
    private final static String TEMPLATES_PATH = "templates/";
    private final static String RESOURCES_PATH = "resources/";

    @Override
    public void init() throws ServletException {
        try {
            router = new Router(getServletContext().getRealPath(RESOURCES_PATH + "routes.conf"));
        } catch (FileNotFoundException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get from request url name of needed template
            String webAppPath = getServletContext().getContextPath();
            String templateUrlString = req.getRequestURI().replace(webAppPath, "");
            
            // Construct full path to needed template
            String templateRoute = router.getRoute(URI.create(templateUrlString)).getTemplateName();
            String fullPath = getServletContext().getRealPath(RESOURCES_PATH + TEMPLATES_PATH + templateRoute);
            
            // Send template as response
            printFile(resp.getOutputStream(), new File(fullPath));
        } catch (RouteNotFoundException e) {
            resp.getOutputStream().print(e.getMessage());
        }
    }
    
    private void printFile(ServletOutputStream out, File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            out.println(scanner.nextLine());
        }
    }
}
