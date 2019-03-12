 CREATE TABLE `gym_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `display_img_urls` text,
  `label` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `mini_program_code_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `member_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

 CREATE TABLE `gym_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `gym_id` bigint(20) DEFAULT NULL,
  `price_info` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `gym_coach` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gym_id` bigint(20) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;