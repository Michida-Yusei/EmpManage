package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDeleteDao;
import exception.E070Exception;
import exception.E090Exception;

/**
 * 部署表の情報を削除する際に部署番号を受け取り、DepartmentDelateDaoに渡すクラス.
 * <br>利用者が削除確認画面で確定した場合に呼び出される
 * <br>部署表の情報を削除する際に部署番号を受け取り、DepartmentDelateDaoに渡す.
 *
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
 * @see dao.DepartmentDeleteDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/DepartmentDelete")
public class DepartmentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * DeparmentDeleteのコンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *  部署表の情報を削除する際に部署番号を受け取り、DepartmentDelateDaoに渡す
	 *  <br>DepartmentDelateDaoのdeleteメソッドを呼ぶ
	 *  <br>削除が完了したらreuslt.jspにフォワードする.
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

       //セッションタイムアウトかどうかの確認
 		 if(id==null) {
		 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
		 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
		 return;
      }

		int departmentNo=Integer.parseInt(request.getParameter("departmentNo"));
		String msg=null;
	    DepartmentDeleteDao dd=new DepartmentDeleteDao();
	    try {
			msg=dd.delete(departmentNo);
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

	  //リザルト画面にフォワードする
	    request.setAttribute("url", url);
		RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
	    dispatch.forward(request, response);
	}

}
