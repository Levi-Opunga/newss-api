import com.google.gson.Gson;
import dao.DepartmentDao;
import dao.Sql2oArticlesDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oStaffDao;
import exceptions.ApiException;
import models.Articles;
import models.Department;
import models.Staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oArticlesDao articlesDao = new Sql2oArticlesDao();
        Sql2oStaffDao staffDao = new Sql2oStaffDao();
        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
        Gson gson = new Gson();
        List<Integer> departmentNumbers = new ArrayList<>();

////////////////////For Articles//////////////////////
// deleting
        delete("/delete-article/:id", (req, res) -> {

            int Articles_id = Integer.parseInt(req.params(":id"));
            if (articlesDao.getById(Articles_id)== null){
                throw new ApiException(404, String.format("The Article with an ID of %s does not exist you cant delete it", Articles_id));
            }else{
            articlesDao.deleteById(Articles_id);
            return gson.toJson(articlesDao.getAll());}
        });

        delete("/delete-AllArticle", (req, res) -> {
if()
            articlesDao.deleteAll();
            return gson.toJson(articlesDao.getAll());
        });

// Displaying
        post("/add-article", (req, res) -> {
            Articles article = gson.fromJson(req.body(), Articles.class);
            if (departmentNumbers.contains(article.getDept_id())) {
                articlesDao.add(article);
                return gson.toJson(article);
            } else {
                throw new ApiException(404, String.format("The department ID you have allocated this article does not exist please make sure there is a department with an ID of '%s'", article.getDept_id()));
            }
        });

        get("/get-byArticleId/:id", (req, res) -> {

            int id = Integer.parseInt(req.params(":id"));
            Articles article = articlesDao.getById(id);
            return gson.toJson(article);
        });

        get("/get-allArticles", (req, res) -> {

            List<Articles> articleList = articlesDao.getAll();
            return gson.toJson(articleList);
        });
// Updating
        patch("/update_article/:id", (req, res) -> {

            Articles article = gson.fromJson(req.body(), Articles.class);
            int id = Integer.parseInt(req.params(":id"));
            article.setId(id);
            articlesDao.update(article);
            return gson.toJson(article);
        });

//
// tried out deleting using object for fun
//        post("/delete-article/", (req, res) -> {
//            Gson gson = new Gson();
//           ObjectId object = gson.fromJson(req.body(),ObjectId.class);
//            System.out.println(object.getNumber());
//
////            int Articles_id = Integer.parseInt(req.params(":id"));
//            articlesDao.deleteById(object.getNumber());
//            return gson.toJson(articlesDao.getAll());
//        });
//


///////////////// For Staff//////////////////////////////////////////
        // deleting
        delete("/delete-staff/:id", (req, res) -> {

            int staffId = Integer.parseInt(req.params(":id"));
            staffDao.deleteById(staffId);
            return gson.toJson(staffDao.getAll());
        });

        delete("/delete-AllStaff", (req, res) -> {

            staffDao.deleteAll();
            return gson.toJson(staffDao.getAll());
        });

// Displaying
        post("/add-Staff", (req, res) -> {

            Staff staff = gson.fromJson(req.body(), Staff.class);
            staffDao.add(staff);
            return gson.toJson(staff);
        });

        get("/get-byStaffId/:id", (req, res) -> {

            int id = Integer.parseInt(req.params(":id"));
            Staff staff = staffDao.getById(id);
            return gson.toJson(staff);
        });

        get("/get-allStaff", (req, res) -> {

            List<Staff> staffList = staffDao.getAll();
            return gson.toJson(staffList);
        });

// Updating
        patch("/update_staff/:id", (req, res) -> {

            Staff staff = gson.fromJson(req.body(), Staff.class);
            int id = Integer.parseInt(req.params(":id"));
            staff.setId(id);
            staffDao.update(staff);
            return gson.toJson(staff);
        });


////////////////For Department//////////////////////////////////////
// deleting
        delete("/delete-department/:id", (req, res) -> {
            int departmentId = Integer.parseInt(req.params(":id"));
            departmentNumbers.remove(((Integer) departmentId));
            departmentDao.deleteById(departmentId);
            return gson.toJson(departmentDao.getAll());
        });

        delete("/delete-AllDepartments", (req, res) -> {
            departmentNumbers.clear();
            departmentDao.deleteAll();
            return gson.toJson(departmentDao.getAll());
        });

// Displaying && Adding
        post("/add-Department", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            departmentNumbers.add(department.getId());
            return gson.toJson(department);
        });

        get("/get-byDepartmentId/:id", (req, res) -> {

            int id = Integer.parseInt(req.params(":id"));
            Department department = departmentDao.getById(id);
            return gson.toJson(department);
        });

        get("/get-allDepartments", (req, res) -> {

            List<Department> departmentList = departmentDao.getAll();
            return gson.toJson(departmentList);
        });

        get("/get-all/staff/:department-id", (req, res) -> {

            int id = Integer.parseInt(req.params(":department-id"));
            List<Staff> staffList = departmentDao.getAllStaff(id);
            return gson.toJson(staffList);
        });
        get("/get-all/articles/:department-id", (req, res) -> {

            int id = Integer.parseInt(req.params(":department-id"));
            List<Articles> articlesList = departmentDao.getAllArticles(id);
            return gson.toJson(articlesList);
        });

// Updating
        patch("/update_department/:id", (req, res) -> {

            Department department = gson.fromJson(req.body(), Department.class);
            int id = Integer.parseInt(req.params(":id"));
            department.setId(id);
            departmentDao.update(department);
            return gson.toJson(department);
        });

/////////////////////filter////////////////////////////

        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });
        after((req, res) -> {
            res.type("application/json");
        });

    }


}
