-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema quickfixdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `quickfixdb` ;

-- -----------------------------------------------------
-- Schema quickfixdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quickfixdb` DEFAULT CHARACTER SET utf8 ;
USE `quickfixdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(70) NOT NULL,
  `street2` VARCHAR(70) NULL,
  `city` VARCHAR(70) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NOT NULL DEFAULT 'Person',
  `last_name` VARCHAR(45) NOT NULL DEFAULT 'Doe',
  `email` VARCHAR(80) NOT NULL DEFAULT 'example@example.com',
  `phone` VARCHAR(45) NOT NULL DEFAULT '(123)-456-7890',
  `biography` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `address_id` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `provider` ;

CREATE TABLE IF NOT EXISTS `provider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company` VARCHAR(45) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `rate_per_hour` DECIMAL(6,2) NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `enabled` TINYINT NOT NULL,
  `address_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `description` TEXT NULL,
  `logo_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_provider_address1_idx` (`address_id` ASC),
  INDEX `fk_provider_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_provider_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trade` ;

CREATE TABLE IF NOT EXISTS `trade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_post` ;

CREATE TABLE IF NOT EXISTS `job_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `complete` TINYINT NOT NULL,
  `enabled` TINYINT NOT NULL,
  `start_date` DATE NULL,
  `special_instructions` TEXT NULL,
  `materials_provided` TINYINT NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  `user_id` INT NOT NULL,
  `budget_max` DECIMAL(10,2) NULL,
  `bid_by` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `appointment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `appointment` ;

CREATE TABLE IF NOT EXISTS `appointment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `appointment_date` DATETIME NULL,
  `provider_id` INT NOT NULL,
  `job_post_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_appointment_provider1_idx` (`provider_id` ASC),
  INDEX `fk_appointment_job_post1_idx` (`job_post_id` ASC),
  CONSTRAINT `fk_appointment_provider1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `provider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_post_has_trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_post_has_trade` ;

CREATE TABLE IF NOT EXISTS `job_post_has_trade` (
  `job_post_id` INT NOT NULL,
  `trade_id` INT NOT NULL,
  PRIMARY KEY (`job_post_id`, `trade_id`),
  INDEX `fk_post_has_job_type_job_type1_idx` (`trade_id` ASC),
  INDEX `fk_post_has_job_type_post1_idx` (`job_post_id` ASC),
  CONSTRAINT `fk_post_has_job_type_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_has_job_type_job_type1`
    FOREIGN KEY (`trade_id`)
    REFERENCES `trade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_area` ;

CREATE TABLE IF NOT EXISTS `project_area` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `specialty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `specialty` ;

CREATE TABLE IF NOT EXISTS `specialty` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `trade_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_focus_trade1_idx` (`trade_id` ASC),
  CONSTRAINT `fk_focus_trade1`
    FOREIGN KEY (`trade_id`)
    REFERENCES `trade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bid` ;

CREATE TABLE IF NOT EXISTS `bid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(10,2) NOT NULL,
  `user_provider_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `bid_date` DATETIME NULL,
  `provider_note` TEXT NULL,
  `accepted` TINYINT NOT NULL,
  `rating_by_user` INT(1) NULL,
  `user_comment` TEXT NULL,
  `rating_by_provider` INT(1) NULL,
  `provider_comment` TEXT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bid_user_provider1_idx` (`user_provider_id` ASC),
  INDEX `fk_bid_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_bid_user_provider1`
    FOREIGN KEY (`user_provider_id`)
    REFERENCES `provider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bid_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `provider_has_trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `provider_has_trade` ;

CREATE TABLE IF NOT EXISTS `provider_has_trade` (
  `provider_id` INT NOT NULL,
  `trade_id` INT NOT NULL,
  PRIMARY KEY (`provider_id`, `trade_id`),
  INDEX `fk_provider_has_trade_trade1_idx` (`trade_id` ASC),
  INDEX `fk_provider_has_trade_provider1_idx` (`provider_id` ASC),
  CONSTRAINT `fk_provider_has_trade_provider1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `provider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_has_trade_trade1`
    FOREIGN KEY (`trade_id`)
    REFERENCES `trade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `focus_has_job_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `focus_has_job_post` ;

