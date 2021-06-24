create table customers(
CUSTOMER_ID number,
NAME varchar2(70),
PESEL number,
ADRESS varchar2(90),
BIRTH_DATE date,
PHONE_NUM number
);
/
create sequence customers_sequence
minvalue 1
maxvalue 9999999
start with 1
increment by 1;
/