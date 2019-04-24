-- 教练列表
CREATE TABLE `gym_play_coach` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `gym_id` bigint(20)  DEFAULT NULL,
   `name` varchar(255) DEFAULT NULL,
   `phone` varchar(255) DEFAULT NULL,
   `join_time` timestamp  DEFAULT NULL,
   `member_array` text,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 这个表已经创建了,学员列表
-- CREATE TABLE `gym_bind_members` (
--  `id` bigint(20) NOT NULL,
--  `nick_name` varchar(255) DEFAULT NULL,
--  `uid` varchar(255) DEFAULT NULL,
--  `phone_num` varchar(255) DEFAULT NULL,
--  `gym_name` varchar(255) DEFAULT NULL,
--  `bind_time` timestamp  DEFAULT NULL,
--  PRIMARY KEY (`id`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8;