INSERT INTO USUARIO VALUES(2, 'Sanchez Garcia', '1234', 'fran@algo.com',1, 'Francisco', 'MED', '9165742887', 'medico')
INSERT INTO USUARIO VALUES(1, 'Arias Navarro', '1234', 'ana@algo.com',0, 'Ana', 'MED', '9163524775', 'medico1')
INSERT INTO USUARIO VALUES(5, 'Gordo Porras', '1234', 'sagrario@algo.com',1, 'Sagrario', 'PAC', '9163547895', 'paciente')
INSERT INTO USUARIO VALUES(4, 'Sagres Pintada', '1234', 'antonio@algo.com',1, 'Antonio', 'FAR', '9168745882', 'farma')
INSERT INTO USUARIO VALUES(8, 'Rendondo Anular', '1234', 'jesus@algo.com',1, 'Jesus', 'ADMIN', '9163745887', 'admin')
INSERT INTO USUARIO VALUES(14, 'Gorka Irasola', '1234', 'sara@algo.com',0, 'sara', 'FAR', '9163589775', 'farma2')

INSERT INTO MEDICO VALUES ( 'C.S La alameda', '12/12/200057', 2)
INSERT INTO MEDICO VALUES ( 'C.S La alameda', '12/12/200057', 1)
INSERT INTO FARMACEUTICO VALUES ( '12/15/20007', 4)
INSERT INTO FARMACEUTICO VALUES ( '12/15/20002', 14)

INSERT INTO FARMACIA VALUES ( 1, 'Madrid', '28045', 'Madrid', 'calle Andrade 12', 1 , 'farmacia Andrade' , 'madrid', '916152335', 4)
INSERT INTO FARMACIA VALUES ( 2, 'Pinto', '28475', 'Madrid', 'calle Santos 34', 1 , 'farmacia Pedro Piqueras' , 'madrid', '916321447', 4)
INSERT INTO FARMACIA VALUES ( 3, 'Coslada', '28822', 'Madrid', 'calle Mejico 22', 1 , 'farmacia Mejico' , 'madrid', '916745886', 14)

INSERT INTO PACIENTE VALUES ( 'Coslada', '28822',324, '11452247', 'Madrid', 'Calle Mejico 31 8º A', '05/19', 0, 47558226334,'Madrid', 5, 2, 2)

INSERT INTO MEDICAMENTO VALUES (6527634, '400 MG 30 SOBRES', 1, 'ZAMBON', 'ESPIDIFEN', 4.62);
INSERT INTO MEDICAMENTO VALUES (7024491, '500 MG 20 COMPRIMIDOS', 1, 'BAYER', 'ASPIRINA', 3.36);
INSERT INTO MEDICAMENTO VALUES (9676384, '1 G 30 COMPRIMIDOS', 1, 'EFERVESCENTE BAYER', 'REDOXON', 6.38);
INSERT INTO MEDICAMENTO VALUES (7098249, '1 G 20 SOBRES', 1, 'GRANULADO EFERVESALTER', 'PARACETAMOL ALTER', 2.81);

INSERT INTO EXISTENCIA_MEDICAMENTO VALUES (1, 10, '2018-04-04', 2, 7024491);
INSERT INTO EXISTENCIA_MEDICAMENTO VALUES (2, 10, '2018-04-04', 2, 9676384);
INSERT INTO EXISTENCIA_MEDICAMENTO VALUES (3, 10, '2018-04-04', 2, 7098249);

INSERT INTO PEDIDOS VALUES (1, 0, '2018-04-04', 2, 5);
INSERT INTO PEDIDOS VALUES (2, 0, '2018-04-09', 2, 5);


INSERT INTO EXISTENCIA_PEDIDO VALUES (1, 5, '2018-04-04', 7024491, 1);
INSERT INTO EXISTENCIA_PEDIDO VALUES (2, 5, '2018-04-04', 7098249, 1);
INSERT INTO EXISTENCIA_PEDIDO VALUES (3, 5, '2018-04-04', 9676384, 1);

INSERT INTO EXISTENCIA_PEDIDO VALUES (4, 20, '2018-04-04', 7098249, 2);
INSERT INTO EXISTENCIA_PEDIDO VALUES (5, 4, '2018-04-04', 9676384, 2);
INSERT INTO EXISTENCIA_PEDIDO VALUES (6, 2, '2018-04-04', 7024491, 2);





INSERT INTO TRATAMIENTO VALUES (1, '2017-05-28', '2018-04-04', 3, 1, '8 horas', 6527634, 5);
INSERT INTO TRATAMIENTO VALUES (2, '2017-05-28', '2018-04-04', 4, 2, '6 horas', 9676384, 5);
INSERT INTO TRATAMIENTO VALUES (3, '2017-05-28', '2018-04-04', 6, 1, '4 horas', 7098249, 5);

INSERT INTO MENSAJE VALUES ( 1, 'EnfermedadDsdPaciente', '2017-05-28', true, 'Esto es una prueba de mensaje de 1 a 2',2,5)
INSERT INTO MENSAJE VALUES ( 2, 'EnfermedadDsdMedico', '2017-05-28', true, 'Esto es una prueba de mensaje de 2 a 1',5,2)
