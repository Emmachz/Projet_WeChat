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
       ('FR-PAC', 'provence-alpes-cote-dazur'),
       ('FR-ALL', 'all');

INSERT INTO `Alert` (idAlert, description, region, idRegion )
VALUES (1,'attentat','ile-de-france', 'FR-IDF');

INSERT INTO `User` (`user_numeroBank`, `user_wallet`, `user_id`, `user_name`, `user_login`, `user_nameBank`,`user_email`,`id_region`)
VALUES ('1234567890', '10', 1, 'LIU','haliu','MyBank', 'olivialiu0916@gmail.com', 'FR-IDF');

INSERT INTO `User` (`user_numeroBank`, `user_wallet`, `user_id`, `user_name`, `user_login`, `user_nameBank`,`user_email`,`id_region`)
VALUES ('2345678901', '30', 2, 'OUNISSI','myounissi','YesBank','Haoyue.Liu@etu.univ-paris1.fr', 'FR-IDF');

INSERT INTO `ExternalSeller` (`id_ES`, `login_seller`, `seller_nameBank`, `seller_numeroBank`)
VALUES (1, 'jouetClub', 'MyBank', '9345635401');

INSERT INTO `ExternalSeller` (`id_ES`, `login_seller`, `seller_nameBank`, `seller_numeroBank`)
VALUES (2, 'delveroo', 'YesBank', '3556354014');