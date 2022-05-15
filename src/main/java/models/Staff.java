package models;

import java.util.Objects;

public class Staff {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String rank;
    private String staffRole;
    private int dept_id;
     String department;


    public Staff() {
    }

    public Staff(String name, String email, String phone, String rank, String staffRole, int dept_id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rank = rank;
        this.staffRole = staffRole;
        this.dept_id = dept_id;
        department = "";
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
       this.rank = rank;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id && dept_id == staff.dept_id && name.equals(staff.name) && email.equals(staff.email) && phone.equals(staff.phone) && rank.equals(staff.rank) && staffRole.equals(staff.staffRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, rank, staffRole, dept_id);
    }
}
