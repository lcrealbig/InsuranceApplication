delete from vehicle_type_config;

--1.0
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (1, 'MOT','MTB', '1.0');
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (2, 'MOT','CAR', '1.0');
--2.0
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (3, 'MOT','MTB', '2.0');
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (4, 'MOT','CAR', '2.0');
insert into vehicle_type_config (id, policy_line_type, vehicle_type, version) values (5, 'TRA', 'TRA', '2.0');