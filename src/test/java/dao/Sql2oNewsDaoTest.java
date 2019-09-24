package dao;

import org.h2.util.New;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.News;
import models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {
    private Connection conn;
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentDao departmentDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao =new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        News news=setUpNews();
        assertEquals(1,newsDao.getAll().size());
    }
    @Test
    public void add_asignsId(){
        News news=setUpNews();
        assertEquals(1,news.getId());
    }

    @Test
    public void getAll() {
        News news1=setUpNews();
        News news2= setUpNews();
        assertEquals(2,newsDao.getAll().size());
    }

    @Test
    public void getAllNewsByDepartment() {
        News news=setUpNews();
        List<News> allNewsByDepartmentId = newsDao.getAllNewsByDepartment(news.getDepartment_id());
        assertEquals(news.getDepartment_id(),allNewsByDepartmentId.get(0).getDepartment_id());
    }

    @Test
    public void deleteById() {
        News news=setUpNews();
        News news2= setUpNews();
        assertEquals(2,newsDao.getAll().size());
        newsDao.deleteById(news.getId());
        assertEquals(1,newsDao.getAll().size());
    }

    @Test
    public void clearAll() {
    }

    //helper
    public News setUpNews(){
        News news =new News("Review","The employees in this department are friendly","Daisy",2);
        newsDao.add(news);
        return news;
    }

}