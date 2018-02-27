CREATE sequence hibernate_sequence start with 1 increment by 5;

CREATE TABLE payment (
  id integer not null,
  amount decimal(19,2),
  card_token varchar(255),
  charge_id varchar(255),
  chargeStatus varchar(255),
  description varchar(255),
  order_id integer, primary key (id)
);
