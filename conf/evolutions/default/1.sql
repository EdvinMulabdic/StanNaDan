# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        integer auto_increment not null,
  name                      varchar(255),
  neighborhood              varchar(255),
  address                   varchar(255),
  price                     integer,
  capacity                  integer,
  beds                      integer,
  rooms                     integer,
  area                      integer,
  floor                     integer,
  description               TEXT,
  lat                       varchar(255),
  lng                       varchar(255),
  constraint pk_apartment primary key (id))
;

create table price (
  id                        integer auto_increment not null,
  persons_no                integer,
  nights_no                 integer,
  constraint pk_price primary key (id))
;

create table reservation (
  id                        integer auto_increment not null,
  date_from                 datetime(6),
  date_to                   datetime(6),
  visitor_name              varchar(255),
  visitor_lastname          varchar(255),
  visitor_email             varchar(255),
  visitor_identification_no varchar(255),
  phone                     varchar(255),
  comment                   varchar(255),
  arrival                   varchar(255),
  cost                      integer,
  constraint pk_reservation primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table apartment;

drop table price;

drop table reservation;

SET FOREIGN_KEY_CHECKS=1;

