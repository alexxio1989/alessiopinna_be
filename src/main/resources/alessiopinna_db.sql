-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema alessiopinna
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alessiopinna
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alessiopinna` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `alessiopinna` ;

-- -----------------------------------------------------
-- Table `alessiopinna`.`tpl_utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`tpl_utente` (
  `codice` VARCHAR(100) NOT NULL,
  `descrizione` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`utente` (
  `id_utente` VARCHAR(36) NOT NULL,
  `anagrafica` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `photo_url` LONGTEXT NULL DEFAULT NULL,
  `provider` VARCHAR(15) NULL DEFAULT NULL,
  `tipo_utente` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_utente`),
  INDEX `FK1resxj9bns02xfguq7njwd45` (`tipo_utente` ASC) VISIBLE,
  CONSTRAINT `FK1resxj9bns02xfguq7njwd45`
    FOREIGN KEY (`tipo_utente`)
    REFERENCES `alessiopinna`.`tpl_utente` (`codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`acquisto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`acquisto` (
  `id_acquisto` VARCHAR(36) NOT NULL,
  `data_acquisto` DATETIME(6) NOT NULL,
  `quantita` INT NOT NULL,
  `id_utente` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id_acquisto`),
  INDEX `FK714ve4qass6e5lruah6w9gj71` (`id_utente` ASC) VISIBLE,
  CONSTRAINT `FK714ve4qass6e5lruah6w9gj71`
    FOREIGN KEY (`id_utente`)
    REFERENCES `alessiopinna`.`utente` (`id_utente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`tpl_servizio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`tpl_servizio` (
  `codice` VARCHAR(100) NOT NULL,
  `descrizione` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`servizio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`servizio` (
  `id_servizio` VARCHAR(36) NOT NULL,
  `data_creazione` DATE NULL DEFAULT NULL,
  `descrizione` VARCHAR(255) NULL DEFAULT NULL,
  `enable` INT NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `nome_ext` VARCHAR(255) NULL DEFAULT NULL,
  `prezzo` DECIMAL(9,2) NULL DEFAULT NULL,
  `tipo_servizio` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_servizio`),
  INDEX `FK54gdoevfe2nroafweic0xtdw9` (`tipo_servizio` ASC) VISIBLE,
  CONSTRAINT `FK54gdoevfe2nroafweic0xtdw9`
    FOREIGN KEY (`tipo_servizio`)
    REFERENCES `alessiopinna`.`tpl_servizio` (`codice`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`evento` (
  `data_fine` DATETIME(6) NULL DEFAULT NULL,
  `data_inizio` DATETIME(6) NULL DEFAULT NULL,
  `id_servizio` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id_servizio`),
  CONSTRAINT `FK7t08jfqkcakjdj4mt7g1i4hwi`
    FOREIGN KEY (`id_servizio`)
    REFERENCES `alessiopinna`.`servizio` (`id_servizio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`acquisto_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`acquisto_evento` (
  `data_fine` DATETIME(6) NULL DEFAULT NULL,
  `data_inizio` DATETIME(6) NULL DEFAULT NULL,
  `id_event_calendar` VARCHAR(255) NULL DEFAULT NULL,
  `id_acquisto` VARCHAR(36) NOT NULL,
  `id_servizio` VARCHAR(36) NOT NULL,
  `id_utente` VARCHAR(36) NULL DEFAULT NULL,
  PRIMARY KEY (`id_acquisto`),
  INDEX `FK6dd9myvl7r6w5e4iu2mn6c66c` (`id_servizio` ASC) VISIBLE,
  CONSTRAINT `FK5rhk4ns4yagpojf48ir1nlua7`
    FOREIGN KEY (`id_acquisto`)
    REFERENCES `alessiopinna`.`acquisto` (`id_acquisto`),
  CONSTRAINT `FK6dd9myvl7r6w5e4iu2mn6c66c`
    FOREIGN KEY (`id_servizio`)
    REFERENCES `alessiopinna`.`evento` (`id_servizio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`prodotto` (
  `magazzino` INT NULL DEFAULT NULL,
  `id_servizio` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id_servizio`),
  CONSTRAINT `FK2l6ur7mqtoh35e3lhw30q7yvb`
    FOREIGN KEY (`id_servizio`)
    REFERENCES `alessiopinna`.`servizio` (`id_servizio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`acquisto_prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`acquisto_prodotto` (
  `id_acquisto` VARCHAR(36) NOT NULL,
  `id_servizio` VARCHAR(36) NOT NULL,
  `id_utente` VARCHAR(36) NULL DEFAULT NULL,
  PRIMARY KEY (`id_acquisto`),
  INDEX `FK3fsh20yfx7nbmix2arkfbqvg7` (`id_servizio` ASC) VISIBLE,
  CONSTRAINT `FK3fsh20yfx7nbmix2arkfbqvg7`
    FOREIGN KEY (`id_servizio`)
    REFERENCES `alessiopinna`.`prodotto` (`id_servizio`),
  CONSTRAINT `FKhah5xtldqawsm8sqnqoa6ucxd`
    FOREIGN KEY (`id_acquisto`)
    REFERENCES `alessiopinna`.`acquisto` (`id_acquisto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`img_servizio` (
  `id_image` VARCHAR(36) NOT NULL,
  `id_servizio` VARCHAR(36) NOT NULL,
  `base64` LONGTEXT NULL DEFAULT NULL,
  `url` VARCHAR(45) NULL DEFAULT NULL,

  PRIMARY KEY (`id_image`),
  INDEX `FKc8qb7g4bn585mwygsbna56oqq` (`id_servizio` ASC) VISIBLE,
  CONSTRAINT `FKc8qb7g4bn585mwygsbna56oqq`
    FOREIGN KEY (`id_servizio`)
    REFERENCES `alessiopinna`.`servizio` (`id_servizio`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`token` (
  `id` VARCHAR(36) NOT NULL,
  `access_token` LONGTEXT NULL DEFAULT NULL,
  `date_creation` DATETIME(6) NULL DEFAULT NULL,
  `date_exiration` DATETIME(6) NULL DEFAULT NULL,
  `expires_in_seconds` INT NULL DEFAULT NULL,
  `provider` VARCHAR(255) NULL DEFAULT NULL,
  `scope` LONGTEXT NULL DEFAULT NULL,
  `token_type` VARCHAR(255) NULL DEFAULT NULL,
  `id_utente` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKpoweg60t19qe615km77656ld8` (`id_utente` ASC) VISIBLE,
  CONSTRAINT `FKpoweg60t19qe615km77656ld8`
    FOREIGN KEY (`id_utente`)
    REFERENCES `alessiopinna`.`utente` (`id_utente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



INSERT INTO tpl_utente (codice, descrizione) VALUES ('U', 'USER');
INSERT INTO tpl_utente (codice, descrizione) VALUES ('SU', 'SUPER_USER');
INSERT INTO utente (id_utente,anagrafica, email, password, photo_url, provider, tipo_utente) VALUES ( MID(UUID(),1,36),'ADMIN', 'apinna.elearn@gmail.com', 'apinna.elearn@gmail.com', '', '', 'SU');
