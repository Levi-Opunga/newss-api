package dao;


import models.Staff;
import models.Department;
import models.Staff;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oStaffDaoTest {
    Sql2oStaffDao dao = new Sql2oStaffDao();

    Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
    Connection conn;
    @Before
    public void setup() throws Exception {
        db.sql2o = new Sql2o("jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:sql/createh2.sql'","","");
        conn = db.sql2o.open();
        Department department = make_department();
       departmentDao.add(department);
    }
    @After

    public void tearDown() throws Exception {
        conn.close();
    }


    @Test
    public void testIfItSavesAndIfFindByIdWorks() throws Exception {
        Staff staff = make_staff();
        dao.add(staff);
        assertEquals(true,staff.equals(  dao.getById(staff.getId())));
    }
    @Test
    public void test_if_it_deletes_an_animalById(){
        Staff staff = make_staff();
        dao.add(staff);
        dao.deleteById(staff.getId());
        assertEquals(0,dao.getAll().size());
    }
    @Test
    public void testIfItDeletesAll(){
        Staff staff = make_staff();
        Staff staff1 = make_staff();
        Staff staff2 = make_staff();
        dao.add(staff);
        dao.add(staff1);
        dao.add(staff2);
        dao.deleteAll();
        assertEquals(0,dao.getAll().size());
    }
    @Test
    public void TestIfItUpdatesAnimal(){
        Staff staff = make_staff();
        dao.add(staff);
        staff.setName("Cynthia");
        dao.update(staff);
        System.out.println(staff.getName());
        assertTrue(staff.equals(dao.getById(staff.getId())));
    }



    public Staff make_staff() {
        return new Staff("name", "email", "078899", "rank", "role", 1);
    }
    public Department make_department(){
        return new Department("department","description");
    }


}