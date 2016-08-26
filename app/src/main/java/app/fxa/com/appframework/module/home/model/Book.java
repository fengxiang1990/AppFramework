package app.fxa.com.appframework.module.home.model;

/**
 * Created by fengxiang on 2016/8/25.
 */
public class Book {
    public int id;
    public String name;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
