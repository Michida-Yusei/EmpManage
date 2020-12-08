package exception;

/**
 * 社員情報の変更、削除で指定した社員番号が未登録である場合に発生する.
 * <br>変更、削除で指定した社員番号が「社員表」に未登録である場合に発生する
 * <br>本クラスは{@linkplain Exception}クラスを継承している.
 *
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E022Exception extends Exception {

    /** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 22L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-022";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message 出力させるエラーメッセージ
     * @since 1.0
     */
    public E022Exception(String message) {
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

