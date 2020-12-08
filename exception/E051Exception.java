package exception;

/**
 * 登録の際に入力したユーザ識別名が既に登録済みである.
 * <br>登録で指定したユーザー識別名がシステム利用者表に登録済みであった場合に発生する
 * <br>本クラスは{@linkplain java.lang.Exception}クラスを継承している.
 *
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E051Exception extends Exception{
	 /** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 31L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-051";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message 出力させるエラーメッセージ
     * @since 1.0
     */
    public E051Exception(String message) {
        super(message);
    }

    /**
     * エラーコードの出力.
     * <br>呼び出し元にエラーコードを戻す.
     *
     * @return 対応したエラーコード
     * @since 1.0
     */
    public String getErrorCode() {
        return ERR_CODE;
    }
}
