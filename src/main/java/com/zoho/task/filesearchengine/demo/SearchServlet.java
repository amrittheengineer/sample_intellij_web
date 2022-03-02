package com.zoho.task.filesearchengine.demo;

import java.io.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "SearchServlet", value = "/search-servlet")
public class SearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();

        out.println("<ul>");
        String query = request.getParameter("fileName");

        out.println("<h3>Query = " + query + "</h3>");
        int depth;
        String includeFolders = request.getParameter("includeFolders");
        try{
            depth = Integer.parseInt(request.getParameter("depth"));
        } catch (Exception ignored){
            depth = 4; // default depth is 4.
        }

        SearchFile.listFilesUsingDirectoryStream(
                query, out, depth,
                includeFolders != null && includeFolders.equals("on") // default is false
        );
        out.println("</ul>");

        out.close();
    }

    public void destroy() {
    }
}