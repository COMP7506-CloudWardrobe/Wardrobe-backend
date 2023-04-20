drop database if exists wardrobe;

create database wardrobe;

use wardrobe;

CREATE TABLE `User`
(
    `userId`   INT(11)                          NOT NULL AUTO_INCREMENT,
    `userName` VARCHAR(50) COLLATE utf8mb4_bin  NOT NULL,
    `email`    VARCHAR(100) COLLATE utf8mb4_bin NOT NULL,
    `password` VARCHAR(100) COLLATE utf8mb4_bin NOT NULL,
    PRIMARY KEY (`userId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

INSERT INTO `User` (`userName`, `email`, `password`)
VALUES ('user1', 'user1@example.com', 'password1');


CREATE TABLE `Clothes`
(
    `clothesId` INT(11)    NOT NULL AUTO_INCREMENT,
    `userId`    INT(11)    NOT NULL,
    `type`      TINYINT(1) NOT NULL,
    PRIMARY KEY (`clothesId`),
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE INDEX `idx_user_id` ON `Clothes` (`userId`);


INSERT INTO `Clothes` (`userId`, `type`)
VALUES (1, 1);

INSERT INTO `Clothes` (`userId`, `type`)
VALUES (1, 2);

CREATE TABLE `Suits`
(
    `suitId`     INT(11) NOT NULL AUTO_INCREMENT,
    `userId`      INT(11) NOT NULL,
    `topId`       INT(11),
    `bottomId`    INT(11),
    `onePieceId`  INT(11),
    `shoesId`     INT(11),
    `accessoryId` INT(11),
    PRIMARY KEY (`suitId`),
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

INSERT INTO `Suits` (`userId`, `topId`, `bottomId`, `onePieceId`, `shoesId`, `accessoryId`)
VALUES (1, 1, 2, null, null, null);


CREATE INDEX `idx_user_id` ON `Suits` (`userId`);



