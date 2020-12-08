package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentInsertDao;
import exception.E012Exception;
import exception.E031Exception;
import exception.E070Exception;
import exception.E090Exception;

/**
 * 利用者が入力した部署情報を登録するためにDepartmentInsertDaoに渡す.
 * <br>利用者が入力した部署番号が登録されているか確認する
 * <br>部署番号が登録されていなかった場合に部署情報を登録する.
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
 * @see dao.DepartmentInsertDao
 * @see exception.E012Exception
 * @see exception.E031Exception
 * @see exception.E070Exception
 * @see exception.E090Exception
 *
 */
@WebServlet("/DepartmentInsert")
public class DepartmentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * DepartmentInsertのコンストラクタ―
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 *  部署表の登録する情報をDepartmentInsertDaoに渡す.
	 *  <br>DepartmentInsertDaoのinsertメソッドを呼ぶ
	 *  <br>登録が完了したらresult.jspにフォワードする.
	 *
	 * @exception E090Exception システムエラー
	 * @exception E070Exception セッションタイムアウトエラー
	 * @exception E012Exception 部署番号が整数でない
	 * @exception E031Exception 部署番号が登録済み
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Error er=new Error();
		HttpSession session=request.getSession();
		int authority=(int) session.getAttribute("authority");
		String id=(String) session.getAttribute("id");

		//セッションタイムアウトかどうかの確認
 		 if(id==null) {
		 E070Exception e070=new E070Exception("セッションタイムアウト　ログインし直してください");
		 er.doPost(request, response, e070.getErrorCode(), e070.getMessage());
		 return;
      }


		int departmentNo=0;
		String departmentName=null;
		String address=null;

		try {
		departmentNo=Integer.parseInt(request.getParameter("departmentNo"));
		departmentName=request.getParameter("departmentName");
		address=request.getParameter("address");
		//部署番号が整数でない場合
		}catch(NumberFormatException e) {
			E012Exception e012=new E012Exception("部署番号は整数を入力して下さい");
			er.doPost(request, response,e012.getErrorCode(),e012.getMessage());
		return;

		}
     int check=0;
	    DepartmentInsertDao dd=new DepartmentInsertDao();
	    try {
			check=dd.insert(departmentNo,departmentName,address);
		} catch (E090Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    //部署番号が登録済みの場合
	    if(check==1) {
	    	E031Exception e031=new E031Exception("部署番号が登録済みです");
	    	er.doPost(request, response,e031.getErrorCode(),e031.getMessage());
			return;
	    }

	    String msg="登録されました";
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
