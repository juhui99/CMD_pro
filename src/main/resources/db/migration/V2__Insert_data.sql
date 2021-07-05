-- rule 생성 순서
/*
1. storage
2. read_rule
3. query_read_rule
4. data_rule
5. column_rule
6. write_rule
7. excel_write_rule
8. template
9. excel_template
10. excel_tab
11. rule
12. read_write_link
13. upload
14. request_log
*/

-- 1. storage
insert into users ( `user_index`,
                    `user_id`,
                    `user_pwd`,
                    `user_name`,
                    `user_age`,
                    `user_major`,
                    `user_email`,
                    `user_gender`,
                    `user_profile`)
values(1, 'juhee', '1234','이주희', '23', '컴공', 'juhee@gmail.com', 'W', 'profile');
