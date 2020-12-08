package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.E090Exception;
/**
 * DBに接続し等級情報を登録する.
 * <br>等級番号が未登録だった場合に登録する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception;
 * @see java.sql.Statment
 * @see exception.E090Exception
 * @see dao.SuperDao
 *
 */
public class GradeInsertDao {

	/**
	 * 部署情報を登録する.
	 * <br>引数の部署番号から登録済みでないかの確認をする
	 * <br>引数の等級番号、最低給与、最大給与を登録する.
	 *
	 * @param dgrrade 登録する等級番号
	 * @param minSalary 登録する最低給与
	 * @param maxSalary 登録する最大給与
	 * @return 登録済みか確認するための変数
	 * @throws E090Exception システムエラー
	 */
	public int insert(int grade,float minSalary,float maxSalary) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        SuperDao sd=new SuperDao();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//登録済みか確認するためのSQL

			String sql1="select * from grade where GRADE= " + grade;
			rs=stmt.executeQuery(sql1);

			//登録済みの場合
				if(rs.next()) {
					check=1;
					return check;

			}
			//等級情報を登録するSQL
			String sql="insert into grade values("+grade+","+minSalary+","+maxSalary+")";
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
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException sqlEx){
				}
			}
		}
		return check;
	}
}
