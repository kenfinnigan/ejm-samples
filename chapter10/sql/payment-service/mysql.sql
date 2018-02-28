DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE hibernate_sequence (
  next_val bigint
);

insert into hibernate_sequence values ( 1 );

DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
  id integer not null,
  amount decimal(19,2),
  card_token varchar(255),
  charge_id varchar(255),
  chargeStatus varchar(255),
  description varchar(255),
  order_id integer, primary key (id)
);
