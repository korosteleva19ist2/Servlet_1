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

@WebServlet(urlPatterns = "/del")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();

    Gson gson=new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder() используют для корректного возращения json
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //чтобы мы могли считывать тело нашего запроса
        /*StringBuffer jb= new StringBuffer();
        //построчно будем записывать наш запрос в стрингбилдинг?
        String line;
        try{
            //будем читать данные из body (нашего request)
            BufferedReader reader=request.getReader();
            while((line=reader.readLine()) != null)
            {
                jb.append(line);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }

        JsonObject jobj=gson.fromJson(String.valueOf(jb), JsonObject.class);*/
        request.setCharacterEncoding("utf-8");
        //int id=jobj.get("id").getAsInt();
        //парсим json на получение данных
        int id= Integer.parseInt(request.getParameter("id"));
        model.delete(id);

        //сервлет должен возвращать данные в формате json
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(gson.toJson(model.getFromList())); //возвращаем полный обнволенный список пользователей на наш запрос

    }
}
