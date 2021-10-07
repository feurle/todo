create database todo;
create user 'todouser'@'%' identified by 'secret';
grant all on todo.* to 'todouser'@'%';