
DROP DATABASE IF EXISTS biblioteca_db;

CREATE DATABASE biblioteca_db;

\c biblioteca_db

set client_encoding to 'latin1';

DROP TABLE IF EXISTS libros CASCADE;
CREATE TABLE libros
(
    isbn        CHARACTER VARYING(30),
    titulo      CHARACTER VARYING(50),
    autor       CHARACTER VARYING(50),
    categoria   CHARACTER VARYING(30),
    ejemplares_disponibles INT,

    CONSTRAINT pk_libros PRIMARY KEY (isbn)
);

-- -----------------------------

DROP TABLE IF EXISTS estudiantes CASCADE;
CREATE TABLE estudiantes
(
    dni         INT,
    apellido    CHARACTER VARYING(50),
    nombres     CHARACTER VARYING(50),
    direccion   CHARACTER VARYING(50),

    CONSTRAINT pk_estudiantes PRIMARY KEY (dni)
);

-- -----------------------------

DROP TABLE IF EXISTS prestamos CASCADE;
CREATE TABLE prestamos
(
    id                  INT,
    isbn_libro          CHARACTER VARYING(30),
    dni_estudiante      INT,
    fecha_inicio        DATE,
    fecha_vencimiento   DATE,
    nro_renovacion      INT,
    CONSTRAINT pk_prestamos PRIMARY KEY (id),
    CONSTRAINT fk_prestamos_libros_isbn FOREIGN KEY (isbn_libro) REFERENCES libros (isbn),
    CONSTRAINT fk_prestamos_estudiantes_dni FOREIGN KEY (dni_estudiante) REFERENCES estudiantes (dni)
);

