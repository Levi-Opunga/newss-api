package dao;

import models.Articles;
import org.sql2o.*;

import java.util.List;

public class Sql2oArticlesDao implements ArticlesDao {

    @Override
    public void add(Articles article) {
        try (Connection conn = db.sql2o.open()) {
            String sql = "INSERT into articles (title,message,dept_id) values (:title,:message,:dept_id)";
            int id = (int) conn.createQuery(sql)
                    .addParameter("title", article.getTitle())
                    .addParameter("message", article.getMessage())
                    .addParameter("dept_id", article.getDept_id())
                    .executeUpdate()
                    .getKey();
            article.setId(id);
        } catch (Exception e) {
        }

    }

    @Override
    public Articles getById(int id) {
        try (Connection con = db.sql2o.open()){
            String sql = "SELECT * from articles WHERE id=:id ";
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Articles.class);
        }

            }

    @Override
    public List<Articles> getAll() {
        try (Connection con = db.sql2o.open()){
            String sql = "SELECT * FROM articles";
            return con.createQuery(sql).executeAndFetch(Articles.class);
        }
    }

    @Override
    public void update(Articles article) {
       try (Connection con = db.sql2o.open()){
           String sql = "Update articles set (title,message,dept_id) =(:title,:message,:dept_id) WHERE id=:id;";
        con.createQuery(sql)
                .addParameter("title", article.getTitle())
                .addParameter("message", article.getMessage())
                .addParameter("dept_id", article.getDept_id())
                .addParameter("id",article.getId())
                .executeUpdate();
       }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = db.sql2o.open()){
            String sql = "DELETE from articles WHERE id=:id";
        con.createQuery(sql)
                .addParameter("id",id)
                .executeUpdate();
        }

        }


    @Override
    public void deleteAll() {
        try (Connection con = db.sql2o.open()){
            String sql = "TRUNCATE table articles";
            con.createQuery(sql).executeUpdate();
        }

    }
}
