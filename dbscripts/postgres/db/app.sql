set schema 'public';

drop table if exists country;

create table country (
    id bigint primary key,
    cities jsonb
);

insert into country (id, cities) values (1, '["tanger", "tetouan"]');
insert into country (id, cities) values (2, '["casablanca"]');
insert into country (id, cities) values (3, '["new york"]');
insert into country (id, cities) values (4, '["marrakech"]');
insert into country (id, cities) values (5, '["taza"]');