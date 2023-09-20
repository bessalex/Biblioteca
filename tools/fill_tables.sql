-- Insertar datos en la tabla "libro"
INSERT INTO libros (isbn, titulo, autor, categoria, ejemplares_disponibles)
VALUES
    ('9780061120084', 'El último teorema de Fermat', 'Simon Singh', 'ciencia', 1),
    ('9780307588364', 'El último confín de la tierra', 'Lucas Bridges', 'historia', 2);

-- Insertar datos en la tabla "estudiante"
INSERT INTO estudiantes (dni, apellido, nombres, direccion)
VALUES
    (987654321, 'Saviola', 'Javier', 'Buenos Aires, Buenos Aires'),
    (555555555, 'Ortega', 'Ariel', 'San Salvador de Jujuy, Jujuy');


-- Insertar datos en la tabla "prestamo"
INSERT INTO prestamos (id, isbn_libro, dni_estudiante, fecha_inicio, fecha_vencimiento, nro_renovacion)
VALUES
    (1, '9780307588364', 987654321, '2023-09-14', '2023-10-14', 1),
    (2,'9780061120084', 555555555, '2023-09-15', '2023-10-15', 1);

-- Confirmar la transacción
COMMIT;