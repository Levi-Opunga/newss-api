package dao;

import models.Articles;
import models.Department;
import models.Staff;

import java.util.List;

public interface DepartmentDao {

    //Add
    void add(Department staff);

    //Get by ID
    Department getIdById(int id);
    List<Department> getAll();

    //update
    void update(Department department);

    //delete
    void deleteById(int id);
    void deleteAll();

  List<Articles>  getAllArticles(int id);

}
