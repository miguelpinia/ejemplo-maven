use miPrimerBase;

drop table if exists imagen;

create table if not exists imagen (
  id serial primary key,
  datos longblob not null,
  nombre varchar(255) not null unique
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
