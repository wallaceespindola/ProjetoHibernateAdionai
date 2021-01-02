# Host: 127.0.0.1  (Version: 5.5.24-log)
# Date: 2014-04-07 08:11:50
# Generator: MySQL-Front 5.3  (Build 2.42)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

USE `db_sge`;

#
# Source for table "tb_alm_kit"
#

DROP TABLE IF EXISTS `tb_alm_kit`;
CREATE TABLE `tb_alm_kit` (
  `id_servico` int(11) NOT NULL,
  `cd_kit` int(11) NOT NULL,
  PRIMARY KEY (`id_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_alm_kit"
#


#
# Source for table "tb_calendario"
#

DROP TABLE IF EXISTS `tb_calendario`;
CREATE TABLE `tb_calendario` (
  `Dt_inicial` datetime NOT NULL,
  `Dt_final` datetime NOT NULL,
  PRIMARY KEY (`Dt_inicial`,`Dt_final`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_calendario"
#


#
# Source for table "tb_categoria"
#

DROP TABLE IF EXISTS `tb_categoria`;
CREATE TABLE `tb_categoria` (
  `Id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `Tx_categoria` char(20) DEFAULT NULL,
  `Dt_vigencia` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

#
# Data for table "tb_categoria"
#

INSERT INTO `tb_categoria` VALUES (1,'Troca de equipamento',NULL),(2,'Reparo cabeamento in',NULL),(3,'Reparo cabeamento ex',NULL),(4,'Reconfiguração local',NULL),(5,'Reconfiguração remot',NULL),(6,'Causa cliente',NULL);

#
# Source for table "tb_dados_clientes"
#

DROP TABLE IF EXISTS `tb_dados_clientes`;
CREATE TABLE `tb_dados_clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) DEFAULT NULL,
  `cnpj` varchar(14) DEFAULT NULL,
  `nm_cliente` char(20) NOT NULL,
  `cd_email` tinytext,
  `dt_admissao` datetime DEFAULT NULL,
  `Endereco` varchar(100) DEFAULT NULL,
  `Numero` varchar(255) DEFAULT NULL,
  `Complemento` varchar(30) DEFAULT NULL,
  `Bairro` varchar(30) DEFAULT NULL,
  `Cidade` varchar(40) DEFAULT NULL,
  `UF` char(2) DEFAULT NULL,
  `Pais` varchar(40) DEFAULT NULL,
  `CEP` varchar(10) DEFAULT NULL,
  `nr_tel` decimal(14,0) DEFAULT NULL,
  `nr_celular` decimal(14,0) DEFAULT NULL,
  `nr_ident` char(20) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "tb_dados_clientes"
#

INSERT INTO `tb_dados_clientes` VALUES (1,'10000000001',NULL,'José Silva','cliente@gmail.com\n','2014-01-01 00:00:00','Rua Nogueira Acioli','100','CB 2','Jardim Guanabara','Rio de Janeiro','RJ','Brasil','21221000',34238978,98987647,'447564753'),(2,'10000000002',NULL,'Cleosvaldo Nogueira','a@a.com\t','2013-12-25 00:00:00','Rua Doutor Miguel Couto','236','Casa 1','Jardim Normandia','Volta Redonda','RJ','Brasil','27251260',33224567,NULL,NULL),(3,'10000000003',NULL,'Luiza Diesel','l@l.com','2014-01-01 00:00:00','Base Aérea do Galeão',NULL,NULL,'Galeão','Rio de Janeiro','RJ','Brasil',NULL,22337766,988776655,'447564333');

#
# Source for table "tb_log"
#

DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `Nr_matricula` int(11) NOT NULL,
  `Dt_operacao` datetime NOT NULL,
  `Tx_descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Nr_matricula`,`Dt_operacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_log"
#


#
# Source for table "tb_rh"
#

DROP TABLE IF EXISTS `tb_rh`;
CREATE TABLE `tb_rh` (
  `nr_matricula` int(11) NOT NULL,
  `cpf` decimal(11,0) DEFAULT NULL,
  `nm_empregado` char(20) NOT NULL,
  `cd_email` tinytext,
  PRIMARY KEY (`nr_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_rh"
#

INSERT INTO `tb_rh` VALUES (1,11111111110,'Datena Costa','jose@gmail.com'),(2,11111111112,'Veronica Souza','ver@onica.com');

#
# Source for table "tb_rh_disponibilidade"
#

DROP TABLE IF EXISTS `tb_rh_disponibilidade`;
CREATE TABLE `tb_rh_disponibilidade` (
  `nr_matricula` int(11) NOT NULL,
  `dt_inicio_indisp` datetime NOT NULL,
  `dt_fim_indisp` datetime NOT NULL,
  PRIMARY KEY (`nr_matricula`),
  CONSTRAINT `TB_RH_TB_RH_DISPONIBILIDADE_FK1` FOREIGN KEY (`nr_matricula`) REFERENCES `tb_rh` (`nr_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_rh_disponibilidade"
#


#
# Source for table "tb_servico"
#

DROP TABLE IF EXISTS `tb_servico`;
CREATE TABLE `tb_servico` (
  `Id_servico` int(11) NOT NULL AUTO_INCREMENT,
  `Nm_servico` varchar(100) DEFAULT NULL,
  `Qt_inicio` int(11) DEFAULT NULL,
  `Qt_fim` int(11) DEFAULT NULL,
  `Qt_emp` int(11) DEFAULT NULL,
  `Dt_vigencia` datetime DEFAULT NULL COMMENT 'Data fim da validade do serviço',
  PRIMARY KEY (`Id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

#
# Data for table "tb_servico"
#

INSERT INTO `tb_servico` VALUES (1,'Instalação Banda Larga 2M',111,240,2,'2011-01-01 00:00:11'),(2,'Instalação Banda Larga 10M',120,240,2,'2022-01-01 00:02:00'),(3,'Instalação Banda Larga 15M',120,240,2,'2020-01-01 00:00:00'),(4,'Instalação de Banda Garantida 2M',120,240,2,'2020-01-01 00:00:00'),(5,'Instalação de Banda Garantida 5M',120,240,2,'2020-01-01 00:00:00'),(6,'Instalação de Banda Garantida 10M',120,240,2,'2020-01-01 00:00:00'),(7,'Instalação de 1 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(8,'Instalação de 5 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(9,'Instalação de 10 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(10,'Desinstalação de Banda Larga 2M',120,240,2,'2020-01-01 00:00:00'),(11,'Desinstalação de Banda Larga 10M',120,240,2,'2020-01-01 00:00:00'),(12,'Desinstalação de Banda Larga 15M',120,240,2,'2020-01-01 00:00:00'),(13,'Desinstalação de Banda Garantida 2M',120,240,2,'2020-01-01 00:00:00'),(14,'Desinstalação de Banda Garantida 5M',120,240,2,'2020-01-01 00:00:00'),(15,'Desinstalação de Banda Garantida 10M',120,240,2,'2020-01-01 00:00:00'),(16,'Desinstalação de 1 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(17,'Desinstalação de 5 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(18,'Desinstalação de 10 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(19,'Manutenção de Banda Larga 2M',120,240,2,'2020-01-01 00:00:00'),(20,'Manutenção de Banda Larga 10M',120,240,2,'2020-01-01 00:00:00'),(21,'Manutenção de Banda Larga 15M',120,240,2,'2020-01-01 00:00:00'),(22,'Manutenção de Banda Garantida 2M',120,240,2,'2020-01-01 00:00:00'),(23,'Manutenção de Banda Garantida 5M',120,240,2,'2020-01-01 00:00:00'),(24,'Manutenção de Banda Garantida 10M',120,240,2,'2020-01-01 00:00:00'),(25,'Manutenção de 1 linha telefônica',120,240,2,'2020-01-01 00:00:00'),(26,'Manutenção de 5 linhas telefônica',120,240,2,'2020-01-01 00:00:00'),(27,'Manutenção de 10 linhas telefônica',120,240,2,'2020-01-01 00:00:00'),(28,'Serviço de teste',1,1,1,'2014-04-01 16:25:41'),(29,'Nome servico 4',1,1,10,'2014-01-01 23:59:59'),(30,'Nome servico Novo',1,10,10,'2014-01-01 23:59:59'),(31,'Nome servico 4',1,1,10,'2017-10-01 23:59:59'),(32,'Nome servico 3',1,1,10,'2014-01-01 23:59:59'),(33,'Nome servico 333',1,1,10,'2014-01-01 23:59:59'),(34,'Nome servico 333',1,1,10,'2014-01-25 23:59:59'),(36,'3',19,20,3,'2014-01-25 23:59:59'),(37,'Nome servico 6',1,1,1,'2014-01-25 23:59:59'),(38,'Instalação Banda Larga 2M',1,1,1,'2010-10-10 10:10:10');

#
# Source for table "tb_os"
#

DROP TABLE IF EXISTS `tb_os`;
CREATE TABLE `tb_os` (
  `Id_OS` int(11) NOT NULL AUTO_INCREMENT,
  `Id_servico` int(11) NOT NULL,
  `Id_categoria` int(11) DEFAULT NULL,
  `Dt_geracao` datetime DEFAULT NULL,
  `Nr_cpf` varchar(11) DEFAULT NULL,
  `Nr_cnpj` varchar(14) DEFAULT NULL,
  `Tx_detalhe` text,
  `Cd_kit` char(3) DEFAULT NULL,
  `Cd_status` char(1) DEFAULT NULL COMMENT 'A - Aberta R – Reagendada P – Pendente C – Cancelada T - Atendida F - Finalizada',
  `Dt_fim` datetime DEFAULT NULL COMMENT 'Data de finalização da O.S',
  PRIMARY KEY (`Id_OS`),
  KEY `TB_CATEGORIA_TB_OS_FK1` (`Id_categoria`),
  KEY `IDX_TB_OS` (`Id_servico`),
  CONSTRAINT `TB_CATEGORIA_TB_OS_FK1` FOREIGN KEY (`Id_categoria`) REFERENCES `tb_categoria` (`Id_categoria`),
  CONSTRAINT `TB_SERVICO_TB_OS_FK1` FOREIGN KEY (`Id_servico`) REFERENCES `tb_servico` (`Id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "tb_os"
#

INSERT INTO `tb_os` VALUES (1,1,1,'2014-03-23 00:00:00','10000000001',NULL,'O cliente está solicitando um novo serviço!','1','a',NULL),(2,2,2,'2014-03-24 00:00:00','10000000002',NULL,'Outro detalhe!','2','p',NULL),(3,1,1,'2014-03-23 00:00:00','10000000003',NULL,'Algumas informações!','1','p',NULL);

#
# Source for table "tb_reporte"
#

DROP TABLE IF EXISTS `tb_reporte`;
CREATE TABLE `tb_reporte` (
  `Id_OS` int(11) NOT NULL,
  `Cd_status` char(1) NOT NULL DEFAULT '' COMMENT 'C – A caminho / E – Em atendimento / A – Atendimento Finalizado / P – Atendimento Pendente',
  `Dt_reporte` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Data e hora e m que o reporte foi feio pela equipe',
  `Cd_pendencia` char(1) DEFAULT NULL COMMENT 'Motivo da Pendencia: C – pelo cliente / T – problema técnico',
  `Tx_longitude` char(8) DEFAULT NULL,
  `Tx_latitude` char(8) DEFAULT NULL,
  `Nr_matricula` int(11) DEFAULT NULL COMMENT 'Mat Empregado',
  PRIMARY KEY (`Id_OS`,`Cd_status`,`Dt_reporte`),
  CONSTRAINT `TB_OS_TB_REPORTE_FK1` FOREIGN KEY (`Id_OS`) REFERENCES `tb_os` (`Id_OS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_reporte"
#

INSERT INTO `tb_reporte` VALUES (1,'c','2014-03-23 00:00:00',NULL,'-43.1767','-22.9334',1),(2,'p','2014-03-24 00:00:00',NULL,'-43.1788','-22.9020',2),(3,'c','2014-03-23 00:00:00',NULL,'-43.3588','-22.9610',1);

#
# Source for table "tb_janela"
#

DROP TABLE IF EXISTS `tb_janela`;
CREATE TABLE `tb_janela` (
  `Id_janela` int(11) NOT NULL AUTO_INCREMENT,
  `Id_servico` int(11) NOT NULL,
  `Hr_inicial` time NOT NULL DEFAULT '00:00:00',
  `Hr_final` time NOT NULL DEFAULT '00:00:00',
  `Dt_vig_fim` datetime DEFAULT NULL,
  `Dt_vig_ini` datetime NOT NULL,
  PRIMARY KEY (`Id_janela`),
  KEY `TB_SERVICO_TB_JANELA_FK1` (`Id_servico`),
  CONSTRAINT `TB_SERVICO_TB_JANELA_FK1` FOREIGN KEY (`Id_servico`) REFERENCES `tb_servico` (`Id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

#
# Data for table "tb_janela"
#

INSERT INTO `tb_janela` VALUES (1,1,'09:00:00','10:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(2,2,'09:00:00','10:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(3,1,'11:00:00','12:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(4,1,'14:00:00','15:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(5,1,'16:00:00','17:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(6,1,'17:00:00','18:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(7,2,'11:00:00','12:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(8,2,'14:00:00','15:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(9,2,'16:00:00','17:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00'),(10,2,'17:00:00','18:00:00','2014-03-01 00:00:00','2014-12-30 00:00:00');

#
# Source for table "tb_dias"
#

DROP TABLE IF EXISTS `tb_dias`;
CREATE TABLE `tb_dias` (
  `Id_janela` int(11) NOT NULL,
  `Cd_dia` char(2) NOT NULL DEFAULT '' COMMENT 'S – Segunda T – Terça Q – Quarta Q – Quinta S – Sexta SA – Sábado D - Domingo',
  PRIMARY KEY (`Id_janela`,`Cd_dia`),
  CONSTRAINT `TB_JANELA_TB_DIAS_FK1` FOREIGN KEY (`Id_janela`) REFERENCES `tb_janela` (`Id_janela`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_dias"
#

INSERT INTO `tb_dias` VALUES (1,'Q'),(1,'S'),(1,'T');

#
# Source for table "tb_equipe"
#

DROP TABLE IF EXISTS `tb_equipe`;
CREATE TABLE `tb_equipe` (
  `Id_equipe` int(11) NOT NULL AUTO_INCREMENT,
  `Id_servico` int(11) NOT NULL,
  `Nr_matricula` int(11) NOT NULL,
  `Nr_smarthfone` int(11) NOT NULL,
  `Nr_cep_inicial` int(11) NOT NULL,
  `Nr_cep_final` int(11) NOT NULL,
  PRIMARY KEY (`Id_equipe`),
  KEY `TB_SERVICO_TB_EQUIPE_FK1` (`Id_servico`),
  KEY `IDX_TB_EQUIPE` (`Nr_matricula`),
  CONSTRAINT `TB_SERVICO_TB_EQUIPE_FK1` FOREIGN KEY (`Id_servico`) REFERENCES `tb_servico` (`Id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "tb_equipe"
#

INSERT INTO `tb_equipe` VALUES (1,1,1,99998877,21221000,21221999),(2,2,2,88776655,27343000,27343999);

#
# Source for table "tb_agenda"
#

DROP TABLE IF EXISTS `tb_agenda`;
CREATE TABLE `tb_agenda` (
  `Id_janela` int(11) NOT NULL,
  `Id_equipe` int(11) NOT NULL,
  `Dt_criacao` datetime NOT NULL,
  `Dt_fim` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_janela`,`Id_equipe`),
  KEY `TB_EQUIPE_TB_AGENDA_FK1` (`Id_equipe`),
  CONSTRAINT `TB_EQUIPE_TB_AGENDA_FK1` FOREIGN KEY (`Id_equipe`) REFERENCES `tb_equipe` (`Id_equipe`),
  CONSTRAINT `TB_JANELA_TB_AGENDA_FK1` FOREIGN KEY (`Id_janela`) REFERENCES `tb_janela` (`Id_janela`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_agenda"
#

INSERT INTO `tb_agenda` VALUES (1,1,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(2,2,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(3,1,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(4,1,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(5,1,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(6,1,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(7,2,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(8,2,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(9,2,'2014-03-26 00:00:00','2014-03-30 00:00:00'),(10,2,'2014-03-26 00:00:00','2014-03-30 00:00:00');

#
# Source for table "tb_agendamento"
#

DROP TABLE IF EXISTS `tb_agendamento`;
CREATE TABLE `tb_agendamento` (
  `Id_agendamento` int(11) NOT NULL AUTO_INCREMENT,
  `Id_OS` int(11) NOT NULL,
  `Dt_agendamento` datetime NOT NULL,
  `Nr_matricula` int(11) NOT NULL,
  `Cd_status` char(1) NOT NULL DEFAULT '' COMMENT 'A:Aberto F:Fechado C:Cancelado',
  `Id_janela` int(11) NOT NULL,
  `Id_equipe` int(11) NOT NULL,
  PRIMARY KEY (`Id_agendamento`),
  KEY `TB_OS_TB_AGENDAMENTO_FK1` (`Id_OS`),
  KEY `TB_AGENDA_TB_AGENDAMENTO_FK1` (`Id_janela`,`Id_equipe`),
  CONSTRAINT `TB_AGENDA_TB_AGENDAMENTO_FK1` FOREIGN KEY (`Id_janela`, `Id_equipe`) REFERENCES `tb_agenda` (`Id_janela`, `Id_equipe`),
  CONSTRAINT `TB_OS_TB_AGENDAMENTO_FK1` FOREIGN KEY (`Id_OS`) REFERENCES `tb_os` (`Id_OS`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "tb_agendamento"
#

INSERT INTO `tb_agendamento` VALUES (1,1,'2014-04-01 07:00:00',1,'a',1,1),(2,2,'2014-04-02 09:00:00',2,'a',2,2),(3,3,'2014-04-03 10:00:00',1,'a',1,1);

#
# Source for table "tb_bloqueio"
#

DROP TABLE IF EXISTS `tb_bloqueio`;
CREATE TABLE `tb_bloqueio` (
  `Id_equipe` int(11) NOT NULL,
  `Dt_inicio` datetime NOT NULL,
  `Dt_fim` datetime NOT NULL,
  PRIMARY KEY (`Id_equipe`,`Dt_inicio`,`Dt_fim`),
  CONSTRAINT `TB_EQUIPE_TB_BLOQUEIO_FK1` FOREIGN KEY (`Id_equipe`) REFERENCES `tb_equipe` (`Id_equipe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_bloqueio"
#


#
# Source for table "tb_tec_auxiliares"
#

DROP TABLE IF EXISTS `tb_tec_auxiliares`;
CREATE TABLE `tb_tec_auxiliares` (
  `Id_equipe` int(11) NOT NULL,
  `Nr_matricula` int(11) NOT NULL,
  PRIMARY KEY (`Id_equipe`,`Nr_matricula`),
  CONSTRAINT `TB_EQUIPE_TB_TECNICOS_AUXILIARES_FK1` FOREIGN KEY (`Id_equipe`) REFERENCES `tb_equipe` (`Id_equipe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_tec_auxiliares"
#

INSERT INTO `tb_tec_auxiliares` VALUES (1,1),(1,2),(2,1),(2,2);

#
# Source for table "tb_usuario"
#

DROP TABLE IF EXISTS `tb_usuario`;
CREATE TABLE `tb_usuario` (
  `nr_matricula` int(11) NOT NULL,
  `cd_usuario` char(1) NOT NULL DEFAULT '' COMMENT 'A - Administrador S – supervisor T – Técnico C – Coordenador D - Atendente',
  PRIMARY KEY (`nr_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_usuario"
#

INSERT INTO `tb_usuario` VALUES (1,'A'),(2,'S'),(3,'C'),(4,'D'),(5,'T'),(6,'T'),(7,'T'),(8,'T'),(9,'T'),(10,'T'),(11,'T');

#
# Source for view "vw_agenda"
#

DROP VIEW IF EXISTS `vw_agenda`;
CREATE VIEW `vw_agenda` AS 
  select `j`.`Id_servico` AS `id_servico`,`a`.`Id_janela` AS `id_janela`,`a`.`Id_equipe` AS `id_equipe`,`j`.`Hr_inicial` AS `Hr_inicial`,`j`.`Hr_final` AS `Hr_final`,`ag`.`Id_agendamento` AS `id_agendamento` from ((`db_sge`.`tb_agenda` `a` join `db_sge`.`tb_janela` `j` on((`a`.`Id_janela` = `j`.`Id_janela`))) left join `db_sge`.`tb_agendamento` `ag` on(((`ag`.`Id_janela` = `a`.`Id_janela`) and (`ag`.`Id_equipe` = `a`.`Id_equipe`))));

#
# Source for view "vw_almoxarife"
#

DROP VIEW IF EXISTS `vw_almoxarife`;
CREATE VIEW `vw_almoxarife` AS 
  select `db_sge`.`tb_alm_kit`.`id_servico` AS `ID_SERVICO`,`db_sge`.`tb_alm_kit`.`cd_kit` AS `CD_KIT` from `db_sge`.`tb_alm_kit`;

#
# Source for view "vw_dados_clientes"
#

DROP VIEW IF EXISTS `vw_dados_clientes`;
CREATE VIEW `vw_dados_clientes` AS 
  select `db_sge`.`tb_dados_clientes`.`id_cliente` AS `id_cliente`,`db_sge`.`tb_dados_clientes`.`cpf` AS `CPF`,`db_sge`.`tb_dados_clientes`.`cnpj` AS `CNPJ`,`db_sge`.`tb_dados_clientes`.`nm_cliente` AS `NM_CLIENTE`,`db_sge`.`tb_dados_clientes`.`cd_email` AS `CD_EMAIL`,`db_sge`.`tb_dados_clientes`.`Endereco` AS `ENDERECO`,`db_sge`.`tb_dados_clientes`.`Complemento` AS `COMPLEMENTO`,`db_sge`.`tb_dados_clientes`.`Numero` AS `NUMERO`,`db_sge`.`tb_dados_clientes`.`Bairro` AS `BAIRRO`,`db_sge`.`tb_dados_clientes`.`Cidade` AS `CIDADE`,`db_sge`.`tb_dados_clientes`.`UF` AS `UF`,`db_sge`.`tb_dados_clientes`.`CEP` AS `CEP`,`db_sge`.`tb_dados_clientes`.`nr_tel` AS `NR_TEL`,`db_sge`.`tb_dados_clientes`.`nr_celular` AS `NR_CELULAR`,`db_sge`.`tb_dados_clientes`.`nr_ident` AS `NR_IDENT` from `db_sge`.`tb_dados_clientes`;

#
# Source for view "vw_dados_rh"
#

DROP VIEW IF EXISTS `vw_dados_rh`;
CREATE VIEW `vw_dados_rh` AS 
  select `r`.`nr_matricula` AS `NR_MATRICULA`,`r`.`nm_empregado` AS `NM_EMPREGADO`,`d`.`dt_inicio_indisp` AS `dt_inicio_indisp`,`d`.`dt_fim_indisp` AS `dt_fim_indisp` from (`db_sge`.`tb_rh` `r` left join `db_sge`.`tb_rh_disponibilidade` `d` on((`r`.`nr_matricula` = `d`.`nr_matricula`)));

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
