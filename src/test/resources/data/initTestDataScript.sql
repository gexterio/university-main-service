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

insert into faculties (id, name, duration)
values (1, 'Engineering', 3),
       (2, 'Math sciences', 4);


insert into groups(id, name, faculty_id)
values (1, '11-AA', 1),
       (2, '11-AB', 1),
       (3, '12-AA', 2);

insert into subjects (id, name, description)
values (1, 'Mathematics', 'Mathematicians have always been fascinated by numbers.'),
       (2, 'Music','Music is everywhere in the world around us; it is part of all of our lives, whether we play it, actively listen to it, or hear it in passing.'),
       (3, 'Engineering Science', 'Engineering Science description.');


insert into teachers (id, age, email, experience, faculty_id, first_name, grade, last_name)
values (1, 20, 'AndrewMoore600@email.com', 3, 1, 'Andrew', 'First-professional Degree', 'Moore'),
       (2, 22, 'PaulGarcia168@email.com', 3, 1, 'Paul', 'Doctoral Degree', 'Garcia'),
       (3, 30, 'RobertWilliams148@email.com', 12, 2, 'Robert', 'First-professional Degree', 'Williams');

insert into students (id, age, course, email, first_name, last_name, group_id)
values (1, 28, 1, 'JohnSmith573@email.com', 'John', 'Smith', 1),
       (2, 21, 1, 'DanielLopez375@email.com', 'Daniel', 'Lopez', 1),
       (3, 27, 2, 'AnthonyMoore366@email.com', 'Anthony', 'Moore', 2);

insert into lessons (id, duration, group_id, name, start_time, subject_id, teacher_id)
values (1, 60, 1, 'Web Development Fundamentals', '2023-02-22 00:33:02.716164', 1, 1),
       (2, 60, 1, 'Introduction to Computer Programming', '2023-02-14 13:33:02.722755', 1, 1),
       (3, 60, 1, 'Web Development Fundamentals', '2023-02-12 22:33:02.727123', 1, 1),
       (4, 60, 1, 'User Experience (UX) Design Principles', '2023-01-11 16:33:02.731543', 1, 1),
       (5, 90, 1, 'Cybersecurity and Network Security', '2023-03-13 23:33:02.736576', 1, 2),
       (6, 120, 2, 'Data Structures and Algorithms', '2023-02-21 09:33:02.742183', 2, 2),
       (7, 60, 2, 'Cloud Computing Fundamentals', '2023-02-12 07:33:02.749965', 2, 2),
       (8, 60, 2, 'Artificial Intelligence and Machine Learning', '2023-02-22 22:33:02.757773', 2, 3),
       (9, 30, 3, 'Artificial Intelligence and Machine Learning', '2023-02-11 22:33:02.762680', 3, 3),
       (10, 120, 3, 'Mobile Application Development', '2023-02-19 23:33:02.767549', 3, 3);