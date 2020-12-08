package model;

import java.io.Serializable;
/**
 * エラーコード・エラーメッセージのBean.
 * <br>DBから取得した情報を格納・表示に利用する
 * <br>コンストラクタとプライベート変数のセッター/ゲッターを持つ.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.Serializable
 */
public class ErrorBean implements Serializable{
private String errorCode;
private String errorMsg;

public String getErrorCode() {
	return errorCode;
}

public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}

public String getErrorMsg() {
	return errorMsg;
}

public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}

}
