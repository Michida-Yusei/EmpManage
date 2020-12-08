package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.UserBean;
/**
 * DBに接続し利用者情報を更新する.
 * <br>指定された利用者IDの利用者情報を更新する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception
 * @see java.sql.Statment
 * @see java.util.ArrayList
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see model.UserBean
 */
public class UserUpdateDao {
	/**
	 * 更新、削除の際に利用者IDが登録されているかを確認する.
	 * <br>登録済みなら利用者情報を返す.
	 *
	 * @param  userId 登録されているか確認するため
	 * @return UserBean
	 * @throws E090Exception システムエラー
	 */
	public UserBean searchUser(String userId)throws E090Exception{
		UserBean ub=new UserBean();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<String> list=new ArrayList<String>();
        SuperDao sd=new SuperDao();
        int check=0;

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//登録されているか確認するためのSQL
			String sql="select * from systemsuser where USERID='"+userId+"'";
			rs=stmt.executeQuery(sql);


			while(rs.next()){
			list.add(rs.getString("EMAIL"));
			ub.setUserId(rs.getString("USERID"));
			ub.setPass(rs.getString("PASSWORD"));
			ub.setEmployeeNo(rs.getInt("EMPLOYEENO"));
			ub.setMail(rs.getString("EMAIL"));
			ub.setAuthority(rs.getInt("AUTHORITY"));
			}
			//未登録であった場合
			 if(list.isEmpty()) {
				    check=1;
					ub.setCheck(check);
					return ub;
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
		return ub;
	}
	/**
	 *利用者IDから利用者情報を更新する.
	 * <br>引数の利用者IDの利用者情報を更新する
	 * <br>引数の社員番号が登録されているかの確認をする.
	 *
	 * @param userId 更新する利用者ID
	 * @param pass 更新するパスワード
	 * @param employeeNo 更新する社員番号
	 * @param mail 更新するメールアドレス
	 * @param authority 更新する権限
	 * @return 登録済みか確認するための変数
	 * @throws E090Exception システムエラー
	 */
	public int update(String userId,String pass,int employeeNo,
			              String mail,int authority) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        ArrayList<String> list=new ArrayList<String>();
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//社員番号が登録されているか確認するためのSQL
			String sql1="select * from employee where EMPLOYEENO="+employeeNo;
			rs=stmt.executeQuery(sql1);

			while(rs.next()) {
				list.add(rs.getString("JOB"));
			}
			//社員番号が未登録の場合
			if(list.isEmpty()) {
				check=1;
				return check;
			}

			//利用者情報を更新するためのSQL
			String sql="update systemsuser set PASSWORD='"+pass+"',EMPLOYEENO="+employeeNo+","
					+ "EMAIL='"+mail+"',AUTHORITY="+authority+" where USERID='"+userId+"'";
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
		return check;
	}
}
