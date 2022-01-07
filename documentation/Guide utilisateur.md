# Guide utilisateur : Outil de conseil à l’archivage/suppression de documents sur une machine personnelle

## Introduction

### Généralités

Ce document est à destination de toute personne voulant utiliser cette application comme aide pour la gestion de ses fichiers personnels.
Afin de mieux comprendre les fonctionnalités et utilisations de cette application, nous avons créé le présent guide.

Les objectifs du logiciel sont de présenter à l'utilisateur une liste de fichiers identifiés comme peu utiles sur son disque dur, afin qu'il puisse choisir entre les archiver ou les supprimer. Il pourra alors choisir où son fichier est déplacé, pour pouvoir effectuer lui-même les actions de suppression ou archivage : nous évitons ainsi des suppressions involontaires.

### Restriction

Ceci est le travail d'Alexandre Boulanger et Anne-Sophie Jourlin : toute copie ou utilisation non personnelle est illégale.

### Identification du logiciel

Nom du logiciel : ConseillerArchivageSuppression

Version : 1.0

Langue d'utilisation : Français

Auteurs : Alexandre Boulanger et Anne-Sophie Jourlin

### Environnement du logiciel

Compatibilité certifiée avec Windows 10
Devrait être compatible Linux et Mac

### Installation du logiciel

Pour installer le logiciel, il suffit de télécharger le fichier .jar "LDEK??????" et de l'éxecuter via l'invite de commandes avec la commande suivante
java -jar "PAPPL-jwd.jar"

## Guide de l'utilisateur

### Généralités

Nos ordinateurs sont de plus en plus envahis de documents inutiles que nous dispersons à travers supports physiques, Cloud et messagerie. Suite à un état de l'art succinct, nous avons construit cette application pour aider à détecter les fichiers inutiles, même des les endroits les plus reculés.
Toutefois nous tenons aussi pour importante la conservation de documents déterminés, c'est pourquoi nous avons mis des barrières afin d'éviter toute perte de documentation.

Nous allons présenter les différentes fonctions du logiciel ainsi que l'architecture générale de l'interface.

Nous recommandons de lancer cette application régulièrement afin que ses données soient plus affinées.

### Fonctions du logiciel

Lors d'une utilisation normale, l'utilisateur a la possibilité de définir sur quels critères il base le plus la recherche de fichiers inutiles, quels dossiers sont examinés, et debloquer l'accès à des dossiers donnés. Nous reviendrons sur le détail du fonctionnement de cela dans une partie dédiée.
De plus, nous cherchons à cibler des documents personnels et non des fichiers d'application. L'utilisateur peut choisir quelles extensions sont retenues par l'application.

L'utilisateur fait ensuite le choix de quels critères vont être prépondérants lors du calcul de score.

Enfin, lorsque le score a été calculé suivant les critères fixés, le logiciel fourni à l'utilisateur une liste de fichiers qu'il recommande soit de supprimer, soit d'archiver. Si l'utilisateur décide de protéger un fichier particulier d'être présenté par l'application, il peut le définir à ce moment là, et cela restera enregistré pour la prochaine utilisation.


### Exemples d'utilisation

On peut regarder tous les fichiers de type pdf, txt, docx qui sont des fichiers utilisés par exemple dans des cours dont on n'a plus l'utilité direct. Ainsi en ajoutant tous les chemins d'accès dans lesquels sont stockés les fichiers de cours (téléchargement et/ou autre) et en indiquant les extensions traitées, on peut voir quels fichiers sont à stocker.

### Message d'erreur et problèmes connus

Nous avons rencontré un problème une fois, lors d'une présentation, ce problème permettait de lancer la suppression et l'archivage sans avoir définit de dossier.
Cependant, nous n'avons jamais réussi à le reproduire.
