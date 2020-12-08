package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.DepartmentBean;
/**
 * DBに接続し部署情報を返す.
 * <br>部署表から部署情報を取得する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception
 * @see java.sql.Statment
 * @see java.util.ArrayList
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see model.DepartmentBean
 */
public class DepartmentShowDao {

	/**
	 * 部署情報を取得する.
	 * <br>部署情報取得し、呼び出し元に返す.
	 *
	 * @return DepartmentBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<DepartmentBean> findAll() throws E090Exception{
		ArrayList<DepartmentBean> depList=new ArrayList<DepartmentBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;

        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();

			//部署情報を取得するSQL
			String sql="select * from department";
            rs=stmt.executeQuery(sql);

			while(rs.next()){
				DepartmentBean db=new DepartmentBean();
                 db.setDepartmentNo(rs.getInt("DEPARTMENTNO"));
                 db.setDepartmentName(rs.getString("DEPARTMENTNAME"));
                 db.setAddress(rs.getString("ADDRESS"));
                 depList.add(db);
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
		return depList;
	}
}
