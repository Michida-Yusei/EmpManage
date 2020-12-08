package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DBのURLを取得する.
 * <br>DBの接続先を呼び出し元に返す.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.SQLEXception
 */
public class SuperDao {
	/**
	 * DBのURLを取得する.
	 * <br>Connection型の変数にURLを入れて、呼び出し元に返す.
	 * @return Connection型
	 * @throws SQLException
	 */
public Connection open() throws SQLException {
	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/emp?serverTimezone=JST","","");
	return conn;

}
}
