package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.DepartmentBean;
/**
 * DBに接続し部署番号が登録してあった場合に部署を削除する.
 * <br>部署番号が部署表に登録されているかを確認する
 * <br>受け取った部署番号から部署を削除する
 * <br>本クラスは{@linkplain DepartmentUpdateDao}クラスを継承している.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception;
 * @see java.sql.Statment
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see jp.co.scc_kk.kensyu.exception.DepartmentUpdateDao
 * @see model.DepartmentBean
 *
 */
public class DepartmentDeleteDao extends DepartmentUpdateDao{
	/**
	 * 更新、削除の際に部署番号が登録されているかを確認する.
	 * <br>削除しようとしている部署に社員が所属しているかの確認をする
	 * <br>登録済みなら部署情報を返す.
	 *
	 * @param  departmentId 登録されているか確認をするため
	 *                       社員が所属しているか確認するため
	 * @return DepartmentBean
	 * @throws E090Exception システムエラー
	 */
	public DepartmentBean searchDepartment(int departmentId)throws E090Exception{
		DepartmentBean db=new DepartmentBean();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
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
            //社員が所属しているかの確認をするためのSQL
			 String sql1="select * from employee where DEPARTMENT=" +departmentId;
             rs1=stmt.executeQuery(sql1);

             //社員が所属していた場合
             if(rs1.next()) {
            	 check=2;
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
			if(rs1!=null){
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
 * 部署番号から部署を削除する.
 * <br>引数の部署番号の部署を削除する.
 *
 * @param departmentNo 削除する部署の部署番号
 * @return 削除完了のメッセージ
 * @throws E090Exception システムエラー
 */
	public String delete(int departmentNo) throws E090Exception{
		/** */
		Connection conn=null;
		Statement stmt=null;
        String msg="削除しました。";
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//部署情報を削除するSQL
			String sql="delete from department where DEPARTMENTNO="+departmentNo;
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
