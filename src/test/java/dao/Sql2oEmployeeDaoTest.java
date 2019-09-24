package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import dao.*;
import models.*;

import static org.junit.Assert.*;

public class Sql2oEmployeeDaoTest {
    private Sql2oEmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        employeeDao = new Sql2oEmployeeDao(sql2o);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }
}