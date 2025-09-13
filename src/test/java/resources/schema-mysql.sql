# MySQL Database
create table if not exists payment_entity (
id varchar(36) not null,
amount int,
status varchar(50),
transaction_id varchar(50),
created timestamp default current_timestamp,
primary key (id)
);

create table if not exists credit_request_entity (
id varchar(36) not null,
bank_id varchar(36),
created timestamp default current_timestamp,
status varchar(50),
primary key (id)
);

create table if not exists order_entity (
id varchar(36) not null,
created timestamp default current_timestamp,
payment_id varchar(36),
credit_id varchar(36),
primary key (id),
constraint fk_order_payment foreign key (payment_id) references payment_entity (id),
constraint fk_order_credit foreign key (credit_id) references credit_request_entity (id)
);
