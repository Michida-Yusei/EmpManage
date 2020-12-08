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
import exception.E012Exception;
import exception.E032Exception;
import exception.E070Exception;
import exception.E090Exception;
import model.DepartmentBean;

/**
 * 利用者が入力した部署番号から更新入力画面に部署情報を表示する.
 * <br>利用者が入力した部署番号が登録されているか確認する.
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
 * @see dao.DepartmentUpdateDao
 * @see exception.E012Exception
 * @see exception.E032Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.DepartmentBean
 *
 */
@WebServlet("/DepartmentUpdateSearch")
public class DepartmentUpdateSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentUpdateSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  部署表の情報を更新する際に更新入力画面に部署情報を表示する.
	 *  <br>DepartmentUpdateDaoのsearchDepartmentメソッドを呼ぶ
	 *  <br>部署情報を表示するためにdepartmentUpdate.jspにフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E012Exception 部署番号が整数でない
	 * @exception E032Exception 部署番号が登録されていない
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Error er=new Error();
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");

		//セッションタイムアウトかの確認
 		 if(id==null) {
		 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
		 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
		 return;
      }
		int departmentNo;
		try {
		departmentNo=Integer.parseInt(request.getParameter("departmentNo"));
		}catch(NumberFormatException e) {
			E012Exception e012=new E012Exception("部署番号は整数を入力してください");
			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;

		}

	    DepartmentUpdateDao dd=new DepartmentUpdateDao();
	    DepartmentBean db=new DepartmentBean();
	    try {
			db=dd.searchDepartment(departmentNo);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    int check=db.getCheck();
	    //部署番号が登録されていない場合
	    if(check==1) {
	    	E032Exception e032=new E032Exception("部署番号が登録されていません。");
	    	er.doPost(request, response, e032.getErrorCode(), e032.getMessage());
	    	return;

	    }
        request.setAttribute("db", db);
	    RequestDispatcher dispatch=request.getRequestDispatcher("departmentUpdate.jsp");
		dispatch.forward(request, response);
	}

}
