create database fastbook;
use fastbook;

create table Student(
                        ID int auto_increment primary key,
                        username varchar(50),
                        password varchar(50),
                        name varchar(50),
                        email text,
                        phone_number varchar(15)
);

create table Post(
                     ID int auto_increment primary key,
                     student_id int,
                     title text,
                     description text,
                     time_stamp timestamp,

                     foreign key (student_id) references Student (id)
);


create table Simple_Post(
                            post_id int primary key,
                            picture_url text,
                            likes int,

                            foreign key (post_id) references Post(id)
);

create table Activity_Post(
                              post_id int primary key,

                              foreign key (post_id) references Post (id)
);

create table Question(
                         post_id int primary key,
                         votes int,

                         foreign key (post_id) references Post (id)
);

create table Answer(
                       id int auto_increment primary key,
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
                        id int auto_increment primary key,
                        post_id int,
                        student_id int,
                        text text,
                        likes int,
                        time_stamp timestamp,

                        foreign key (post_id) references Simple_Post (post_id),
                        foreign key (student_id) references Student (id)
);

create table Reply(
                      id int auto_increment primary key,
                      post_id int,
                      text text,

                      foreign key (post_id) references Activity_Post (post_id)
);

create table Student_ActivityReply(
                                      id int auto_increment primary key,
                                      reply_id int,
                                      post_id int,
                                      student_id int,
                                      time_stamp timestamp,

                                      foreign key (post_id) references Activity_Post (post_id),
                                      foreign key (reply_id) references Reply (id),
                                      foreign key (student_id) references Student (id)
);

create table Friend(
                       id int auto_increment primary key,
                       student_id_from int,
                       student_id_to int,
                       status boolean,
                       time_stamp timestamp,

                       foreign key (student_id_from) references Student (id),
                       foreign key (student_id_to) references Student (id)
);

create table Message(
                        id int auto_increment primary key,
                        student_id_from int,
                        student_id_to int,
                        text text,
                        time_stamp timestamp,

                        foreign key (student_id_from) references Student (id),
                        foreign key (student_id_to) references Student (id)
);