
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
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('뽕','http://openapi.naver.com/l?AAABWLQQ6DIBAAX7MeTYUVlgMHsfoPWlYxTREtbeLvq8kcJpPM9uX9sDA4cATUX2IGMARDD50Gul1iFDisypHZfuKaqxcfVmuDyJ40Cgxe+9AEmibZPpAJhfRV3HmysZQMsgMxnlxvXtJcJ//jvX6u7zPOvnCdYgI5LgHkXbWiUUhKmz+PgHn2mwAAAA==' ,'조심','http://shopping.phinf.naver.net/main_6521648/6521648679.2.jpg','10900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('우와','http://openapi.naver.com/l?AAABXLQQ7CIBBA0dMMSxJhCLBgQWt7D5ShNEaKiCbc3pr81Uv+60NtOFhmMAL8DIuHScI0sz4quXc+KnvQcFpbRApGo8AYdIiXaFKS6oZkUMjAcqPkcu8VpAexnv3fupeNl/Clxu/H88QtdOIlF5DrHkFetRJCW4XK/gDAzCJSiQAAAA==' ,'사과커터깈ㅋ ','http://shopping.phinf.naver.net/main_7522795/7522795459.jpg','5000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('악마사냥','http://openapi.naver.com/l?AAAB3LQQ6CMBBA0dMMS2LpkM4sugCEe1Q7UGIsFasJt5ea/NVL/usj+2Fh7IGxNA7ALRAW6ekvPXQKeKjykcS+w5aqhxzWGEYURwYb9M44rzzNs25vKISNdlXYZbYh5wS6g2Y6K29a41JH95W9vm/PExeXpY4hgp5WD/pKF0ZmpYl/lr3VBJsAAAA=' ,'','http://shopping.phinf.naver.net/main_8094991/8094991389.jpg','28500',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('뉴발뉴발','http://openapi.naver.com/l?AAABWMQQ7CIBBFTzMsiTJjgAULUXsPlKE0RooVTbi9NPmLl5e8//7y1h3cPBgLnnbwB7AX0Xpl98lrFU/uTmtLxMFoUhSDDvEYTUp4uhMbUhhE3ji53FoFPIOaxva2LmWWJfx4k4/1NeQcGsuSC+C0RMCrthbNeFD2D4OODBaJAAAA' ,'','http://shopping.phinf.naver.net/main_7993884/7993884229.4.jpg','249900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('제이나?','http://openapi.naver.com/l?AAABWLyw6DIBAAv2Y9GgprWQ4cfP4HLauYpoiUNvHvq8kcJpPM/uV8WBh7aAWY/hIzQIcwdkASDFXlSGw/YUvViw+rtUFkRxoleqedv3maZ9U8kAmlclXIPNtQSgLVgpxOrjetcamj+3Gun9v7jIsrXMcQQU2rBzWQQCEbpLv4A/hgQ5mSAAAA' ,'','http://shopping.phinf.naver.net/main_8040254/8040254860.jpg','54000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('I am a banana!','http://openapi.naver.com/l?AAABWLQQrDIBBFT6NLIeOEsQsXhZJ7TOIYQ6mx1ha8fQ3/8RYP/vsrtfuV85huvYj/pLPop3RPdEMUdoSAgYnDFFyMdl5RHIJlnapEn1oryt4VLIPrW468m8w/qWY7XyPu3MTklJVdjqDsg2AiGML5DzboDSV9AAAA' ,'Am I a banana?','http://shopping.phinf.naver.net/main_7217272/7217272145.jpg','12000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('아이맥','http://openapi.naver.com/l?AAAB2Lyw7CIBAAv2Y5Nspus/TAoQ/7Hyjb0hgpIpr070WTOUwmmedb8mHhMkLXgqG/TDBUGaBn6FtVjiT2Ffak7nJY5o5InGHS5B07f/ZmWbC9khjS6FTIsthQSgLsQc+V35u2uDbRfSQ3t/1R4+qKNDFEwHnzgBNrrflkEPELUWHCfZIAAAA=' ,'','http://cfile217.uf.daum.net/image/1505C9384DC8E7BF1FF9F2','1234',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('고드름','http://openapi.naver.com/l?AAABWLQQ6DIBAAX7MejbKrwIEDtvoPWlYxTREtbcLvq8kcJpPM/uWjGBgtDAi2gXEAjaBvl9geFFW5JDafsKXqxcVIqYnYKUmCvJPOt17NM3YPYkUCXRUOnk3IOQFaENPJ9aY1LnV0Pz7q5/Y+4+Iy1zFEwGn1gPeu16IRWjftH5YJwCqSAAAA' ,'','http://shopping.phinf.naver.net/main_5692029/5692029901.jpg','7120',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('키위','http://openapi.naver.com/l?AAABXLTQrDIBBA4dOMy4DOBHXhIuTnHraOMZQaa23B2zeFt/rgvT5cu4N1AaNgIlhnsDMYEq0Xdu90FvHg7rS2ROyNJkXBax9kMDHieCM2pNCLVDm61FoBnEBtV/+3HHkfsv9yHe7n88LdNx5yyoDbEQAXI6UdSWmUP6rFDySJAAAA' ,'','http://shopping.phinf.naver.net/main_8119542/8119542731.jpg','44650',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('반반무마니','http://openapi.naver.com/l?AAABWMQQ7CIBAAX7McSYQlwIFDte0/UJbSGClFNOH3YjKnSWbOD9XuYLnB1YI1sMxgBEyGtV7IvdNR2JO609oikjcaBQavfbgEE6NUdySDQnqWKkWXWisgJxDr4N+WPW88+y9V/jheQ26+Ec8pg1z3AHJW46ukFmh/MJHspokAAAA=' ,'','http://shopping.phinf.naver.net/main_5794537/5794537249.20120211195514.jpg','17470',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('드럼','http://openapi.naver.com/l?AAAB2LQQ7CIBAAX7McSQpU2AMHW+UfKEtpjJQimvB7q8kcJpPM/qbaLVwnQAk4/8XBNLPWC9lX2gp7ULdao1LkjVZCBa99GIKJUY43RUYJ6VmqFG1qrYA8g3AHv7eseeHZf6jy+/Y84uIb8ZwySLcGkBeNiMNozAm/v8DLTYkAAAA=' ,'','http://cfile217.uf.daum.net/image/1505C9384DC8E7BF1FF9F2','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('엄청 비싼 드럼','http://openapi.naver.com/l?AAAB2LQQ7CIBAAX7McSQpU2AMHW+UfKEtpjJQimvB7q8kcJpPM/qbaLVwnQAk4/8XBNLPWC9lX2gp7ULdao1LkjVZCBa99GIKJUY43RUYJ6VmqFG1qrYA8g3AHv7eseeHZf6jy+/Y84uIb8ZwySLcGkBeNiMNozAm/v8DLTYkAAAA=' ,'우와.... ','http://shopping.phinf.naver.net/main_7999158/7999158869.jpg','95000000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('에뛰드','http://openapi.naver.com/l?AAACWLQQ6DIBAAX7MeDcKahQOHavUftCximiJS2sTf16bJHCaTzP7mcliYRjAERsA0gBlg+IsCMzb1yGxfccvNgw9LZBDZaUKJ3pHzndchqP6GrFEq18TCwcZaM6gLyPnk9+Y1LW1yHy7tfXuecXGV2xQTqHn1oK69JikEUme+cNFZjpIAAAA=' ,'','http://shopping.phinf.naver.net/main_5872004/5872004719.20120211201558.jpg','9350',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('간지','http://openapi.naver.com/l?AAABXLQQ6DIBBA0dMMS0NhFFywkFbvMS2jmKaIljbh9rXJX73k7x8+qoPRw3AFjzAO4C14KUrN7N5xy+LJ1RnTIzJZgwoDGQqXYOdZt3dki0qTiAfPLpaSQQ+gprP/m9e0NIm+fDSP7XXiQoWbFBPoaQ2gb1bKvjOt6vAHgmmcd4kAAAA=' ,'','http://shopping.phinf.naver.net/main_8009675/8009675264.jpg','44900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('마크','http://openapi.naver.com/l?AAABXLQQ7CIBBA0dNMl6RlRqELFqDtPVCG0hgpVjTh9tbkr17yXx/em4HJgb2AI5gsOA2u72orbN5pK92Dm1FqJGKvFUkKXvkwBB0jnm7EmiT6Lu0cTaq1AFqQ89H/LWteRPZf3sV9ex64+Moipww4rwHwqod+OOOIkn7thfCJiQAAAA==' ,'','http://shopping.phinf.naver.net/main_8101639/8101639324.jpg','61500',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('에반','http://openapi.naver.com/l?AAAB2LQQ6CMBQFT/NYEmwL/V10URDuUe2HEiNUrCbcXjCZ1WTm9eFtt+g7GA1ToW/RVjCE3qEVoO40roHr/g3BUZH3xPYd11Q8eLdaG6XYk1ZCBa99uAQaR1nfFJMS0hdx49HGnBOkgxgOzjfNy1Qu/stbeV+fh5x85nKJC+QwB8hro4WkuqmM+QF2KTYzpAAAAA==' ,'','http://shopping.phinf.naver.net/main_6723856/6723856099.jpg','240000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('아스카ㅠㅠ','http://openapi.naver.com/l?AAAB2LQQ6DIBAAX7MejcJWlgMHtPoPWlYxTZFa2oTfV5vMaTLz+vBeDIwDaAW6gbGHvgFNMFroBdBwGtuBHf4NgaUql8TmHbZUPbgYpTQiO1Io0DvlfOtpnuXlhkwopKvCzrMJOSeQFsR0cL5pjUsd3Zf3+r49D7m4zHUMEeS0epBXajqFnW6x/QFQaC3rpAAAAA==' ,'','http://shopping.phinf.naver.net/main_8067469/8067469141.jpg','86000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('몇호기','http://openapi.naver.com/l?AAAB2LQQ6CMBQFT/NYEmir/V10URDuUe2HGmOpWE24vWAyq8nM68PrZjH0MBqmwdCha2AIg0MnQP1h3Bmu/zcER1XZMtt3XHL14M1qbZRiT1oJFbz2oQ00TfJ0VUxKSF/FlScbS8mQDmLcOd58T3Od/JfX+rY8dzn7wnWKCXK8B8iLJtkY2QptfsZww7qkAAAA' ,'','http://shopping.phinf.naver.net/main_7830931/7830931279.jpg','116850',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('이쁘다','http://openapi.naver.com/l?AAABWLQQrDIBAAX7MepeoGtwcPCW3+YesaQ6kx1hb8fROYwzAw+5drd3CfYJxholNoALqI1gu7T9qKeHF31l4R2ZNFjcFbH1SgGM3wQCbUxotUObrUWgEzgp4PzreseZHZ/7jK5/Y+4uIby5wymHkNYG6kUCulBrR/UFZQk4kAAAA=' ,'','http://shopping.phinf.naver.net/main_8142111/8142111547.jpg','9999',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('샤넬','http://openapi.naver.com/l?AAABXLQQ6DIBBA0dOMSxKZsYwLFtTqPWgZxTRFpLSJt69N/uolf/9IOSyMAzCCIxivwARuaOqRxb7jlpunHNaYnkg8G9IUvPGhDTzP2N1JmDT6JhaZbaw1AzrQ09n/zWtaVPJfKeqxvU5cfBWVYgKc1gB441b37cVw1/0AjI6LNYkAAAA=' ,'이쁘다잉','http://shopping.phinf.naver.net/main_8129167/8129167855.jpg','1250000',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('샤넬 귀걸이','http://openapi.naver.com/l?AAABXLQQ7CIBBA0dMMS2JhKtMFC6ztPVCG0hgpVjTh9tbkr17yXx/em4VpBNLgEKYLEIIbRW2F7TttRTy4WWMGRPZkUGHwxocuUIy6vyETKu1F2jnaVGsB7UDNR/+3rHmR2X95l/fteeDiK8ucMuh5DaCvdDL92VDXDz8lgNHTiQAAAA==' ,'사줘요오빠','http://shopping.phinf.naver.net/main_8075678/8075678159.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('쏘로굿','http://openapi.naver.com/l?AAABXLSw6CMBRG4dX8DEn6wNsOOgCk+6j2QomxVKwm7F5MzuhLzuvD++EwjTAe1mAa0AvYEVOPocPgm3oUdu+0lebBhyOyWnMwpKWOgUIU0cyz6m6ajZYqNGnn2aVaC1QP6c/+b1nz0ubw5b29b88Tl1C5zSlD+TVCXUlYSSQMXX7QWCkhkgAAAA==' ,'이쁨','http://shopping.phinf.naver.net/main_7192771/7192771876.1.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('아이폰','http://openapi.naver.com/l?AAAB2LQQ7CIBAAX7M9EgpbgQMHK/IPlKU0RkormvT3VpM5TCaZ9U3bbuF6ATOAxr84GA9xoD2MvGt7JfvKS+0etFulDCIFrVBgDCrEPuqU5HBD0ihk6PJGyebWKsgzCH/we+tcJlbChzZ2X55HnEIjVnIB6ecI0inBjeCn3pgvQw0Tp5IAAAA=' ,'파이브에스','http://shopping.phinf.naver.net/main_7209206/7209206199.20131018142437.jpg','9900',2);
insert into L3_POSTS (title,shopUrl,contents,imgUrl,price,write_user_id) values('미생','http://openapi.naver.com/l?AAABWMSw7CIBQAT/NYkraPBrpg0R/3QHmUxkgR0YTbi8msJpl5fShXDfsCs4FFwb6CQpg2Vmoi/Q5XYg+qWspJCLJKikE4K63rnfIex5sgJQa0LGTyOpSSAGcYTOPfpjMePNovZX6/nk0ethCPIQKa0wFuqu+mEdu7+wFIA8kZiQAAAA==' ,'기면 기고 아니면 아닌거야!!!!!','http://shopping.phinf.naver.net/main_8109539/8109539440.2.jpg','9900',2);


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

