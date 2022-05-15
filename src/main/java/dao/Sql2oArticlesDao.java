package dao;

import models.Articles;
import models.Department;
import org.sql2o.*;

import java.util.List;

import static dao.db.sql2o;

public class Sql2oArticlesDao implements ArticlesDao {

    @Override
    public void add(Articles article) {
        String sql = "INSERT into articles (title,message,dept_id,department) values (:title,:message,:dept_id,:department);";
        String sql2 = "SELECT * from departments WHERE id=:id;";

        try (Connection conn = sql2o.open()) {
            String department = conn.createQuery(sql2)
                    .addParameter("id", article.getDept_id())
                    .executeAndFetchFirst(Department.class)
                    .getName();
            article.setDepartment(department);

            int id = (int) conn.createQuery(sql)
                    .addParameter("title", article.getTitle())
                    .addParameter("message", article.getMessage())
                    .addParameter("dept_id", article.getDept_id())
                    .addParameter("department", article.getDepartment())
                    .executeUpdate()
                    .getKey();
            article.setId(id);
        } catch (Exception e) {
        }

    }

    @Override
    public void addGeneralArticle(Articles article) {
        article.setDepartment("THIS IS A GENERAL COMPANY ARTICLE");
        article.setDept_id(0);
        String sql = "INSERT into articles (title,message,dept_id,department) values (:title,:message,:dept_id,:department);";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("title", article.getTitle())
                    .addParameter("message", article.getMessage())
                    .addParameter("dept_id", article.getDept_id())
                    .addParameter("department", article.getDepartment())
                    .executeUpdate()
                    .getKey();
            article.setId(id);
        }
    }

    @Override
    public Articles getById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * from articles WHERE id=:id ";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Articles.class);
        }

    }

    @Override
    public List<Articles> getAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM articles";
            return con.createQuery(sql).executeAndFetch(Articles.class);
        }
    }

    @Override
    public void update(Articles article) {
        try (Connection con = sql2o.open()) {
            String sql = "Update articles set (title,message,dept_id) =(:title,:message,:dept_id) WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("title", article.getTitle())
                    .addParameter("message", article.getMessage())
                    .addParameter("dept_id", article.getDept_id())
                    .addParameter("id", article.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE from articles WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }

    }


    @Override
    public void deleteAll() {
        try (Connection con = sql2o.open()) {
            String sql = "TRUNCATE table articles";
            con.createQuery(sql).executeUpdate();
        }

    }
}
