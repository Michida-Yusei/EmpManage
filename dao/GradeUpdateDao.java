package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.GradeBean;
/**
 * DBに接続し等級情報を更新する.
 * <br>指定された等級番号の等級情報を更新する.
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
public class GradeUpdateDao {

	/**
	 * 更新、削除の際に等級番号が登録されているかを確認する.
	 * <br>登録済みなら等級情報を返す.
	 *
	 * @param  grade 登録されているか確認するため
	 * @return GradeBean
	 * @throws E090Exception システムエラー
	 */
	public GradeBean searchGrade(int grade)throws E090Exception{
		GradeBean gb=new GradeBean();
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
			String sql="select * from grade where GRADE="+ grade;
			rs=stmt.executeQuery(sql);


			while(rs.next()){
			list.add(rs.getString("GRADE"));
			gb.setGrade(rs.getInt("GRADE"));
			gb.setMinSalary(rs.getFloat("MINSALARY"));
			gb.setMaxSalary(rs.getFloat("MAXSALARY"));
			}
			//未登録であった場合
			 if(list.isEmpty()) {
				    check=1;
					gb.setCheck(check);
					return gb;
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
		return gb;
	}
	/**
	 * 等級番号から等級情報を更新する.
	 * <br>引数の等級番号の等級情報を更新する.
	 *
	 * @param grade 更新する等級の等級番号
	 * @param minSalary 更新する等級の最低給与
	 * @param maxSalary 更新する等級の最大給与
	 * @return 更新完了のメッセージ
	 * @throws E090Exception システムエラー
	 */
	public String update(int grade,float minSalary,float maxSalary) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
        String msg="更新しました";
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//等級情報を更新するためのSQL
			String sql="update grade set MINSALARY="+minSalary+",MAXSALARY="+maxSalary
					+ " where GRADE="+grade;
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
