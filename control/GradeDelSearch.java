package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GradeDeleteDao;
import exception.E012Exception;
import exception.E042Exception;
import exception.E070Exception;
import exception.E090Exception;
import model.GradeBean;

/**
 * 利用者が入力した等級番号から削除確認画面に等級情報を表示する.
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
 * @see dao.DepartmentDeleteDao
 * @see exception.E012Exception
 * @see exception.E042Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.GradeBean
 *
 */
@WebServlet("/GradeDelSearch")
public class GradeDelSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeDelSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  等級表の情報を削除する際に削除確認画面に等級情報を表示する.
	 *  <br>GradeDelateDaoのsearchGradeメソッドを呼ぶ
	 *  <br>等級情報を表示するためにgradeDelete.jspにフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E012Exception 等級番号が整数でない
	 * @exception E042Exception 等級番号が登録されていない
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
		int grade;
		try {
		grade=Integer.parseInt(request.getParameter("grade"));
		}catch(NumberFormatException e) {
			E012Exception e012=new E012Exception("等級番号は整数を入力してください");
			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;

		}

	    GradeDeleteDao gd=new GradeDeleteDao();
	    GradeBean gb=new GradeBean();
	    try {
			gb=gd.searchGrade(grade);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    int check=gb.getCheck();
	    //等級番号が登録されていない場合
	    if(check==1) {
	    	E042Exception e042=new E042Exception("等級番号が登録されていません。");
	    	er.doPost(request, response, e042.getErrorCode(), e042.getMessage());
	    	return;
	    }
        request.setAttribute("gb", gb);
	    RequestDispatcher dispatch=request.getRequestDispatcher("gradeDelete.jsp");
		dispatch.forward(request, response);
	}

}
