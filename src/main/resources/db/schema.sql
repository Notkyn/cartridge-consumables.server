DROP TABLE IF EXISTS `department_cartridge_megre`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `cartridge`;
DROP TABLE IF EXISTS `drum`;
DROP TABLE IF EXISTS `magnetic_shaft`;
DROP TABLE IF EXISTS `primary_charge_shaft`;
DROP TABLE IF EXISTS `cleaning_blade`;
DROP TABLE IF EXISTS `dispensing_blade`;

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
    `_id_drum` INT UNSIGNED NULL,
    `_id_magnetic_shaft` INT UNSIGNED NULL,
    `_id_primary_charge_shaft` INT UNSIGNED NULL,
    `_id_cleaning_blade` INT UNSIGNED NULL,
    `_id_dispensing_blade` INT UNSIGNED NULL,
    PRIMARY KEY(`_id`),
    CONSTRAINT `_id_drum_ibfk_1`
    FOREIGN KEY (`_id_drum`) REFERENCES `drum`(`_id`),
    CONSTRAINT `_id_magnetic_shaft_ibfk_2`
    FOREIGN KEY (`_id_magnetic_shaft`) REFERENCES `magnetic_shaft`(`_id`),
    CONSTRAINT `_id_primary_charge_shaft_ibfk_3`
    FOREIGN KEY (`_id_primary_charge_shaft`) REFERENCES `primary_charge_shaft`(`_id`),
    CONSTRAINT `_id_cleaning_blade_ibfk_4`
    FOREIGN KEY (`_id_cleaning_blade`) REFERENCES `cleaning_blade`(`_id`),
    CONSTRAINT `_id_dispensing_blade_ibfk_5`
    FOREIGN KEY (`_id_dispensing_blade`) REFERENCES `dispensing_blade`(`_id`)
    )
    ENGINE = InnoDB 
    DEFAULT CHARACTER SET = utf8mb4;
    
CREATE TABLE `department`(
	`_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY(`_id`)
    )
    ENGINE = InnoDB 
    DEFAULT CHARACTER SET = utf8mb4;
    
CREATE TABLE `department_cartridge_megre`(
	`_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`_id_department_megre` INT UNSIGNED NULL,
    `_id_cartridge_megre` INT UNSIGNED NULL,
    PRIMARY KEY (`_id`),
    CONSTRAINT `_id_ibfk_1`
    FOREIGN KEY (`_id_department_megre`) REFERENCES `department`(`_id`),
    CONSTRAINT `_id_cartridge_megre_ibfk_2`
    FOREIGN KEY (`_id_cartridge_megre`) REFERENCES `cartridge`(`_id`)
    )
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;