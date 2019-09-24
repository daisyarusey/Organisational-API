package dao;

import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
;
import org.sql2o.Connection;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        Department department = setupDepartment();
        assertTrue(departmentDao.getAll().contains(department));
    }

    @Test
    public void getAll() {
        Department department = setupDepartment();
        Department department1= setupDepartment();
        assertEquals(2,departmentDao.getAll().size());
    }

    @Test
    public void findById() {
        Department department1 = setupDepartment();
        assertEquals(department1, departmentDao.findById(department1.getId()));
    }



    @Test
    public void deleteById() {
        Department department = setupDepartment();
        Department department1= setupDepartment();
        assertEquals(2,departmentDao.getAll().size());
        departmentDao.deleteById(department.getId());
        assertEquals(1,departmentDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Department department = setupDepartment();
        Department department1= setupDepartment();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }

    public Department setupDepartment(){
        Department department= new Department("ICT", "ict support", 15);
        departmentDao.add(department);
        return department;
    }
}