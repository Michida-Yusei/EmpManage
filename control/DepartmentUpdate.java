package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentUpdateDao;
import exception.E070Exception;
import exception.E090Exception;

/**
 * 利用者が入力した部署情報を更新するためにDepartmentUpdateDaoに渡す.
 * <br>部署情報を受け取り、更新するためにDepartmentUpdateDaoに渡す.
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
 * @see dao.DepartmentUpdateDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/DepartmentUpdate")
public class DepartmentUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  部署表の更新する情報をDepartmentUpdateDaoに渡す.
	 *  <br>DepartmentUpdateDaoのupdateメソッドを呼ぶ
	 *  <br>更新が完了したらresult.jspにフォワードする.
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
		int authority=(int) session.getAttribute("authority");
		String id=(String) session.getAttribute("id");

		//セッションタイムアウトかの確認
 		 if(id==null) {
		 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
		 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
		 return;
      }
		int departmentNo=0;
		String departmentName=null;
		String address=null;


			departmentNo=Integer.parseInt(request.getParameter("departmentNo"));
			departmentName=request.getParameter("departmentName");
			address=request.getParameter("address");


		String msg=null;
	    DepartmentUpdateDao dd=new DepartmentUpdateDao();
	    try {
			msg=dd.update(departmentNo, departmentName, address);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    request.setAttribute("msg", msg);

        String url=null;
	    if(authority==1) {
		    url="topAdmin.jsp";
		    }else if(authority==2) {
		    url="topJinji.jsp";
		    }

	    //リザルト画面へフォワード
	    request.setAttribute("url", url);
		RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
	    dispatch.forward(request, response);
	}

}
