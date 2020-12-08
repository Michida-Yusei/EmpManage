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

import dao.SalaryDao;
import exception.E070Exception;
import exception.E090Exception;
import model.SalaryBean;

/**
 * 部署ごとの人数と平均給与を表示する.
 * <br>SlaryDaoから部署ごとの人数と平均給与を受け取る.
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
 * @see dao.SalaryDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.SalaryBean
 *
 */
@WebServlet("/DepartmentSalary")
public class DepartmentSalary extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Departmentslaryのコンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentSalary() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  部署ごとの人数と平均給与を受け取る.
	 *  <br>SalaryDaoのdepartmentAvgを呼ぶ
	 *  <br>人数と平均を表示するためにをdepartmentSalary.jspにフォワードする.
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

		//セッションタイムアウトかどうかの確認
		if(id==null) {
			 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
			 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
			 return;
	       }

		 SalaryDao sd=new SalaryDao();
		 ArrayList<SalaryBean> salList=new ArrayList<SalaryBean>();

  	   try {
		salList=sd.departmentAvg();
	} catch (E090Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
  		 request.setAttribute("salList", salList);

    	   RequestDispatcher dispatch=request.getRequestDispatcher("departmentSalary.jsp");
			dispatch.forward(request, response);


	}

}
