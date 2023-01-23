-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema freedb_alessiopinna
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema freedb_alessiopinna
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `freedb_alessiopinna` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `freedb_alessiopinna` ;

-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`tpl_prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`tpl_prodotto` (
  `id_tpl_prodotto` INT NOT NULL AUTO_INCREMENT,
  `codice` VARCHAR(100) NULL DEFAULT NULL,
  `descrizione` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tpl_prodotto`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`prodotto` (
  `id_prodotto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `nome_ext` TEXT NULL DEFAULT NULL,
  `descrizione` LONGTEXT NULL DEFAULT NULL,
  `img_primaria` BLOB NULL DEFAULT NULL,
  `data_creazione` DATE NULL DEFAULT NULL,
  `enable` TINYINT NULL DEFAULT NULL,
  `prezzo` DECIMAL(9,2) NULL DEFAULT NULL,
  `quantita` INT NULL,
  `id_tpl_prodotto` INT NOT NULL,
  PRIMARY KEY (`id_prodotto`),
  INDEX `fk_prodotto_tpl_prodotto1_idx` (`id_tpl_prodotto` ASC) VISIBLE,
  CONSTRAINT `fk_prodotto_tpl_prodotto1`
    FOREIGN KEY (`id_tpl_prodotto`)
    REFERENCES `freedb_alessiopinna`.`tpl_prodotto` (`id_tpl_prodotto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`tpl_utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`tpl_utente` (
  `id_tpl_utente` INT NOT NULL AUTO_INCREMENT,
  `codice` VARCHAR(45) NULL DEFAULT NULL,
  `descrizione` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tpl_utente`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`utente` (
  `id_utente` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `provider` VARCHAR(15) NULL DEFAULT NULL,
  `id_tpl_utente` INT NOT NULL,
  PRIMARY KEY (`id_utente`),
  INDEX `fk_utente_tpl_utente1_idx` (`id_tpl_utente` ASC) VISIBLE,
  CONSTRAINT `fk_utente_tpl_utente1`
    FOREIGN KEY (`id_tpl_utente`)
    REFERENCES `freedb_alessiopinna`.`tpl_utente` (`id_tpl_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`token` (
  `id_token` INT NOT NULL AUTO_INCREMENT,
  `access_token` MEDIUMTEXT NULL DEFAULT NULL,
  `token_type` VARCHAR(255) NULL DEFAULT NULL,
  `expires_in_seconds` INT NULL DEFAULT NULL,
  `scope` TEXT NULL DEFAULT NULL,
  `date_creation` TIMESTAMP NULL DEFAULT NULL,
  `date_exiration` TIMESTAMP NULL DEFAULT NULL,
  `provider` VARCHAR(255) NULL DEFAULT NULL,
  `id_utente` INT NOT NULL,
  PRIMARY KEY (`id_token`),
  INDEX `fk_token_utente1_idx` (`id_utente` ASC) VISIBLE,
  CONSTRAINT `fk_token_utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `freedb_alessiopinna`.`utente` (`id_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`dati_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`dati_evento` (
  `id_dati_evento` INT NOT NULL,
  `id_event` VARCHAR(45) NULL DEFAULT NULL,
  `data_inizio` TIMESTAMP NULL DEFAULT NULL,
  `data_fine` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id_dati_evento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`acquisto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`acquisto` (
  `id_acquisto` INT NOT NULL AUTO_INCREMENT,
  `id_utente` INT NOT NULL,
  `id_prodotto` INT NOT NULL,
  `quantita` INT NOT NULL,
  `data_acquisto` TIMESTAMP NOT NULL,
  `id_dati_evento` INT NULL,
  PRIMARY KEY (`id_acquisto`),
  INDEX `fk_aquisto_utente1_idx` (`id_utente` ASC) VISIBLE,
  INDEX `fk_aquisto_prodotto1_idx` (`id_prodotto` ASC) VISIBLE,
  INDEX `fk_acquisto_dati_evento1_idx` (`id_dati_evento` ASC) VISIBLE,
  CONSTRAINT `fk_aquisto_prodotto1`
    FOREIGN KEY (`id_prodotto`)
    REFERENCES `freedb_alessiopinna`.`prodotto` (`id_prodotto`),
  CONSTRAINT `fk_aquisto_utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `freedb_alessiopinna`.`utente` (`id_utente`),
  CONSTRAINT `fk_acquisto_dati_evento1`
    FOREIGN KEY (`id_dati_evento`)
    REFERENCES `freedb_alessiopinna`.`dati_evento` (`id_dati_evento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;