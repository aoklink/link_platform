---- 这个表已经创建了,教练列表
--CREATE TABLE `coach_account` (
--  `phone_num` varchar(11) NOT NULL COMMENT '手机号',
--  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
--  `head_icon` varchar(256) DEFAULT NULL,
--  `uid` varchar(32) NOT NULL COMMENT '教练id',
--  `user_type` varchar(32) NOT NULL,
--  `build_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
--  PRIMARY KEY (`uid`)
--) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- 教练绑定健身房表
CREATE TABLE `gym_play_coach_with_gym` (
   `coach_uid` varchar(255) NOT NULL,
   `gym_id` bigint(20)  DEFAULT NULL,
   `bind_time` timestamp  DEFAULT NULL,
   PRIMARY KEY (`coach_uid`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 学员绑定教练表
CREATE TABLE `gym_play_member_with_coach` (
   `coach_uid` varchar(255) NOT NULL,
   `student_uid` varchar(255) NOT NULL,
   `student_nick` varchar(255) NOT NULL,
   `gym_id` bigint(20)  DEFAULT NULL,
   `bind_time` timestamp  DEFAULT NULL,
   PRIMARY KEY (`student_uid`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;