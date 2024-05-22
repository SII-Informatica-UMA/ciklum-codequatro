create table ejercicios (id bigint generated by default as identity, descripcion varchar(50) not null, dificultad varchar(50) not null, id_entrenador bigint not null, material varchar(50) not null, musculos_trabajados varchar(50) not null, nombre varchar(50) not null, observaciones varchar(50) not null, tipo varchar(50) not null, primary key (id));
create table fragmento_rutina (id bigint generated by default as identity, "duracion en minutos" bigint not null, "numero de repeticiones" bigint not null, "numero de series" bigint not null, ejercicios_id bigint not null, rutina_id bigint not null, primary key (id));
create table multimedia (ejercicios_id bigint not null, multimedia varchar(50) not null);
create table rutinas (id bigint generated by default as identity, descripcion varchar(50) not null, id_entrenador bigint not null, nombre varchar(50) not null, observaciones varchar(50) not null, primary key (id));
alter table if exists fragmento_rutina add constraint FK2yl2w27k5vkl6u8xg7wim5xq7 foreign key (ejercicios_id) references ejercicios;
alter table if exists fragmento_rutina add constraint FK7cyv9l5r75a2erprqctjqbu97 foreign key (rutina_id) references rutinas;
alter table if exists multimedia add constraint FKqx33895wyp9jvt1e2wujjhw9p foreign key (ejercicios_id) references ejercicios;
