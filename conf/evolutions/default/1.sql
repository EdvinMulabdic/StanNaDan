# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        integer,
  name                      varchar(255),
  neighborhood              varchar(255),
  address                   varchar(255),
  price                     integer,
  capacity                  integer,
  beds                      integer,
  rooms                     integer,
  area                      integer,
  floor                     integer,
  description               varchar(255),
  lat                       varchar(255),
  lng                       varchar(255))
;

create table price (
  id                        integer,
  persons_no                integer,
  nights_no                 integer)
;

create table reservation (
  id                        integer,
  date_from                 datetime(6),
  date_to                   datetime(6),
  visitor_name              varchar(255),
  visitor_lastname          varchar(255),
  visitor_email             varchar(255),
  visitor_identification_no varchar(255),
  phone                     varchar(255),
  comment                   varchar(255),
  arrival                   varchar(255),
  cost                      integer)
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table apartment;

drop table price;

drop table reservation;

SET FOREIGN_KEY_CHECKS=1;

