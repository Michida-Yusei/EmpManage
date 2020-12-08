package model;

import java.io.Serializable;
/**
 * 部署ごと及び職種ごとの人数と平均給与のBean.
 * <br>DBから取得した情報を格納・表示に利用する
 * <br>コンストラクタとプライベート変数のセッター/ゲッターを持つ.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.Serializable
 */
public class SalaryBean implements Serializable{

private int departmentNo;
private String departmentName;
private String job;
private float averageSalary;
private int ninzu;

public SalaryBean() {

}

public float getAverageSalary() {
	return averageSalary;
}

public void setAverageSalary(float averageSalary) {
	this.averageSalary = averageSalary;
}

public int getNinzu() {
	return ninzu;
}

public void setNinzu(int ninzu) {
	this.ninzu = ninzu;
}

public int getDepartmentNo() {
	return departmentNo;
}

public void setDepartmentNo(int departmentNo) {
	this.departmentNo = departmentNo;
}

public String getDepartmentName() {
	return departmentName;
}

public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}

public String getJob() {
	return job;
}

public void setJob(String job) {
	this.job = job;
}


}
