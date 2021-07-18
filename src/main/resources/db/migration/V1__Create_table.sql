CREATE TABLE `users` (
                         `user_index` int(11) NOT NULL,
                         `user_id` varchar(10) NOT NULL,
                         `user_pwd` varchar(20) NOT NULL,
                         `user_name` varchar(80) DEFAULT NULL,
                         `user_age` varchar(20) DEFAULT NULL,
                         `user_major` varchar(20) DEFAULT NULL,
                         `user_email` varchar(200) DEFAULT NULL,
                         `user_gender` varchar(20) DEFAULT NULL,
                         `user_profile` varchar(20) DEFAULT NULL,
                         `user_manage` int NOT NULL,
                         PRIMARY KEY (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;