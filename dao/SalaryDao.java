package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.E090Exception;
import model.SalaryBean;
/**
 * 集計関数を利用し人数と平均給与を返す.
 * <br>部署ごとの人数と平均給与を取得する
 * <br>職種ごとの人数と平均給与を取得する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.sql.Connection
 * @see java.sql.SQLEXception;
 * @see java.sql.Statment
 * @see java.util.ArrayList
 * @see exception.E090Exception
 * @see dao.SuperDao
 * @see model.SalaryBean
 *
 */
public class SalaryDao {
	/**
	 * 部署ごとの人数と平均給与を取得する.
	 * <br>集計関数を使い人数と平均給与を取得する.
	 *
	 * @return SalaryBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<SalaryBean> departmentAvg() throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        SuperDao sd=new SuperDao();
        ArrayList<SalaryBean> salList=new ArrayList<SalaryBean>();


		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();

			//部署毎の人数と平均給与を取得するSQL
			String sql="select e.DEPARTMENT,d.DEPARTMENTNAME,COUNT(*),AVG(e.SALARY)  "
					+ "from employee as e,department as d  "
					+ "where e.DEPARTMENT=d.DEPARTMENTNO group by e.DEPARTMENT" ;
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				SalaryBean sb=new SalaryBean();
				sb.setDepartmentNo(rs.getInt("e.DEPARTMENT"));
				sb.setDepartmentName(rs.getString("d.DEPARTMENTNAME"));
				sb.setNinzu(rs.getInt("COUNT(*)"));
				sb.setAverageSalary(rs.getFloat("AVG(e.SALARY)"));
				salList.add(sb);
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
		return salList;
	}
	/**
	 * 職種ごとの人数と平均給与を取得する.
	 * <br>集計関数を使い人数と平均給与を取得する.
	 *
	 * @return SalaryBean型のArrayList
	 * @throws E090Exception システムエラー
	 */
	public ArrayList<SalaryBean> jobAvg() throws E090Exception{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
        SuperDao sd=new SuperDao();
        ArrayList<SalaryBean> salList=new ArrayList<SalaryBean>();


		try{

			Class.forName("com.mysql.jdbc.Driver");
			conn=sd.open();
			stmt=conn.createStatement();
			//職種ごとの人数と平均給与を取得するSQL
			String sql="select JOB,COUNT(*),AVG(SALARY) from employee group by JOB" ;
			rs=stmt.executeQuery(sql);

			while(rs.next()){
				SalaryBean sb=new SalaryBean();
				sb.setJob(rs.getString("JOB"));
				sb.setNinzu(rs.getInt("COUNT(*)"));
				sb.setAverageSalary(rs.getFloat("AVG(SALARY)"));
				salList.add(sb);
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
		return salList;
	}
}
