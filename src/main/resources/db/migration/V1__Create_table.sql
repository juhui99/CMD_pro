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
                         `progressing` int NOT NULL,
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

# CREATE TABLE `board` (
#                        `board_index` int NOT NULL AUTO_INCREMENT,
#                        `board_subject` varchar(50),
#                        `board_content` text,
#                        `board_writer` varchar (50) NOT NULL,
#                        `board_date` datetime NOT NULL,
#                        `board_hit` int NOT NULL DEFAULT 0,
#                        `board_notice` int NOT NULL DEFAULT 0,
#                        `board_kind` varchar(20),
#                        `board_realm` varchar(20),
#                        `board_recommend` int NOT NULL DEFAULT 0,
#                        `board_delete_option` int NOT NULL DEFAULT 0,
#                        PRIMARY KEY (board_index)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE `board_pro` (
     `bno` int NOT NULL AUTO_INCREMENT,
     `subject` varchar(50),
     `content` text,
     `writer` varchar (50) NOT NULL,
     `reg_date` datetime NOT NULL,
     `hit` int NOT NULL DEFAULT 0,
     `notice` varchar(20),
     `kind` varchar(20),
     `realm` varchar(20),
     `recommend` int NOT NULL DEFAULT 0,
     `delete_option` int NOT NULL DEFAULT 0,
      PRIMARY KEY (`bno`)
      );

 CREATE TABLE `board_recommend` (
     `kind` varchar(20),
     `realm` varchar(20),
     `bno` int NOT NULL,
     `userID` varchar(20),
     FOREIGN KEY(bno) REFERENCES board_pro(bno) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comment` (
    `bno` int NOT NULL,
    `c_bno` int NOT NULL AUTO_INCREMENT,
    `c_sequence` int,
    `level` int NOT NULL DEFAULT 0,
    `reg_date` datetime NOT NULL,
    `writer` varchar(20) NOT NULL,
    `content` text,
    `delete_option` int NOT NULL DEFAULT 0,
    FOREIGN KEY(bno) REFERENCES board_pro(bno) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (`c_bno`)

);

CREATE TABLE `file`(
    `f_no` int PRIMARY KEY AUTO_INCREMENT,
    `bno` int NOT NULL,
    `filename` varchar(200),
    `fileOriginName` varchar(300),
    `fileUrl` varchar(500),
    FOREIGN KEY(bno) REFERENCES board_pro(bno) ON DELETE CASCADE ON UPDATE CASCADE
) ;





