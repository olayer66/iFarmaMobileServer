INSERT INTO USUARIO VALUES(1, 'Sanchez Garcia', '1234', 'fran@algo.com',1, 'Francisco', 'MED', '9165742887', 'medico')
INSERT INTO USUARIO VALUES(2, 'Gordo Porras', '1234', 'sagrario@algo.com',1, 'Sagrario', 'PAC', '9163547895', 'sagrario')
INSERT INTO USUARIO VALUES(3, 'Aliga Saez', '1234', 'ana@algo.com',1, 'Ana', 'PAC', '9163547895', 'ana')
INSERT INTO USUARIO VALUES(4, 'Navarro Pardo', '1234', 'marta@algo.com',1, 'Marta', 'PAC', '9163547895', 'marta')
INSERT INTO USUARIO VALUES(5, 'Arias Navarro', '1234', 'ana@algo.com',0, 'Ana', 'MED', '9163524775', 'medico1')
INSERT INTO USUARIO VALUES(6, 'Soto Bajo', '1234', 'sergio@algo.com',1, 'Sergio', 'PAC', '9163547895', 'sergio')
INSERT INTO USUARIO VALUES(7, 'Sigo Arror', '1234', 'andres@algo.com',1, 'Andres', 'PAC', '9163547895', 'andres')
INSERT INTO USUARIO VALUES(8, 'Al-Mandil', '1234', 'mohamed@algo.com',1, 'mohamed', 'PAC', '9163547895', 'mohamed')

INSERT INTO MEDICO VALUES ( 'C.S La madrid DC', '12/12/200057', 1)
INSERT INTO MEDICO VALUES ( 'C.S La alameda', '09/14/153687', 5)

INSERT INTO FARMACIA VALUES ( 1, 'Madrid', '28045', 'Madrid', 'calle Andrade 12', 1 , 'farmacia Andrade' , 'madrid', '916152335')
INSERT INTO FARMACIA VALUES ( 2, 'Pinto', '28475', 'Madrid', 'calle Santos 34', 1 , 'farmacia Pedro Piqueras' , 'madrid', '916321447')
INSERT INTO FARMACIA VALUES ( 3, 'Coslada', '28822', 'Madrid', 'calle Mejico 22', 1 , 'farmacia Mejico' , 'madrid', '916745886')

INSERT INTO PACIENTE VALUES ( 'Coslada', '28822',324, '11452247', 'Madrid', 'Calle Mejico 31 8º A', '05/19', 0, 47558226334,'Madrid', 2, 1, 1)
INSERT INTO PACIENTE VALUES ( 'Coslada', '28822',111, '11452247', 'Madrid', 'avda. Madrid 21 4º B', '06/18', 0, 44578222782,'Madrid', 3, 2, 1)
INSERT INTO PACIENTE VALUES ( 'Coslada', '28822',578, '11452247', 'Madrid', 'Calle Venezuela 11 1º A', '05/19', 0, 14578887226,'Madrid', 4, 3, 1)
INSERT INTO PACIENTE VALUES ( 'Madrid', '28045',222, '11452247', 'Madrid', 'Calle Pedralejos 37', '08/17', 0, 45022787408,'Madrid', 6, 1, 5)
INSERT INTO PACIENTE VALUES ( 'Madrid', '28003',377, '11452247', 'Madrid', 'avda. Castellana 234 21º 3', '05/21', 0, 78544114571,'Madrid', 7, 2, 5)
INSERT INTO PACIENTE VALUES ( 'Madrid', '28012',258, '11452247', 'Madrid', 'Calle Mejico 21 3º A', '01/19', 0, 36568871554,'Madrid', 8, 3, 5)


