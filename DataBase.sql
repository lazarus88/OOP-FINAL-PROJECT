drop database kgb;
CREATE SCHEMA `kgb` ;
USe kgb;

CREATE TABLE `User` (`Id` bigInt,
                     `FullName` varchar(100),
                     `UserName` varchar(100),
                     `HashPassword` varchar(100),
                     `Status` varchar(100),
                     `CreatedAt` Date,
                     `UpdatedAt` Date,
                     `UpdateAdminId` bigInt,
                     `Role` varchar(100)
);

CREATE TABLE `UserAchivement` (
                                  `Id` bigInt,
                                  `UserId` bigInt,
                                  `AchivementId` int,
                                  `AchivedAt` Date
);


CREATE TABLE `UserQuizHistory` (
                                   `Id` bigInt,
                                   `UserId` bigInt,
                                   `QuizId` int,
                                   `TookAt` Date,
                                   `Duration` bigInt,
                                   `Score` int,
                                   `Status` varchar(100),
                                   `IsPractice` bit
);

CREATE TABLE `Mail` (
                        `Id` bigInt,
                        `SenderUserId` bigInt,
                        `ReciverUserId` bigInt,
                        `Message` varchar(100),
                        `MailTypeId` int,
                        `CreatedAt` Date,
                        `Status` varchar(100)
);

CREATE TABLE `Quiz` (
                        `Id` bigInt,
                        `CreatorUserId` bigInt,
                        `IsRandom` bit,
                        `IsOneVsMultiple` bit,
                        `IsImmidiatle` bit,
                        `IsPracticeEnable` bit,
                        `QuizTypeId` int,
                        `Status` varchar(100)
);

CREATE TABLE `Achivement` (
                              `Id` bigInt,
                              `UserId` bigInt,
                              `AchivementId` int,
                              `AchievedAt` Date
);

CREATE TABLE `Friend` (
                          `Id` bigInt,
                          `senderUserId` bigInt,
                          `ReciverUserId` bigInt,
                          `invitedAt` Date,
                          `status` varchar(100)
);



