-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`tpl_corso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`tpl_corso` (
  `idtpl_corso` INT NOT NULL AUTO_INCREMENT,
  `codice` VARCHAR(100) NULL,
  `descrizione` VARCHAR(255) NULL,
  PRIMARY KEY (`idtpl_corso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`corso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`corso` (
  `idcorso` INT NOT NULL AUTO_INCREMENT,
  `titolo` VARCHAR(255) NULL DEFAULT NULL,
  `descrizione` LONGTEXT NULL DEFAULT NULL,
  `giorni_orari` TEXT NULL DEFAULT NULL,
  `img_name` VARCHAR(255) NULL DEFAULT NULL,
  `data_creazione` DATE NULL DEFAULT NULL,
  `enable` TINYINT NULL DEFAULT NULL,
  `prezzo` DECIMAL(9,2) NULL DEFAULT NULL,
  `tpl_corso_idtpl_corso` INT NOT NULL,
  PRIMARY KEY (`idcorso`),
  INDEX `fk_corso_tpl_corso1_idx` (`tpl_corso_idtpl_corso` ASC) VISIBLE,
  CONSTRAINT `fk_corso_tpl_corso1`
    FOREIGN KEY (`tpl_corso_idtpl_corso`)
    REFERENCES `freedb_alessiopinna`.`tpl_corso` (`idtpl_corso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`tpl_utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`tpl_utente` (
  `idtpl_utente` INT NOT NULL AUTO_INCREMENT,
  `codice` VARCHAR(45) NULL,
  `descrizione` VARCHAR(45) NULL,
  PRIMARY KEY (`idtpl_utente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`utente` (
  `idutente` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `skypeID` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `tpl_utente_idtpl_utente` INT NOT NULL,
  PRIMARY KEY (`idutente`),
  INDEX `fk_utente_tpl_utente1_idx` (`tpl_utente_idtpl_utente` ASC) VISIBLE,
  CONSTRAINT `fk_utente_tpl_utente1`
    FOREIGN KEY (`tpl_utente_idtpl_utente`)
    REFERENCES `freedb_alessiopinna`.`tpl_utente` (`idtpl_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `freedb_alessiopinna`.`prenotazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `freedb_alessiopinna`.`prenotazione` (
  `utente_idutente` INT NOT NULL,
  `corso_idcorso` INT NOT NULL,
  `qnt_ore` VARCHAR(255) NULL DEFAULT NULL,
  `data_prenotazione` DATE NULL DEFAULT NULL,
  `ora_inizio` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`utente_idutente`, `corso_idcorso`),
  INDEX `fk_utente_has_corso_corso1_idx` (`corso_idcorso` ASC) VISIBLE,
  INDEX `fk_utente_has_corso_utente_idx` (`utente_idutente` ASC) VISIBLE,
  CONSTRAINT `fk_utente_has_corso_corso1`
    FOREIGN KEY (`corso_idcorso`)
    REFERENCES `freedb_alessiopinna`.`corso` (`idcorso`),
  CONSTRAINT `fk_utente_has_corso_utente`
    FOREIGN KEY (`utente_idutente`)
    REFERENCES `freedb_alessiopinna`.`utente` (`idutente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;