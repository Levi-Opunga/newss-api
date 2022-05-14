package dao;

import models.Staff;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oStaffDao implements StaffDao {

    @Override
    public void add(Staff staff) {
        try (Connection conn = db.sql2o.open()) {
            String sql = "INSERT into staff (name,email,phone,rank,staffRole,dept_id) values (:name,:email,:phone,:rank,:staffRole,:dept_id)";
            int id = (int) conn.createQuery(sql)
                    .addParameter("name", staff.getName())
                    .addParameter("email", staff.getEmail())
                    .addParameter("phone", staff.getName())
                    .addParameter("rank", staff.getRank())
                    .addParameter("staffRole", staff.getStaffRole())
                    .addParameter("dept_id", staff.getDept_id())
                    .executeUpdate()
                    .getKey();
            staff.setId(id);
        } catch (Exception e) {
        }

    }

    @Override
    public Staff getById(int id) {
        try (Connection con = db.sql2o.open()){
            String sql = "SELECT * from staff WHERE id=:id ";
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Staff.class);
        }

    }

    @Override
    public List<Staff> getAll() {
        try (Connection con = db.sql2o.open()){
            String sql = "SELECT * FROM staff";
            return con.createQuery(sql).executeAndFetch(Staff.class);
        }
    }

    @Override
    public void update(Staff staff) {
        try (Connection con = db.sql2o.open()){
            String sql = "Update staff set (name,email,phone,rank,staffRole,dept_id) =(:name,:email,:phone,:rank,:staffRole,:dept_id) WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("name", staff.getName())
                    .addParameter("email", staff.getEmail())
                    .addParameter("phone", staff.getName())
                    .addParameter("rank", staff.getRank())
                    .addParameter("staffRole", staff.getStaffRole())
                    .addParameter("dept_id", staff.getDept_id())
                    .addParameter("id",staff.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = db.sql2o.open()){
            String sql = "DELETE from staff WHERE id=:id";
            con.createQuery(sql).addParameter("id",id).executeUpdate();
        }
    }


    @Override
    public void deleteAll() {
        try (Connection con = db.sql2o.open()){
            String sql = "TRUNCATE table staff";
            con.createQuery(sql).executeUpdate();
        }

    }
}
