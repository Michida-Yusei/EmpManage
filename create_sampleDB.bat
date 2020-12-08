@echo off
rem 初期化
set USR_NAME=
set USE_PASS=
set DB_NAME=
set FILE_NAME="create_database.sql"
rem 入力要求
set /P USR_NAME="MySQLのログインユーザ名: "
set /P USE_PASS="ログインユーザのパスワード: "
set /P DB_NAME="作成するDB名を入力: "

rem DB作成SQLファイルが残ったままか
IF EXIST %FILE_NAME% (
echo Y | del %FILE_NAME%
)

rem DB作成SQLファイルの作成
echo CREATE DATABASE %DB_NAME%; >> create_database.sql
echo SHOW DATABASES; >> create_database.sql

rem MySQLの起動およびDB作成SQLの実行
mysql -u %USR_NAME% -p%USE_PASS% -v -f -s < create_database.sql
mysql -u %USR_NAME% -p%USE_PASS% -v -f -s %DB_NAME% < sample_tables.sql

rem DB作成SQLファイルの削除
echo Y | del "create_database.sql"

pause