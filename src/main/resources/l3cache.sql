
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema L3CACHE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `L3CACHE` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `L3CACHE` ;

-- -----------------------------------------------------
-- Table `L3CACHE`.`L3_USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `L3CACHE`.`L3_USERS` ;

CREATE TABLE IF NOT EXISTS `L3CACHE`.`L3_USERS` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `joinDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `L3CACHE`.`L3_POSTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `L3CACHE`.`L3_POSTS` ;

CREATE TABLE IF NOT EXISTS `L3CACHE`.`L3_POSTS` (
  `post_id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `shopUrl` VARCHAR(300) NULL,
  `contents` TEXT NULL,
  `imgUrl` VARCHAR(300) NULL,
  `price` VARCHAR(100) NULL,
  `writeDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `write_user_id` INT NOT NULL,
  `post_read` INT NULL DEFAULT 0,
  PRIMARY KEY (`post_id`),
  INDEX `fk_L3_POSTS_L3_USERS1_idx` (`write_user_id` ASC),
  CONSTRAINT `fk_L3_POSTS_L3_USERS1`
    FOREIGN KEY (`write_user_id`)
    REFERENCES `L3CACHE`.`L3_USERS` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `L3CACHE`.`LIKE_POSTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `L3CACHE`.`LIKE_POSTS` ;

CREATE TABLE IF NOT EXISTS `L3CACHE`.`LIKE_POSTS` (
  `L3_USERS_user_id` INT NOT NULL,
  `L3_POSTS_post_id` BIGINT NOT NULL,
  `likeDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`L3_USERS_user_id`, `L3_POSTS_post_id`),
  INDEX `fk_L3_USERS_has_L3_POSTS_L3_POSTS1_idx` (`L3_POSTS_post_id` ASC),
  INDEX `fk_L3_USERS_has_L3_POSTS_L3_USERS_idx` (`L3_USERS_user_id` ASC),
  CONSTRAINT `fk_L3_USERS_has_L3_POSTS_L3_USERS`
    FOREIGN KEY (`L3_USERS_user_id`)
    REFERENCES `L3CACHE`.`L3_USERS` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_L3_USERS_has_L3_POSTS_L3_POSTS1`
    FOREIGN KEY (`L3_POSTS_post_id`)
    REFERENCES `L3CACHE`.`L3_POSTS` (`post_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


--test data


insert into L3_USERS (email,password) values ('test@test.com',password('test'));
insert into L3_USERS (email,password) values ('haha@haha.com',password('hahahaha'));
insert into L3_USERS (email,password) values ('gaga@naver.com',password('qwerqwgs'));
insert into L3_USERS (email,password) values ('asdfasdf@gmail.com',password('t134yrg1'));
insert into L3_USERS (email,password) values ('scv34r@naver.com',password('adf13gsd'));

insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 01','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',1);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 02','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 03','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',3);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 04','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',1);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 05','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 06','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',3);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 07','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',1);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 08','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 09','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 10','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 11','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',4);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 12','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',5);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 13','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',5);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 14','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',1);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 15','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 16','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',3);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 17','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 18','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',3);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 19','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 20','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('post 21','http://openapi.naver.com/l?AAABXLTQ6DIBBA4dMMS2JhzOCCBf7dg5ZBTFOkljbx9tXkrb7kvb+8HxamAXoFboSph76BDi9xBKYR9ShsP2kr4smHJeoQ2RtChcGTD7dgYtTtHdmg0l6knaNNtRbQDtR8dr1lzYvM/se7fGyvExdfWeaUQc9rAD1SpwipVdT+AZ9KaxCSAAAA' ,'very very good','http://shopping.phinf.naver.net/main_7655077/7655077187.20140428143017.jpg','9900',5);

insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(1,3);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(2,5);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(1,2);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(1,1);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(2,3);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(3,1);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(3,2);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(3,4);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(5,1);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(5,12);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(5,3);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(3,20);
insert into LIKE_POSTS (L3_USERS_user_id, L3_POSTS_post_id) values(2,1);

