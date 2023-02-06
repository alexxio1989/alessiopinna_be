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
  `id` VARCHAR(36) NOT NULL,
  `data_creazione` DATE NULL DEFAULT NULL,
  `descrizione` VARCHAR(255) NULL DEFAULT NULL,
  `enable` INT NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `nome_ext` VARCHAR(255) NULL DEFAULT NULL,
  `prezzo` DECIMAL(9,2) NULL DEFAULT NULL,
  `tipo_servizio` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
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
  `id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK8n2giw7wyg6befyhwtxx34np8`
    FOREIGN KEY (`id`)
    REFERENCES `alessiopinna`.`servizio` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
-- Table `alessiopinna`.`acquisto_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`acquisto_evento` (
  `id_acquisto_evento` VARCHAR(36) NOT NULL,
  `data_acquisto` DATETIME(6) NOT NULL,
  `quantita` INT NOT NULL,
  `data_fine` DATETIME(6) NULL DEFAULT NULL,
  `data_inizio` DATETIME(6) NULL DEFAULT NULL,
  `id_event_calendar` VARCHAR(255) NULL DEFAULT NULL,
  `id_utente` VARCHAR(36) NOT NULL,
  `id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id_acquisto_evento`),
  INDEX `FKqug6ff06aej9v7a3nq0c3u63o` (`id_utente` ASC) VISIBLE,
  INDEX `FKgdvj6ctd4viuallx8ia9ksqnw` (`id` ASC) VISIBLE,
  CONSTRAINT `FKgdvj6ctd4viuallx8ia9ksqnw`
    FOREIGN KEY (`id`)
    REFERENCES `alessiopinna`.`evento` (`id`),
  CONSTRAINT `FKqug6ff06aej9v7a3nq0c3u63o`
    FOREIGN KEY (`id_utente`)
    REFERENCES `alessiopinna`.`utente` (`id_utente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`prodotto` (
  `magazzino` INT NULL DEFAULT NULL,
  `id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK9wo773fjfj8eikwehagweaisu`
    FOREIGN KEY (`id`)
    REFERENCES `alessiopinna`.`servizio` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`acquisto_prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`acquisto_prodotto` (
  `id_acquisto_prodotto` VARCHAR(36) NOT NULL,
  `data_acquisto` DATETIME(6) NOT NULL,
  `quantita` INT NOT NULL,
  `id_utente` VARCHAR(36) NOT NULL,
  `id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id_acquisto_prodotto`),
  INDEX `FK5gj70nug8e36g1dr6d0jipbxp` (`id_utente` ASC) VISIBLE,
  INDEX `FKj76j8t04mh3oacfvalm88witn` (`id` ASC) VISIBLE,
  CONSTRAINT `FK5gj70nug8e36g1dr6d0jipbxp`
    FOREIGN KEY (`id_utente`)
    REFERENCES `alessiopinna`.`utente` (`id_utente`),
  CONSTRAINT `FKj76j8t04mh3oacfvalm88witn`
    FOREIGN KEY (`id`)
    REFERENCES `alessiopinna`.`prodotto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`detail_acquisto_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`detail_acquisto_evento` (
  `id` INT NOT NULL,
  `key_payment` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(100) NULL DEFAULT NULL,
  `id_acquisto_evento` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKn19uf397c5ojv993mtvv27lt8` (`id_acquisto_evento` ASC) VISIBLE,
  CONSTRAINT `FKn19uf397c5ojv993mtvv27lt8`
    FOREIGN KEY (`id_acquisto_evento`)
    REFERENCES `alessiopinna`.`acquisto_evento` (`id_acquisto_evento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`detail_acquisto_prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`detail_acquisto_prodotto` (
  `id` INT NOT NULL,
  `key_payment` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(100) NULL DEFAULT NULL,
  `id_acquisto_prodotto` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKqr3hx9je0ldxkwfv7ajoo6qa4` (`id_acquisto_prodotto` ASC) VISIBLE,
  CONSTRAINT `FKqr3hx9je0ldxkwfv7ajoo6qa4`
    FOREIGN KEY (`id_acquisto_prodotto`)
    REFERENCES `alessiopinna`.`acquisto_prodotto` (`id_acquisto_prodotto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `alessiopinna`.`img_servizio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`img_servizio` (
  `id_image` VARCHAR(36) NOT NULL,
  `img` TINYBLOB NULL DEFAULT NULL,
  `img_url` VARCHAR(45) NULL DEFAULT NULL,
  `key` VARCHAR(255) NULL DEFAULT NULL,
  `id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id_image`),
  INDEX `FKmv4ptq5b1s6moay0o2symptmy` (`id` ASC) VISIBLE,
  CONSTRAINT `FKmv4ptq5b1s6moay0o2symptmy`
    FOREIGN KEY (`id`)
    REFERENCES `alessiopinna`.`servizio` (`id`))
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