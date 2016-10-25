import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get/")
public class GetHandler extends HttpServlet {
    public static String TABLE_HEADER
            = "<table style=\"width:100%\""
              +  "border=\"1\"\n>";
    public static String TABLE_ROW
            = "\t<tr>\n"
              + "\t\t<th>%s</th>\n"
              + "\t\t<th>%s</th>\n"
              + "\t</tr>\n";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println(TABLE_HEADER);

        Enumeration<String> names = request.getParameterNames();
        List<String> namesList = new ArrayList<>();

        while ( names.hasMoreElements() ) {
            namesList.add(names.nextElement());

        }
        Collections.sort(namesList);

        for ( String name : namesList ) {
            out.println(String.format(TABLE_ROW, name, request.getParameter(name)));
        }
    }
}