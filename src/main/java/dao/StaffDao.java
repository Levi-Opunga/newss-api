package dao;


import models.Staff;

import java.util.List;

public interface StaffDao {
    //Add
    void add(Staff staff);

    //Get by ID
    Staff getById(int id);

    //get all
    List<Staff> getAll();

    //update
    void update(Staff staff);

    //delete
    void deleteById(int id);
    void deleteAll();

}
