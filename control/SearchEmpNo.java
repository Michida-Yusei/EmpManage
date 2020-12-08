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
import exception.E012Exception;
import exception.E070Exception;
import exception.E080Exception;
import exception.E090Exception;
import model.EmployeeBean;

/**
 * 利用者が入力した社員番号から社員情報を表示する.
 * <br>利用者が入力した社員番号が登録されているか確認する.
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
 * @see exception.E012Exception
 * @see exception.E080Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 * @see model.EmployeeBean
 *
 */
@WebServlet("/SearchEmpNo")
public class SearchEmpNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpNo() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  利用者が入力した社員番号をSearchDaoに渡す.
	 *  <br>SearchDaoのempSearchメソッドを呼ぶ
	 *  <br>社員情報を表示するためにリザルト画面にフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E012Exception 社員番号が整数でない
	 * @exception E080Exception 社員番号が登録されていない
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


		int employeeNo=0;
		try {
		employeeNo=Integer.parseInt(request.getParameter("employeeNo"));
		}catch(NumberFormatException e) {
			E012Exception e012=new E012Exception("入力エラー");

			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;

		}
	    SearchDao sd=new SearchDao();
	    ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
	    try {
			empList=sd.empSearch(employeeNo);
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
	    	E080Exception e081=new E080Exception("入力された社員番号が登録されていません");
	    	er.doPost(request, response, e081.getErrorCode(), e081.getMessage());
	    	return;
	    }

	    //リザルト画面へフォワード
	    request.setAttribute("empList", empList);
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
