create table users (
USER_ID varchar(20) default nextval('user_id_seq'),
USER_NAME varchar(20),
USER_PASSWORD varchar(20),
NAME varchar(70),
ADDRESS varchar(70),
TYPE varchar(30),
BIRTH_DATE date,
PESEL int
);
insert into users(user_name,user_password,name,address,type,birth_date,pesel)
values('mat92','czarny','Mateusz','Hoekeindseweg','user','1992-12-03',1234567);
