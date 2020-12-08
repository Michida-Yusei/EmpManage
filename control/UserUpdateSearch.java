package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserUpdateDao;
import exception.E052Exception;
import exception.E070Exception;
import exception.E090Exception;
import model.UserBean;

/**
 * 利用者が入力した等級番号から更新入力画面に等級情報を表示する.
 * <br>利用者が入力した等級番号が登録されているか確認する.
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
 * @see dao.UserUpdateDao
 * @see exception.E052Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.UserBean
 *
 */
@WebServlet("/UserUpdateSearch")
public class UserUpdateSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  等級表の情報を更新する際に更新入力画面に等級情報を表示する.
	 *  <br>GradeUpdateDaoのsearchGradeメソッドを呼ぶ
	 *  <br>等級情報を表示するためにgradeUpdate.jspにフォワードする.
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
		String userId=null;
	    userId=(request.getParameter("userId"));


	    UserUpdateDao ud=new UserUpdateDao();
	    UserBean ub=new UserBean();
	    try {
			ub=ud.searchUser(userId);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    int check=ub.getCheck();
	    if(check==1) {
	    	E052Exception e052=new E052Exception("ユーザーIDが登録されていません。");
	    	er.doPost(request, response, e052.getErrorCode(), e052.getMessage());
	    	return;
	    }
	    String msg="更新しました";
        request.setAttribute("ub", ub);
        request.setAttribute("msg", msg);
	    RequestDispatcher dispatch=request.getRequestDispatcher("userUpdate.jsp");
		dispatch.forward(request, response);
	}

}
