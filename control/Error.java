package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ErrorBean;

/**
 *
 *
 * エラー画面に表示する情報を受けとる.
 * <br>エラーコード、エラーメッセージを受け取る.
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
 * @see model.ErrorBean
 *
 */
@WebServlet("/Error")
public class Error extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Error() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * エラーコード、エラーメッセージをうけとる.
	 * <br>error.jspにフォワードする
	 * @param errorCode エラーコード
	 * @param errorMsg  エラーメッセージ
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response,String errorCode,String errorMsg) throws ServletException, IOException {
		// TODO Auto-generated method stub
        ErrorBean eb=new ErrorBean();
        eb.setErrorCode(errorCode);
        eb.setErrorMsg(errorMsg);
        request.setAttribute("eb",eb);
        //error.jspにフォワードする
        RequestDispatcher dispatch=request.getRequestDispatcher("error.jsp");
        dispatch.forward(request, response);
}

}
