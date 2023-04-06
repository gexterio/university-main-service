create table if not exists
    roles (
    id serial primary key,
    name varchar(32) not null unique
);