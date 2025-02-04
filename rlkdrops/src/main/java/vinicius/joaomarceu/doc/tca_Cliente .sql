CREATE TABLE `tca_Cliente` (
  `cpf_cliente` varchar(11) NOT NULL,
  `Nome_cliente` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tca_Cliente` (`cpf_cliente`, `Nome_cliente`) VALUES
('10873850912', 'marceu'),
('12345678912', 'teste'),
('12369854785', 'Popopo'),
('14134835933', 'anaflvaia'),
('14785236914', 'Pedro'),
('14789632514', 'testeCliente'),
('45632178963', 'clientola');

DELIMITER $$
CREATE TRIGGER `validaCPF_tca_Cliente` BEFORE INSERT ON `tca_Cliente` FOR EACH ROW BEGIN
    DECLARE msg VARCHAR(255);

    SET NEW.cpf_cliente = REPLACE(NEW.cpf_cliente, '-', '');
    SET NEW.cpf_cliente = REPLACE(NEW.cpf_cliente, '.', '');

    IF LENGTH(NEW.cpf_cliente) != 11 OR NOT NEW.cpf_cliente REGEXP '^[0-9]+$' THEN
        SET msg = 'CPF inválido. Por favor, insira um CPF válido.';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg;
    END IF;
END
$$
DELIMITER ;

ALTER TABLE `tca_Cliente`
  ADD PRIMARY KEY (`cpf_cliente`);
ALTER TABLE `tca_Cliente` ADD FULLTEXT KEY `cpf_cliente` (`cpf_cliente`,`Nome_cliente`);
COMMIT;

