#drop database kgb;
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


INSERT INTO User (FullName,UserName, HashPassword,
                    Status, CreatedAt, UpdatedAt,
                    UpdateAdminId, Role)
VALUES ('Nata Tatikishvili', 'Tatika', 'luboiRagaca', 'active', '2024-07-05',  '2024-07-10', 4, 'momxmarebeli');

INSERT INTO User (FullName,UserName, HashPassword,
                  Status, CreatedAt, UpdatedAt,
                  UpdateAdminId, Role)
VALUES ('lazo pachuliani', 'lestanberi', 'luboiRagaca2', 'active', '2024-07-05',  '2024-07-15', 3, 'momxmarebeli');

INSERT INTO Friend (SenderUserId, ReceiverUserId, InvitedAt, Status)
VALUES (1, 2,'2024-07-07' , 'friends');

INSERT INTO User (FullName,UserName, HashPassword,
                  Status, CreatedAt, UpdatedAt,
                  UpdateAdminId, Role)
VALUES ('kote qerdiyoshvili', 'kostia', 'luboiRagaca3', 'active', '2023-08-05',  '2023-09-15', 3, 'momxmarebeli');

INSERT INTO Friend (SenderUserId, ReceiverUserId, InvitedAt, Status)
VALUES (3, 1,'2024-01-01' , 'no longer friends');
INSERT INTO Mail (SenderUserId, ReceiverUserId, Message, MailTypeId, CreatedAt, Status)
VALUES (3, 5, 'hello my friend' , 3, null, 'SENT');
INSERT INTO Mail (SenderUserId, ReceiverUserId, Message, MailTypeId, CreatedAt, Status)
VALUES (4, 5, 'multiple_choice' , 2, null, 'SENT');