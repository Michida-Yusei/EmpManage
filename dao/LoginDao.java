package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.E090Exception;
/**
 * ログイン時の利用者認証を行う.
 * <br>利用者IDとパスワードから認証を行う.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.ResultSet
 * @see java.sql.SQLEXception
 * @see java.sql.Statement
 * @see exception.E090Exception
 */
public class LoginDao {
	/**
	 * 利用者IDとパスワードから認証を行う.
	 * <br>利用者IDとパスワードが一致した場合にログインできる
	 * <br>ログイン成功できたら権限を返す.
	 * @param  userId 利用者ID
	 * @param  pass パスワード
	 * @return 利用者の権限
	 * @throws E090Exception
	 */
	public int check(String userId,String pass) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        SuperDao sd=new SuperDao();
        int authority=0;


		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//利用者IDとパスワードが正しいか確認するSQL
			String sql="select * from systemsuser where USERID='"+userId+"' and PASSWORD='"+pass+"'";
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				authority=rs.getInt("AUTHORITY");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(SQLException sqlEx){
				}
			}
			if(stmt!=null){
				try{
					stmt.close();
				}catch(SQLException sqlEx){
				}
			}
			if(conn!=null){
				try{
						conn.close();
				}catch(SQLException sqlEx){
				}
			}
		}
		return authority;
	}
}
