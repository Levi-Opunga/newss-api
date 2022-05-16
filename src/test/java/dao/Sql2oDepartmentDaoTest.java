package dao;


import models.Articles;
import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public class Sql2oDepartmentDaoTest {
   // Sql2oArticlesDao dao = new Sql2oArticlesDao();
    Sql2oDepartmentDao dao = new Sql2oDepartmentDao();
    Connection conn;
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
        Department department = make_department();
        dao.add(department);
        assertEquals(true,department.equals(dao.getById(department.getId())));
    }
    @Test
    public void test_if_it_deletes_an_animalById(){
        Department department = make_department();
        dao.add(department);
        dao.deleteById(department.getId());
        assertEquals(0,dao.getAll().size());
    }
    @Test
    public void testIfItDeletesAll(){
        Department department = make_department();
        Department department1 = make_department();
        Department department2 = make_department();
        dao.add(department);
        dao.add(department1);
        dao.add(department2);
        dao.deleteAll();
        assertEquals(0,dao.getAll().size());
    }
    @Test
    public void TestIfItSaveUpdatesAnimal(){
        Department department = make_department();
        dao.add(department);
        department.setName("Accounting");
        dao.update(department);
        assertEquals(department, dao.getById(department.getId()));
    }



    public Articles make_article(){
        return new Articles("title","message",1);
    }
    public Department make_department(){
        return new Department("department","description");
    }


}