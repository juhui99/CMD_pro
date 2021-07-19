CREATE TABLE `users` (
                         `user_index` int NOT NULL AUTO_INCREMENT,
                         `user_id` varchar(10) NOT NULL,
                         `user_pwd` varchar(20) NOT NULL,
                         `user_name` varchar(80) DEFAULT NULL,
                         `user_age` varchar(20) DEFAULT NULL,
                         `user_major` varchar(20) DEFAULT NULL,
                         `user_email` varchar(200) DEFAULT NULL,
                         `user_gender` varchar(20) DEFAULT NULL,
                         `user_profile` varchar(20) DEFAULT NULL,
                         `user_manager` int NOT NULL DEFAULT 0,
                         PRIMARY KEY (`user_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `survey` (
                         `survey_index` int NOT NULL AUTO_INCREMENT,
                         `user_index` int NOT NULL,
                         `survey_title` varchar(200) NOT NULL,
                         `survey_content` varchar(1000) DEFAULT NULL,
                         `survey_reg_date` datetime NOT NULL DEFAULT NOW(),
                         `survey_end_date` datetime DEFAULT NULL,
                         `survey_count` int DEFAULT NULL,
                         `survey_duplicate` int DEFAULT NULL,
                         PRIMARY KEY (`survey_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# CREATE TABLE `survey_item` (
#                           `survey_item_index` int NOT NULL AUTO_INCREMENT,
#                           `survey_index` int NOT NULL,
#                           `survey_item_content` varchar(1000) DEFAULT NULL,
#                           PRIMARY KEY (`survey_item_index`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# CREATE TABLE `survey_result` (
#                           `survey_result_index` int NOT NULL AUTO_INCREMENT,
#                           `survey_index` int NOT NULL,
#                           `survey_item_index` int NOT NULL,
#                           `survey_result` varchar(1000) DEFAULT NULL,
#                           `survey_result_reg_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
#                           PRIMARY KEY (`survey_result_index`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;