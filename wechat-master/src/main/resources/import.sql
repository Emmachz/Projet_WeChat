INSERT INTO `Region` (`idRegion`, `region`)
VALUES ('FR-ARA', 'auvergne-rhone-alpes'),
       ('FR-BFC', 'bourgogne-franche-comte'),
       ('FR-BRE', 'bretagne'),
       ('FR-CVL', 'centre-val-de-loire'),
       ('FR-COR', 'corse'),
       ('FR-GES', 'grand-est'),
       ('FR-HDF', 'hauts-de-france'),
       ('FR-IDF', 'ile-de-france'),
       ('FR-NOR', 'normandie'),
       ('FR-NAQ', 'nouvelle-aquitaine'),
       ('FR-OCC', 'occitanie'),
       ('FR-PDL', 'pays-de-la-loire'),
       ('FR-PAC', 'provence-alpes-cote-dazur');

INSERT INTO User2 (age, idUser, vetementKg, fristName, lastName, region, idRegion)
VALUES (22, 1, '123', 'emma', 'chen', 'ile-de-france', 'FR-IDF');

INSERT INTO Message (idMessage, idUser2, description, region)
VALUES (1, 1, 'inondation', 'ile-de-france');

INSERT INTO `Alert` (idAlert, description, region, idRegion )
VALUES (1,'attentat','ile-de-france', 'FR-IDF');

