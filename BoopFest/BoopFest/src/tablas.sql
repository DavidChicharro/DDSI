CREATE TABLE grupo(
	nombre_grupo varchar2(30) PRIMARY KEY, 
	integrantes varchar2(150), 
	genero_musical varchar2(15)
);

create table escenario(
	id_escenario varchar2(5) primary key, 
	nombre_escenario varchar2(30)
);
select * from asigna;

insert into personal values('PER0009', 'Jaimito', 'Jiji Jaja', 'kxjañfei', 'fijo', 'camarero', 1300.00, 470.80);


create table actua(
	nombre_grupo references grupo(nombre_grupo),
	id_escenario references escenario(id_escenario),
	fecha date, 
	hora timestamp, 
	duracion number,
	primary key (nombre_grupo,fecha,hora)
);

create table festival(
	id_festival varchar2(5) primary key, 
	nombre_festival varchar2(30), 
	lugar varchar2(30), 
	fecha_inicio date, 
	fecha_fin date, 
	aforo_maximo number(5)
);

create table organiza(
	nombre_grupo references grupo(nombre_grupo),
	id_festival references festival(id_festival),
	primary key (nombre_grupo)
);

create table personal(
	cod_empleado varchar2(7) primary key, 
	nombre_empleado varchar2(20), 
	apellidos_empleado varchar2(50), 
	contraseña varchar2(20), 
	tipo_contrato varchar2(10), 
	funcion varchar2(15), 
	sueldo number(6,2), 
	finiquito number(5,2)
);

create table asigna(
	id_festival references festival(id_festival), 
	cod_empleado references personal(cod_empleado), 
	hora_entrada timestamp, 
	hora_salida timestamp,
	primary key (cod_empleado,hora_entrada,hora_salida)
);

create table cliente(
	dni varchar2(9) primary key, 
	nombre_cliente varchar2(20), 
	tarjeta_credito number(16)
);

create table entrada(
	id_festival references festival(id_festival), 
	dni references cliente(dni),
	tipo_entrada varchar2(7), 
	precio_entrada number(4,2),
	primary key (id_festival,dni)
);

create table linea(
	nombre_linea varchar2(10) primary key, 
	frecuencia_paso number, 
	hora_inicio_servicio timestamp, 
	hora_fin_servicio timestamp
);

create table bonobus(
	id_festival references festival(id_festival), 
	dni references cliente(dni),
	nombre_linea references linea(nombre_linea),
	precio_bus number(4,2),
	primary key (id_festival,dni)
);

create table vehiculo(
	matricula varchar2(7) primary key, 
	tipo_vehiculo varchar2(10), 
	capacidad number(3), 
	marca varchar2(20), 
	modelo varchar2(20),
        estado varchar2(15),
        disponibilidad varchar2(15)
);

create table realiza(
	matricula references vehiculo(matricula),
	nombre_linea references linea(nombre_linea),
	primary key (matricula)
);

create table parada(
	id_parada varchar2(5) primary key, 
	nombre_parada varchar2(30), 
	ubicacion_parada varchar2(50)
);

create table abarca(
	nombre_linea references linea(nombre_linea),
	id_parada references parada(id_parada),
	primary key (nombre_linea,id_parada)
);

create table mapa(
	ciudad varchar2(30) primary key
);

create table contiene(
	nombre_linea references linea(nombre_linea),
	ciudad references mapa(ciudad),
	primary key (nombre_linea)
);


--Triggers

create or replace trigger "VEHICULOS_DISPONIBLES"
BEFORE
    update of disponibilidad on "VEHICULO_REALIZA"
    for each row
begin
    IF (:new.estado='') THEN
        :new.disponibilidad:=’N’;
    ELSE
        :new.disponibilidad:=:new.disponibilidad;
    END IF;
end;




