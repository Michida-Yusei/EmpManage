package exception;

/**
 * システムのエラーがある場合に発生する.
 * <br>ファイルアクセスエラー、SQLエラー、Javaシステム上のエラー等のシステムのエラーがある場合に発生する
 * <br>本クラスは{@linkplain Exception}クラスを継承している.
 *
  * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */

public class E090Exception extends Exception{
	/** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 90L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-090";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message 出力させるエラーメッセージ
     * @since 1.0
     */
    public E090Exception(String message) {
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
