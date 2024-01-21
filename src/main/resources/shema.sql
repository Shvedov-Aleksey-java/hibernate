drop table if exists users, company, profile, chat, users_chat cascade;



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
    id bigserial primary key,
    users_id bigint not null unique references users(id),
    street varchar(128),
    language char(2)
);

create table if not exists chat
(
    id bigserial primary key,
    name varchar(64) not null unique
);

create table if not exists users_chat
(
    users_id bigint references users(id),
    chat_id bigint references chat(id),
    created_at timestamp not null,
    created_by varchar(128) not null
);
