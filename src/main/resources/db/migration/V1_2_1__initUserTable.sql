create table if not exists users
(
    id         serial primary key,
    username   varchar(64) not null unique,
    password   varchar(128) not null,
    role_id    int references roles (id)
    );