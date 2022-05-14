package dao;

import models.Articles;
import models.Department;

import java.util.List;

public interface ArticlesDao {

    //Add
   void add(Articles article);

   //Get by ID

    Articles getIdById(int id);

    //get all
     List<Articles> getAll();


    //update
    void update(Articles Article);

    //delete
    void deleteById(int id);
    void deleteAll();

}
