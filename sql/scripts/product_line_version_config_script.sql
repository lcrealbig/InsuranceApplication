delete from product_line_version_config;

--1.0
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (1, 'MOT', '1.0');
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (2, 'APA', '1.0');

--2.0
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (3, 'MOT', '2.0');
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (4, 'TRA', '2.0');
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (5, 'APA', '2.0');
INSERT INTO product_line_version_config (id, policy_line_type, version) VALUES (6, 'HOU', '2.0');