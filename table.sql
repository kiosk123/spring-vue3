create table messages (
    id int(11) not null auto_increment,
    text varchar(128),
    created_date datetime not null default CURRENT_TIMESTAMP,
    constraint messages_pk primary key(id)
);