CREATE TABLE IF NOT EXISTS `focus_has_job_post` (
  `focus_id` INT NOT NULL,
  `job_post_id` INT NOT NULL,
  PRIMARY KEY (`focus_id`, `job_post_id`),
  INDEX `fk_focus_has_job_post_job_post1_idx` (`job_post_id` ASC),
  INDEX `fk_focus_has_job_post_focus1_idx` (`focus_id` ASC),
  CONSTRAINT `fk_focus_has_job_post_focus1`
    FOREIGN KEY (`focus_id`)
    REFERENCES `specialty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_focus_has_job_post_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `provider_has_focus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `provider_has_focus` ;

CREATE TABLE IF NOT EXISTS `provider_has_focus` (
  `provider_id` INT NOT NULL,
  `focus_id` INT NOT NULL,
  PRIMARY KEY (`provider_id`, `focus_id`),
  INDEX `fk_provider_has_focus_focus1_idx` (`focus_id` ASC),
  INDEX `fk_provider_has_focus_provider1_idx` (`provider_id` ASC),
  CONSTRAINT `fk_provider_has_focus_provider1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `provider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_has_focus_focus1`
    FOREIGN KEY (`focus_id`)
    REFERENCES `specialty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bid_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bid_comment` ;

CREATE TABLE IF NOT EXISTS `bid_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `comment_date` DATETIME NULL,
  `bid_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bid_comment_bid1_idx` (`bid_id` ASC),
  INDEX `fk_bid_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_bid_comment_bid1`
    FOREIGN KEY (`bid_id`)
    REFERENCES `bid` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bid_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_post_has_project_area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_post_has_project_area` ;

CREATE TABLE IF NOT EXISTS `job_post_has_project_area` (
  `job_post_id` INT NOT NULL,
  `project_area_id` INT NOT NULL,
  PRIMARY KEY (`job_post_id`, `project_area_id`),
  INDEX `fk_job_post_has_project_area_project_area1_idx` (`project_area_id` ASC),
  INDEX `fk_job_post_has_project_area_job_post1_idx` (`job_post_id` ASC),
  CONSTRAINT `fk_job_post_has_project_area_job_post1`
    FOREIGN KEY (`job_post_id`)
    REFERENCES `job_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_post_has_project_area_project_area1`
    FOREIGN KEY (`project_area_id`)
    REFERENCES `project_area` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS quickfix@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'quickfix'@'localhost' IDENTIFIED BY 'quickfix';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'quickfix'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (1, '1428 Elms St', NULL, 'Denver', 'CO', '80903');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (2, '187 Crystal Lake Ln', NULL, 'Denver', 'CO', '80903');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', 'Joe', 'Joe', 'joeadmin@example.com', '(456)-456-4562', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (2, 'handygill', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'provider', 'Gill', 'Bert', 'gill-bert@example.con', '(303)-123-1234', NULL, NULL, NULL, NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `provider`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (1, 'Gills Handy Service', 'Gills@Example.com', '(564)-456-4582', 20.0, NULL, NULL, 1, 2, 2, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trade`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (1, 'General', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (2, 'Plumber', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (3, 'Electrictian', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (4, 'Carpenter', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (5, 'Roofer', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (6, 'Landscaper', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (7, 'HVAC', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (8, 'Painter', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (9, 'Drywall', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (10, 'Gutter', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (11, 'Housekeeper', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (12, 'Flooring', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (13, 'Insulation', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (14, 'Crawl Space', NULL, NULL, NULL);
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (15, 'Masonry', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (1, 'Kitchen Remodel', 'i have roaches', NULL, NULL, 0, 1, NULL, NULL, 0, NULL, 1, 10000, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `appointment`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `appointment` (`id`, `description`, `appointment_date`, `provider_id`, `job_post_id`) VALUES (1, 'kitchen estimate', NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post_has_trade`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `job_post_has_trade` (`job_post_id`, `trade_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_area`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `project_area` (`id`, `name`) VALUES (1, 'Counter Tops');

COMMIT;


-- -----------------------------------------------------
-- Data for table `specialty`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (1, 'General', 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bid`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `bid` (`id`, `amount`, `user_provider_id`, `post_id`, `bid_date`, `provider_note`, `accepted`, `rating_by_user`, `user_comment`, `rating_by_provider`, `provider_comment`, `enabled`) VALUES (1, 9000.0, 1, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `provider_has_trade`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `focus_has_job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `focus_has_job_post` (`focus_id`, `job_post_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `provider_has_focus`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bid_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `bid_comment` (`id`, `content`, `comment_date`, `bid_id`, `user_id`) VALUES (1, 'comment', NULL, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post_has_project_area`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `job_post_has_project_area` (`job_post_id`, `project_area_id`) VALUES (1, 1);

COMMIT;

