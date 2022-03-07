delete from policy_line_type_config;

--1.0
INSERT INTO policy_line_type_config (id, product_id, policy_line_type, version) VALUES (1, 'M', 'MOT', '1.0');
--2.0
INSERT INTO policy_line_type_config (id, product_id, policy_line_type, version) VALUES (2, 'M', 'MOT', '2.0');
INSERT INTO policy_line_type_config (id, product_id, policy_line_type, version) VALUES (3, 'M', 'TRA', '2.0');