drop database kgb;
CREATE SCHEMA `kgb` ;
USe kgb;

CREATE TABLE `User` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                    `FullName` varchar(100),
                    `UserName` varchar(100),
                    `HashPassword` varchar(100),
                    `Status` varchar(100),
                    `CreatedAt` Date,
                    `UpdatedAt` Date,
                    `UpdateAdminId` bigInt,
                    `Role` varchar(100),
                     PRIMARY KEY (`Id`)
);

CREATE TABLE `UserAchievement` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                               `UserId` bigInt,
                               `AchievementId` int,
                               `AchievedAt` Date,
                               PRIMARY KEY (`Id`)
);


CREATE TABLE `UserQuizHistory` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                               `UserId` bigInt,
                               `QuizId` int,
                               `TookAt` Date,
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
                    `CreatedAt` Date,
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
                    `Status` varchar(100),
                    PRIMARY KEY (`Id`)
);

CREATE TABLE `Friend` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                      `SenderUserId` bigInt,
                      `ReceiverUserId` bigInt,
                      `InvitedAt` Date,
                      `Status` varchar(100),
                      PRIMARY KEY (`Id`)
);

CREATE TABLE `Question` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                        `QuizId` int,
                        `Question` varchar(500),
                        `QuestionTypeId` int,
                        `Status` varchar(100),
                        PRIMARY KEY (`Id`)
);
CREATE TABLE `Answer` (`Id` bigInt NOT NULL AUTO_INCREMENT,
                      `QuestionId` int,
                      `Answer` varchar(500),
                      `IsCorrect` bit,
                      `status` varchar(100),
                      PRIMARY KEY (`Id`)
);



