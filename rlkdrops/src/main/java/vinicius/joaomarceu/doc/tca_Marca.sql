
CREATE TABLE `tca_Marca` (
  `id_marca` int NOT NULL,
  `Nome_marca` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Descricao_marca` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tca_Marca` (`id_marca`, `Nome_marca`, `Descricao_marca`) VALUES
(1, 'OUS', 'Marca internacional para skatistas'),
(3, 'Qix', 'Marca brasileira que faz sucesso nacionalmente.'),
(4, 'DCShoes', 'Marca muito utilizada por todos'),
(5, 'teste', 'adsad'),
(6, 'Nike', 'Fabricante de artigos esportivos'),
(7, 'Adidas', 'Corporação multinacional que projeta e fabrica calçados, roupas e acessórios'),
(8, 'New Balance', 'Corporação multinacional americana que se especializa em calçados e roupas esportivas'),
(9, 'Puma', 'Empresa multinacional alemã que projeta e fabrica calçados esportivos e casuais'),
(10, 'Fila', 'confortavel e atraente para pessoas que praticam esportes'),
(14, 'testeCategoria', 'Quero mostrar que isso funciona'),
(15, 'testeCategoria1', 'sei lá'),
(16, 'testeee', 'primeiro testee'),
(17, 'Nike', 'Marca famosa'),
(18, 'AAAA', 'AAAAA'),
(19, 'aa', 'aa'),
(20, 'aaaa', 'aaaaa'),
(21, 'MCD', 'Marca do Brasil'),
(22, 'analinda', 'mt bicuda'),
(23, 'bruna', 'bruna');

ALTER TABLE `tca_Marca`
  ADD PRIMARY KEY (`id_marca`);

ALTER TABLE `tca_Marca`
  MODIFY `id_marca` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;
