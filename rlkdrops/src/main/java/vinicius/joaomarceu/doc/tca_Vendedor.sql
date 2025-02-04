CREATE TABLE `tca_Vendedor` (
  `Cpf_vendedor` varchar(11) NOT NULL,
  `Nome_vendedor` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tca_Vendedor` (`Cpf_vendedor`, `Nome_vendedor`) VALUES
('12345674125', '12345674125'),
('12345678910', 'joao marceu'),
('12345678912', 'Cleiton'),
('12745896124', 'Cleberson'),
('14563987412', 'vendedola'),
('15478963', 'testeVendedor'),
('74185296312', 'ana flavia'),
('80456321057', 'Clebin');

ALTER TABLE `tca_Vendedor`
  ADD PRIMARY KEY (`Cpf_vendedor`);
COMMIT;
