## Objectifs du système à modéliser

L'objectif de ce projet Java Apache Camel est de concevoir un système de gestion WeChat qui offre aux utilisateurs la possibilité d'effectuer des transactions financières, telles que des versements entre utilisateurs, de faire des dons et des paiements au vendeur. Le système de gestion WeChat offre aussi la possibilité au Gournement de faire des alertes aux utilisateurs selon leurs régions.

Pour tout transfert financières, chaque Utilisateur posède deux portefeuille, un portfeuille sur Wechat et un autre dans sa banque externe (son compte bancaire). Le système doit prendre en considération le solde du portefeuille Wechat de l'émetteur en premier tant, permettant ainsi d'effectuer le versement directement, si le solde est suffisant. Dans le cas contraire, la demande de versement sera acheminée vers son compte bancaire et si celle si n'est pas aussi sufisante wechat renvoie un message. Pour une trasaction bien réalisé, le système doit envoyé aux utilisateurs, des notifications par e-mail confirmant le succès d'un versement. 

De plus, le système doit prendre en charge la fonctionnalité permettant au gouvernement d'envoyer des alertes et des demandes de donationsaux utilisateurs de WeChat selon la region. Les utilisateurs correspondant aux régions demandé ont également la possibilité d'envoyer des dons, tels que des vêtements et de l'argent, selon les besoins.

## Effectuer un paiement par WeChat : 
### Exigences fonctionnelles
Tout utilisateur ou vendeur WeChat doit relier son compte WeChat à un numéro de compte en banque et un nom de banque pour effectuer des actions monétaires sur WeChat.
Un vendeur, inscrit en tant que vendeur externe sur WeChat, peut faire payer un acheteur par WeChat si ce dernier possède un compte sur WeChat.
Une vente déclarée par un vendeur ne sera prise en compte par le système qu’après la confirmation de l’utilisateur relié à cette vente.
Si un acheteur ne possède pas le montant requis sur son portefeuille WeChat, le système débite automatiquement son compte en banque.

### Exigences non fonctionnelles
Tous les échanges monétaires effectués par WeChat avec les banques doivent être fiables.

<img width="620" alt="Capture d’écran 2024-01-15 à 03 34 39" src="https://github.com/Emmachz/Projet_WeChat/assets/112880851/9398a7c8-1328-40e0-bf7c-b9bacfd0f8ff">



## Effectuer un virement sur WeChat
### Exigences fonctionnelles
Le master DOIT permettre réaliser l'opération de versement en cas de solde de WeChat suffisant.
La banque DOIT permettre de réaliser l'opération de versement en cas de solde de WeChat insuffisant.
Le master DOIT informer le User en cas de succès de versement par email.

<img width="556" alt="Capture d’écran 2024-01-15 à 03 35 25" src="https://github.com/Emmachz/Projet_WeChat/assets/112880851/0cb49b26-c7c4-42b0-baa7-49035abab6a6">

## Envoyer une alerte aux utilisateurs WeChat
### Exigences fonctionnelles
Le gouvernement DOIT alerter sur WeChat les utilisateurs lorsqu’il y a un événement majeur.
Les utilisateurs DOIVENT recevoir uniquement les alertes qui concernent leur région. 
Le master DOIT envoyer un alerte à une région existante dans la France métropolitaine.
Le gouvernement DOIT pouvoir envoyer une alerte à une seule région.
Le gouvernement DOIT pouvoir envoyer une alerte à toutes les régions de la France métropolitaine.
### Exigences non fonctionnelles
Lors de l’envoie d’une alerte, le gouvernement doit informer tous les utilisateurs concernés de façon fiable.

<img width="515" alt="Capture d’écran 2024-01-15 à 03 36 35" src="https://github.com/Emmachz/Projet_WeChat/assets/112880851/1934c479-a942-4e7f-93f3-89489f46f9c6">

## Effectuer des donations sur Wechat 
### Exigences fonctionnelles
Pour aider une région, le gouvernement DOIT créer une demande de donations et la lier à une région sur WeChat.
Le master DOIT envoyer la demande de donations à toutes les régions, à l'exception de la région impactée.
Pour répondre à une demande de don, l’utilisateur DOIT effectuer un don depuis WeChat.
Si le don de l’utilisateur est de l’argent, il DOIT faire un versement au gouvernement. 
Si la quantité demandée est atteinte, le master DOIT envoyer une demande d’update au gouvernement.
### Exigences non fonctionnelle
Les donations effectuées sur WeChat doivents être fiables.

<img width="497" alt="Capture d’écran 2024-01-15 à 03 37 30" src="https://github.com/Emmachz/Projet_WeChat/assets/112880851/d76e0ef1-c1ab-4ac8-b610-83db5a5a07aa">


si c’est une donation d’argent le système exécute le même processus que le versement d’argent 
