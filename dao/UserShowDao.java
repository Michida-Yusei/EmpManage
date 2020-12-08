package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.UserBean;
/**
 * DBに接続し利用者情報を返す.
 * <br>システム利用者表から利用者情報を取得する.
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
public class UserShowDao {
	/**
	 * 利用者情報を取得する.
	 * <br>利用者情報取得し、呼び出し元に返す.
	 *
	 * @return UserBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<UserBean> findAll(){
		ArrayList<UserBean> usList=new ArrayList<UserBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;

        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//利用者情報を取得するSQL
			String sql="select * from systemsuser";
            rs=stmt.executeQuery(sql);
			while(rs.next()){
                 UserBean us=new UserBean();
                 us.setUserId(rs.getString("USERID"));
                 us.setPass(rs.getString("PASSWORD"));
                 us.setEmployeeNo(rs.getInt("EMPLOYEENO"));
                 us.setMail(rs.getString("EMAIL"));
                 us.setAuthority(rs.getInt("AUTHORITY"));
                 usList.add(us);

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
		return usList;
	}
}
