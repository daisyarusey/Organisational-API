package dao;

import models.Department;
import models.Employee;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }
    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (name,description,total_employees) VALUES (:name,:description,:total_employees)";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }



    @Override
    public Department findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Employee> getAllEmployeesForADepartment(int department_id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees WHERE department_id=:department_id")
                    .executeAndFetch(Employee.class);
        }
    }

    @Override
    public List<News> getAllNewsForDepartment(int department_id) {
        String sql = "SELECT * FROM news where department_id=:department_id";
        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("departmentId", department_id)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void update(int id, String name, String description, int total_employees) {
        String sql = "UPDATE departments SET(name,description)=(:name,:description) WHERE id=id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name",name)
                    .addParameter("description",description)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