INSERT INTO MEDICAMENTO VALUES (6527634, '400 MG 30 SOBRES', 1, 'ZAMBON', 'ESPIDIFEN', 4.62);
INSERT INTO MEDICAMENTO VALUES (7024491, '500 MG 20 COMPRIMIDOS', 1, 'BAYER', 'ASPIRINA', 3.36);
INSERT INTO MEDICAMENTO VALUES (9676384, '1 G 30 COMPRIMIDOS', 1, 'EFERVESCENTE BAYER', 'REDOXON', 6.38);
INSERT INTO MEDICAMENTO VALUES (7098249, '1 G 20 SOBRES', 1, 'GRANULADO EFERVESALTER', 'PARACETAMOL ALTER', 2.81);

INSERT INTO TRATAMIENTO VALUES (1, '2017-05-28', '2018-04-04', 3, 1, '8 horas', 6527634, 2);
INSERT INTO TRATAMIENTO VALUES (2, '2017-05-28', '2018-04-04', 4, 2, '6 horas', 9676384, 2);
INSERT INTO TRATAMIENTO VALUES (3, '2017-05-28', '2018-04-04', 6, 1, '4 horas', 7098249, 2);

INSERT INTO TRATAMIENTO VALUES (4, '2017-05-28', '2018-04-04', 3, 1, '8 horas', 7024491, 3);

INSERT INTO TRATAMIENTO VALUES (5, '2017-05-28', '2018-04-04', 4, 2, '6 horas', 9676384, 4);
INSERT INTO TRATAMIENTO VALUES (6, '2017-05-28', '2018-04-04', 6, 1, '4 horas', 7098249, 4);

INSERT INTO TRATAMIENTO VALUES (7, '2017-05-28', '2018-04-04', 3, 1, '8 horas', 6527634, 6);
INSERT INTO TRATAMIENTO VALUES (8, '2017-05-28', '2018-04-04', 4, 2, '6 horas', 9676384, 6);

INSERT INTO TRATAMIENTO VALUES (9, '2017-05-28', '2018-04-04', 6, 1, '4 horas', 7098249, 7);

INSERT INTO MENSAJE VALUES ( 1, 'EnfermedadDsdPaciente', '2017-05-28', false, 'Esto es una prueba de mensaje de 1 a 2',1,2)
INSERT INTO MENSAJE VALUES ( 2, 'EnfermedadDsdMedico', '2017-05-28', false, 'Esto es una prueba de mensaje de 2 a 1',2,1)

INSERT INTO MENSAJE VALUES ( 3, 'EnfermedadDsdPaciente', '2017-05-28', false, 'Esto es una prueba de mensaje de 1 a 2',1,3)
INSERT INTO MENSAJE VALUES ( 4, 'EnfermedadDsdMedico', '2017-05-28', true, 'Esto es una prueba de mensaje de 2 a 1',3,1)

INSERT INTO MENSAJE VALUES ( 5, 'EnfermedadDsdPaciente', '2017-05-28', false, 'Esto es una prueba de mensaje de 1 a 2',1,4)
INSERT INTO MENSAJE VALUES ( 6, 'EnfermedadDsdMedico', '2017-05-28', true, 'Esto es una prueba de mensaje de 2 a 1',4,1)

INSERT INTO MENSAJE VALUES ( 7, 'EnfermedadDsdPaciente', '2017-05-28', false, 'Esto es una prueba de mensaje de 1 a 2',5,6)
INSERT INTO MENSAJE VALUES ( 8, 'EnfermedadDsdMedico', '2017-05-28', true, 'Esto es una prueba de mensaje de 2 a 1',6,5)

INSERT INTO MENSAJE VALUES ( 9, 'EnfermedadDsdPaciente', '2017-05-28', false, 'Esto es una prueba de mensaje de 1 a 2',5,7)
INSERT INTO MENSAJE VALUES ( 10, 'EnfermedadDsdMedico', '2017-05-28', false, 'Esto es una prueba de mensaje de 2 a 1',7,5)

INSERT INTO MENSAJE VALUES ( 11, 'EnfermedadDsdPaciente', '2017-05-28', false, 'Esto es una prueba de mensaje de 1 a 2',5,8)
INSERT INTO MENSAJE VALUES ( 12, 'EnfermedadDsdMedico', '2017-05-28', true, 'Esto es una prueba de mensaje de 2 a 1',8,5)

