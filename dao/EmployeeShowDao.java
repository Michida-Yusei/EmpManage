package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.EmployeeBean;
/**
 * DBに接続し社員情報を返す.
 * <br>社員表から社員情報を取得する.
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
public class EmployeeShowDao {
	/**
	 * 社員情報を取得する.
	 * <br>社員情報取得し、呼び出し元に返す.
	 *
	 * @return EmployeeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
    public ArrayList<EmployeeBean> findAll(){
		ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;

        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//社員情報を取得するSQL
			String sql="select e1.EMPLOYEENO,e1.EMPLOYEENAME,e1.JOB,e1.MANAGERNO,e2.EMPLOYEENAME,e1.JOININGCOMPANYDATE,"
					+ "e1.SALARY,e1.ALLOWANCE,e1.DEPARTMENT,d.DEPARTMENTNAME ,(select g.GRADE from grade as g "
					+ "where g.MINSALARY <= e1.SALARY AND e1.SALARY <= g.MAXSALARY) as GRADENO "
					+ "from employee as e1 left outer join employee as e2 on e1.MANAGERNO=e2.EMPLOYEENO,department as d "
					+ "where e1.DEPARTMENT=d.DEPARTMENTNO";
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				 EmployeeBean eb=new EmployeeBean();
				eb.setEmployeeNo(rs.getInt("e1.EMPLOYEENO"));
			    eb.setEmployeeName(rs.getString("e1.EMPLOYEENAME"));
			    eb.setJob(rs.getString("e1.JOB"));
			    eb.setManagerNo(rs.getInt("e1.MANAGERNO"));
			    eb.setManagerName(rs.getString("e2.EMPLOYEENAME"));
			    eb.setJoiningCompanyDate(rs.getDate("e1.JOININGCOMPANYDATE"));
			    eb.setSalary(rs.getFloat("e1.SALARY"));
			    eb.setAllowance(rs.getFloat("e1.ALLOWANCE"));
			    eb.setDepartment(rs.getInt("e1.DEPARTMENT"));
			    eb.setDepartmentName(rs.getString("d.DEPARTMENTNAME"));
			    eb.setGrade(rs.getInt("GRADENO"));
			  empList.add(eb);
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
		return empList;
	}
}
