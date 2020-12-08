package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import exception.E090Exception;
/**
 * DBに接続し等級番号が登録してあった場合に等級情報を削除する.
 * <br>等級番号が等級表に登録されているかを確認する
 * <br>受け取った等級番号から等級情報を削除する
 * <br>本クラスは{@linkplain GradeUpdateDao}クラスを継承している.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception;
 * @see java.sql.Statment
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see jp.co.scc_kk.kensyu.exception.GradeUpdateDao
 *
 */
public class GradeDeleteDao extends GradeUpdateDao{

	/**
	 * 等級番号から等級情報を削除する.
	 * <br>引数の等級番号の等級情報を削除する.
	 *
	 * @param grade 削除する等級情報の等級番号
	 * @return 削除完了のメッセージ
	 * @throws E090Exception システムエラー
	 */
	public String delete(int grade) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
        String msg="削除しました";
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//等級情報を削除するSQL
			String sql="delete from grade where GRADE="+ grade;
           stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try{
					stmt.close();
				}catch(SQLException sqlEx){
				}
			if(conn!=null){
				try{
						conn.close();
				}catch(SQLException sqlEx){
				}
			}
			}
		}
		return msg;
	}

}
