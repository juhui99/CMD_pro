CREATE TABLE `users` (
                         `user_index` int(11) NOT NULL,
                         `user_id` varchar(10) DEFAULT NULL,
                         `user_pwd` varchar(20) DEFAULT NULL,
                         `user_name` varchar(80) DEFAULT NULL,
                         `user_age` varchar(20) DEFAULT NULL,
                         `user_major`varchar(20) DEFAULT NULL,
                         `user_email`varchar(200) DEFAULT NULL,
                         `user_gender`varchar(20) DEFAULT NULL,
                         `user_profile`varchar(20) DEFAULT NULL,
                         PRIMARY KEY (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users3` (
                         `user_index` int(11) NOT NULL,
                         `user_id` varchar(10) DEFAULT NULL,
                         `user_pwd` varchar(20) DEFAULT NULL,
                         `user_name` varchar(80) DEFAULT NULL,
                         `user_age` varchar(20) DEFAULT NULL,
                         `user_major`varchar(20) DEFAULT NULL,
                         `user_email`varchar(200) DEFAULT NULL,
                         `user_gender`varchar(20) DEFAULT NULL,
                         `user_profile`varchar(20) DEFAULT NULL,
                         PRIMARY KEY (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;