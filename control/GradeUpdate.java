package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GradeUpdateDao;
import exception.E012Exception;
import exception.E070Exception;
import exception.E090Exception;

/**
 * 利用者が入力した等級情報を更新するためにGradeUpdateDaoに渡す.
 * <br>等級情報を受け取り、更新するためにGradeUpdateDaoに渡す.
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
 * @see dao.GradeUpdateDao
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/GradeUpdate")
public class GradeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  等級表の更新する情報をGradeUpdateDaoに渡す.
	 *  <br>GradeUpdateDaoのupdateメソッドを呼ぶ
	 *  <br>更新が完了したらresult.jspにフォワードする.
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
		int authority=(int) session.getAttribute("authority");
		String id=(String) session.getAttribute("id");

 		 if(id==null) {
		 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
		 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
		 return;
      }
		int grade=0;
		float minSalary=0;
	    float maxSalary=0;

	    try {
			grade=Integer.parseInt(request.getParameter("grade"));
			minSalary=Float.parseFloat(request.getParameter("minSalary"));
			maxSalary=Float.parseFloat(request.getParameter("maxSalary"));
			}catch(NumberFormatException e) {
				E012Exception e012=new E012Exception("給料は実数を入力してください");
				er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
			return;

			}
     String msg=null;
	    GradeUpdateDao dd=new GradeUpdateDao();
	    try {
			msg=dd.update(grade, minSalary, maxSalary);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    request.setAttribute("msg", msg);

        String url=null;
	    if(authority==1) {
		    url="topAdmin.jsp";
		    }else if(authority==2) {
		    url="topJinji.jsp";
		    }

	    //リザルト画面へフォワード
	    request.setAttribute("url", url);
		RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
	    dispatch.forward(request, response);
	}

}
