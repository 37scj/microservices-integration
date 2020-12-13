drop database if exists droneDB;
create database droneDB;
use droneDB;
drop table if exists tb_drone;
create table tb_drone
(
    id          bigint auto_increment
        primary key,
    latitude    double       null,
    longitude   double       null,
    nome        varchar(255) null,
    temperatura double       null
);
