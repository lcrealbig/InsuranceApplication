--CONFIG just for testing premium calculation
delete from object_risks_config;
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','OC','1');
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','AC','1');
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','NNW','1');
insert into object_risks_config(object_type,object_risks,version) 
values ('VEH','ASI','1');