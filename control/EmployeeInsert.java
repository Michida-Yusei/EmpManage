package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeInsertDao;
import exception.E012Exception;
import exception.E021Exception;
import exception.E023Exception;
import exception.E024Exception;
import exception.E070Exception;
import exception.E090Exception;

/**
 * 利用者が入力した社員情報を登録するためにEmployeeInsertDaoに渡す.
 * <br>利用者が入力した社員番号が登録されているか確認する
 * <br>社員番号が登録されていなかった場合に社員情報を登録する.
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.io.IOException
 * @see java.time.LocalDate
 * @see java.time.format.DateTimeFormatter
 * @see java.time.format.DateTimeParseException
 * @see java.time.format.ResolverStyle
 * @see javax.servlet.RequestDispatcher
 * @see javax.servlet.ServletException
 * @see javax.servlet.annotation.WebServlet
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.http.HttpServletRequest
 * @see javax.servlet.http.HttpServletResponse
 * @see javax.servlet.http.HttpSession
 * @see dao.EmployeeInsertDao
 * @see exception.E012Exception
 * @see exception.E021Exception
 * @see exception.E023Exception
 * @see exception.E024Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/EmployeeInsert")
public class EmployeeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  社員表の登録する情報をEmployeeInsertDaoに渡す.
	 *  <br>EmployeeInsertDaoのinsertメソッドを呼ぶ
	 *  <br>登録が完了したらresult.jspにフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E012Exception 社員番号・上司番号・部署番号が整数でない
	 *                            日付の型が正しくない
	 *                            給料・手当が実数でない
	 * @exception E021Exception 社員番号が登録済み
	 * @exception E023Exception 部署番号が登録されていない
	 * @exception E024Exception 上司番号が登録されていない
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


		int employeeNo=0;
		String employeeName=null;
		String job=null;
		int managerNo=0;
		String joiningCompanyDate=null;
		float salary=0;
		float allowance=0;
		int department=0;

		try {
	    employeeNo=Integer.parseInt(request.getParameter("employeeNo"));
	    employeeName=request.getParameter("employeeName");
		job=request.getParameter("job");
		managerNo=Integer.parseInt(request.getParameter("managerNo"));
	    joiningCompanyDate=request.getParameter("joiningCompanyDate");

	    try {
	    LocalDate.parse(joiningCompanyDate,
	    	    DateTimeFormatter.ofPattern("uuuu/MM/dd").withResolverStyle(ResolverStyle.STRICT));
	    }catch(DateTimeParseException e){
	    	E012Exception e012=new E012Exception("日付入力エラー");
			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;
	    }


	    salary=Float.parseFloat(request.getParameter("salary"));
	    allowance=Float.parseFloat(request.getParameter("allowance"));
	    department=Integer.parseInt(request.getParameter("department"));
		}catch(NumberFormatException e) {
			E012Exception e012=new E012Exception(
					"社員番号・上司番号・部署番号は整数、給料・手当は実数を入力してください");
			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;
		}

        int check=0;
	    EmployeeInsertDao ed=new EmployeeInsertDao();
	    try {
			check=ed.insert(employeeNo, employeeName, job, managerNo, joiningCompanyDate, salary, allowance, department);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    //社員番号が登録されていない場合
	    if(check==1) {
        E021Exception e021=new E021Exception("入力された社員番号は登録済みです。");
        er.doPost(request, response, e021.getErrorCode(), e021.getMessage());
        return;
        //部署番号が登録されていない場合
	    }else if(check==2) {
	    E023Exception e023=new E023Exception("入力された部署番号は登録されていません。");
	    er.doPost(request, response, e023.getErrorCode(), e023.getMessage());
	    return;
	    //上司番号が登録されていない場合
	    }else if(check==3) {
	    E024Exception e024=new E024Exception("入力された上司番号は登録されていません。上司が存在しない場合は０を入力してください。");
	    er.doPost(request, response, e024.getErrorCode(), e024.getMessage());
	    return;
	    }
	    String msg="登録しました";
	    request.setAttribute("msg", msg);

        String url=null;
	    if(authority==1) {
		    url="topAdmin.jsp";
		    }else if(authority==2) {
		    url="topJinji.jsp";
		    }
	  //リザルト画面にフォワード
	    request.setAttribute("url", url);
		RequestDispatcher dispatch=request.getRequestDispatcher("result.jsp");
	    dispatch.forward(request, response);
	}

}
