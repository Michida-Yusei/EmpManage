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

import dao.UserShowDao;
import exception.E070Exception;
import exception.E090Exception;
import model.UserBean;

/**
 * 表示する利用者情報を受け取る.
 * <br>UserShowDaoから受けとった利用者情報を表示する.
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
 * @see dao.UserShowDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.UserBean
 *
 */
@WebServlet("/UserShow")
public class UserShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserShow() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  利用者一覧に表示する利用者情報を受けとる.
	 *  <br>UserShowDaoのfindAllメソッドを呼ぶ
	 *  <br>利用者情報を表示するためにUserShow.jspにフォワードする.
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

		 UserShowDao ud=new UserShowDao();
		 ArrayList<UserBean> usList=new ArrayList<UserBean>();
    	 usList=ud.findAll();
  		 request.setAttribute("usList", usList);


    	   RequestDispatcher dispatch=request.getRequestDispatcher("userShow.jsp");
			dispatch.forward(request, response);

	}

}
