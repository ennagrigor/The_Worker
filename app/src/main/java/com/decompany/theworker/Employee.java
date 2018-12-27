package com.decompany.theworker;

public class Employee {
    String uId;
    String name;
    String position;
    String email;
    String phone;
    String managerName;
    String department;
    String linkToPhoto;
    String password;
    String kindOfEmployee;
    String salary;

    public Employee(String uid, String name, String position, String email, String phone, String managerName, String department, String linkToPhoto, String password, String kindOfEmployee, String salary) {
        this.uId = uid;
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.managerName = managerName;
        this.department = department;
        this.linkToPhoto = linkToPhoto;
        this.password = password;
        this.kindOfEmployee = kindOfEmployee;
        this.salary = salary;
    }

    public Employee() {}

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLinkToPhoto() {
        return linkToPhoto;
    }

    public void setLinkToPhoto(String linkToPhoto) {
        this.linkToPhoto = linkToPhoto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKindOfEmployee() {
        return kindOfEmployee;
    }

    public void setKindOfEmployee(String kindOfEmployee) {
        this.kindOfEmployee = kindOfEmployee;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
