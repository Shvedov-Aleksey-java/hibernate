drop table if exists users cascade;
drop sequence if exists user_id_seq cascade;

create table if not exists users
(
    id         serial primary key,
    username   varchar(128),
    firstname  varchar(128),
    lastname   varchar(128),
    birth_date date,
    role       varchar(32)
);

create sequence user_id_seq
    owned by users.id