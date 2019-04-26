# 数据库更改通知 2019_04_26

## 用户表增加权限字段
```roomsql
alter table gym_admin_user add column permission_array varchar(512);
```
## 新增表
教练绑定健身房表
```roomsql
CREATE TABLE `gym_play_coach_with_gym` (
   `coach_uid` varchar(255) NOT NULL,
   `gym_id` bigint(20)  DEFAULT NULL,
   `bind_time` timestamp  DEFAULT NULL,
   PRIMARY KEY (`coach_uid`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
学员绑定教练表
```roomsql
CREATE TABLE `gym_play_member_with_coach` (
   `coach_uid` varchar(255) NOT NULL,
   `student_uid` varchar(255) NOT NULL,
   `student_nick` varchar(255) NOT NULL,
   `gym_id` bigint(20)  DEFAULT NULL,
   `bind_time` timestamp  DEFAULT NULL,
   PRIMARY KEY (`student_uid`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```