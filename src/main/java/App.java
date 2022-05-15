import com.google.gson.Gson;
import dao.DepartmentDao;
import dao.Sql2oArticlesDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oStaffDao;
import models.Articles;
import models.Department;
import models.Staff;

import java.util.List;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oArticlesDao articlesDao = new Sql2oArticlesDao();
        Sql2oStaffDao staffDao = new Sql2oStaffDao();
        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao();
////////////////////For Articles//////////////////////
// deleting
        delete("/delete-article/:id", (req, res) -> {
            Gson gson = new Gson();
            int Articles_id = Integer.parseInt(req.params(":id"));
            articlesDao.deleteById(Articles_id);
            return gson.toJson(articlesDao.getAll());
        });

        delete("/delete-AllArticle", (req, res) -> {
            Gson gson = new Gson();
            articlesDao.deleteAll();
            return gson.toJson(articlesDao.getAll());
        });

// Displaying
        post("/add-article", (req, res) -> {
            Gson gson = new Gson();
            Articles article = gson.fromJson(req.body(), Articles.class);
            articlesDao.add(article);
            System.out.println(articlesDao.getAll().size());
            System.out.println(article.getDepartment());
            return gson.toJson(article);
        });

        get("/get-byArticleId/:id", (req, res) -> {
            Gson gson = new Gson();
            int id = Integer.parseInt(req.params(":id"));
            Articles article = articlesDao.getById(id);
            return gson.toJson(article);
        });

        get("/get-allArticles", (req, res) -> {
            Gson gson = new Gson();
            List<Articles> articleList = articlesDao.getAll();
            return gson.toJson(articleList);
        });
// Updating
        patch("/update_article/:id", (req, res) -> {
            Gson gson = new Gson();
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
            Gson gson = new Gson();
            int staffId = Integer.parseInt(req.params(":id"));
            staffDao.deleteById(staffId);
            return gson.toJson(staffDao.getAll());
        });

        delete("/delete-AllStaff", (req, res) -> {
            Gson gson = new Gson();
            staffDao.deleteAll();
            return gson.toJson(staffDao.getAll());
        });

// Displaying
        post("/add-Staff", (req, res) -> {
            Gson gson = new Gson();
            Staff staff = gson.fromJson(req.body(), Staff.class);
            staffDao.add(staff);
            return gson.toJson(staff);
        });

        get("/get-byStaffId/:id", (req, res) -> {
            Gson gson = new Gson();
            int id = Integer.parseInt(req.params(":id"));
            Staff staff = staffDao.getById(id);
            return gson.toJson(staff);
        });

        get("/get-allStaff", (req, res) -> {
            Gson gson = new Gson();
            List<Staff> staffList = staffDao.getAll();
            return gson.toJson(staffList);
        });

// Updating
        patch("/update_staff/:id", (req, res) -> {
            Gson gson = new Gson();
            Staff staff = gson.fromJson(req.body(), Staff.class);
            int id = Integer.parseInt(req.params(":id"));
            staff.setId(id);
            staffDao.update(staff);
            return gson.toJson(staff);
        });


////////////////For Department//////////////////////////////////////
// deleting
        delete("/delete-department/:id", (req, res) -> {
            Gson gson = new Gson();
            int departmentId = Integer.parseInt(req.params(":id"));
            departmentDao.deleteById(departmentId);
            return gson.toJson(departmentDao.getAll());
        });

        delete("/delete-AllDepartments", (req, res) -> {
            Gson gson = new Gson();
            departmentDao.deleteAll();
            return gson.toJson(departmentDao.getAll());
        });

// Displaying && Adding
        post("/add-Department", (req, res) -> {
            Gson gson = new Gson();
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            return gson.toJson(department);
        });

        get("/get-byDepartmentId/:id", (req, res) -> {
            Gson gson = new Gson();
            int id = Integer.parseInt(req.params(":id"));
            Department department = departmentDao.getById(id);
            return gson.toJson(department);
        });

        get("/get-allDepartments", (req, res) -> {
            Gson gson = new Gson();
            List<Department> departmentList = departmentDao.getAll();
            return gson.toJson(departmentList);
        });

        get("/get-all/staff/:department-id", (req, res) -> {
            Gson gson = new Gson();
            int id = Integer.parseInt(req.params(":department-id"));
            List<Staff> staffList = departmentDao.getAllStaff(id);
            return gson.toJson(staffList);
        });
        get("/get-all/articles/:department-id",(req, res) ->{
            Gson gson = new Gson();
            int id = Integer.parseInt(req.params(":department-id"));
            List<Articles> articlesList = departmentDao.getAllArticles(id);
            return gson.toJson(articlesList);
        });

// Updating
        patch("/update_department/:id", (req, res) -> {
            Gson gson = new Gson();
            Department department = gson.fromJson(req.body(), Department.class);
            int id = Integer.parseInt(req.params(":id"));
            department.setId(id);
            departmentDao.update(department);
            return gson.toJson(department);
        });

/////////////////////filter////////////////////////////

        after((req, res) -> {
            res.type("application/json");
        });

    }


}
