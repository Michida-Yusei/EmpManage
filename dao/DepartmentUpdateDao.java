package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.DepartmentBean;
/**
 * DBに接続し部署情報を更新する.
 * <br>指定された部署番号の部署情報を更新する.
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
public class DepartmentUpdateDao {
	/**
	 * 更新、削除の際に部署番号が登録されているかを確認する.
	 * <br>登録済みなら部署情報を返す.
	 *
	 * @param  departmentId 登録されているか確認するため
	 * @return DepartmentBean
	 * @throws E090Exception システムエラー
	 */
	public DepartmentBean searchDepartment(int departmentId)throws E090Exception{
		DepartmentBean db=new DepartmentBean();
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
			String sql="select * from department where DEPARTMENTNO="+departmentId;
			rs=stmt.executeQuery(sql);


			while(rs.next()){
			list.add(rs.getString("ADDRESS"));
			db.setDepartmentNo(rs.getInt("DEPARTMENTNO"));
			db.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			db.setAddress(rs.getString("ADDRESS"));
			}
           //未登録であった場合
			 if(list.isEmpty()) {
				    check=1;
					db.setCheck(check);
					return db;
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
		return db;
	}
	/**
	 * 部署番号から部署情報を更新する.
	 * <br>引数の部署番号の部署情報を更新する.
	 *
	 * @param departmentNo 更新する部署の部署番号
	 * @param departmentName 更新する部署の部署名
	 * @param address 更新する部署の住所
	 * @return 更新完了のメッセージ
	 * @throws E090Exception システムエラー
	 */
	public String update(int departmentNo,String departmentName,String address) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
        String msg="更新しました";
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//部署情報を更新するためのSQL
			String sql="update department set DEPARTMENTNAME='"+departmentName+"',ADDRESS='"+address+"'"
					+ "where DEPARTMENTNO="+departmentNo;
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
