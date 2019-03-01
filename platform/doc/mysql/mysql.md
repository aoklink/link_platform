# 修改Root密码
update mysql.user set authentication_string=password('Root123456') where user='root' and Host ='localhost';