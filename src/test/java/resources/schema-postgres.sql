# PostgresSQL Database
-- ??????? ??? ??????
create table if not exists payment_entity (
    id uuid primary key,
    amount integer,
    status varchar(50),
    transaction_id varchar(50),
    created timestamp default current_timestamp
);

-- ??????? ??? ????????? ??????
create table if not exists credit_request_entity (
    id uuid primary key,
    bank_id uuid,
    created timestamp default current_timestamp,
    status varchar(50)
);

-- ??????? ???????, ????????? ?? ?????? ? ??????
create table if not exists order_entity (
    id uuid primary key,
    created timestamp default current_timestamp,
    payment_id uuid,
    credit_id uuid,
    constraint fk_order_payment foreign key (payment_id) references payment_entity (id),
    constraint fk_order_credit foreign key (credit_id) references credit_request_entity (id)
);
