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

import dao.DepartmentShowDao;
import exception.E070Exception;
import exception.E090Exception;
import model.DepartmentBean;

/**
 * 表示する部署情報を受け取る.
 * <br>DepartmentShowDaoから受けとった部署情報を表示する.
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
 * @see dao.DepartmentShowDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.DepartmentBean
 *
 */
@WebServlet("/DepartmentShow")
public class DepartmentShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * DepartmentShowのコンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentShow() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  部署一覧に表示する部署情報を受けとる.
	 *  <br>DepartmentShowDaoのfindAllメソッドを呼ぶ
	 *  <br>部署情報を表示するためにdepartmentShow.jspにフォワードする.
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
		int authority=(int) session.getAttribute("authority");

		 DepartmentShowDao dd=new DepartmentShowDao();
		 ArrayList<DepartmentBean> depList=new ArrayList<DepartmentBean>();

  	   try {
		depList=dd.findAll();
	} catch (E090Exception e) {

	}
  	   //部署一覧画面へフォワードする
  		 request.setAttribute("depList", depList);
      if(authority==1) {
    	   RequestDispatcher dispatch=request.getRequestDispatcher("departmentShowAdmin.jsp");
			dispatch.forward(request, response);
       }else if(authority==2) {

      	   RequestDispatcher dispatch=request.getRequestDispatcher("departmentShowAdmin.jsp");
  			dispatch.forward(request, response);
       }else if(authority==3){

      	   RequestDispatcher dispatch=request.getRequestDispatcher("departmentShowIppan.jsp");
  			dispatch.forward(request, response);
       }
	}

}
