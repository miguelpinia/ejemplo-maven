use miPrimerBase;

drop table if exists comentario;
drop table if exists lugar;
drop table if exists usuario;
drop table if exists persona;

create table if not exists persona (
  id serial primary key,
  nombre varchar(255) not null,
  apellido1 varchar(255) not null,
  apellido2 varchar(255) not null
) engine=InnoDB default charset=utf8;

create table if not exists usuario (
  id serial primary key,
  usuario varchar(255) not null,
  contraseña varchar(255) not null,
  persona_id bigint(20) unsigned not null,
  foreign key(persona_id) references persona(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists lugar (
  id serial primary key,
  latitud varchar(20) not null,
  longitud varchar(20) not null,
  nombre varchar(255) not null unique
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists comentario (
  id serial primary key,
  lugar_id bigint(20) unsigned not null,
  comentario longtext not null,
  foreign key(lugar_id) references lugar(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into lugar (latitud, longitud, nombre) values ('19.324499386445776', '-99.17937085032463', 'Harry');
insert into lugar (latitud, longitud, nombre) values ('19.324328359583355', '-99.17934268712997', 'Quesadillas');
