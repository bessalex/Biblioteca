-- docker-compose-v1.exe -f .\docker_compose.yaml up -d
-- docker exec -it tools_postgres_1 bash
-- psql -h localhost -U postgres

set client_encoding to 'latin1';

DROP TABLE IF EXISTS libro CASCADE;
CREATE TABLE libro
(
    isbn        CHARACTER VARYING(30),
    titulo      CHARACTER VARYING(30),
    autor       CHARACTER VARYING(30),
    categoria   CHARACTER VARYING(30),
    ejemplares_disponibles INT,

    CONSTRAINT pk_libro PRIMARY KEY (isbn)
);

-- -----------------------------

DROP TABLE IF EXISTS estudiante CASCADE;
CREATE TABLE estudiante
(
    dni         INT,
    apellido    CHARACTER VARYING(30),
    nombres     CHARACTER VARYING(30),
    direccion   CHARACTER VARYING(30),

    CONSTRAINT pk_estudiante PRIMARY KEY (dni)
);

-- -----------------------------

DROP TABLE IF EXISTS prestamo CASCADE;
CREATE TABLE prestamo
(
    id                  SERIAL,
    isbn_libro          CHARACTER VARYING(30),
    dni_estudiante      INT,
    fecha_inicio        DATE,
    fecha_vencimiento   DATE,
    nro_renovacion      INT,
    CONSTRAINT pk_prestamo PRIMARY KEY (id),
    CONSTRAINT fk_prestamo_libro_isbn FOREIGN KEY (isbn_libro) REFERENCES libro (isbn),
    CONSTRAINT fk_prestamo_estudiante_dni FOREIGN KEY (dni_estudiante) REFERENCES estudiante (dni)
);

