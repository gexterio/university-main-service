create table if not exists faculties
(
    id       serial primary key not null unique,
    name     varchar(64) not null unique,
    duration integer
    );

create table if not exists groups
(
    id serial primary key not null unique,
    name varchar(32) not null unique,
    faculty_id int references faculties(id)
    );

create table if not exists students
(
    id serial primary key not null unique,
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    age smallint,
    group_id int references groups(id),
    course smallint,
    email varchar(64) unique
    );

create table if not exists subjects
(
    id serial primary key not null unique,
    name varchar(64) not null unique,
    description varchar(256)
    );

create table if not exists teachers
(
    id serial primary key not null unique,
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    age smallint not null,
    grade varchar(32),
    experience int,
    email varchar(64) unique,
    faculty_id int references faculties(id)
    );

create table if not exists lessons
(
    id serial primary key not null unique,
    name varchar(64) not null,
    duration int,
    start_time timestamptz,
    subject_id int references subjects(id),
    group_id int references groups(id),
    teacher_id int references teachers(id)
    );
