delete from object_type_config;

--1.0
insert into object_type_config (id, policy_line_type, obj_type, version) values (1, 'MOT','VEH','1.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (2, 'MOT','DRI','1.0');
--2.0
insert into object_type_config (id, policy_line_type, obj_type, version) values (3, 'MOT','VEH','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (4, 'MOT','DRI','2.0');
insert into object_type_config (id, policy_line_type, obj_type, version) values (5, 'TRA','VEH','2.0');