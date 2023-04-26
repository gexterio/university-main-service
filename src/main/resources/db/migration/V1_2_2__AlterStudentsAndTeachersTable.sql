alter table students
    add column
        user_id int references users (id);
alter table teachers
    add column
        user_id int references users (id);