package models;

import java.util.Objects;

public class Articles {
    int id;
   private String title;
  private String message;
  private int dept_id;

    public Articles(String title, String message, int dept_id) {
        this.title = title;
        this.message = message;
        this.dept_id = dept_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articles articles = (Articles) o;
        return id == articles.id && dept_id == articles.dept_id && title.equals(articles.title) && message.equals(articles.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, message, dept_id);
    }
}
