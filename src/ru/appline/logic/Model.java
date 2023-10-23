package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//из него будем брать неободимые данные и бвыводить на экран
public class Model implements Serializable{
    private static final Model instance=new Model();
    private final Map<Integer,User> model;

    public static Model getInstance()
    {
        return instance;
    }
    private Model()
    {
        model=new HashMap<>();
        model.put(1,new User("Liza","LizaFam",24000));
        model.put(2,new User("Sanya","SanyaFam",37000));
        model.put(3,new User("Tanya","TanyaFam",49000));
        model.put(4,new User("Nataha","NatahaFam",59000));
    }
    public void add(User user, int id)
    {
        model.put(id,user);
    }
    public void delete(int id)
    {
        model.remove(id);
    }
    public void update(User user, int id)
    {
        model.replace(id,user);
    }

    public Map<Integer,User> getFromList()
    {
        return model;
    }
}
