package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import exception.E060Exception;
import exception.E090Exception;

/**
 *
 * ログインする際の利用者IDとパスワードをLoginDaoに渡す.
 * <br>認証を行い、権限ごとにトップページに遷移させる.
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
 * @see jp.co.scc_kk.kensyu.model.LoginDao
 * @see exception.E060Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * 利用者ID、パスワードによる認証を行う.
	 * <br>認証を行い、権限ごとにトップページに遷移させる。
	 * @exception E090Exception システムエラー
	 * @exception E060Exception ログインエラー
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id;
		String pass;
		int authority = 0;

		id=request.getParameter("id");
		pass=request.getParameter("pas");

        HttpSession session=request.getSession();
        session.setAttribute("id", id);

		LoginDao lgDao=new LoginDao();

		try {
			authority=lgDao.check(id,pass);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		session.setAttribute("authority", authority);

		//権限ごとに異なるトップページにフォワードする
		if(authority==1){
			RequestDispatcher dispatch=request.getRequestDispatcher("topAdmin.jsp");
			dispatch.forward(request, response);
		}else if(authority==2){
			RequestDispatcher dispatch=request.getRequestDispatcher("topJinji.jsp");
			dispatch.forward(request, response);
		}else if(authority==3) {
			RequestDispatcher dispatch=request.getRequestDispatcher("topIppan.jsp");
			dispatch.forward(request, response);
		}else {
			E060Exception e060=new E060Exception("ログイン失敗　入力値の確認をお願いします。");
			Error er =new Error();
			er.doPost(request, response, e060.getErrorCode(), e060.getMessage());
		}


	}

}
