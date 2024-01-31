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
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (3, '221 Baker st', NULL, 'Denver', 'CO', '80903');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (4, '112 Ocean Ave', NULL, 'Denver', 'CO', '80903');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (5, '10 Cloverfield Ln', NULL, 'Lakewood', 'CO', '80948');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (6, '1801 Roswell Rd', NULL, 'Glendale', 'CO', '80905');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (7, '632 Jammin St', NULL, 'Englewood', 'CO', '80909');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (8, '987 Gaga Rd', NULL, 'Aurora', 'CO', '80902');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (9, '254 Cheene Rd', NULL, 'Broomfield', 'CO', '80954');
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (10, ' 4583 Highland Ln', NULL, 'Thornton', 'CO', '80945');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', 'Joe', 'Joe', 'joeadmin@example.com', '(456)-456-4562', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (2, 'handygill', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'Gill', 'Bert', 'gill-bert@example.con', '(303)-123-1234', 'Been fixing things since i was born.', 'https://img.freepik.com/free-photo/high-view-shot-smiley-man-wearing-cap_23-2148283857.jpg?size=626&ext=jpg', NULL, NULL, 2);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (3, 'neo', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Client', 'Thomas', 'Anderson', 'neo@matrix.com', '(303)-123-0102', 'Plumbing my way to your home ', 'https://img.freepik.com/free-photo/smiling-man_1098-15443.jpg?size=626&ext=jpg', NULL, NULL, 3);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (4, 'ripley', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Client', 'Ellen', 'Ripley', 'ripley@weyland.com', '(303)-123-0103', 'Photographing events to memories', 'https://img.freepik.com/free-photo/front-view-smiley-woman-with-earbuds_23-2148613052.jpg?size=626&ext=jpg', NULL, NULL, 4);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (5, 'sarahc', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'Sarah', 'Conner', 's.conner@skynet.com', '(303)-123-0104', 'Septic Specialist at your service.', 'https://img.freepik.com/free-photo/close-up-smiley-woman-outdoors_23-2149002410.jpg?size=626&ext=jpg&ga=GA1.2.2106260043.1706544026&semt=ais', NULL, NULL, 5);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (6, 'mcclane', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'John', 'McClane', 'j.mcclane@yahoo.com', '(303)-123-0105', 'Framing is my passion', 'https://img.freepik.com/free-photo/portrait-cheerful-handsome-man-plaid-shirt-looking-camera-smiling-grey-wall_176420-3399.jpg?size=626&ext=jpg', NULL, NULL, 6);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (7, 'bobsae', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'Bob', 'Sae', 'b.sae@yahoo.com', '(303)-123-0106', 'Painting life in your living spaces since 2005', 'https://img.freepik.com/free-photo/school-scene-with-queer-teens_23-2150379383.jpg?size=626&ext=jpg', NULL, NULL, 7);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (8, 'jimbob', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'Jimmy', 'Roberts', 'j.bob@yahoo.com', '(303)-123-0106', 'Professional in the Shocky shocky', 'https://img.freepik.com/free-photo/man-having-video-call-with-his-family_23-2149120895.jpg?size=626&ext=jpg&ga=GA1.2.2106260043.1706544026&semt=ais', NULL, NULL, 8);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (9, 'carlh', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'Carl', 'Hugs', 'c.hugs@yahoo.com', '(303)-123-0107', 'Landscaping dreams to reality', 'https://img.freepik.com/premium-photo/young-handsome-hispanic-tourist-man-with-dreadlocks-stree_251136-11360.jpg?size=626&ext=jpg&ga=GA1.2.2106260043.1706544026&semt=ais', NULL, NULL, 9);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `email`, `phone`, `biography`, `image_url`, `create_date`, `update_date`, `address_id`) VALUES (10, 'trentb', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Provider', 'Trent', 'Brown', 't.brown@yahoo.com', '(303)-123-0108', 'Bricks ! build houses up like legos.', 'https://img.freepik.com/premium-photo/natural-snap-aka-no-editing_1048944-17904195.jpg?size=626&ext=jpg&ga=GA1.1.2106260043.1706544026&semt=ais', NULL, NULL, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `provider`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (1, 'Gills Handy Service', 'Gills@Example.com', '(564)-456-4582', 20.0, NULL, NULL, 1, 2, 2, NULL, NULL);
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (2, 'Sarah\'s Septic', 'Septic@Example.com', '(564)-456-4454', 22.0, NULL, NULL, 1, 3, 5, 'Sarah\'s Septic for Stinky Situations ', NULL);
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (3, 'John\'s Framing Co', 'Johnframing@Example.com', '(548)-696-4582', 30.0, NULL, NULL, 1, 4, 6, 'John\'s Framing at your service since 1999', NULL);
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (4, 'Bob Photography and Paint', 'PhotoBob@Example.com', '(303)-456-5825', 19.0, NULL, NULL, 1, 5, 7, NULL, NULL);
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (5, 'Jim\'s Shocky Shocky', 'JimShocky@Example.com', '(303-457-1152', 54.0, NULL, NULL, 1, 6, 8, NULL, NULL);
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (6, 'Friendly Neighborhood Lawncare', 'LawncareCarl@example.com', '(303)-125-5596', NULL, NULL, NULL, 1, 7, 9, NULL, NULL);
INSERT INTO `provider` (`id`, `company`, `email`, `phone`, `rate_per_hour`, `create_date`, `update_date`, `enabled`, `address_id`, `user_id`, `description`, `logo_url`) VALUES (7, 'Crystal Lake Masonry', 'Crystallake@Example.com', '(546)-588-4568', NULL, NULL, NULL, 1, 8, 10, NULL, NULL);

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
INSERT INTO `trade` (`id`, `name`, `create_date`, `update_date`, `image_url`) VALUES (16, 'Photography', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (1, 'Kitchen Remodel', 'I need a remodal', NULL, NULL, 0, 1, NULL, NULL, 0, NULL, 1, 10000, NULL);
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (2, 'Tree Removal', ' i need a tree removed from my front yeard', NULL, NULL, 0, 1, NULL, NULL, 0, NULL, 2, 2500, NULL);
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (3, 'Wedding Photographer Needed', 'I need a photographer to photograph my wedding', NULL, NULL, 1, 1, NULL, NULL, 1, NULL, 3, 800, NULL);
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (4, 'Living Room Needs Paint', 'My living rooms needs a new color', NULL, NULL, 0, 1, NULL, NULL, 0, NULL, 2, 2000, NULL);
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (5, 'Exterior Home Re-Paint ', 'Trying to give my home a new look', NULL, NULL, 0, 1, NULL, NULL, 1, NULL, 2, 3000, NULL);
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (6, 'Septic Tank Drain', 'I need my septic tank drained', NULL, NULL, 1, 1, NULL, NULL, 1, NULL, 3, 6000, NULL);
INSERT INTO `job_post` (`id`, `title`, `description`, `create_date`, `update_date`, `complete`, `enabled`, `start_date`, `special_instructions`, `materials_provided`, `image_url`, `user_id`, `budget_max`, `bid_by`) VALUES (7, 'Light Fixture Installation', 'Looking to give my kitchen a new look ', NULL, NULL, 0, 1, NULL, NULL, 1, NULL, 2, 500, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `appointment`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `appointment` (`id`, `description`, `appointment_date`, `provider_id`, `job_post_id`) VALUES (1, 'kitchen estimate', NULL, 1, 1);
INSERT INTO `appointment` (`id`, `description`, `appointment_date`, `provider_id`, `job_post_id`) VALUES (2, 'Tree Removal estimate', NULL, 6, 2);
INSERT INTO `appointment` (`id`, `description`, `appointment_date`, `provider_id`, `job_post_id`) VALUES (3, 'Wedding  photo Estimate ', NULL, 4, 3);
INSERT INTO `appointment` (`id`, `description`, `appointment_date`, `provider_id`, `job_post_id`) VALUES (4, 'Septic tank estimate', NULL, 2, 6);

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
INSERT INTO `project_area` (`id`, `name`) VALUES (2, 'Basement');
INSERT INTO `project_area` (`id`, `name`) VALUES (3, 'Master Bathroom');
INSERT INTO `project_area` (`id`, `name`) VALUES (4, 'Master Bedroom');
INSERT INTO `project_area` (`id`, `name`) VALUES (5, 'Attic ');
INSERT INTO `project_area` (`id`, `name`) VALUES (6, 'Guest Bedroom');
INSERT INTO `project_area` (`id`, `name`) VALUES (7, 'Guest Bathroom');
INSERT INTO `project_area` (`id`, `name`) VALUES (8, 'Garage');
INSERT INTO `project_area` (`id`, `name`) VALUES (9, 'Front Deck ');
INSERT INTO `project_area` (`id`, `name`) VALUES (10, 'Rear Deck');
INSERT INTO `project_area` (`id`, `name`) VALUES (11, 'Crawl Space');
INSERT INTO `project_area` (`id`, `name`) VALUES (12, 'Master Bed-Closet');
INSERT INTO `project_area` (`id`, `name`) VALUES (13, 'Guest Bed-Closet');
INSERT INTO `project_area` (`id`, `name`) VALUES (14, 'Front Yard');
INSERT INTO `project_area` (`id`, `name`) VALUES (15, 'Back Yard');
INSERT INTO `project_area` (`id`, `name`) VALUES (16, 'Kitchen');
INSERT INTO `project_area` (`id`, `name`) VALUES (17, 'Bonus Room');
INSERT INTO `project_area` (`id`, `name`) VALUES (18, 'Living Room');
INSERT INTO `project_area` (`id`, `name`) VALUES (19, 'Other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `specialty`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (1, 'Handyman ', 1, 'General purpose handy many ', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (2, 'Framing and structural work', 4, 'Structure framing sheds,garages and more ', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (3, 'Trim and finish carpentry', 4, 'Custome door and window trim', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (4, 'Residential wiring ', 3, 'Electrical home wiring for lights, switches and outlets', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (5, 'Appliance repair', 3, 'Home appliance repairman', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (6, 'Leak prevention and repair', 2, 'CPVC/ PVC/Cooper/PEX pipe repair', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (7, 'Septic tank ', 2, 'Septic tanke repair/removal or installation ', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (8, 'Wallpaper installation and removal', 8, 'Custom wallpaper designs and more ', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (9, 'Home maintenance', 1, 'Routine home checks for abnormalities', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (10, 'Window/ Door frame ', 4, 'Repair/Removal of doors and windows ', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (11, 'Brick Structures', 15, 'Custom made brick structures like stairs, firepits and gazebos', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (12, 'Interior Painting', 8, 'Painting living spaces like bedrooms and bathrooms', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (13, 'Exterior Painting', 8, 'Outdoor paint like houses and sheds', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (14, 'Event Photography', 16, 'Photograping events and moments', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (15, 'Weed Removal ', 6, 'weeds removed from yard', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (16, 'Tree Trimming', 6, 'Trimming branches of trees for clearings or overhangs', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (17, 'Yard Trim', 6, 'Standard yard trim', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (18, 'Light Fixtures', 3, 'Light fixtures installation ', NULL);
INSERT INTO `specialty` (`id`, `name`, `trade_id`, `description`, `image_url`) VALUES (19, 'Tree Removal', 6, 'Removal of tree from root', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bid`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `bid` (`id`, `amount`, `user_provider_id`, `post_id`, `bid_date`, `provider_note`, `accepted`, `rating_by_user`, `user_comment`, `rating_by_provider`, `provider_comment`, `enabled`) VALUES (1, 9000.0, 1, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, 1);
INSERT INTO `bid` (`id`, `amount`, `user_provider_id`, `post_id`, `bid_date`, `provider_note`, `accepted`, `rating_by_user`, `user_comment`, `rating_by_provider`, `provider_comment`, `enabled`) VALUES (2, 800, 1, 3, NULL, 'I am willing to do it for you Neo . Accept my bid so we can get started', 0, 4, 'Gill is the man with the camera and the drill!', 3, 'Great working for Neo he is pretty cool', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `provider_has_trade`
-- -----------------------------------------------------
START TRANSACTION;
USE `quickfixdb`;
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (1, 1);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (2, 3);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (3, 4);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (4, 8);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (4, 16);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (5, 3);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (6, 6);
INSERT INTO `provider_has_trade` (`provider_id`, `trade_id`) VALUES (7, 15);

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
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (2, 7);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (5, 4);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (5, 5);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (5, 18);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (3, 2);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (3, 3);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (3, 9);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (3, 10);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (6, 15);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (6, 16);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (6, 17);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (6, 19);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (4, 8);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (4, 12);
INSERT INTO `provider_has_focus` (`provider_id`, `focus_id`) VALUES (4, 13);

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
INSERT INTO `job_post_has_project_area` (`job_post_id`, `project_area_id`) VALUES (2, 14);
INSERT INTO `job_post_has_project_area` (`job_post_id`, `project_area_id`) VALUES (3, 19);

COMMIT;

