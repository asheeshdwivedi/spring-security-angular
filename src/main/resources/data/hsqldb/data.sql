INSERT INTO authority (authority_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (authority_id, name) VALUES (3, 'ROLE_SYS_ADMIN');
INSERT INTO authority (authority_id, name) VALUES (2, 'ROLE_USER');


INSERT INTO user (user_id, created_at, created_by, updated_at, updated_by, version, email, first_name, last_name, password) VALUES (1, '2016-01-18 16:39:38', 'admin1@xxx.com', null, null, 0, 'admin1@xxx.com', 'Admin', 'Admin', 'admin1Pass');
INSERT INTO user (user_id, created_at, created_by, updated_at, updated_by, version, email, first_name, last_name, password) VALUES (2, '2016-01-18 16:39:38', 'admin1@xxx.com', null, null, 0, 'admin2@xxx.com', 'System', 'Admin', 'admin2Pass');
INSERT INTO user (user_id, created_at, created_by, updated_at, updated_by, version, email, first_name, last_name, password) VALUES (3, '2016-01-18 16:39:38', 'admin1@xxx.com', null, null, 0, 'user1@xxx.com', 'User', 'User', 'user1Pass');



INSERT INTO user_authorities (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authorities (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authorities (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authorities (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authorities (user_id, authority_id) VALUES (3, 2);
INSERT INTO user_authorities (user_id, authority_id) VALUES (2, 3);