package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
//получение данных из мапы
public class ServletList extends HttpServlet {
    Model model=Model.getInstance();
    Gson gson=new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder() используют для корректного возращения json
    protected void doGet (HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");

        PrintWriter pw=response.getWriter();
        int id= Integer.parseInt(request.getParameter("id"));
        if(id==0)
        {
            pw.print(gson.toJson(model.getFromList()));
        } else if(id>0)
        {
            if(id>model.getFromList().size())
                pw.print("Такого пользователя нет");
            else
                pw.print(gson.toJson(model.getFromList().get(id)));
        }
        else
        {
            pw.print("ID должен быть больше нуля");
        }

    }

}
