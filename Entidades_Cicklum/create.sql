create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
create sequence ejercicios_seq start with 1 increment by 50;
create sequence fragmento_rutina_seq start with 1 increment by 50;
create sequence rutinas_seq start with 1 increment by 50;
create table ejercicios (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, dificultad varchar(50) not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina ("duracion en minutos" bigint not null, ejercicios_id bigint not null, id bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint not null, id_entrenador bigint not null, descripcion varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
alter table if exists rutinas_ejercicios add constraint FK58n7ohg6ru4behceqccrs9ddi foreign key (ejercicios_id) references fragmento_rutina;
alter table if exists rutinas_ejercicios add constraint FK4o1bj3jyk9bfkoq9wfrjb825c foreign key (rutinas_id) references rutinas;
