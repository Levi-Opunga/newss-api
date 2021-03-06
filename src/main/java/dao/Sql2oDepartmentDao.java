package dao;

import models.Articles;
import models.Department;
import models.Staff;
import org.sql2o.Connection;

import java.util.List;

import static dao.db.sql2o;

public class Sql2oDepartmentDao implements DepartmentDao {

    @Override
    public void add(Department department) {
        try (Connection conn = sql2o.open()) {
            String sql = "INSERT into departments (name,description) values (:name,:description)";
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("name", department.getName())
                    .addParameter("description", department.getDescription())
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public Department getById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * from departments WHERE id=:id ";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM departments";
            return con.createQuery(sql).executeAndFetch(Department.class);
        }
    }

    @Override
    public void update(Department department) {
        try (Connection con = sql2o.open()) {
            String sql = "Update departments set (name,description) =(:name,:description) WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("name", department.getName())
                    .addParameter("description", department.getDescription())
                    .addParameter("id", department.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE from departments WHERE id=:id";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }

    }


    @Override
    public void deleteAll() {
        try (Connection con = sql2o.open()) {
            String sql = "TRUNCATE table departments";
            con.createQuery(sql).executeUpdate();
        }

    }

    @Override
    public List<Articles> getAllArticles(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * from articles where dept_id=:dept_id";
            return con.createQuery(sql)
                    .addParameter("dept_id", id)
                    .executeAndFetch(Articles.class);
        }

    }

    @Override
    public List<Staff> getAllStaff(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * from staff where dept_id=:dept_id";
            return con.createQuery(sql)
                    .addParameter("dept_id", id)
                    .executeAndFetch(Staff.class);
        }
    }
}
