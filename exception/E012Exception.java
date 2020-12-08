package exception;

/**
 * 入力した社員情報、部署情報、等級情報の形式に誤りがある場合に発生する.
 * <br>入力した社員情報情報の形式に以下の誤りがある場合に発生する
 * <br>　・社員番号が整数でない
 * <br>　・上司の社員番号が整数でない
 * <br>　・部署番号が整数でない
 * <br>　・入社年月日がyyyy/MM/DD形式でない
 * <br>　・月給が実数でない
 * <br>　・手当が実数でない
 * <br>　・等級が整数でない
 * <br>本クラスは{@linkplain Exception}クラスを継承している.
 *
 * @version 1.0 JDK1.8.0_151
 * @author 中原大誠（電子開発学園）
 *
 * @see java.lang.Exception
 *
 */
public class E012Exception extends Exception {

    /** シリアルバージョン番号({@value}) */
    private static final long serialVersionUID = 12L;

    /** エラーコード({@value}) */
    private static final String ERR_CODE = "E-012";

    /**
     * コンストラクタ.
     * <br>メッセージを受け取り、スーパークラスへ受け渡す.
     *
     * @param message 出力させるエラーメッセージ
     * @since 1.0
     */
    public E012Exception(String message) {
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

