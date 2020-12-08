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
import exception.E012Exception;
import exception.E053Exception;
import exception.E070Exception;
import exception.E090Exception;

/**
 * 利用者が入力した利用者情報を更新するためにUserUpdateDaoに渡す.
 * <br>利用者情報を受け取り、更新するためにUserUpdateDaoに渡す.
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
 * @see exception.E012Exception
 * @see exception.E053Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */

@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  システム利用者表の更新する情報をUserUpdateDaoに渡す.
	 *  <br>UserUpdateDaoのupdateメソッドを呼ぶ
	 *  <br>更新が完了したらresult.jspにフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E012Exception 社員番号、権限が整数でない
	 * @exception E053Exception 社員番号が登録されていない
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
		String pass=null;
		int employeeNo=0;
		String mail=null;
		int authority=0;

		userId=(request.getParameter("userId"));
		pass=request.getParameter("pass");
		try {
		employeeNo=Integer.parseInt(request.getParameter("employeeNo"));
		mail=request.getParameter("mail");
		authority=Integer.parseInt(request.getParameter("authority"));
		}catch(NumberFormatException e) {
			E012Exception e012=new E012Exception("社員番号、権限は整数を入力してください");
			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;
		}
     int check=0;
	   UserUpdateDao ud=new UserUpdateDao();
	    try {
			check=ud.update(userId, pass, employeeNo,mail,authority);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    if(check==1) {
	    	E053Exception e053=new E053Exception("社員番号が登録されていません。");
	    	er.doPost(request, response, e053.getErrorCode(), e053.getMessage());
	    	return;
	    }

		    String url="topAdmin.jsp";
		    String msg="更新しました";
	        request.setAttribute("url", url);
	        request.setAttribute("msg", msg);
	      //リザルト画面へフォワード
		    RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
			dispatch.forward(request, response);

	}

}
