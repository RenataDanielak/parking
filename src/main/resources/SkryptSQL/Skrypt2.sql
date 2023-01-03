//uruchom jako uzytkownik parking_user

CREATE TABLE `miejsce` (
	`id` int not null auto_increment,
    `numer_miejsca` int not null,
    primary key (`id`)
);

CREATE TABLE `rezerwacja` (
	`uzytkownik` varchar(250),
    `od` datetime,
    `do` datetime,
    `id` int not null auto_increment,
    `miejsce_id` int,
    primary key (`id`),
	CONSTRAINT FK_miejsce FOREIGN KEY (miejsce_id) REFERENCES miejsce (id)
);

insert into miejsce(numer_miejsca) values (1);
insert into miejsce(numer_miejsca) values (2);
insert into miejsce(numer_miejsca) values (3);

//insert into rezerwacja(uzytkownik, od, do, miejsce_id) values ('Renata', '2022-12-10 10:00:00', '2022-12-10 12:00:00', 1);