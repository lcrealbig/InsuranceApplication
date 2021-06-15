-- IN PROGRESS

create table clients(
CLIENT_ID number,
CLIENT_NAME varchar2(70),
CLIENT_PESEL number,
CLIENT_ADRESS varchar2(70),
CLIENT_BIRTH_DATE date,
CLIENT_LICENCE_YEARS number,   --how many years does client have driving licence
CLIENT_PHONE_NUM number,
CLIENT_INS_TIME number,        ---how many years does client have OC insurance
CLIENT_CLAIMS number,          --how many claims client had past 6 years
CLIENT_PRODUCTS number,         --solve how to store client products (varray? number to pass into another table as id?)
);