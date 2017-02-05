package arch2.router.frontcontroller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arch2.data.DataRepository;
import arch2.data.StubDataRepositoryImpl;
import arch2.router.RouteNotFoundException;
import arch2.router.Router;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@WebServlet("/")
public class FrontController extends HttpServlet {
    private Router router;
    // TODO move to configs
    private final static String TEMPLATES_PATH = "templates/";
    private final static String RESOURCES_PATH = "resources/";
    private Configuration templateConfig = new Configuration();
    private DataRepository dataRepository = new StubDataRepositoryImpl();
    

    @Override
    public void init() throws ServletException {
        try {
            router = new Router(getServletContext().getRealPath(RESOURCES_PATH + "routes.conf"));
            String templateLoadingDirectory = getServletContext().getRealPath(RESOURCES_PATH + TEMPLATES_PATH);
            getServletContext().log("Template loading directory: " + templateLoadingDirectory);
            templateConfig.setDirectoryForTemplateLoading(new File(templateLoadingDirectory));
            dataRepository.initialize();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get from request url name of needed template
            String webAppPath = getServletContext().getContextPath();
            String templateUrlString = req.getRequestURI().replace(webAppPath, "");
            getServletContext().log("Template url string: " + templateUrlString);
            
            // Get template name
            String templateName = router.getRoute(URI.create(templateUrlString)).getTemplateName();
            getServletContext().log("Template name: " + templateName);
            
            // Set templates values
            // TODO create templates mapping to rendering functions
            Map<String, Object> templateValues = new HashMap<>();
            templateValues.put("categories", dataRepository.getAllCategories());
            renderTemplate(resp, templateName, templateValues);
        } catch (RouteNotFoundException |  TemplateException e) {
            resp.getOutputStream().print(e.getMessage());
        }
    }
    
    private void renderTemplate(HttpServletResponse response, String templateName, Map<String, Object> values) 
            throws IOException, TemplateException {
        
        Template template = templateConfig.getTemplate(templateName);
        Writer templateWriter = new OutputStreamWriter(response.getOutputStream());
        template.process(values, templateWriter);
    }
}
