--TABLE
create table objects_risks_config (
object_type varchar(10),
object_risks varchar(30),
version varchar(30)
);
--CONFIG
insert into object_risks(object_type,object_risks,version) 
values ('VEH','OC','1');
insert into object_risks(object_type,object_risks,version) 
values ('VEH','AC','1');
insert into object_risks(object_type,object_risks,version) 
values ('VEH','NNW','1');
insert into object_risks(object_type,object_risks,version) 
values ('VEH','ASI','1');
