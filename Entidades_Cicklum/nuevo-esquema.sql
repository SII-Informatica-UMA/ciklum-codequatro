CREATE TABLE EJERCICIOS (ID BIGINT NOT NULL, DESCRIPCION VARCHAR(50) NOT NULL, DIFICULTAD VARCHAR(50) NOT NULL, MATERIAL VARCHAR(50) NOT NULL, MUSCULOS_TRABAJADOS VARCHAR(50) NOT NULL, NOMBRE VARCHAR(50) NOT NULL, TIPO VARCHAR(50) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE RUTINAS (ID BIGINT NOT NULL, DESCRIPCION VARCHAR(50) NOT NULL, NOMBRE VARCHAR(50) NOT NULL, OBSERVACIONES VARCHAR(50) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE Subentidad (ID BIGINT NOT NULL, DESCRIPCION VARCHAR(50) NOT NULL, DIFICULTAD VARCHAR(50) NOT NULL, DURACION_MINUTOS BIGINT NOT NULL, MATERIAL VARCHAR(50) NOT NULL, MUSCULOS_TRABAJADOS VARCHAR(50) NOT NULL, NOMBRE VARCHAR(50) NOT NULL, REPETICIONES BIGINT NOT NULL, SERIES BIGINT NOT NULL, TIPO VARCHAR(50) NOT NULL, PRIMARY KEY (ID))
CREATE TABLE Ejercicios_MULTIMEDIA (Ejercicios_ID BIGINT, MULTIMEDIA VARCHAR(50) NOT NULL)
CREATE TABLE relRutinaEjercicio (relacionRutina_ID BIGINT NOT NULL, ejercicios_ID BIGINT NOT NULL, PRIMARY KEY (relacionRutina_ID, ejercicios_ID))
CREATE TABLE SubEntidad_Ejercicio_MULTIMEDIA (SubEntidad_Ejercicio_ID BIGINT, MULTIMEDIA VARCHAR(50) NOT NULL)
ALTER TABLE relRutinaEjercicio ADD CONSTRAINT FK_relRutinaEjercicio_ejercicios_ID FOREIGN KEY (ejercicios_ID) REFERENCES Subentidad (ID)
ALTER TABLE relRutinaEjercicio ADD CONSTRAINT FK_relRutinaEjercicio_relacionRutina_ID FOREIGN KEY (relacionRutina_ID) REFERENCES RUTINAS (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
