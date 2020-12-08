package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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
 * 利用者が入力した入社年月日の範囲から社員情報を表示する.
 * <br>利用者が入力した入社年月日の範囲に該当する社員が所属しているか確認する.
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
@WebServlet("/SearchJoining")
public class SearchJoining extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchJoining() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  利用者が入力した入社年月日の範囲をSearchDaoに渡す.
	 *  <br>SearchDaoのjoiningSearchメソッドを呼ぶ
	 *  <br>社員情報を表示するためにリザルト画面にフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E080Exception 入社年月日の範囲に該当する社員がいない
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


		String joining1=request.getParameter("joining1");
		String joining2=request.getParameter("joining2");
		try {
			LocalDate.parse(joining1,
		    	    DateTimeFormatter.ofPattern("uuuu/MM/dd").withResolverStyle(ResolverStyle.STRICT));
			LocalDate.parse(joining2,
		    	    DateTimeFormatter.ofPattern("uuuu/MM/dd").withResolverStyle(ResolverStyle.STRICT));
		    }catch(DateTimeParseException e){
		    	E012Exception e012=new E012Exception("日付入力エラー");
				er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
			return;
		    }

	    SearchDao sd=new SearchDao();
	    ArrayList<EmployeeBean> empList=new ArrayList<EmployeeBean>();
	    try {
			empList=sd.joiningSearch(joining1,joining2);
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
	    	E080Exception e080=new E080Exception("指定の範囲に入社した社員がいません");
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
