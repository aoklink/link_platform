# Install MYSQL 8 On Docker

# Pull Image (拉取镜像)
```docker pull mysql```
# Start (启动镜像)
```docker run -p 3306:3306 -v /root/data/mysql/:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest```
# Initializate (初始化)
[init_platform.sql](/files/init_platform.sql)