package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.GradeBean;
/**
 * DBに接続し等級情報を返す.
 * <br>等級表から等級情報を取得する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception
 * @see java.sql.Statment
 * @see java.util.ArrayList
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see model.GradeBean
 */
public class GradeShowDao {

public ArrayList<GradeBean> findAll()throws E090Exception{
	/**
	 * 等級情報を取得する.
	 * <br>等級情報取得し、呼び出し元に返す.
	 *
	 * @return gradeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	ArrayList<GradeBean> graList=new ArrayList<GradeBean>();
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;

    SuperDao sd=new SuperDao();

	try{

		Class.forName("com.mysql.jdbc.Driver");
		conn=sd.open();
		stmt=conn.createStatement();
		String sql="select * from grade";
        rs=stmt.executeQuery(sql);
		while(rs.next()){
			 GradeBean gb=new GradeBean();
             gb.setGrade(rs.getInt("GRADE"));
             gb.setMinSalary(rs.getFloat("MINSALARY"));
             gb.setMaxSalary(rs.getFloat("MAXSALARY"));
             graList.add(gb);
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
	return graList;
}
}
