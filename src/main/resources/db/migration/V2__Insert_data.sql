insert into users ( `user_id`,
                    `user_pwd`,
                    `user_name`,
                    `user_age`,
                    `user_major`,
                    `user_email`,
                    `user_gender`,
                    `user_profile`,
                    `user_manager`)
values('aaaa', '0a0a','이주희', '23', '보안', 'aaaa@gmail.com', 'W', 'profile',1);

insert into users ( `user_id`,
                    `user_pwd`,
                    `user_name`,
                    `user_age`,
                    `user_major`,
                    `user_email`,
                    `user_gender`,
                    `user_profile`)
values('bbbb', '1b1b','김주희', '23', '백앤드', 'bbbb@gmail.com', 'W', 'profile');


insert into survey (`user_index`,
                    `survey_title`,
                    `survey_duplicate`)
values(0, '많이 사용하는 언어',2);

insert into board_pro (`subject`,
                       `content`,
                       `writer`,
                       `reg_date`,
                       `hit`,
                       `notice`,
                       `kind`,
                       `realm`,
                       `recommend`,
                       `delete_option`)
values('글제목','글내용1입니다.','ehfehfdl0927',now(),0,0,'자유게시판','백엔드',0,0);













-- insert into survey_item ( `survey_item_index`,
--                     `survey_index`,
--                     `survey_item_content`)
--
-- insert into survey_result ( `survey_result_index`,
--                     `survey_index`,
--                     `survey_item_index`,
--                     `survey_result`,
--                     `survey_result_reg_date`)