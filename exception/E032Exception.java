package exception;

/**
 * 部署情報の変更、削除で指定した部署番号が未登録である場合に発生する.
 * <br>変更、削除で指定した部署番号が「部門表」に未登録である場合に発生する
 * <br>本クラスはjava.lang.Exceptionクラスを継承している.
 *
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E032Exception extends Exception {

    /** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 32L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-032";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param messege 出力させるエラーメッセージ
     * @since 1.0
     */
    public E032Exception(String messege) {
        super(messege);
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

