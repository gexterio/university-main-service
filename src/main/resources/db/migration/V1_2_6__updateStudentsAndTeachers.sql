update students
set user_id = (select id
               from users
               where users.username = students.email)
where 1=1;

update teachers
set user_id = (select id
               from users
               where users.username = teachers.email)
where 1=1;

