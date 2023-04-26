delete
from lessons;
delete
from students;
delete
from teachers;
delete
from groups;
delete
from faculties;
delete
from subjects;
delete
from users;

insert into faculties (id, name, duration)
values (1, 'Engineering', 3),
       (2, 'Math sciences', 4);


insert into groups(id, name, faculty_id)
values (1, '11-AA', 1),
       (2, '11-AB', 1);

insert into subjects (id, name, description)
values (1, 'Mathematics', 'Mathematicians have always been fascinated by numbers.'),
       (2, 'Music',
        'Music is everywhere in the world around us; it is part of all of our lives, whether we play it, actively listen to it, or hear it in passing.');

insert into users (id, username, password, role_id)
values (1, 'JohnSmith573@email.com', '{noop}pass', 2),
       (2, 'DanielLopez375@email.com', '{noop}pass', 2),
       (3, 'AndrewMoore600@email.com', '{noop}pass', 3),
       (4, 'PaulGarcia168@email.com', '{noop}pass', 3),
       (5, 'admin@test.com', '{noop}pass', 1);

insert into teachers (id, age, email, experience, faculty_id, first_name, grade, last_name, user_id)
values (1, 20, 'AndrewMoore600@email.com', 3, 1, 'Andrew', 'First-professional Degree', 'Moore',3),
       (2, 22, 'PaulGarcia168@email.com', 3, 1, 'Paul', 'Doctoral Degree', 'Garcia',4);

insert into students (id, age, course, email, first_name, last_name, group_id, user_id)
values (1, 28, 1, 'JohnSmith573@email.com', 'John', 'Smith', 1,1),
       (2, 21, 1, 'DanielLopez375@email.com', 'Daniel', 'Lopez', 1,2);

insert into lessons (id, duration, group_id, name, start_time, subject_id, teacher_id)
values (1, 60, 1, 'Web Development Fundamentals', '2023-02-22 14:33:02.716164', 1, 1),
       (2, 60, 1, 'Introduction to Computer Programming', '2023-02-14 13:33:02.722755', 1, 1),
       (3, 60, 1, 'Web Development Fundamentals', '2023-03-20 22:33:02.727123', 1, 1);


SELECT pg_catalog.setval(
               pg_get_serial_sequence('lessons', 'id'),
               (SELECT MAX(id) FROM "public"."lessons") + 1);

SELECT pg_catalog.setval(
               pg_get_serial_sequence('students', 'id'),
               (SELECT MAX(id) FROM "public"."students") + 1);

SELECT pg_catalog.setval(
               pg_get_serial_sequence('teachers', 'id'),
               (SELECT MAX(id) FROM "public"."teachers") + 1);

SELECT pg_catalog.setval(
               pg_get_serial_sequence('users', 'id'),
               (SELECT MAX(id) FROM "public"."users") + 1);