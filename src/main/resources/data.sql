insert into user_details(id,birth_date,name)
values(10001, current_date(), 'Ranga');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'Ravi');

insert into user_details(id,birth_date,name)
values(10003, current_date(), 'Sathish');

insert into post(id, description, user_id)
values (20001, 'description for user 1', 10001);

insert into post(id, description, user_id)
values (20002, 'description for user 2', 10002);

insert into post(id, description, user_id)
values (20003, 'description for user 2', 10002);