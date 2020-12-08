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

import dao.SearchDao;
import exception.E070Exception;
import exception.E080Exception;
import exception.E090Exception;
import model.EmployeeBean;

/**
 * 利用者が入力した部署から社員情報を表示する.
 * <br>利用者が入力した部署が登録されているか確認する.
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
 * @see dao.SearchDao
 * @see exception.E080Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.EmployeeBean
 *
 */
@WebServlet("/SearchDep")
public class SearchDep extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDep() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  利用者が入力した部署名をSearchDaoに渡す.
	 *  <br>SearchDaoのdepSearchメソッドを呼ぶ
	 *  <br>社員情報を表示するためにリザルト画面にフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E080Exception 部署名が登録されていない
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
		int authority=(int) session.getAttribute("authority");


	    String departmentName=null;

		departmentName=request.getParameter("departmentName");

	    SearchDao sd=new SearchDao();
	    ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
	    try {
			empList=sd.depSearch(departmentName);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    int check=0;
	    for(EmployeeBean eb : empList) {
	    	check=eb.getCheck();
	    }

	  //検索結果が０件の場合
	    if(check==1) {
	    	E080Exception e080=new E080Exception("入力された部署が登録されていません");
	    	er.doPost(request, response, e080.getErrorCode(), e080.getMessage());
	    	return;
	    }

	    request.setAttribute("empList", empList);
	  //リザルト画面へフォワード
	    if(authority==1) {
	    	   RequestDispatcher dispatch=request.getRequestDispatcher("searchResultAdmin.jsp");
				dispatch.forward(request, response);
	       }else if(authority==2) {
	      	   RequestDispatcher dispatch=request.getRequestDispatcher("searchResultAdmin.jsp");
	  			dispatch.forward(request, response);
	       }else if(authority==3){
	      	   RequestDispatcher dispatch=request.getRequestDispatcher("searchResultIppan.jsp");
	  			dispatch.forward(request, response);
	       }


	}

}
