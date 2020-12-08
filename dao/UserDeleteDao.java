package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import exception.E090Exception;
/**
 * DBに接続し利用者IDが登録してあった場合に利用者情報を削除する.
 * <br>社員番号がシステム利用者表に登録されているかを確認する
 * <br>受け取った利用者IDから利用者情報を削除する
 * <br>本クラスは{@linkplain UserUpdateDao}クラスを継承している.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception;
 * @see java.sql.Statment
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see jp.co.scc_kk.kensyu.exception.UserUpdateDao
 *
 */
public class UserDeleteDao extends UserUpdateDao{

	/**
	 * 利用者IDから利用者情報を削除する.
	 * <br>引数の利用者IDの利用者情報を削除する.
	 *
	 * @param userId 削除する利用者情報の利用者ID
	 * @return 削除完了のメッセージ
	 * @throws E090Exception システムエラー
	 */
	public String delete(String userId) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
        String msg="削除しました";
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//利用者情報を削除するSQL
			String sql="delete from systemsuser where USERID='"+userId+"'";
           stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		return msg;
	}
}
