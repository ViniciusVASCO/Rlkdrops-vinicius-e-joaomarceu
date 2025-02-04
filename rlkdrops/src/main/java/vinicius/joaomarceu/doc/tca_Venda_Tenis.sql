CREATE TABLE `tca_Venda_Tenis` (
  `id_venda` int NOT NULL,
  `id_tenis` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `tca_Venda_Tenis` (`id_venda`, `id_tenis`) VALUES
(12, 4),
(15, 9),
(14, 11);


ALTER TABLE `tca_Venda_Tenis`
  ADD PRIMARY KEY (`id_venda`,`id_tenis`),
  ADD KEY `id_tenis` (`id_tenis`);
COMMIT;
