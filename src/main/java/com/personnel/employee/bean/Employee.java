package com.personnel.employee.bean;

import com.personnel.department.bean.Department;

/**
 * 员工
 * @author Administrator
 *
 */
public class Employee {
    private Integer id;
    private String name;//员工姓名
    private String number;//员工编码
    private String idCard;//身份证
    private String degree;//文化程度
    private String companyName;//公司
    private String departmentName;//部门
    private Integer departmentId;//部门id
    private String phone;//电话
    private String inDate;//入职时间
    private String outDate;//离职时间
    private String post;//岗位
    private String emergencyName;//紧急联系人
    private String emergencyPhone;//紧急联系人电话
    private boolean flag;//禁用标志
    
    private Department department;
    
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getEmergencyName() {
        return emergencyName;
    }
    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }
    public String getEmergencyPhone() {
        return emergencyPhone;
    }
    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
}
