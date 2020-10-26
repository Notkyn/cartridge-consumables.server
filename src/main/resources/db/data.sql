SET SQL_SAFE_UPDATES = 0;

# cleaning all notes from all tables;
DELETE FROM `department` WHERE `_id` > 0;
ALTER TABLE `department` AUTO_INCREMENT = 1;
DELETE FROM `cartridge` WHERE `_id` > 0;
ALTER TABLE `cartridge` AUTO_INCREMENT = 1;
DELETE FROM `drum` WHERE `_id` > 0;
ALTER TABLE `drum` AUTO_INCREMENT = 1;
DELETE FROM `magnetic_shaft` WHERE `_id` > 0;
ALTER TABLE `magnetic_shaft` AUTO_INCREMENT = 1;
DELETE FROM `primary_charge_shaft` WHERE `_id` > 0;
ALTER TABLE `primary_charge_shaft` AUTO_INCREMENT = 1;
DELETE FROM `cleaning_blade` WHERE `_id` > 0;
ALTER TABLE `cleaning_blade` AUTO_INCREMENT = 1;
DELETE FROM `dispensing_blade` WHERE `_id` > 0;
ALTER TABLE `dispensing_blade` AUTO_INCREMENT = 1;

# insert new notes to all tables;
INSERT INTO `drum`(`name`) VALUES ('drum_1');
INSERT INTO `drum`(`name`) VALUES ('drum_2');
INSERT INTO `drum`(`name`) VALUES ('drum_3');
INSERT INTO `drum`(`name`) VALUES ('drum_4');
INSERT INTO `drum`(`name`) VALUES ('drum_5');

INSERT INTO `magnetic_shaft`(`name`) VALUES ('magnetic_shaft_1');
INSERT INTO `magnetic_shaft`(`name`) VALUES ('magnetic_shaft_2');
INSERT INTO `magnetic_shaft`(`name`) VALUES ('magnetic_shaft_3');
INSERT INTO `magnetic_shaft`(`name`) VALUES ('magnetic_shaft_4');
INSERT INTO `magnetic_shaft`(`name`) VALUES ('magnetic_shaft_5');

INSERT INTO `primary_charge_shaft`(`name`) VALUES ('primary_charge_shaft_1');
INSERT INTO `primary_charge_shaft`(`name`) VALUES ('primary_charge_shaft_2');
INSERT INTO `primary_charge_shaft`(`name`) VALUES ('primary_charge_shaft_3');
INSERT INTO `primary_charge_shaft`(`name`) VALUES ('primary_charge_shaft_4');
INSERT INTO `primary_charge_shaft`(`name`) VALUES ('primary_charge_shaft_5');

INSERT INTO `cleaning_blade`(`name`) VALUES ('cleaning_blade_1');
INSERT INTO `cleaning_blade`(`name`) VALUES ('cleaning_blade_2');
INSERT INTO `cleaning_blade`(`name`) VALUES ('cleaning_blade_3');
INSERT INTO `cleaning_blade`(`name`) VALUES ('cleaning_blade_4');
INSERT INTO `cleaning_blade`(`name`) VALUES ('cleaning_blade_5');

INSERT INTO `dispensing_blade`(`name`) VALUES ('dispensing_blade_1');
INSERT INTO `dispensing_blade`(`name`) VALUES ('dispensing_blade_2');
INSERT INTO `dispensing_blade`(`name`) VALUES ('dispensing_blade_3');
INSERT INTO `dispensing_blade`(`name`) VALUES ('dispensing_blade_4');
INSERT INTO `dispensing_blade`(`name`) VALUES ('dispensing_blade_5');

INSERT INTO `cartridge`(`name`, `_id_drum`, `_id_magnetic_shaft`, `_id_primary_charge_shaft`, `_id_cleaning_blade`, `_id_dispensing_blade`)
	VALUES ('cartridge_1', 1, 1, 1, 1, 1);
INSERT INTO `cartridge`(`name`, `_id_drum`, `_id_magnetic_shaft`, `_id_primary_charge_shaft`, `_id_cleaning_blade`, `_id_dispensing_blade`)
	VALUES ('cartridge_2', 2, 2, 2, 2, 2);
INSERT INTO `cartridge`(`name`, `_id_drum`, `_id_magnetic_shaft`, `_id_primary_charge_shaft`, `_id_cleaning_blade`, `_id_dispensing_blade`)
	VALUES ('cartridge_3', 3, 3, 3, 3, 3);
INSERT INTO `cartridge`(`name`, `_id_drum`, `_id_magnetic_shaft`, `_id_primary_charge_shaft`, `_id_cleaning_blade`, `_id_dispensing_blade`)
	VALUES ('cartridge_4', 4, 4, 4, 4, 4);
INSERT INTO `cartridge`(`name`, `_id_drum`, `_id_magnetic_shaft`, `_id_primary_charge_shaft`, `_id_cleaning_blade`, `_id_dispensing_blade`)
	VALUES ('cartridge_5', 1, 1, 1, 1, 1);

INSERT INTO `department`(`name`) VALUES ('department_1');
INSERT INTO `department`(`name`) VALUES ('department_2');
INSERT INTO `department`(`name`) VALUES ('department_3');
INSERT INTO `department`(`name`) VALUES ('department_4');
INSERT INTO `department`(`name`) VALUES ('department_5');

SET SQL_SAFE_UPDATES = 1;