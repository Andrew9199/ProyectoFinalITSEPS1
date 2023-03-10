CREATE TABLE RegistroProfesores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    profesor_id INT,
    fecha_hora_entrada VARCHAR(20),
    fecha_hora_salida VARCHAR(20),
    registro_status VARCHAR(15),
    FOREIGN KEY (profesor_id) REFERENCES profesores(id)
);