-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema zpr
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `zpr` ;

-- -----------------------------------------------------
-- Schema zpr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `zpr` ;
USE `zpr` ;

-- -----------------------------------------------------
-- Table `zpr`.`currency_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`currency_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `name_idx` USING BTREE (`name`) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`currency` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `currency_type_id` INT NOT NULL,
  `timestamp` DATETIME NOT NULL,
  `price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_currency_currency_type_idx` (`currency_type_id` ASC) VISIBLE,
  INDEX `timestamp_idx` USING BTREE (`timestamp`) VISIBLE,
  CONSTRAINT `fk_currency_currency_type1`
    FOREIGN KEY (`currency_type_id`)
    REFERENCES `zpr`.`currency_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`index_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`index_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `name_idx` USING BTREE (`name`) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`index_entry_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`index_entry_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `window_size` INT NULL,
  `index_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `name_idx` USING BTREE (`name`) VISIBLE,
  INDEX `fk_index_entry_type_index_type_idx` (`index_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_index_entry_type_index_type`
    FOREIGN KEY (`index_type_id`)
    REFERENCES `zpr`.`index_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`index_entry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`index_entry` (
  `id` BIGINT(30) NOT NULL AUTO_INCREMENT,
  `currency_id` BIGINT(20) NOT NULL,
  `index_entry_type_id` INT NOT NULL,
  `value` DECIMAL(30,10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_index_currency_idx` (`currency_id` ASC) VISIBLE,
  INDEX `fk_index_entry_index_entry_type_idx` (`index_entry_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_index_currency1`
    FOREIGN KEY (`currency_id`)
    REFERENCES `zpr`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_index_entry_index_entry_type`
    FOREIGN KEY (`index_entry_type_id`)
    REFERENCES `zpr`.`index_entry_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`aggregation_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`aggregation_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `name_idx` USING BTREE (`name`) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`aggregation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`aggregation` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `aggregation_type_id` INT NOT NULL,
  `first_id` BIGINT(20) NOT NULL,
  `last_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aggregation_aggregation_type_idx` (`aggregation_type_id` ASC) VISIBLE,
  INDEX `fk_aggregation_first_id_idx` (`first_id` ASC) VISIBLE,
  INDEX `fk_aggregation_last_id_idx` (`last_id` ASC) VISIBLE,
  CONSTRAINT `fk_aggregation_aggregation_type1`
    FOREIGN KEY (`aggregation_type_id`)
    REFERENCES `zpr`.`aggregation_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aggregation_first_id`
    FOREIGN KEY (`first_id`)
    REFERENCES `zpr`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aggregation_last_id`
    FOREIGN KEY (`last_id`)
    REFERENCES `zpr`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`entry_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`entry_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `name_idx` USING BTREE (`name`) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `zpr`.`aggregation_entry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zpr`.`aggregation_entry` (
  `id` BIGINT(30) NOT NULL AUTO_INCREMENT,
  `aggregation_id` BIGINT(20) NOT NULL,
  `entry_type_id` INT NOT NULL,
  `value` DECIMAL(30,10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_entry_entry_type_idx` (`entry_type_id` ASC) VISIBLE,
  INDEX `fk_entry_aggregation_idx` (`aggregation_id` ASC) VISIBLE,
  CONSTRAINT `fk_entry_entry_type1`
    FOREIGN KEY (`entry_type_id`)
    REFERENCES `zpr`.`entry_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_entry_aggregation1`
    FOREIGN KEY (`aggregation_id`)
    REFERENCES `zpr`.`aggregation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO currency_type(name)
VALUES ('bitcoin'),
       ('zcash'),
       ('ethernum'),
       ('litecoin');

INSERT INTO index_type(name)
VALUES ('sma'),
       ('ema'),
       ('rsi');

INSERT INTO index_entry_type(name, window_size, index_type_id)
VALUES ('sma5', 5, (SELECT id FROM index_type WHERE index_type.name = 'sma' LIMIT 1)),
       ('sma9', 9, (SELECT id FROM index_type WHERE index_type.name = 'sma' LIMIT 1)),
       ('sma14', 14, (SELECT id FROM index_type WHERE index_type.name = 'sma' LIMIT 1)),
       ('ema5', 5, (SELECT id FROM index_type WHERE index_type.name = 'ema' LIMIT 1)),
       ('ema9', 9, (SELECT id FROM index_type WHERE index_type.name = 'ema' LIMIT 1)),
       ('ema14', 14, (SELECT id FROM index_type WHERE index_type.name = 'ema' LIMIT 1)),
       ('rsi5', 5, (SELECT id FROM index_type WHERE index_type.name = 'rsi' LIMIT 1)),
       ('rsi9', 9, (SELECT id FROM index_type WHERE index_type.name = 'rsi' LIMIT 1)),
       ('rsi14', 14, (SELECT id FROM index_type WHERE index_type.name = 'rsi' LIMIT 1));

INSERT INTO aggregation_type(name)
VALUES ('minute'),
       ('hourly'),
       ('daily'),
       ('monthly');

INSERT INTO entry_type(name)
VALUES ('min'),
       ('max'),
       ('avg');
