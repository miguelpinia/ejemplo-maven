use miPrimerBase;

drop table if exists calificaciones;

create table if not exists calificaciones (
  id serial primary key,
  calificacion int not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into calificaciones (calificacion) values (10), (9), (4), (7), (9), (7), (5), (2), (6), (5), (10);
