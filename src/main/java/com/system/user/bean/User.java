package com.system.user.bean;

public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String companyName;
    private String departmentName;
    private String employeeName;
    private String inDate;
    private String outDate;
    private Integer[] roleIds;
    private Integer employeeId;
    private boolean flag;
    
    public Integer[] getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getInDate() {
        return inDate;
    }
    public void setInDate(String inDate) {
        this.inDate = inDate;
    }
    public String getOutDate() {
        return outDate;
    }
    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    
}
