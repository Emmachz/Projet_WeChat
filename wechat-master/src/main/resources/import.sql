INSERT INTO Region (idRegion, region)
VALUES (1, 'ile-de-france');

INSERT INTO User2 (age, idUser, vetementKg, fristName, lastName, region, idRegion)
VALUES (22, 1, '123', 'emma', 'chen', 'ile-de-france', 1);

INSERT INTO Message (idMessage, idUser2, description, region)
VALUES (1, 1, 'inondation', 'ile-de-france');

REPLACE INTO `Alert` (`idAlert`, `description`, `region`, `idRegion` )
VALUES (1,'attentat','ile-de-france', 1);

INSERT INTO `Help` (`helpId`, `region`, `moneySupport`, `timeSupport`, `clotheSupport`  )
VALUES (1,'ile-de-france',80000, 80000, 80000),(2,'haut-de-seine',80000, 80000, 80000);

INSERT INTO `Donation` (`donationId`, `description` )
VALUES (1,'famine dans le monde');

INSERT INTO `Donation_Help` (`Donation_donationId`, `helps_helpId` )
VALUES (1,1),(1,2);

