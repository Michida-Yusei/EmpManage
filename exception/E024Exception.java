package exception;

/**
 * 社員情報の登録、変更で指定した社員の上司の社員番号が未登録である場合に発生する.
 * <br>登録、変更で指定した社員の上司の社員番号が「社員表」に未登録である場合に発生する
 * <br>本クラスは{@linkplain Exception}クラスを継承している.
 *
 *@version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E024Exception extends Exception {

    /** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 24L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-024";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message エラーメッセージ
     * @since 1.0
     */
    public E024Exception(String message) {
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

