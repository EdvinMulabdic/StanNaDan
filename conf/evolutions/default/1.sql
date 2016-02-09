# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        integer auto_increment not null,
  name                      varchar(255),
  title                     varchar(255),
  location                  varchar(255),
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
  user_id                   integer,
  is_visible                tinyint(1) default 0,
  constraint pk_apartment primary key (id))
;

create table app_user (
  id                        integer auto_increment not null,
  email                     varchar(255),
  password                  varchar(255),
  user_access_level         integer,
  constraint pk_app_user primary key (id))
;

create table price (
  id                        integer auto_increment not null,
  persons_no                integer,
  nights_no                 integer,
  constraint pk_price primary key (id))
;

create table reservation (
  id                        integer auto_increment not null,
  date_from                 varchar(255),
  date_to                   varchar(255),
  visitor_name              varchar(255),
  visitor_lastname          varchar(255),
  visitor_email             varchar(255),
  capacity                  varchar(255),
  phone                     varchar(255),
  comment                   varchar(255),
  cost                      integer,
  apartment_id              integer,
  constraint pk_reservation primary key (id))
;

alter table reservation add constraint fk_reservation_apartment_1 foreign key (apartment_id) references apartment (id) on delete restrict on update restrict;
create index ix_reservation_apartment_1 on reservation (apartment_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table apartment;

drop table app_user;

drop table price;

drop table reservation;

SET FOREIGN_KEY_CHECKS=1;

