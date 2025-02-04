CREATE TABLE `tca_Venda` (
  `id_venda` int NOT NULL,
  `cpf_cliente` varchar(11) NOT NULL,
  `Cpf_vendedor` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tca_Venda` (`id_venda`, `cpf_cliente`, `Cpf_vendedor`) VALUES
(8, '12345678912', '80456321057'),
(9, '12345678912', '12345674125'),
(10, '12345678912', '12345674125'),
(12, '12345678912', '12345678912'),
(14, '45632178963', '14563987412'),
(15, '10873850912', '12345674125');

ALTER TABLE `tca_Venda`
  ADD PRIMARY KEY (`id_venda`),
  ADD KEY `cpf_cliente` (`cpf_cliente`),
  ADD KEY `Cpf_vendedor` (`Cpf_vendedor`);

ALTER TABLE `tca_Venda`
  MODIFY `id_venda` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

