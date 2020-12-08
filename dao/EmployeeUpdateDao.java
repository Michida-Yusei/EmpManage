package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.EmployeeBean;
/**
 * DBに接続し社員情報を更新する.
 * <br>指定された社員番号の社員情報を更新する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception
 * @see java.sql.Statment
 * @see java.util.ArrayList
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see model.EmployeeBean
 */
public class EmployeeUpdateDao {
	/**
	 * 更新、削除の際に社員番号が登録されているかを確認する.
	 * <br>登録済みなら社員情報を返す.
	 *
	 * @param  employeeId 登録されているか確認するため
	 * @return EmployeeBean
	 * @throws E090Exception システムエラー
	 */
	public EmployeeBean searchEmployee(int employeeId)throws E090Exception{
		EmployeeBean em=new EmployeeBean();
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
			//登録されているか確認するためのSQL
			String sql="select * from employee where EMPLOYEENO="+employeeId;
			rs=stmt.executeQuery(sql);



			while(rs.next()){
			list.add(rs.getString("JOB"));

			em.setEmployeeNo(rs.getInt("EMPLOYEENO"));
			em.setEmployeeName(rs.getString("EMPLOYEENAME"));
			em.setJob(rs.getString("JOB"));
			em.setJoiningCompanyDate(rs.getDate("JOININGCOMPANYDATE"));
			em.setManagerNo(rs.getInt("MANAGERNO"));
			em.setSalary(rs.getInt("SALARY"));
			em.setAllowance(rs.getInt("ALLOWANCE"));
			em.setDepartment(rs.getInt("DEPARTMENT"));
			}
			//未登録であった場合
			if(list.isEmpty()) {
				check=1;
				em.setCheck(check);
				return em;
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
		return em;
	}
	/**
	 * 部署番号から部署情報を更新する.
	 * <br>引数の部署番号の部署情報を更新する
	 * <br>引数の上司番号が登録されているかの確認をする
	 * <br>引数の部署番号が部署表に登録されているかの確認.
	 *
	 * @param employeeNo 更新する社員番号
	 * @param employeeName 更新する社員名
	 * @param job         更新する職種
	 * @param managerNo 更新する上司番号
	 * @param joiningCompany 更新する入社年月日
	 * @param salary 更新する給料
	 * @param allowance 更新する手当
	 * @param department 更新する部署番号
	 * @return 登録済みか確認するための変数
	 * @throws E090Exception システムエラー
	 */
	public int update(int employeeNo,String employeeName,String job,int managerNo,
			String joiningCompany,float salary,float allowance,int department) throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> list1=new ArrayList<String>();
        int check=0;
        SuperDao sd=new SuperDao();


		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//部署番号が登録されているか確認するためのSQL
			String sql1="select * from department where DEPARTMENTNO= "+department;
			rs=stmt.executeQuery(sql1);

			while(rs.next()) {
				list.add(rs.getString("ADDRESS"));
			}
			//部署番号が未登録の場合
			if(list.isEmpty()) {
				check=1;
				return check;
			}
			//上司番号が登録されているか確認するためのSQL
			String sql2="select * from employee where EMPLOYEENO= "+ managerNo;
			  rs1=stmt.executeQuery(sql2);
			  while(rs1.next()) {
					list1.add(rs1.getString("JOB"));
				}
			//上司番号が未登録の場合
				if(list1.isEmpty() && managerNo!=0) {
				    check=2;
					return check;
			  }
				//社員情報を更新するためのSQL
			String sql="update employee set EMPLOYEENAME='"+employeeName+"',JOB='"+job+"'"
					+ ",MANAGERNO="+managerNo+",JOININGCOMPANYDATE='"+joiningCompany+"'"
					+ ",SALARY="+salary+",ALLOWANCE="+allowance+",DEPARTMENT="+department+" where EMPLOYEENO="+employeeNo;
           stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try{
					stmt.close();
				}catch(SQLException sqlEx){
					throw new E090Exception("システムエラー");
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

			}
		return check;
	}

}
