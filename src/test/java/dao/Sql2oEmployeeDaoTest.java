package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import dao.*;
import models.*;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oEmployeeDaoTest {
    private Connection conn;
    private Sql2oEmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        employeeDao = new Sql2oEmployeeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        Employee employee = setUpEmployee();
        assertTrue(employeeDao.getAll().contains(employee));
    }

    @Test
    public void getAll() {
        Employee employee = setUpEmployee();
        Employee employee1 = setUpEmployee();
        assertEquals(2,employeeDao.getAll().size());
    }

    @Test
    public void findById() {
        Employee employee = setUpEmployee();
        Employee employee1 = setUpEmployee();
        assertEquals(employee, employeeDao.findById(employee.getId()));
    }

    @Test
    public void getAllEmployeesByDepartment() {
        Employee employee = setUpEmployee();
        List<Employee> allEmployeesByDepartment = employeeDao.getAllEmployeesByDepartment(employee.getDepartment_id());
        assertEquals(employee.getDepartment_id(),allEmployeesByDepartment.get(0).getDepartment_id());
    }

    @Test
    public void deleteById() {
        Employee employee = setUpEmployee();
        Employee employee1 = setUpEmployee();
        assertEquals(2,employeeDao.getAll().size());
        employeeDao.deleteById(employee.getId());
        assertEquals(1,employeeDao.getAll().size());
    }

    @Test
    public void clearAll() {
        Employee employee = setUpEmployee();
        Employee employee1 = setUpEmployee();
        employeeDao.clearAll();
        assertEquals(0,employeeDao.getAll().size());
    }

    //helper
    public Employee setUpEmployee(){
        Employee employee =new Employee ("Daisy","ict manager","In charge of the ict department",1);
        employeeDao.add(employee);
        return employee;
    }
}