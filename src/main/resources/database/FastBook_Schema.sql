create database FastBook;
use FastBook;

create table Student(
ID int not null primary key,
username varchar(50),
password varchar(50),
name varchar(50)
);

create table Post(
ID int not null primary key,
student_id int,
title text,
description text,
name varchar(50),
time_stamp timestamp,

foreign key (student_id) references Student (id)
);

create table Simple_Post(
post_id int primary key not null,
picture_url text,
likes int,

foreign key (post_id) references Post(id)
);

create table Activity_Post(
post_id int primary key not null,

foreign key (post_id) references Post (id)
);

create table Question(
post_id int primary key not null,
votes int,

foreign key (post_id) references Post (id)
);

create table Answer(
id int primary key not null,
post_id int,
student_id int,
marked_correct boolean,
votes int,
text text,
time_stamp timestamp,

foreign key (post_id) references Question (post_id),
foreign key (student_id) references Student (id)
);

create table Comment(
id int primary key not null,
post_id int,
student_id int,
text text,
likes int,
time_stamp timestamp,

foreign key (post_id) references Simple_Post (post_id),
foreign key (student_id) references Student (id)
);

create table Reply(
id int primary key not null,
post_id int,
student_id int,
text text,
time_stamp timestamp,

foreign key (post_id) references Activity_Post (post_id),
foreign key (student_id) references Student (id)
);

create table Friend(
id int primary key not null,
student_id_from int,
student_id_to int,
status boolean,

foreign key (student_id_from) references Student (id),
foreign key (student_id_to) references Student (id)
);

create table Message(
id int primary key not null,
student_id_from int,
student_id_to int,
text text,
time_stamp timestamp,

foreign key (student_id_from) references Student (id),
foreign key (student_id_to) references Student (id)
);



