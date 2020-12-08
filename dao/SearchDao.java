package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.EmployeeBean;
/**
 * 利用者が入力した検索キーから検索を行う.
 * <br>社員番号をキーとし検索する
 * <br>部署をキーとし検索する
 * <br>職種をキーとし検索する
 * <br>入社年月日の範囲をキーとし検索する
 * <br>等級の範囲をキーとし検索する.
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
public class SearchDao {
	/**
	 * 社員番号を検索キーとして検索する.
	 * <br>社員情報を取得し返す.
	 *
	 * @param  employeeNo 検索キーとなる社員番号
	 * @return EmployeeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<EmployeeBean> empSearch(int employeeNo) throws E090Exception{
		ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			String sql="select e1.EMPLOYEENO,e1.EMPLOYEENAME,e1.JOB,e1.MANAGERNO,e2.EMPLOYEENAME,e1.JOININGCOMPANYDATE,"
					+ "e1.SALARY,e1.ALLOWANCE,e1.DEPARTMENT,d.DEPARTMENTNAME ,(select g.GRADE from grade as g "
					+ "where g.MINSALARY <= e1.SALARY AND e1.SALARY <= g.MAXSALARY) as GRADENO "
					+ "from employee as e1 left outer join employee as e2 on e1.MANAGERNO=e2.EMPLOYEENO,department as d "
					+ "where e1.DEPARTMENT=d.DEPARTMENTNO and e1.EMPLOYEENO=" +employeeNo;
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

			//検索結果が一件もない場合
			if(empList.isEmpty()) {
				EmployeeBean eb=new EmployeeBean();
				check=1;
				eb.setCheck(check);
				empList.add(eb);
				return empList;
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
	/**
	 * 部署を検索キーとして検索する.
	 * <br>部署情報を取得し返す.
	 *
	 * @param  departmentName 検索キーとなる部署名
	 * @return EmployeeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<EmployeeBean> depSearch(String departmentName) throws E090Exception{
		ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			String sql="select e1.EMPLOYEENO,e1.EMPLOYEENAME,e1.JOB,e1.MANAGERNO,e2.EMPLOYEENAME,e1.JOININGCOMPANYDATE,"
					+ "e1.SALARY,e1.ALLOWANCE,e1.DEPARTMENT,d.DEPARTMENTNAME ,(select g.GRADE from grade as g "
					+ "where g.MINSALARY <= e1.SALARY AND e1.SALARY <= g.MAXSALARY) as GRADENO "
					+ "from employee as e1 left outer join employee as e2 on e1.MANAGERNO=e2.EMPLOYEENO,department as d "
					+ "where e1.DEPARTMENT=d.DEPARTMENTNO and d.DEPARTMENTNAME='" +departmentName+"'";
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
           //検索結果が一件もない場合
			if(empList.isEmpty()) {
				EmployeeBean eb=new EmployeeBean();
				check=1;
				eb.setCheck(check);
				empList.add(eb);
				return empList;
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
	/**
	 * 職種を検索キーとして検索する.
	 * <br>社員情報を取得し返す.
	 *
	 * @param  job 検索キーとなる職種
	 * @return EmployeeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<EmployeeBean> jobSearch(String job) throws E090Exception{
		ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			String sql="select e1.EMPLOYEENO,e1.EMPLOYEENAME,e1.JOB,e1.MANAGERNO,e2.EMPLOYEENAME,e1.JOININGCOMPANYDATE,"
					+ "e1.SALARY,e1.ALLOWANCE,e1.DEPARTMENT,d.DEPARTMENTNAME ,(select g.GRADE from grade as g "
					+ "where g.MINSALARY <= e1.SALARY AND e1.SALARY <= g.MAXSALARY) as GRADENO "
					+ "from employee as e1 left outer join employee as e2 on e1.MANAGERNO=e2.EMPLOYEENO,department as d "
					+ "where e1.DEPARTMENT=d.DEPARTMENTNO and e1.job='" +job+"'";
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
            //検索結果が一件もない場合
			if(empList.isEmpty()) {
				EmployeeBean eb=new EmployeeBean();
				check=1;
				eb.setCheck(check);
				empList.add(eb);
				return empList;
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
	/**
	 * 入社年月日の範囲を検索キーとして検索する.
	 * <br>社員情報を取得し返す.
	 *
	 * @param  joining1 検索キーとなる入社年月日
	 * @param  joining2 検索キーとなる入社年月日
	 * @return EmployeeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<EmployeeBean> joiningSearch(String joining1,String joining2) throws E090Exception{
		ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			String sql="select e1.EMPLOYEENO,e1.EMPLOYEENAME,e1.JOB,e1.MANAGERNO,e2.EMPLOYEENAME,e1.JOININGCOMPANYDATE,"
					+ "e1.SALARY,e1.ALLOWANCE,e1.DEPARTMENT,d.DEPARTMENTNAME ,(select g.GRADE from grade as g "
					+ "where g.MINSALARY <= e1.SALARY AND e1.SALARY <= g.MAXSALARY) as GRADENO "
					+ "from employee as e1 left outer join employee as e2 on e1.MANAGERNO=e2.EMPLOYEENO,department as d "
					+ "where e1.DEPARTMENT=d.DEPARTMENTNO "
					+ "and e1.JOININGCOMPANYDATE >='"+ joining1+ "' and e1.JOININGCOMPANYDATE <='" +joining2+"'";
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
           //検索結果が一件もない場合
			if(empList.isEmpty()) {
				EmployeeBean eb=new EmployeeBean();
				check=1;
				eb.setCheck(check);
				empList.add(eb);
				return empList;
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
	/**
	 * 等級の範囲を検索キーとして検索する.
	 * <br>社員情報を取得し返す.
	 *
	 * @param  grade1 検索キーとなる等級
	 * @param  grade2 検索キーとなる等級
	 * @return EmployeeBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<EmployeeBean> gradeSearch(int grade1,int grade2) throws E090Exception{
		ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        int check=0;
        SuperDao sd=new SuperDao();

		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			String sql="select e1.EMPLOYEENO,e1.EMPLOYEENAME,e1.JOB,e1.MANAGERNO,e2.EMPLOYEENAME,e1.JOININGCOMPANYDATE,"
					+ "e1.SALARY,e1.ALLOWANCE,e1.DEPARTMENT,d.DEPARTMENTNAME ,(select g.GRADE from grade as g "
					+ "where g.MINSALARY <= e1.SALARY AND e1.SALARY <= g.MAXSALARY) as GRADENO "
					+ "from employee as e1 left outer join employee as e2 on e1.MANAGERNO=e2.EMPLOYEENO,department as d "
					+ "where e1.DEPARTMENT=d.DEPARTMENTNO "
					+ "having GRADENO >= "+grade1+ " and GRADENO <= "+grade2;
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
           //検索結果が一件もない場合
			if(empList.isEmpty()) {
				EmployeeBean eb=new EmployeeBean();
				check=1;
				eb.setCheck(check);
				empList.add(eb);
				return empList;
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
