drop table if exists goods cascade
create table goods (
                       id bigserial,
                       name varchar(255) not null,
                       price numeric(8, 2) not null,
                       primary key (id)
);

drop table if exists clients cascade
create table clients (
                         id bigserial,
                         name varchar(80),
                         phone varchar(30) not null unique,
                         password varchar(80) not null,
                         primary key (id)
);

drop table if exists roles;
create table roles (
                       id                    serial,
                       name                  VARCHAR(50) not null,
                       primary key (id)
);

drop table if exists clients_roles;
create table clients_roles (
                             client_id        bigserial  not null,
                             role_id          int        not null,
                             primary key (client_id, role_id),
                             foreign key (client_id) references clients (id),
                             foreign key (role_id)   references roles (id)
);

drop table if exists orders cascade
create table orders (
                        id bigserial,
                        client_id bigint not null,
                        price numeric(8, 2) not null,
                        date timestamp,
                        address varchar(255) not null,
                        primary key (id),
                        constraint fk_client_id foreign key (client_id) references clients(id)
);

drop table if exists orders_lines cascade
create table orders_lines (
                              id bigserial,
                              goods_id bigint not null,
                              order_id bigint not null,
                              count int not null, primary key (id),
                              price numeric(8, 2) not null,
                              constraint fk_goods_id foreign key (goods_id) references goods(id),
                              constraint fk_order_id foreign key (order_id) references orders(id)
);

-- Заполнение таблиц

insert into goods (name, price) values
('Goods A', 752.0),
('Goods B', 56.0),
('Goods C', 127.0),
('Goods D', 581.0),
('Goods E', 123.0),
('Goods F', 888.0),
('Goods G', 777.0),
('Goods H', 555.0),
('Goods I', 68.0),
('Goods J', 85.0);

insert into clients (name, phone, password) values
('Ivanov', '+79996583331', '1234'),
('Petrov', '+79523655467', '$2y$12$/ljjOBe9.OdqE28G2QLaguZTpFgYwEt2ZiWJUPkG2Oh62TsIcLZO.'),
('Sidoroff', '+72223334445', '$2y$12$nSHA6PxI2mTewXN4zLfgw.qRztGXzTkZzJM/Pu0md3SU0blayAD6O');

insert into roles (name)
values
('ROLE_BUYER'), ('ROLE_ADMIN');