select * from users u;
select * from user_roles ur;
select * from refresh_tokens rt;

select * from user_roles ur where ur.user_id = 1;

drop table users;
drop table user_roles;
drop table refresh_tokens;