package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/del")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();

    Gson gson=new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder() используют для корректного возращения json
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        int id= Integer.parseInt(request.getParameter("id"));
        model.delete(id);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(gson.toJson(model.getFromList())); //возвращаем полный обнволенный список пользователей на наш запрос

    }
}
