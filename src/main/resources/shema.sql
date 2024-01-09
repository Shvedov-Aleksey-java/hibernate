drop table if exists users cascade ;

create table if not exists users
(
    username  varchar(128) primary key,
    firstname varchar(128),
    lastname  varchar(128),
    birth_date date,
    age       int,
    role      varchar(32)
    );