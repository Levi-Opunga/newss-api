package dao;

import models.Articles;

import java.util.List;

public interface ArticlesDao {

    //Add
   void add(Articles article);

   //Get by ID

    Articles getById(int id);

    //get all
     List<Articles> getAll();


    //update
    void update(Articles Article);

    //delete
    void deleteById(int id);
    void deleteAll();

}
