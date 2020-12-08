package exception;
/**
 * 社員検索で結果が一件もない場合に発生する.
 * <br>検索で指定した社員番号が社員表に未登録である場合に発生する
 * <br>検索で指定した部署が社員表に未登録である場合に発生する
 * <br>検索で指定した職種が社員表に未登録である場合に発生する
 * <br>検索で指定した入社年月日の範囲に該当する社員情報がない場合に発生する
 * <br>検索で指定した等級の範囲に該当する社員情報がない場合に発生する
 * <br>本クラスは{@linkplain Exception}クラスを継承している.
 *
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E080Exception extends Exception{
	/** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 21L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-080";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message 出力させるエラーメッセージ
     * @since 1.0
     */
    public E080Exception(String message) {
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
