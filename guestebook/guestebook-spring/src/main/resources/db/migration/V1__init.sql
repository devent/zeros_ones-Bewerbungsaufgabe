create table GuestbookEntry (
    id bigint not null,
    author varchar(255) not null,
    comment varchar(255) not null,
    date datetime not null,
    primary key (id)
);

create table hibernate_sequence (
    next_val bigint
);

insert into hibernate_sequence values ( 1 );
