package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
/**
 * DBに接続し利用者情報を登録する.
 * <br>利用者IDが未登録だった場合に登録する
 * <br>社員番号が登録されていた場合に登録する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception;
 * @see java.sql.Statment
 * @see java.util.ArrayList
 * @see exception.E090Exception
 * @see dao.SuperDao
 *
 */
public class UserInsertDao {
	/**
	 * 利用者情報を登録する.
	 * <br>引数の利用者IDから登録済みでないかの確認をする
	 * <br>引数の社員番号が登録されているかの確認をする
	 * <br>引数の利用者ID、パスワード、社員番号、メールアドレス、権限、を登録する.
	 *
	 * @param employeeNo 登録する利用者ID
	 * @param employeeName 登録するパスワード
	 * @param job         登録する社員番号
	 * @param managerNo 登録するメールアドレス
	 * @param joiningCompany 登録する権限
	 * @return 登録済みか確認するための変数
	 * @throws E090Exception システムエラー
	 */
	public int insert(String userId,String pass,int employeeNo,
			String mail,int authority) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
        int check=0;
        ArrayList<String> list=new ArrayList<String>();
        SuperDao sd=new SuperDao();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();

			//利用者IDが登録されているか確認するためのSQL
			String sql1="select * from systemsuser where USERID= '" + userId+"'";
			rs=stmt.executeQuery(sql1);

			//利用者IDが登録済みの場合
				if(rs.next()) {
					check=1;
					return check;

			}

				//社員番号が登録されているか確認するためのSQL
			String sql2="select * from employee where EMPLOYEENO="+ employeeNo;
			rs1=stmt.executeQuery(sql2);

			if(rs1.next()) {
				list.add(rs1.getString("JOB"));
			}

			//社員番号が未登録の場合
			if(list.isEmpty()) {
			    check=2;
				return check;
		  }
			//利用者情報を登録するSQL
			String sql="insert into systemsuser values('"+userId+"','"+pass+"',"+employeeNo+",'"
					+mail+ "',"+authority+")";
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
