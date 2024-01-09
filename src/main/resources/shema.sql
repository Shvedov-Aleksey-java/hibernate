drop table if exists users cascade;

create table if not exists users
(
    username   varchar(128),
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date date,
    role       varchar(32),
    primary key (firstname, lastname, birth_date)
);
