package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDeleteDao;
import exception.E070Exception;
import exception.E090Exception;

/**
 * システム利用者表の情報を削除する際に利用者IDを受け取り、UserDelateDaoに渡すクラス.
 * <br>利用者が削除確認画面で確定した場合に呼び出される
 * <br>システム利用者表の情報を削除する際に利用者IDを受け取り、UserDelateDaoに渡す.
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
 * @see dao.UserDeleteDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  システム利用者表の情報を削除する際に利用者IDを受け取り、UserDelateDaoに渡す
	 *  <br>UserDelateDaoのdeleteメソッドを呼ぶ
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
		String id=(String) session.getAttribute("id");

 		 if(id==null) {
		 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
		 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
		 return;
      }
		String userId=(request.getParameter("userId"));
		String msg=null;
	    UserDeleteDao ud=new UserDeleteDao();
	    try {
			msg=ud.delete(userId);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		    String url="topAdmin.jsp";
	        request.setAttribute("url", url);
	        request.setAttribute("msg", msg);
		    RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
			dispatch.forward(request, response);


	}

}
