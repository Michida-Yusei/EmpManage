package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
/**
 * DBに接続し社員情報を登録する.
 * <br>社員番号が未登録だった場合に登録する
 * <br>上司番号が登録されていた場合に登録する
 * <br>部署番号が登録されていた場合に登録する.
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
public class EmployeeInsertDao {
	/**
	 * 社員情報を登録する.
	 * <br>引数の社員番号から登録済みでないかの確認をする
	 * <br>引数の上司番号が登録されているかの確認をする
	 * <br>引数の部署番号が部署表に登録されているかの確認
	 * <br>引数の社員番号、社員名、職種、上司番号、入社年月日、
	 *     給料、手当、部署番号を登録する.
	 *
	 * @param employeeNo 登録する社員番号
	 * @param employeeName 登録する社員名
	 * @param job         登録する職種
	 * @param managerNo 登録する上司番号
	 * @param joiningCompany 登録する入社年月日
	 * @param salary 登録する給料
	 * @param allowance 登録する手当
	 * @param department 登録する部署番号
	 * @return 登録済みか確認するための変数
	 * @throws E090Exception システムエラー
	 */
	public int insert(int employeeNo,String employeeName,String job,int managerNo,
			String joiningCompany,float salary,float allowance,int department) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;

		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> list1=new ArrayList<String>();
        int check=0;
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//社員番号が登録されているか確認するためのSQL
			String sql1="select * from employee where EMPLOYEENO= "+ employeeNo;
			rs=stmt.executeQuery(sql1);

            //社員番号が登録済みの場合
				if(rs.next()) {
					check=1;
					return check;
				}

           //部署番号が登録されているか確認するためのSQL
			String sql2="select * from department where DEPARTMENTNO= "+ department;
			rs1=stmt.executeQuery(sql2);

			while(rs1.next()) {
				list.add(rs1.getString("ADDRESS"));
			}

           //部署番号が未登録の場合
			if(list.isEmpty()) {
			    check=2;
				return check;
		  }

          //上司番号が登録されているか確認するためのSQL
		  String sql3="select * from employee where EMPLOYEENO= "+ managerNo;
		  rs2=stmt.executeQuery(sql3);
		  while(rs2.next()) {
				list1.add(rs2.getString("JOB"));
			}

          //上司番号が未登録の場合
			if(list1.isEmpty() && managerNo!=0) {
			    check=3;
				return check;

			}
         //社員情報を登録するSQL
			String sql="insert into employee values("+employeeNo+",'"+employeeName+"','"+job+"',"+managerNo+",'"
					+joiningCompany+"',"+salary+","+allowance+","+department+")";
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
			if(rs2!=null){
				try{
						rs.close();
				}catch(SQLException sqlEx){
				}
			}


		}
		return check;
	}
}
