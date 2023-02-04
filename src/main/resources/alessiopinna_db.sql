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
-- Table `freedb_alessiopinna`.`tpl_utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`tpl_utente` (
  `codice` VARCHAR(100) NOT NULL,
  `descrizione` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codice`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`utente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `anagrafica` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `provider` VARCHAR(15) NULL DEFAULT NULL,
  `photo_url` TEXT NULL DEFAULT NULL,
  `tipo_utente` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_utente_tpl_utente1_idx` (`tipo_utente` ASC) VISIBLE,
  CONSTRAINT `fk_utente_tpl_utente1`
    FOREIGN KEY (`tipo_utente`)
    REFERENCES `freedb_alessiopinna`.`tpl_utente` (`codice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`tpl_servizio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`tpl_servizio` (
  `codice` VARCHAR(100) NOT NULL,
  `descrizione` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codice`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`servizio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`servizio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `nome_ext` TEXT NULL DEFAULT NULL,
  `descrizione` LONGTEXT NULL DEFAULT NULL,
  `data_creazione` DATE NULL DEFAULT NULL,
  `enable` TINYINT NULL DEFAULT NULL,
  `prezzo` DECIMAL(9,2) NULL DEFAULT NULL,
  `tipo_servizio` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_servizio_tpl_servizio1_idx` (`tipo_servizio` ASC) VISIBLE,
  CONSTRAINT `fk_servizio_tpl_servizio1`
    FOREIGN KEY (`tipo_servizio`)
    REFERENCES `freedb_alessiopinna`.`tpl_servizio` (`codice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`acquisto_prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`acquisto_prodotto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantita` INT NOT NULL,
  `data_acquisto` TIMESTAMP NOT NULL,
  `utente_id` INT NOT NULL,
  `servizio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_acquisto_prodotto_utente1_idx` (`utente_id` ASC) VISIBLE,
  INDEX `fk_acquisto_prodotto_servizio1_idx` (`servizio_id` ASC) VISIBLE,
  CONSTRAINT `fk_acquisto_prodotto_utente1`
    FOREIGN KEY (`utente_id`)
    REFERENCES `freedb_alessiopinna`.`utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acquisto_prodotto_servizio1`
    FOREIGN KEY (`servizio_id`)
    REFERENCES `freedb_alessiopinna`.`servizio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`detail_acquisto_prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`detail_acquisto_prodotto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `key_payment` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(100) NULL DEFAULT NULL,
  `acquisto_prodotto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_detail_acquisto_prodotto_acquisto_prodotto1_idx` (`acquisto_prodotto_id` ASC) VISIBLE,
  CONSTRAINT `fk_detail_acquisto_prodotto_acquisto_prodotto1`
    FOREIGN KEY (`acquisto_prodotto_id`)
    REFERENCES `freedb_alessiopinna`.`acquisto_prodotto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`img_servizio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`img_servizio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(255) NULL DEFAULT NULL,
  `img_url` VARCHAR(45) NULL DEFAULT NULL,
  `img` BLOB NULL DEFAULT NULL,
  `id_servizio` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_img_servizio_servizio1_idx` (`id_servizio` ASC) VISIBLE,
  CONSTRAINT `fk_img_servizio_servizio1`
    FOREIGN KEY (`id_servizio`)
    REFERENCES `freedb_alessiopinna`.`servizio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`token` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `access_token` MEDIUMTEXT NULL DEFAULT NULL,
  `token_type` VARCHAR(255) NULL DEFAULT NULL,
  `expires_in_seconds` INT NULL DEFAULT NULL,
  `scope` TEXT NULL DEFAULT NULL,
  `date_creation` TIMESTAMP NULL DEFAULT NULL,
  `date_exiration` TIMESTAMP NULL DEFAULT NULL,
  `provider` VARCHAR(255) NULL DEFAULT NULL,
  `utente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_token_utente1_idx` (`utente_id` ASC) VISIBLE,
  CONSTRAINT `fk_token_utente1`
    FOREIGN KEY (`utente_id`)
    REFERENCES `freedb_alessiopinna`.`utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`acquisto_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`acquisto_evento` (
  `id` INT NOT NULL,
  `quantita` INT NOT NULL,
  `data_acquisto` TIMESTAMP NOT NULL,
  `data_inizio` TIMESTAMP NULL,
  `data_fine` TIMESTAMP NULL,
  `id_event_calendar` VARCHAR(255) NULL,
  `utente_id` INT NOT NULL,
  `servizio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_acquisto_evento_utente1_idx` (`utente_id` ASC) VISIBLE,
  INDEX `fk_acquisto_evento_servizio1_idx` (`servizio_id` ASC) VISIBLE,
  CONSTRAINT `fk_acquisto_evento_utente1`
    FOREIGN KEY (`utente_id`)
    REFERENCES `freedb_alessiopinna`.`utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_acquisto_evento_servizio1`
    FOREIGN KEY (`servizio_id`)
    REFERENCES `freedb_alessiopinna`.`servizio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`detail_acquisto_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`detail_acquisto_evento` (
  `id_detail_acquisto` INT NOT NULL AUTO_INCREMENT,
  `key_payment` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(100) NULL DEFAULT NULL,
  `acquisto_evento_id` INT NOT NULL,
  PRIMARY KEY (`id_detail_acquisto`),
  INDEX `fk_detail_acquisto_evento_acquisto_evento1_idx` (`acquisto_evento_id` ASC) VISIBLE,
  CONSTRAINT `fk_detail_acquisto_evento_acquisto_evento1`
    FOREIGN KEY (`acquisto_evento_id`)
    REFERENCES `freedb_alessiopinna`.`acquisto_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`prodotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`prodotto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `magazzino` INT NULL,
  `servizio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_prodotto_servizio1_idx` (`servizio_id` ASC) VISIBLE,
  CONSTRAINT `fk_prodotto_servizio1`
    FOREIGN KEY (`servizio_id`)
    REFERENCES `freedb_alessiopinna`.`servizio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_inizio` TIMESTAMP NULL,
  `data_fine` TIMESTAMP NULL,
  `servizio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_evento_servizio1_idx` (`servizio_id` ASC) VISIBLE,
  CONSTRAINT `fk_evento_servizio1`
    FOREIGN KEY (`servizio_id`)
    REFERENCES `freedb_alessiopinna`.`servizio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO tpl_utente (codice, descrizione) VALUES ('U', 'USER');
INSERT INTO tpl_utente (codice, descrizione) VALUES ('SU', 'SUPER_USER');