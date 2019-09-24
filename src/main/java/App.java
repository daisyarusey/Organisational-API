import com.google.gson.Gson;
import dao.Sql2oEmployeeDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import models.Department;
import org.sql2o.*;
import static  spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oEmployeeDao employeeDao;
        Connection conn;
        Gson gson= new Gson();

        String connectionString = "jdbc:h2:~/organization_Api.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        employeeDao = new Sql2oEmployeeDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();

        //post new department
        post("/departments/new", "application/json", (req, res) -> { // here we accept a request in JSON
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);// update the response status code
            res.type("application/json");
            return gson.toJson(department);
        });

        //get all departments
        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(departmentDao.getAll());//send it back to be displayed
        });

        //find department by id
        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int department_id = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(department_id));
        });
    }
}
