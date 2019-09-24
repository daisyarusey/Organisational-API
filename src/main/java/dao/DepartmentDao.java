package dao;

import models.Department;
import models.Employee;
import models.News;

import java.util.List;

public interface DepartmentDao {
    //create    department
    void add (Department department);

    //read
    List<Department> getAll();
    Department findById(int id);
    List<Employee> getAllEmployeesForADepartment(int department_id);
    List<News> getAllNewsForDepartment(int department_id);
    //update
    void update(int id,String name, String description, int total_employees);

    //delete
    void deleteById(int id);
    void clearAll();
}
