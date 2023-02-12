insert into faculties (id, name, duration) values (default,'Music', 3 );
insert into faculties (id, name, duration) values (default,'Math sciences', 4 );
insert into faculties (id, name, duration) values (default,'Hacking', 5 );
insert into faculties (id, name, duration) values (default,'Management', 4 );

insert into groups (id, name, faculty_id) values (default, '11-AA', 1);
insert into groups (id, name, faculty_id) values (default, '11-AB', 1);
insert into groups (id, name, faculty_id) values (default, '12-AA', 2);
insert into groups (id, name, faculty_id) values (default, '13-AA', 3);
insert into groups (id, name, faculty_id) values (default, '13-AB', 3);
insert into groups (id, name, faculty_id) values (default, '13-AC', 3);
insert into groups (id, name, faculty_id) values (default, '14-AC', 4);
insert into groups (id, name, faculty_id) values (default, '14-AB', 4);

insert into subjects (id, name, description) values (default, 'Mathematics', 'Mathematicians have always been fascinated by numbers.');
insert into subjects (id, name, description) values (default, 'Music', 'Music is everywhere in the world around us; it is part of all of our lives, whether we play it, actively listen to it, or hear it in passing.');
insert into subjects (id, name, description) values (default, 'Engineering Science', 'Engineering Science encompasses a vast range of subjects, from microelectronics to offshore oil platforms.');
insert into subjects (id, name, description) values (default, 'Law', 'Studying law will not only give you the opportunity to qualify as a solicitor or barrister.');
insert into subjects (id, name, description) values (default, 'Modern Languages', 'Studying Modern Languages provides both practical training in written and spoken language. have always been fascinated by numbers.');
insert into subjects (id, name, description) values (default, 'Cyber protection', 'The science about backdoors.');

insert into teachers (id, age, email, experience, faculty_id, first_name, grade, last_name)
values  (1, 20, 'AndrewMoore600@email.com', 3, 3, 'Andrew', 'First-professional Degree', 'Moore'),
        (2, 22, 'PaulGarcia168@email.com', 3, 2, 'Paul', 'Doctoral Degree', 'Garcia'),
        (3, 30, 'RobertWilliams148@email.com', 12, 4, 'Robert', 'First-professional Degree', 'Williams'),
        (4, 39, 'JohnTaylor280@email.com', 5, 2, 'John', 'Master’s Degree', 'Taylor'),
        (5, 77, 'PaulSmith424@email.com', 5, 3, 'Paul', 'Doctoral Degree', 'Smith'),
        (6, 68, 'AnthonyLopez366@email.com', 20, 1, 'Anthony', 'Master’s Degree', 'Lopez'),
        (7, 80, 'RichardWilliams499@email.com', 31, 2, 'Richard', 'Master’s Degree', 'Williams'),
        (8, 51, 'JamesJones481@email.com', 12, 3, 'James', 'First-professional Degree', 'Jones'),
        (9, 27, 'JosephAnderson841@email.com', 1, 2, 'Joseph', 'Bachelor’s Degree', 'Anderson'),
        (10, 29, 'CharlesTaylor528@email.com', 2, 2, 'Charles', 'Master’s Degree', 'Taylor');

insert into students (id, age, course, email, first_name, last_name, group_id)
values  (1, 28, 1, 'JohnSmith573@email.com', 'John', 'Smith', 1),
        (2, 21, 1, 'DanielLopez375@email.com', 'Daniel', 'Lopez', 1),
        (3, 27, 2, 'AnthonyMoore366@email.com', 'Anthony', 'Moore', 2),
        (4, 23, 3, 'ThomasTaylor694@email.com', 'Thomas', 'Taylor', 5),
        (5, 19, 2, 'WilliamTaylor22@email.com', 'William', 'Taylor', 3),
        (6, 28, 1, 'JoshuaLopez283@email.com', 'Joshua', 'Lopez', 4),
        (7, 30, 3, 'MarkDavis913@email.com', 'Mark', 'Davis', 2),
        (8, 29, 1, 'MichaelJackson966@email.com', 'Michael', 'Jackson', 3),
        (9, 28, 3, 'RichardSmith438@email.com', 'Richard', 'Smith', 5),
        (10, 24, 3, 'JamesLopez880@email.com', 'James', 'Lopez', 1);

insert into lessons (id, duration, group_id, name, start_time, subject_id, teacher_id)
values  (1, 60, 6, 'Web Development Fundamentals', '2023-02-22 00:33:02.716164', 4, 4),
    (2, 60, 7, 'Introduction to Computer Programming', '2023-02-14 13:33:02.722755', 5, 9),
    (3, 60, 6, 'Web Development Fundamentals', '2023-02-12 22:33:02.727123', 2, 1),
    (4, 60, 5, 'User Experience (UX) Design Principles', '2023-02-11 16:33:02.731543', 2, 8),
    (5, 90, 1, 'Cybersecurity and Network Security', '2023-02-13 23:33:02.736576', 4, 4),
    (6, 120, 4, 'Data Structures and Algorithms', '2023-02-21 09:33:02.742183', 3, 3),
    (7, 60, 6, 'Cloud Computing Fundamentals', '2023-02-12 07:33:02.749965', 5, 7),
    (8, 60, 5, 'Artificial Intelligence and Machine Learning', '2023-02-22 22:33:02.757773', 4, 1),
    (9, 30, 5, 'Artificial Intelligence and Machine Learning', '2023-02-11 22:33:02.762680', 4, 2),
    (10, 120, 8, 'Mobile Application Development', '2023-02-19 23:33:02.767549', 3, 9);