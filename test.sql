create database bdescuela;
use bdescuela;
create table Alumnos (
id int  not null auto_increment primary key,
nombres nvarchar(50),
apellidos nvarchar(50)
);
/*Insertar*/
insert into Alumnos(nombres, apellidos) values ('Juan Andres', 'Bermudez Gomez');

/*Mostrar*/
select * from Alumnos;

/*Actualizar*/
UPDATE Alumnos SET alumnos.nombres = 'Carlos', alumnos.apellidos = 'Gonzales' WHERE alumnos.id = 1;

/*Eliminar*/
DELETE FROM Alumnos WHERE alumnos.id=1;