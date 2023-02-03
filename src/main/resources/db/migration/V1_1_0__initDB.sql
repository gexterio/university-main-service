create table if not exists faculties
(
    id       serial primary key not null,
    name     varchar(32) not null,
    duration integer
    );

create table if not exists groups
(
    id serial primary key not null,
    name varchar(32) not null,
    faculty_id int references faculties
    );

create table if not exists students
(
    id serial primary key not null,
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    age smallint,
    group_id int references groups,
    course smallint,
    email varchar(32)
    );

create table if not exists subjects
(
    id serial primary key not null,
    name varchar(32) not null,
    description varchar(256)
    );

create table if not exists teachers
(
    id serial primary key not null,
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    age smallint not null,
    grade varchar(32),
    experience int,
    email varchar(32),
    faculty_id int references faculties
    );

create table if not exists lessons
(
    id serial primary key not null,
    name varchar(32) not null,
    duration int,
    start_time timetz,
    subject_id int references subjects not null,
    group_id int references groups,
    teacher_id int references teachers
    );