package model;

import java.io.Serializable;
/**
 * 利用者情報のBean.
 * <br>DBから取得した情報を格納・表示に利用する
 * <br>コンストラクタとプライベート変数のセッター/ゲッターを持つ.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.Serializable
 */

public class UserBean implements Serializable{
private String userId;
private String pass;
private int employeeNo;
private String mail;
private int authority;
private int check;

public UserBean(){

}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public int getEmployeeNo() {
	return employeeNo;
}

public void setEmployeeNo(int employeeNo) {
	this.employeeNo = employeeNo;
}

public int getAuthority() {
	return authority;
}

public void setAuthority(int authority) {
	this.authority = authority;
}

public int getCheck() {
	return check;
}

public void setCheck(int check) {
	this.check = check;
}


}
