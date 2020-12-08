package model;

import java.io.Serializable;
/**
 * 部署情報のBean.
 * <br>DBから取得した情報を格納・表示に利用する
 * <br>コンストラクタとプライベート変数のセッター/ゲッターを持つ.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.Serializable
 */
public class DepartmentBean implements Serializable{
private int departmentNo;
private String departmentName;
private String address;
private int check;

public DepartmentBean(){

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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getCheck() {
	return check;
}
public void setCheck(int check) {
	this.check = check;
}


}
