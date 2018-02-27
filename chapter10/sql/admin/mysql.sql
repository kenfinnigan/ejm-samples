DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id integer not null,
  created datetime,
  header varchar(255),
  image_path varchar(120),
  name varchar(50) not null,
  updated datetime,
  version integer,
  visible bit,
  parent_id integer,
  primary key (id)
);

ALTER TABLE category ADD CONSTRAINT FK2y94svpmqttx80mshyny85wqr FOREIGN KEY (parent_id) REFERENCES category (id);

DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE hibernate_sequence (
  next_val bigint
);

insert into hibernate_sequence values ( 1020 );

