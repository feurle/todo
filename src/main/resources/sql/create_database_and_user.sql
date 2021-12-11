create database stocks;
create user 'stocksuser'@'%' identified by 'stockspwd';
grant all on stocks.* to 'stocksuser'@'%';