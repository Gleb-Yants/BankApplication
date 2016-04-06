DROP DATABASE IF EXISTS citizens;
 
CREATE DATABASE citizens DEFAULT CHARACTER SET 'utf8';
 
USE citizens;

create table clients
(
  client_id int unsigned not null auto_increment,
  name varchar(255) not null,
  address varchar(255) not null,
  primary key (client_id)
) engine=InnoDB;

create table accounts
(
  account_id int unsigned not null auto_increment,
  holder_id int not null,
  money int not null,
  primary key (account_id)
) engine=InnoDB;

 create table transactions
 (
   transaction_id int unsigned not null auto_increment,
   transfrom_account_id int not null,
   transto_account_id int not null,
   amount int not null,
   primary key (transaction_id)
 ) engine=InnoDB;


set names 'utf8';

insert into accounts (holder_id, money)
values (1, 250);
insert into accounts (holder_id, money)
values (2, 500);
insert into accounts (holder_id, money)
values (3, 750);
insert into accounts (holder_id, money)
values (4, 1000);
insert into accounts (holder_id, money)
values (5, 1000000);
insert into accounts (holder_id, money)
values (6, 250);
insert into accounts (holder_id, money)
values (7, 500);
insert into accounts (holder_id, money)
values (8, 750);
insert into accounts (holder_id, money)
values (9, 1000);
insert into accounts (holder_id, money)
values (10, 5000);

insert into clients (name, address)
values ('������� ��������', '����� �������, ��� 4');
insert into clients (name, address)
values ('����', '����� ���������, ��� 3');
insert into clients (name, address)
values ('������� �������', '����� ������������, ��� 53');
insert into clients (name, address)
values ('����� �������������', '����� ��������������, ��� 8');
insert into clients (name, address)
values ('������� � ������', '����� ������, ��� 777');
insert into clients (name, address)
values ('������� ������', '����� �������, ��� 10');
insert into clients (name, address)
values ('�������� �������', '����� ����������������, ��� 22');
insert into clients (name, address)
values ('�������� �������', '����� ������, ��� 625');
insert into clients (name, address)
values ('���� ����', '����� ����������, ��� 33');
insert into clients (name, address)
values ('������� ��������', '����� �����������, ��� 15');