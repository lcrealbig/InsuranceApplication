--CONFIG just for testing premium calculation
delete from object_risks_config;
insert into object_risks_config(object_type,object_risks,required,version)
values ('VEH','OC','true','1');
insert into object_risks_config(object_type,object_risks,required,version)
values ('VEH','AC','false','1');
insert into object_risks_config(object_type,object_risks,required,version)
values ('VEH','NNW','false','1');
insert into object_risks_config(object_type,object_risks,required,version)
values ('VEH','ASI','false','1');