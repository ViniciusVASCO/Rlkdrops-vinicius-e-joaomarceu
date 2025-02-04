
CREATE TABLE `tca_Tenis` (
  `id_tenis` int NOT NULL,
  `Nome_tenis` varchar(45) NOT NULL,
  `Descricao_tenis` varchar(255) DEFAULT NULL,
  `Estoque_tenis` int NOT NULL,
  `Valor_tenis` float NOT NULL,
  `id_marca` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `tca_Tenis` (`id_tenis`, `Nome_tenis`, `Descricao_tenis`, `Estoque_tenis`, `Valor_tenis`, `id_marca`) VALUES
(1, 'Adi2000', 'Tenis lindo e estiloso para o dia a dia', 100, 2, 7),
(3, 'teste', 'teste', 5, 555, 5),
(4, 'teste2', 'aaa', 1, 1, 5),
(5, 'runner', 'tenis de corrida ', 100, 200, 10),
(6, 'TN', 'Tenis confortavel e ótimo para qualquer ocasião', 100, 850, 6),
(9, 'hhjkh', 'hhjhbjb', 1, 1, 14),
(10, 'tenispoggers', 'sei la', 5, 785, 15),
(11, 'number', 'è o primeiro ', 200, 753, 16),
(12, 'Camurça', 'Tenis bom', 20, 200, 17),
(13, 'vasco1998', 'tenis com tamanho gordo e que tem a historia do vasci', 100, 150, 22);

ALTER TABLE `tca_Tenis`
  ADD PRIMARY KEY (`id_tenis`),
  ADD KEY `id_marca` (`id_marca`);

ALTER TABLE `tca_Tenis`
  MODIFY `id_tenis` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

ALTER TABLE `tca_Tenis`
  ADD CONSTRAINT `tca_Tenis_ibfk_1` FOREIGN KEY (`id_marca`) REFERENCES `tca_Marca` (`id_marca`);
COMMIT;

