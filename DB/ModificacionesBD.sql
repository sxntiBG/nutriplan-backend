select * from actividad_fisica;	
select * from objetivo;	
select* from usuarios;
select * from datos_nutricionales_usuario;
select* from plan_usuario;
SELECT * FROM datos_nutricionales_usuario WHERE id_usuario = 1;

insert into usuarios (nombre, correo, contrasena) values ('Jose Angulo', 'jmra0226@gmail.com', '123456789');
insert into usuarios (nombre, correo, contrasena) values ('Flor Angulo', 'flor123@gmail.com', '321654987');
insert into datos_nutricionales_usuario (id_usuario, peso_kg, estatura_m, edad, genero, tmb, id_actividad, factor_af, requerimiento_calorico, imc, fecha_registro)
values (1, 66, 1.77, 26, 1, 1641.25, 3, 1.725, 3250, 21.1, NOW());

insert into datos_nutricionales_usuario (id_usuario, peso_kg, estatura_m, edad, genero, tmb, id_actividad, factor_af, requerimiento_calorico, imc, fecha_registro)
values (2, 70, 1.60, 52, 2, 1641.25, 3, 1.725, 3250, 21.1, NOW());

insert into objetivo (nombre_objetivo, descripcion, carbohidratos_pct, proteinas_pct, grasas_pct) values ('Ganar músculo', 'Subir masa muscular', 0.30, 0.25, 0.45);


UPDATE datos_nutricionales_usuario SET factor_af = 1 WHERE id_usuario = 2;
UPDATE datos_nutricionales_usuario SET id_actividad = 1 WHERE id_usuario = 2;

SELECT * FROM usuarios WHERE id_usuario = 1;
SELECT * FROM objetivo WHERE id_objetivo = 1;
SELECT * FROM datos_nutricionales_usuario WHERE id_usuario = 1;

UPDATE datos_nutricionales_usuario SET genero = 'MASCULINO' WHERE genero = 'M';

UPDATE datos_nutricionales_usuario SET genero = 'FEMENINO' WHERE genero = 'F';
