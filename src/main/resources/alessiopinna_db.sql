create database alessiopinna

use alessiopinna

CREATE TABLE IF NOT EXISTS `alessiopinna`.`utente` (
  `idutente` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL,
  `skypeID` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`idutente`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `alessiopinna`.`corso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`corso` (
  `idcorso` INT NOT NULL AUTO_INCREMENT,
  `titolo` VARCHAR(255) NULL,
  `descrizione` LONGTEXT NULL,
  `giorni_orari` TEXT(4000) NULL,
  `img_name` VARCHAR(255) NULL,
  `data_creazione` DATE NULL,
  `enable` TINYINT NULL,
  `prezzo` DECIMAL(9,2) NULL,
  `tipo` VARCHAR(255) NULL,
  PRIMARY KEY (`idcorso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alessiopinna`.`prenotazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alessiopinna`.`prenotazione` (
  `utente_idutente` INT NOT NULL,
  `corso_idcorso` INT NOT NULL,
  `qnt_ore` VARCHAR(255) NULL,
  `data_prenotazione` DATE NULL,
  `ora_inizio` VARCHAR(255) NULL,
  PRIMARY KEY (`utente_idutente`, `corso_idcorso`),
  INDEX `fk_utente_has_corso_corso1_idx` (`corso_idcorso` ASC) VISIBLE,
  INDEX `fk_utente_has_corso_utente_idx` (`utente_idutente` ASC) VISIBLE,
  CONSTRAINT `fk_utente_has_corso_utente`
    FOREIGN KEY (`utente_idutente`)
    REFERENCES `alessiopinna`.`utente` (`idutente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_utente_has_corso_corso1`
    FOREIGN KEY (`corso_idcorso`)
    REFERENCES `alessiopinna`.`corso` (`idcorso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;