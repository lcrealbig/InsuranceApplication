delete from object_type_config;

--1.0
insert into object_type_config (id, policy_line_type, obj_type, version) values (1, 'MOT','VEH','1.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (2, 'MOT','DRI','1.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (3, 'APA','PRO','1.0');

--2.0
insert into object_type_config (id, policy_line_type, obj_type, version) values (4, 'MOT','VEH','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (5, 'MOT','DRI','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (6, 'TRA','VEH','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (7, 'APA','PRO','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (7, 'HOU','PRO','2.0');