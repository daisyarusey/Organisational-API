package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        Employee employee=setEmployee();
        assertEquals("Daisy",employee.getName());
    }

    @Test
    public void getPosition() {
        Employee employee=setEmployee();
        assertEquals("ict manager",employee.getPosition());
    }

    @Test
    public void getRole() {
        Employee employee=setEmployee();
        assertEquals("In charge of the ict department",employee.getRole());
    }


    @Test
    public void getDepartment_id() {
        Employee employee=setEmployee();
        assertEquals(1,employee.getDepartment_id());
    }

    //helper
    public  Employee setEmployee(){
        return new Employee("Daisy","ict manager","In charge of the ict department",1);
    }
}