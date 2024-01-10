
REPLACE INTO `Artist` (`idArtist`, `name`) VALUES (1,'Radiohead'),(2,'Billie Eillish'),(3,'French Cowboy');

INSERT INTO Account (id, email, balance) VALUES
                                             (1, 'john.doe@example.com', 1000),
                                             (2, 'jane.smith@example.com', 1500),
                                             (3, 'alice.johnson@example.com', 2000),
                                             (4, 'bob.wilson@example.com', 800),
                                             (5, 'eva.martinez@example.com', 1200),
                                             (6, 'seller1@example.com', 500),
                                             (7, 'seller2@example.com', 900),
                                             (8, 'seller3@example.com', 1200),
                                             (9, 'seller4@example.com', 300),
                                             (10, 'seller5@example.com', 700);

REPLACE INTO `Customer` (`idCustomer`, `email`, `fname`, `lname`) VALUES (1,'nicolas.herbaut@univ-paris1.fr','nicolas','herbaut');


REPLACE INTO `Location` (`idLocation`, `name`, `standingGauge`) VALUES (1,'Bordeaux Akea Arena',2000),(2,'Le Moulin (Marseille)',800),(3,'Espace 3000 (Hyères)',200);



REPLACE INTO `Vendor` (`idVendor`, `name`) VALUES (1,'fnac'),(2,'ticket master');


REPLACE INTO `Venue` (`idVenue`, `venueDate`, `idLocation`) VALUES (1,'2022-06-06',1),(2,'2022-06-06',1),(3,'2021-02-03',3),(4,'2022-01-03',2);


REPLACE INTO `VenueLineUp` (`idVenue`, `showTime`, `artist_idArtist`) VALUES (1,'20:00',1),(2,'22:00',1),(2,'19:30',2),(3,'22:00',3);


REPLACE INTO `VenueQuota` (`seatingQuota`, `standingQuota`, `vendor_idVendor`, `venue_idVenue`) VALUES (10,20,1,2),(15,50,2,1),(5,5,2,2),(0,0,2,3);


REPLACE INTO `Ticket` (`idTicket`, `seatReference`, `ticketKey`, `validUntil`, `idCustomer`, `idVendor`, `idVenue`) VALUES (1,NULL,'dummyKey',NULL,1,1,1),(2,'ABC-01','dummyKey',NULL,1,2,1);
