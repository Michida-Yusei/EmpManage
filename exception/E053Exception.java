package exception;

/**
 * 「システム利用者表」変更、削除の際に入力した社員番号が未登録である.
 * <br>登録で指定したユーザー識別名が社員表に未登録であった場合に発生する
 * <br>本クラスは{@linkplain java.lang.Exception}クラスを継承している.
 *
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E053Exception extends Exception{
	 /** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 31L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-053";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message 出力させるエラーメッセージ
     * @since 1.0
     */
    public E053Exception(String message) {
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
