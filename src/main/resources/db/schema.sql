create database if not exists wishlist;
use wishlist;

drop table if exists wish;
drop table if exists wishlist; 
drop table if exists users;


create table users(
user_id int auto_increment primary key,
username varchar(50) not null,
password varchar(255) not null,
name varchar(50) not null,
email varchar(50) unique
);

create table wishlist(
wishlist_id int auto_increment primary key,
title varchar(50) not null,
user_id int,
constraint fk_users foreign key (user_id) references users(user_id) on delete cascade
);

create table wish(
wish_id int auto_increment primary key,
wish_title varchar(50) not null,
price decimal(10, 2),
description text,
url varchar(2048),
image_url varchar(2048),
wishlist_id int,
constraint fk_wishlist foreign key (wishlist_id) references wishlist(wishlist_id) on delete cascade
);