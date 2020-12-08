# EmpManage
サーブレットとJSPを使ったMVCモデルを使った社員情報管理システムです。
社員や部署に対するCRUD機能が主な機能となっています。
 
# Requirement
* Eclipse
* java11
* Tomcat9
* MySQL8

# Usage
 Eclipseで動的Webプロジェクトを作成し、srcフォルダの中にmodel,control,exception,daoをコピーする。
 daoパッケージのSuperDaoのConnectionの部分のユーザ名とパスワードはMySQLのアカウントに設定したものを使用する。
 
 DBの構築
 「create_batsampleDB.bat」と「sample_tables.sql」をダウンロードしてバッチファイルを実行すると
 データベースが出来上がります。ユーザ名、パスワード、ＤＢ名の記入が必要です。
 ユーザ名とパスワードはMySQLで設定されているのものをＤＢ名はempにして下さい。
 
 viewフォルダの中にあるlogin.htmlを実行し、ログイン成功すると機能の選択画面に遷移します。
 ログイン時のユーザ名とパスワードはsystemsuserテーブルに登録されているものです。
 
 
 
