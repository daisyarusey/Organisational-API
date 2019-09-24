package dao;

import models.Employee;

import java.util.List;

public interface EmployeeDao {
    void add(Employee employee);


    //read
    List<Employee> getAll();
    Employee findById(int id);
    List<Employee> getAllEmployeesByDepartment(int department_id);
    //delete
    void deleteById(int id);
    void clearAll();
}
