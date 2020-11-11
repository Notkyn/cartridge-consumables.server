DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `cartridge`;
DROP TABLE IF EXISTS `toner`;
DROP TABLE IF EXISTS `drum`;
DROP TABLE IF EXISTS `magnetic_shaft`;
DROP TABLE IF EXISTS `primary_charge_shaft`;
DROP TABLE IF EXISTS `cleaning_blade`;
DROP TABLE IF EXISTS `dispensing_blade`;

CREATE TABLE `toner`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `drum`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `magnetic_shaft`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `primary_charge_shaft`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `cleaning_blade`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `dispensing_blade`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `cartridge`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `coef_toner` INT UNSIGNED NOT NULL,
  `_id_toner` INT UNSIGNED NOT NULL,
  `_id_drum` INT UNSIGNED NOT NULL,
  `_id_magnetic_shaft` INT UNSIGNED NOT NULL,
  `_id_primary_charge_shaft` INT UNSIGNED NOT NULL,
  `_id_cleaning_blade` INT UNSIGNED NOT NULL,
  `_id_dispensing_blade` INT UNSIGNED NOT NULL,
  PRIMARY KEY(`_id`),
  CONSTRAINT `_id_toner_ibfk_1`
    FOREIGN KEY (`_id_toner`) REFERENCES `toner`(`_id`),
  CONSTRAINT `_id_drum_ibfk_2`
    FOREIGN KEY (`_id_drum`) REFERENCES `drum`(`_id`),
  CONSTRAINT `_id_magnetic_shaft_ibfk_3`
    FOREIGN KEY (`_id_magnetic_shaft`) REFERENCES `magnetic_shaft`(`_id`),
  CONSTRAINT `_id_primary_charge_shaft_ibfk_4`
    FOREIGN KEY (`_id_primary_charge_shaft`) REFERENCES `primary_charge_shaft`(`_id`),
  CONSTRAINT `_id_cleaning_blade_ibfk_5`
    FOREIGN KEY (`_id_cleaning_blade`) REFERENCES `cleaning_blade`(`_id`),
  CONSTRAINT `_id_dispensing_blade_ibfk_6`
    FOREIGN KEY (`_id_dispensing_blade`) REFERENCES `dispensing_blade`(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `department`(
  `_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `_id_cartridge` INT UNSIGNED NOT NULL,
  PRIMARY KEY(`_id`),
  CONSTRAINT `_id_cartridge_ibfk_1`
   FOREIGN KEY(`_id_cartridge`) REFERENCES `cartridge`(`_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;