package dao;

import org.sql2o.Sql2o;

public class db {
    static Sql2o sql2o = new Sql2o("jdbc:h2:~/news-app.db;INIT=RUNSCRIPT from 'classpath:sql/createh2.sql'","","");
}
