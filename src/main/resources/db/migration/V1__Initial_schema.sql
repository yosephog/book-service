create table book (
                      id bigserial primary key not null ,
                      serial_number varchar(255) unique not null ,
                      title varchar(255) not null,
                      author varchar(255) not null,
                      price float8 not null,
                      created_date timestamp not null ,
                      last_modified timestamp not null,
                      version integer not null
);

create table inventory (
                           id bigserial primary key  not null ,
                           serial_number varchar(255) unique  not null ,
                           count_in_stock integer not null ,
                           created_date timestamp not null ,
                           last_modified timestamp not null,
                           version integer not null
);