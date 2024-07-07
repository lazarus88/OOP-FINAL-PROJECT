#drop database kgb;
drop schema  kgb
CREATE SCHEMA `kgb` ;
USe kgb;

CREATE TABLE `User` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                    `FullName` varchar(100),
                    `UserName` varchar(100),
                    `HashPassword` varchar(100),
                    `Status` varchar(100) DEFAULT 'ACTIVE',
                    `CreatedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    `UpdatedAt` TIMESTAMP DEFAULT null ON UPDATE CURRENT_TIMESTAMP,
                    `UpdateAdminId` bigInt,
                    `Role` varchar(100),
                     PRIMARY KEY (`Id`)
);

CREATE TABLE `UserAchievement` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                               `UserId` bigInt,
                               `AchievementId` int,
                               `AchievedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (`Id`)
);


CREATE TABLE `UserQuizHistory` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                               `UserId` bigInt,
                               `QuizId` int,
                               `TookAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               `Duration` bigInt,
                               `Score` int,
                               `Status` varchar(100),
                               `IsPractice` bit,
                               PRIMARY KEY (`Id`)
);

CREATE TABLE `Mail` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                    `SenderUserId` bigInt,
                    `ReceiverUserId` bigInt,
                    `Message` varchar(100),
                    `MailTypeId` int,
                    `CreatedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    `Status` varchar(100),
                    PRIMARY KEY (`Id`)
);

CREATE TABLE `Quiz` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                    `QuizName` varchar(100),
                    `CreatorUserId` bigInt,
                    `IsRandom` bit,
                    `IsOneVsMultiple` bit,
                    `IsImmediate` bit,
                    `IsPracticeEnable` bit,
                    `QuizTypeId` int,
                    `Status` varchar(100) DEFAULT 'ACTIVE',
                    PRIMARY KEY (`Id`)
);

CREATE TABLE `Friend` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                      `SenderUserId` bigInt,
                      `ReceiverUserId` bigInt,
                      `InvitedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      `Status` varchar(100) ,
                      PRIMARY KEY (`Id`)
);

CREATE TABLE `Question` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                        `QuizId` int,
                        `Question` varchar(500),
                        `QuestionTypeId` int,
                        `Status` varchar(100) DEFAULT 'ACTIVE',
                        PRIMARY KEY (`Id`)
);
CREATE TABLE `Answer` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                      `QuestionId` int,
                      `Answer` varchar(500),
                      `IsCorrect` bit,
                      `status` varchar(100) DEFAULT 'ACTIVE',
                      PRIMARY KEY (`Id`)
);


INSERT INTO User (FullName,UserName, HashPassword)
VALUES ('Nata Tatikishvili', 'Tatika', 'luboiRagaca');

INSERT INTO User (FullName,UserName, HashPassword)
VALUES ('test', '11', '11');

INSERT INTO User (FullName,UserName, HashPassword)
VALUES ('lazo pachuliani', 'lestanberi', 'luboiRagaca2');

INSERT INTO Friend (SenderUserId, ReceiverUserId, InvitedAt, Status)
VALUES (1, 2,'2024-07-07' , 'friends');

INSERT INTO User (FullName,UserName, HashPassword)
VALUES ('kote qerdiyoshvili', 'kostia', 'luboiRagaca3');

INSERT INTO Friend (SenderUserId, ReceiverUserId, InvitedAt, Status)
VALUES (3, 1,'2024-01-01' , 'no longer friends');
use kgb