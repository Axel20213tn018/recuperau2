create schema recuperau2;

use recuperau2;

create table docente(
	numero_de_empleado bigint not null primary key,
    nombre varchar(25) not null,
    apellidos varchar(50) not null,
    fecha_nacimiento date not null,
    curp varchar(15) not null
);

create table alumno(
	matricula varchar(15) not null primary key,
    nombre varchar (30) not null,
    apellidos varchar(50) not null,
    fecha_nacimiento date not null,
    curp varchar(15) not null
);

create table calificacion(
	id bigint auto_increment not null primary key,
    materia varchar(20) not null,
    calificaion double not null,
    id_docente bigint not null,
    id_alumn varchar(15) not null,
    constraint docente_cal_fk foreign key (id_docente) references docente(numero_de_empleado),
    constraint alumno_cal_fk foreign key(id_alumn) references alumno(matricula)
);