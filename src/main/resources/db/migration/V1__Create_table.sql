CREATE TABLE `users` (
                         `user_index` int(11) NOT NULL,
                         `user_id` char(10) DEFAULT NULL,
                         `user_pwd` char(20) DEFAULT NULL,
                         `user_name` char(80) DEFAULT NULL,
                         `user_age` char(20) DEFAULT NULL,
                         `user_major`char(20) DEFAULT NULL,
                         `user_email`char(20) DEFAULT NULL,
                         `user_gender`char(20) DEFAULT NULL,
                         `user_profile`char(20) DEFAULT NULL,
                         PRIMARY KEY (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;