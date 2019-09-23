package dao;

import models.News;

import java.util.List;

public interface NewsDao {

    //create
    void add (News news);

    //read
    List<News> getAll();
    List<News> getAllNewsByDepartment(int department_id);

    //delete
    void deleteById(int id);
    void clearAll();
}
