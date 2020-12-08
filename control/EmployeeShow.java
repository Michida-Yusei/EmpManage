package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeShowDao;
import exception.E070Exception;
import exception.E090Exception;
import model.EmployeeBean;

/**
 * 表示する社員情報を受け取る.
 * <br>EmployeeshowDaoから受けとった社員情報を表示する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.IOException
 * @see javax.servlet.RequestDispatcher
 * @see javax.servlet.ServletException
 * @see javax.servlet.annotation.WebServlet
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.http.HttpServletRequest
 * @see javax.servlet.http.HttpServletResponse
 * @see javax.servlet.http.HttpSession
 * @see dao.EmployeeShowDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.EmployeeBean
 *
 */
@WebServlet("/EmployeeShow")
public class EmployeeShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeShow() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  社員一覧に表示する社員情報を受けとる.
	 *  <br>EmployeeshowDaoのfindAllメソッドを呼ぶ
	 *  <br>社員情報を表示するためにemployeeShow.jspにフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Error er=new Error();

		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		if(id==null) {
			 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
			 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
			 return;
	  		 }

		int authority=(int) session.getAttribute("authority");

		 EmployeeShowDao ed=new EmployeeShowDao();
		 ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
    	 empList=ed.findAll();
  		 request.setAttribute("empList", empList);


       if(authority==1) {
    	   RequestDispatcher dispatch=request.getRequestDispatcher("employeeShowAdmin.jsp");
			dispatch.forward(request, response);
       }else if(authority==2) {
      	   RequestDispatcher dispatch=request.getRequestDispatcher("employeeShowAdmin.jsp");
  			dispatch.forward(request, response);
       }else if(authority==3){
      	   RequestDispatcher dispatch=request.getRequestDispatcher("employeeShowIppan.jsp");
  			dispatch.forward(request, response);
       }
	}
}
