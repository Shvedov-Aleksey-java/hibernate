drop table if exists users, company, profile cascade;



create table if not exists company
(
    id   serial primary key,
    name varchar(64) not null unique
);

create table if not exists users
(
    id         bigserial primary key,
    username   varchar(128),
    firstname varchar(128),
    lastname  varchar(128),
    birth_date date,
    role       varchar(32),
    company_id int references company
);

create table if not exists profile
(
    users_id bigint primary key references users(id),
    street varchar(128),
    language char(2)
)
