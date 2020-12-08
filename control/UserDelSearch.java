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
import exception.E052Exception;
import exception.E070Exception;
import exception.E090Exception;
import model.UserBean;

/**
 * 利用者が入力した利用者IDから削除確認画面に利用者情報を表示する.
 * <br>利用者が入力した利用者IDが登録されているか確認する.
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
 * @see exception.E052Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.UserBean
 *
 */
@WebServlet("/UserDelSearch")
public class UserDelSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
   	 *  システム利用者表の情報を削除する際に削除確認画面に利用者情報を表示する.
   	 *  <br>UserDelateDaoのsearchUserメソッドを呼ぶ
   	 *  <br>利用者情報を表示するためにuserDelete.jspにフォワードする.
   	 *
   	 * @exception E090Exception システムエラー
   	 * @exception E070Exception セッションタイムアウトエラー
   	 * @exception E052Exception 利用者IDが登録されていない
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
		String userId;

		userId=(request.getParameter("userId"));

	    UserDeleteDao ud=new UserDeleteDao();
	    UserBean ub=new UserBean();
	    try {
			ub=ud.searchUser(userId);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    int check=ub.getCheck();

	    //利用者IDが登録されていない場合
	    if(check==1) {
	    	E052Exception e052=new E052Exception("入力されたユーザーIDが登録されていません");
	    	er.doPost(request, response, e052.getErrorCode(), e052.getMessage());
	    	return;
	    }

       request.setAttribute("ub", ub);
	    RequestDispatcher dispatch=request.getRequestDispatcher("userDelete.jsp");
		dispatch.forward(request, response);
	}

}
