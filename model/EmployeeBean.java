package model;

import java.io.Serializable;
import java.sql.Date;
/**
 * 社員情報のBean.
 * <br>DBから取得した情報を格納・表示に利用する
 * <br>コンストラクタとプライベート変数のセッター/ゲッターを持つ.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.Serializable
 */
public class EmployeeBean implements Serializable{
private int employeeNo;
private String employeeName;
private String job;
private int managerNo;
private String managerName;
private Date joiningCompanyDate;
private float salary;
private float allowance;
private int department;
private String departmentName;
private int grade;

private int check;

public EmployeeBean(){

}

public int getEmployeeNo() {
	return employeeNo;
}
public void setEmployeeNo(int employeeNo) {
	this.employeeNo = employeeNo;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}
public int getManagerNo() {
	return managerNo;
}
public void setManagerNo(int managerNo) {
	this.managerNo = managerNo;
}

public Date getJoiningCompanyDate() {
	return joiningCompanyDate;
}

public void setJoiningCompanyDate(Date joiningCompanyDate) {
	this.joiningCompanyDate = joiningCompanyDate;
}

public float getSalary() {
	return salary;
}
public void setSalary(float salary) {
	this.salary = salary;
}
public float getAllowance() {
	return allowance;
}
public void setAllowance(float allowance) {
	this.allowance = allowance;
}
public int getDepartment() {
	return department;
}
public void setDepartment(int department) {
	this.department = department;
}

public String getManagerName() {
	return managerName;
}

public void setManagerName(String managerName) {
	this.managerName = managerName;
}

public String getDepartmentName() {
	return departmentName;
}

public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}



public int getCheck() {
	return check;
}

public void setCheck(int check) {
	this.check = check;
}

public int getGrade() {
	return grade;
}

public void setGrade(int grade) {
	this.grade = grade;
}


}
