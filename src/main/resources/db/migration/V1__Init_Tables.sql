create table cats_ww_sloniki."user"
(
    id       serial
        constraint user_id
            primary key,
    name     varchar(255) not null,
    email    varchar(255) not null
        constraint email
            unique,
    password varchar(255) not null
);
create table cats_ww_sloniki.cats
(
    id      serial
        constraint cats_id
            primary key,
    name    varchar(255) not null,
    breed   varchar(255),
    image   bytea,
    user_id integer
        constraint user_fk
            references cats_ww_sloniki."user" (id)
            on update cascade on delete cascade
);
