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

# INSERT INTO `User` (`userName`, `email`, `password`)
# VALUES ('user1', 'user1@example.com', 'password1');


CREATE TABLE `Clothes`
(
    `clothesId` INT(11)    NOT NULL AUTO_INCREMENT,
    `userId`    INT(11)    NOT NULL,
    `type`      TINYINT(1) NOT NULL,
    PRIMARY KEY (`clothesId`),
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE INDEX `idx_user_id` ON `Clothes` (`userId`);


# INSERT INTO `Clothes` (`userId`, `type`)
# VALUES (1, 1);
#
# INSERT INTO `Clothes` (`userId`, `type`)
# VALUES (1, 2);

CREATE TABLE `Suits`
(
    `suitId`       INT(11) NOT NULL AUTO_INCREMENT,
    `userId`       INT(11) NOT NULL,
    `topId`        INT(11),
    `bottomId`     INT(11),
    `outwearId`    INT(11),
    `shoesId`      INT(11),
    `accessoryId1` INT(11),
    `accessoryId2` INT(11),
    PRIMARY KEY (`suitId`),
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE,
    FOREIGN KEY (`suitId`) REFERENCES `Suits` (`suitId`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

# INSERT INTO `Suits` (`userId`, `topId`, `bottomId`, `outwearId`, `shoesId`, `accessoryId1`, `accessoryId2`)
# VALUES (1, 1, 2, null, null, null, null);


CREATE INDEX `idx_user_id` ON `Suits` (`userId`);

CREATE TABLE `Share`
(
    `shareId`   INT(11) NOT NULL AUTO_INCREMENT,
    `userId`    INT(11) NOT NULL,
    `suitId`    INT(11),
    `shareTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `heat`      INT(11)   DEFAULT 0,
    PRIMARY KEY (`shareId`),
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE,
    FOREIGN KEY (`suitId`) REFERENCES `Suits` (`suitId`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


CREATE INDEX `idx_user_id` ON `Share` (`userId`);
CREATE INDEX `idx_suit_id` ON `Share` (`suitId`);

CREATE TABLE `Collection`
(
    `userId`    INT(11) NOT NULL,
    `shareId`   INT(11),
    `shareTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`userId`, `shareId`),
    FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE,
    FOREIGN KEY (`shareId`) REFERENCES `Share` (`shareId`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;


