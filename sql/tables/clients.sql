-- IN PROGRESS

create table clients(
ID number,
NAME varchar2(70),
PESEL number,
ADRESS varchar2(70),
BIRTH_DATE date,
LICENCE_YEARS number,   --how many years does client have driving licence
PHONE_NUM number,
INS_TIME number,        ---how many years does client have OC insurance
CLAIMS number,          --how many claims client had past 6 years
PRODUCTS number,         --solve how to store client products (varray? number to pass into another table as id?)
);