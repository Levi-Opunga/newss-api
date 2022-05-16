package dao;

import models.Articles;
import models.Department;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class Sql2oArticlesDaoTest {
    Sql2oArticlesDao dao = new Sql2oArticlesDao();
    Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
    Connection  conn;
    @Before
    public void setup() throws Exception {
        db.sql2o = new Sql2o("jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:sql/createh2.sql'","","");
        conn = db.sql2o.open();
    }
@After

        public void tearDown() throws Exception {
            conn.close();
        }


    @Test
    public void testIfItSavesAndIfFindByIdWorks() throws Exception {
        Articles articles = make_article();
        departmentDao.add(make_department());
        dao.add(articles);
        assertEquals(true,articles.equals(dao.getById(articles.getId())));
    }
    @Test
    public void test_if_it_deletes_an_animalById(){
        Articles articles = make_article();
        dao.addGeneralArticle(articles);
        dao.deleteById(articles.getId());
        assertEquals(0,dao.getAll().size());
    }
    @Test
    public void testIfItDeletesAll(){
        Articles articles = make_article();
        Articles articles1 = make_article();
        Articles articles2 = make_article();
        dao.addGeneralArticle(articles);
        dao.addGeneralArticle(articles1);
        dao.addGeneralArticle(articles2);
        dao.deleteAll();
        assertEquals(0,dao.getAll().size());
    }
    @Test
    public void TestIfItSaveUpdatesAnimal(){
        Articles article = make_article();
        dao.addGeneralArticle(article);
        article.setTitle("Koala");
        dao.update(article);
        assertEquals(article, dao.getById(article.getId()));
    }



    public Articles make_article(){
        return new Articles("title","message",1);
    }
public Department make_department(){
        return new Department("department","description");
}

}