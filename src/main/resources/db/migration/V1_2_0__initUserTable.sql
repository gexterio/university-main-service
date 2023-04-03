create table if not exists users
(
    id         serial primary key,
    username   varchar(32) not null unique,
    password   varchar(128) not null,
    role       varchar(32)
    );