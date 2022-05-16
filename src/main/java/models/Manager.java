package models;

public class Manager extends Staff {
    public Manager(String name, String email, String phone, String staffRole, int dept_id) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        String rank = "Manager";
        this.setRank(rank);
        this.setStaffRole(staffRole);
        this.setDept_id(dept_id);
    }
}
