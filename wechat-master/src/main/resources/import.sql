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

INSERT INTO `User` (`user_numeroBank`, `user_wallet`, `user_id`, `user_name`, `user_region`, `user_login`, `user_nameBank`,`user_email`,`idRegion`)
VALUES ('1234567890', '10', 1, 'LIU','paris','haliu','MyBank', 'olivialiu0916@gmail.com', 'FR-IDF');

INSERT INTO `Alert` (idAlert, description, region, idRegion )
VALUES (1,'attentat','ile-de-france', 'FR-IDF');

