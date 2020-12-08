package model;

import java.io.Serializable;
/**
 * 等級情報のBean.
 * <br>DBから取得した情報を格納・表示に利用する
 * <br>コンストラクタとプライベート変数のセッター/ゲッターを持つ.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.Serializable
 */
public class GradeBean implements Serializable{
private int grade;
private float minSalary;
private float maxSalary;
private int check;

public GradeBean() {

}

public int getGrade() {
	return grade;
}
public void setGrade(int grade) {
	this.grade = grade;
}
public float getMinSalary() {
	return minSalary;
}
public void setMinSalary(float minSalary) {
	this.minSalary = minSalary;
}
public float getMaxSalary() {
	return maxSalary;
}
public void setMaxSalary(float maxSalary) {
	this.maxSalary = maxSalary;
}

public int getCheck() {
	return check;
}

public void setCheck(int check) {
	this.check = check;
}


}
