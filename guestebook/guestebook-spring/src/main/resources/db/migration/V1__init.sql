create table GuestbookEntry (
    id bigint not null,
    author varchar(255) not null,
    comment varchar(255) not null,
    date datetime not null,
    primary key (id)
);
