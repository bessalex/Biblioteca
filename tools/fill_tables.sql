-- Insertar datos en la tabla "categorias"
INSERT INTO categorias (id, nombre)
VALUES
    (1, 'ciencia'),
    (2, 'historia'),
    (4, 'novela'),
    (3, 'deportes');

-- Insertar datos en la tabla "condiciones_prestamo"
INSERT INTO condiciones_prestamo (id, id_categoria,maximo_dias_prestamo,maximo_renovaciones)
VALUES
    (1, 1, 15, 2),
    (2, 2, 15, 2),
    (4, 4, 15, 2),
    (3, 3, 15, 2);


-- Insertar datos en la tabla "libro"
INSERT INTO libros (isbn, titulo, autor, id_categoria, ejemplares_disponibles)
VALUES
    ('9780061120084', 'El último teorema de Fermat', 'Simon Singh', 1, 1),
    ('9780307588364', 'El último confín de la tierra', 'Lucas Bridges', 2, 2);

-- Insertar datos en la tabla "estudiante"
INSERT INTO estudiantes (dni, apellido, nombres, direccion)
VALUES
    (987654321, 'Saviola', 'Javier', 'Buenos Aires, Buenos Aires'),
    (555555555, 'Ortega', 'Ariel', 'San Salvador de Jujuy, Jujuy');


-- Insertar datos en la tabla "prestamo"
INSERT INTO prestamos (id, isbn_libro, dni_estudiante, id_condicion_prestamo, fecha_inicio, fecha_vencimiento, nro_renovacion)
VALUES
    (1, '9780307588364', 987654321, 1, '2023-09-14', '2023-10-14', 1),
    (2,'9780061120084', 555555555, 2, '2023-09-15', '2023-10-15', 1);

-- Confirmar la transacción
COMMIT;