import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post/")
public class PostHandler extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        RequestDispatcher view = req.getRequestDispatcher("form.html");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        
        out.println(HtmlConstants.ENCODING);

        out.println(HtmlConstants.TABLE_HEADER);

        Enumeration<String> names = req.getParameterNames();
        List<String> namesList = new ArrayList<>();

        while ( names.hasMoreElements() ) {
            namesList.add(names.nextElement());

        }

        for ( String name : namesList ) {
            out.println(String.format(HtmlConstants.TABLE_ROW, name, req.getParameter(name)));
        }
    }
}
