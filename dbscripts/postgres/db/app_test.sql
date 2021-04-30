set schema 'public';

drop table if exists country_test;

create table country_test (
    id bigint primary key,
    cities jsonb
);

insert into country_test (id, cities) values (1, '["tanger", "tetouan"]');
insert into country_test (id, cities) values (2, '["casablanca"]');
insert into country_test (id, cities) values (3, '["new york"]');
insert into country_test (id, cities) values (4, '["marrakech"]');
insert into country_test (id, cities) values (5, '["taza"]');