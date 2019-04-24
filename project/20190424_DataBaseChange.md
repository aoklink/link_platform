# 数据库更改通知 2019_04_24

## 用户表增加权限字段
```roomsql
alter table gym_admin_user add column permission_array varchar(512);
```

### 增加表
```roomsql
CREATE TABLE `gym_play_coach` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `gym_id` bigint(20)  DEFAULT NULL,
   `name` varchar(255) DEFAULT NULL,
   `phone` varchar(255) DEFAULT NULL,
   `join_time` timestamp  DEFAULT NULL,
   `member_array` text,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
