@echo off
rem ������
set USR_NAME=
set USE_PASS=
set DB_NAME=
set FILE_NAME="create_database.sql"
rem ���͗v��
set /P USR_NAME="MySQL�̃��O�C�����[�U��: "
set /P USE_PASS="���O�C�����[�U�̃p�X���[�h: "
set /P DB_NAME="�쐬����DB�������: "

rem DB�쐬SQL�t�@�C�����c�����܂܂�
IF EXIST %FILE_NAME% (
echo Y | del %FILE_NAME%
)

rem DB�쐬SQL�t�@�C���̍쐬
echo CREATE DATABASE %DB_NAME%; >> create_database.sql
echo SHOW DATABASES; >> create_database.sql

rem MySQL�̋N�������DB�쐬SQL�̎��s
mysql -u %USR_NAME% -p%USE_PASS% -v -f -s < create_database.sql
mysql -u %USR_NAME% -p%USE_PASS% -v -f -s %DB_NAME% < sample_tables.sql

rem DB�쐬SQL�t�@�C���̍폜
echo Y | del "create_database.sql"

pause