package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oNewsDao  implements NewsDao{
    private final Sql2o sql2o;
    public Sql2oNewsDao(Sql2o sql2o) { this.sql2o = sql2o;}

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (title, content,writtenBy,department_id) VALUES (:title, :content, :writtenBy, :department_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<News> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> getAllNewsByDepartment(int department_id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE department_id = :department_id")
                    .addParameter("department_id", department_id)
                    .executeAndFetch(News.class);
        }
    }
    @Override
    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE id=:id;";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public void update(int id, String title, String content, String writtenBy, int department_id) {
        String sql = "UPDATE news SET(title,content,writtenBy,department_id)=(:title,:content,:writtenBy,:department_id) WHERE id=id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("title",title)
                    .addParameter("content",content)
                    .addParameter("writtenBy",writtenBy)
                    .addParameter("department_id",department_id)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }



    @Override
    public void deleteById(int id) {
        String sql = "DELETE from news WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
