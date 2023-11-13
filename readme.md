## Objectifs du système à modéliser

L'objectif de la modélisation consiste à créer un système de gestion du trafic RATP avec la capacité de prendre en charge plusieurs utilisateurs ainsi qu'un administrateur RATP. Le système principal gère les abonnements aux différentes lignes, fournit et consulte des informations en temps réel, génère des alertes personnalisées et supervise la réception des alertes. Les utilisateurs ont la possibilité de signaler des incidents tels que des accidents, la présence de contrôleurs ou des ralentissements au système principal. Si plus de 10 notifications sont transmises en moins de 30 minutes, le système principal envoie une demande de confirmation de ces notifications. En cas de confirmation, le système principal envoie une alerte à tous les utilisateurs abonnés à la ligne concernée. De plus, la RATP peut émettre des alertes directement notifiées aux utilisateurs abonnés. Les utilisateurs ont également la faculté de s'abonner ou de se désabonner d'une ligne, ainsi que d'accéder aux informations sur les horaires en temps réel.

## Interfaces

```
artist->master: POST venue
vendor->master: GET Gigs
master->vendor: Collection<Gigs>

Customer->vendor: cli:gig selection

vendor->master: jms:booking
alt booking successfull
    master->vendor: transitional tickets
    vendor->Customer: ticket purshase ok
    Customer->vendor: cli:customer informations
    
    vendor->master: jms:ticketing
    master->vendor: tickets

else booking unsuccessfull
    master->vendor: no quota for gigs
end

opt venue cancellation
    artist->master: DELETE venue
    master->vendor: jms:topic:cancellation
    vendor->Customer: smtp:cancellation email
end
```
![PHOTO-2023-11-10-18-50-59](https://github.com/Emmachz/sample-quarkus-jee-project/assets/112880851/303a5f67-0efe-47c7-a5bb-dd3cfb1844a0)


## Schéma relationnel

![](EER.png)

## Exigences fonctionnelles

* le vendor NE DOIT proposer que les concerts pour lesquels il a un quota disponible, transmis par le master.
* le vendor DOIT pouvoir effectuer les opérations de booking et ticketing
* le master DOIT permettre à l'artiste d'annuler son concert.
* le master DOIT informer le vendor en cas d'annulation de concert
* le vendor DOIT informer les clients de l'annulation du concert par mail
* le master DOIT proposer un service de validation de la clé du ticket, pour les contrôles aux entées.

## Exigences non fonctionnelles

* le booking et le ticketing, bien qu'étant des opérations synchrones, DOIVENT être fiables et donc utiliser le messaging
* Lors de l'annulation de tickets, le master DOIT informer tous les vendors de l'annulation, de façon fiable.
