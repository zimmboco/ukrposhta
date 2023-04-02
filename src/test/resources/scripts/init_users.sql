SET FOREIGN_KEY_CHECKS=0;  -- turn off foreign key checks
TRUNCATE TABLE levels;  -- truncate tables
TRUNCATE TABLE users;
TRUNCATE TABLE projects;
TRUNCATE TABLE projects_users;
TRUNCATE TABLE roles;
TRUNCATE TABLE roles_users;
SET FOREIGN_KEY_CHECKS=1;

insert into levels (level_name) VALUES ('LEVEL_MIDDLE');
insert into roles (role_name) VALUES ('ROLE_QA');
INSERT INTO users (id, email, password, levels_level_name) VALUES (1, 'zimmboco', '1234', 'LEVEL_MIDDLE');
INSERT INTO users (id, email, password, levels_level_name) VALUES (2, 'zimmboco1', '12345', 'LEVEL_MIDDLE');
insert into projects (id, name_project, topic) VALUES (1, 'Igor', 'math');
insert into projects (id, name_project, topic) VALUES (2, 'Ilya', 'run');
insert into projects_users (user_id, project_id) VALUES (1, 1);
insert into projects_users (user_id, project_id) VALUES (2, 1);
insert into roles_users (user_id, role_name) VALUES (1, 'ROLE_QA');
insert into roles_users (user_id, role_name) VALUES (2, 'ROLE_QA');


