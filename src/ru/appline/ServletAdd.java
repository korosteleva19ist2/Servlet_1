package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
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
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
//extends HttpServlet указывает, что класс является сервлетом
public class ServletAdd extends HttpServlet {
    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson=new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder() используют для корректного возращения json


    /*rotected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //в первую очередь должны выдавать html страничку
        response.setContentType("text/html; charset=utf-8");
        //для адекватной обработки вводимых русских символов в форму
        request.setCharacterEncoding("utf-8");
        PrintWriter pw=response.getWriter();

        String name=request.getParameter("name"); //передает значение, введенное в поле имя
        String surname=request.getParameter("surname");
        double salary= Double.parseDouble(request.getParameter("salary"));


        //создаем нвоых пользователей
        User user=new User(name,surname,salary);
        model.add(user,counter.getAndIncrement()); //getAndIncrement() возьмет 5 и увеличит на 1 значение инкремента и при каждом вызове будет увеличивать на 1
        pw.print("<html>"+"<h3>Пользователь "+name+" "+surname+" с зарплатой равной"+" "+salary+ " "+ "Успешно создан</h3>"
        + "<a href=\"addUSer.html\">Создать нового пользователя</a><br/>" +
                "<a href=\"index.jsp\"> домой</a>"+"</html>");

    }*/

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //чтобы мы могли считывать тело нашего запроса
        StringBuffer jb= new StringBuffer();
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

        JsonObject jobj=gson.fromJson(String.valueOf(jb), JsonObject.class);

        //JsonObject jobj=gson.fromJson(jb.toString(), JsonObject.class);
        //JsonElement jobj=gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("utf-8");
        //парсим json на получение данных
        String name=jobj.get("name").getAsString();
        String surname=jobj.get("surname").getAsString();
        double salary=jobj.get("salary").getAsDouble();

        User user=new User(name,surname,salary);
        model.add(user,counter.getAndIncrement());

        //сервлет должен возвращать данные в формате json
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(gson.toJson(model.getFromList())); //возвращаем полный обнволенный список пользователей на наш запрос

    }

}
