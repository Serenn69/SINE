SINE
Projet de jeu vidéo en place depuis 10 ans maintenant. Développement du concept du jeu sous forme de mod puis plugin pour Minecraft. Tout a été réfléchi et développé par moi-même sauf la branche structureLoader (qui permet la sauvegarde/chargement des zones de jeux).

Historique du projet :

2010 : Naissance de l'idée d'un jeu d'attaque défense / CTF / RPG.

21-06-2013 : Lancement du projet "DuelZ".

22-06-2013 : Création du premier prototype, à l'époque nommé "DuelZ".

Septembre 2013 : Mise au repos de DuelZ.

Courant Avril 2016 : Dépoussiérage du projet "DuelZ" en rejoignant une équipe de promotion du ProGaming Minecraft et du e-Sport : MCPG streamé par DigiWave.

Fin Avril 2016 : Ré-étude des archives et du projet "DuelZ".

Courant Mai 2016 : Mise à jour et assimilation des nouvelles fonctionnalités de Minecraft.

Fin Mai 2016 : Décision de reprendre la map et tout le projet à zéro devant autant de changements et de nouvelles fonctionnalités sur Minecraft.

Début Juin 2016 : Un nouveau nom s'impose, après pas mal de recherches, le nouveau nom de "DuelZ" deviendra "S.I.N.E".

14-06-2016 : Début du développement du projet S.I.N.E et de sa nouvelle map.

11-10-2016 : Passage en alpha du projet S.I.N.E.

25-10-2016 : Prototype alpha de S.I.N.E terminé, lancement des phases de test, équilibrage et débogage.

Patch Notes Alpha :

25-10-2016 : _ 1ère séance de tests / débogage.

27-10-2016 : _ Mise en place d'un système de reset manuel de la map. _ L'inventaire est effacé lorsque l'on quitte une équipe via le téléporteur. _ Les joueurs ne peuvent plus drop leurs items. _ Les joueurs ne peuvent plus ramasser des flèches tirées car elles perdaient leurs tags. _ Création d'une zone spécifique au TeamHall pour que les joueurs puissent maintenant activer le bouton de lancement de la partie dans le TeamHall.

28-10-2016 : _ Ajout de la liaison entre les timers de la phase de combat et la fin de la phase de combat. _ Le timer illimité de la phase de combat ne se finira plus immédiatement mais attendra une condition de fin de phase. _ Ajout d'un délai plus long à la fin du chargement de la zone de jeu avant de reprendre le compte à rebours du lancement de partie. _ Configuration des TP en fin de phase de préparation pour la zone de combat en fonction de la taille de la map.

29-10-2016 : _ Correction de l'affichage du message d'erreur du manque de points pour l'achat d'un item lors de l'achat de celui-ci quand on avait pile le nombre de points requis. _ Correction du nombre d'échelle acheté : 1 au lieu de 2. _ Correction du bug qui faisait instantanément disparaître le drapeau dans l'inventaire dès qu'il était récupéré.

02-11-2016 : _ Traduction de la map et du jeu en anglais.

04-11-2016 : _ 2ème séance de tests / débogage.

05-11-2016 : _ Réflexion sur les pièges pour la future version 1.2 les incluant.

06-11-2016 : _ Correction du bug qui bloquait le timer de la mise en place de la défense à 10 secondes. _ Les valeurs de réussite ou défaite des conditions "Perfect defense" et "Blitzkrieg" n'étaient pas correctement mises à jour en fonction de l'équipe attaquante et de l'équipe défensive. _ Correction du bug d'affichage du panneau de récapitulatif des golds gagnés à chaque phase. _ Traduction des panneaux récapitulatif des golds en fin de phase. _ Correction du bug des panneaux de "Blitzkrieg" et de "Taken Flag" qui validait les conditions pour les défenseurs. _ Correction de l'affichage des panneaux récapitulatif des points en fin de phase pour le round "Red Atk & Green Def" qui ne s'affichaient pas au bon endroits. _ Traduction des équipes lors du crédit des points en fin de phase. _ Mise à jour du reset manuel : ajout clear sidebar / ajout des variables pour les pièges / ajout kill des pièges encore existants. _ Suppression du reset manuel de la variable des golds d'équipe rouge et verte car elle faisait redondance avec le reset de la variable de sélection du multiplicateur de golds. _ Suppression du reset manuel des variables de la condition "Blitzkrieg" et "Perfect def." rouge et verte car elle faisait redondance avec la variable de choix des attaquants et des défenseurs en début de partie. _ Suppression du reset manuel de la variable faisant alterner les phases d'attaque et de défense rouge et verte car elle faisait redondance avec la variable de choix des attaquants et des défenseurs en début de partie. _ Correction de l'annonce de la prise du drapeau et de son renvoi à la base, elle prendra plus tous les joueurs en cible mais uniquement celui qui a effectué l'action. _ Annulation de l'impossibilité pour les joueurs de droper leurs items, maintenant ils pourront le faire et ainsi faire des échanges. _ Ajout d'un délai encore plus long à la fin du chargement de la zone de jeu avant de reprendre le compte à rebours du lancement de partie. _ Les points à la fin de la phase de combat seront maintenant correctement crédités. _ Traduction des annonces d'entrées et de sorties d'équipe. _ L'inventaire est clear lorsque l'on rejoint une équipe via le téléporteur. _ Correction d'un bug empêchant le reset de la fin de la phase de combat et bloquant ainsi la suite des événements. _ Correction d'un bug d'affichage du score, celui des verts s'affichait en doublon.

08-11-2016 : _ Début de développement de S.I.N.E sous forme de plugin.

11-02-2017 : _ Release de la version Plugin Alpha v0.1 de S.I.N.E !

21-02-2017 : _ Intégration des beacons buffer.

24-02-2017 : _ Location, configuration et lancement du serveur SINE Alpha Test ! _ 1ère Alpha Session pour S.I.N.E en Alpha v0.1 !

25-02-2017 : _ Correction du mot "Blitzkrieg" sur le cooldown Caster, il manquait le "z". _ Correction du bug qui empêchait de modifier le montant des golds d'équipe au lancement de la partie.

27-02-2017 : _ Amélioration et mise en service des commandes de rôle Admin, Caster et Spec. _ Changement du prix des blocs de muraille, plus d'écarts ont été mis entre chaque "stade" de bloc muraille. _ Les joueurs pouvaient infliger des dégâts aux PNJ et les tuer, ce ne sera plus le cas ! _ Correction des chiffres du compte à rebours du blitzkrieg pour le caster qui ne s'écoulait pas correctement. _ La régénération naturelle due à la saturation ne sera plus un problème, celle-ci a été supprimée.

28-02-2017 : _ La barre des news ne disparaissaient pas au lancement de la partie pour le caster et les specs, c'est chose faites ! _ Il n'est plus possible d'utiliser d'enderpearl en tant que porteur de drapeau ! _ Il n'est plus possible de poser des blocs murailles collés au drapeau, la zone est élargie d'un bloc. _ Les joueurs pouvaient poser des blocs dans la zone attaquant, cela a été corrigé. _ On peut désormais poser les seaux d'eau sur : Grass, Stone, Planks, Stonebrick, Ironblock et Endstone. Attention le seau vide sera effacé et l'eau restera tant que sa source ne sera pas retiré. _ Maintenant le caster et les specs verront les joueurs de chaque équipe de la bonne couleur (Tracker, tab liste & au dessus des têtes). _ Les joueurs réapparaîtront de couleur normal sur les trackers au début de la phase de préparation suivante et non plus au moment de leur respawn.

02-03-2017 : _ Création d'un resource pack pour S.I.N.E afin de modifier quelques textures d'items pour l'Admin menu et les futurs pièges. _ Suppression de la texture des Armor stand afin de ne plus les voir en caster ou spectateur.

04-03-2017 : _ Implémentation d'un Admin Menu afin de gérer le jeu et les joueurs, en voici les fonctionnalités : _ Gestion des joueurs : _ Freeze/Unfreeze tous les joueurs. _ Sélection d'un joueur précisément afin de gérer plusieurs choses pour ce dernier : _ Changer/Intégrer une équipe _ Effacer tout son inventaire _ Effacer uniquement ses armures _ Tuer le joueur _ Bannir le joueur pour différentes durées (10 min / 2 heures / 1 jour / 1 semaine / 1 mois ou à vie) _ Gestion des golds d'équipe : _ Ajouter/Retirer 1, 5, 20, 100 golds à une équipe _ Gestion des rounds du jeu : _ Mettre fin à la phase de préparation, de défense ou de combat prématurément _ Gestion des scores d'équipe : _ Ajouter/Retirer 1 point à une équipe. _ Gestion de la map de jeu : _ Reload la zone de jeu en fonction de la map choisie et de sa taille _ Reload des bases d'attaque (rouge ou verte) _ Reload des drapeaux (rouge ou vert) dans sa base de défense respective _ Gestion de la météo du jeu : _ Programmer le jour ou la nuit, le soleil ou la pluie _ Menu de téléportation pour se rendre aux différents endroits important du jeu : _ Hall _ Salle des paramètres _ Salle des règles _ Salle de debriefing des scores pour le caster _ Logo S.I.N.E dans le ciel _ Magasins rouge / Magasins vert _ Base de défense rouge / Base de défense verte _ Base d'attaque rouge / Base d'attaque verte _ Salle de debriefing des scores rouges / Salle de debriefing des scores verts

05-03-2017 : _ Les joueurs ne devraient plus pouvoir utiliser d'enderpearl en dehors des zones de combat (par exemple au respawn...)

06-03-2017 : _ Mise en place de la fin de phase de combat si une des 2 équipes est complètement éliminée, plusieurs cas de figure se présente alors : _ Si tous les attaquants sont morts, les défenseurs remportent la phase _ Si tous les défenseurs sont morts, les attaquants ont droit au "Last Try", il s'agit de 30 secondes pour s'emparer du drapeau et le capturer (attention il y a 10 secondes de délai de capture). _ Si les attaquants réussissent la capture dans le délai du "Last Try", ils remportent la phase. _ Si les attaquants ne réussissent pas la capture durant le "Last Try" MAIS ont réussi à prendre le drapeau, il s'agit alors d'un "draw" _ Si les attaquants ne réussissent pas la capture durant le "Last Try" ET n'ont pas réussi à prendre le drapeau, les défenseurs remportent la phase.

_ Passage de la version Alpha de S.I.N.E en v0.2 !

10-03-2017 : _ 2nde Alpha Session pour S.I.N.E en Alpha v0.2 !

12-03-2017 : _ L'ajout d'un joueur dans une équipe via le menu Admin ne l'ajoutait pas au Tracker, c'est corrigé. _ En plus d'ajouter le joueur à une équipe via le menu Admin, maintenant il le téléportera à un joueur aléatoire de son équipe. _ Ajout d'un refresh/update de l'inventaire du Caster pendant la phase de préparation juste en ouvrant ce dernier. _ Les trackers devraient correctement afficher les joueurs vivants et morts maintenant. _ A la fin de la phase de combat, tous les joueurs seront désormais soignés au maximum. _ Avec un cooldown la phase de combat n'était jamais stoppé, c'est résolu. _ La barre de cooldown du "Last Try" et du "Blitzkrieg" ne disparaissaient ni à leur fin ni à lors de la résolution d'un événement qui devait les supprimer, ce sera maintenant le cas. _ L'attribution de l'objectif secondaire "Blitzkrieg" devrait maintenant être correctement attribué. _ Les statistiques du round précédent qui s'affiche pour le caster en début de phase de préparation s'effaceront désormais avant d'afficher les nouvelles statistiques. _ Les panneaux d'affichage de score dans les arènes ne devraient plus afficher de chiffres incohérent. _ L'annonce de la prise du drapeau par un attaquant s'affichait mal et ne se stoppait pas, cela est résolu. _ En changeant de rôle puis en redevenant Caster (via les commandes), ce dernier perdait sa barre de raccourci avec, entre autre, le possibilité d'ouvrir le menu d'achats des joueurs de la phase de préparation, cela est résolu. _ Certaines variables et certains cooldowns n'étaient pas correctement stoppaient, ce qui pouvait entraîner une "superposition" des rounds sur le long terme, cela est résolu.

13-03-2017 : _ Mettre fin à la phase de combat via le menu Admin devrait désormais correctement fonctionner. _ Le délai, l'affichage du message ainsi que l'arrêt de la capture du drapeau à l'autel des attaquants avait pas mal de soucis, le système a été revu, repensé et complètement changé pour activer et stopper cette action. _ Le Friendly Fire sera désormais correctement activé ou désactivé via la salle des paramètres. _ Désormais les cooldowns de la phase de combat seront pris en compte, les mise à jour, l'arrêt des tâches ainsi que le retrait des différentes barres de cooldowns sont effectifs. _ Le Caster et les Specs auront désormais la vision nocturne en cas de partie de nuit. _ L'arène RUINS rejoint les arènes du jeu mais n'existe pour le moment qu'en version SMALL (3vs3). _ Les points de vie des joueurs s'affichent désormais au-dessus de leur tête. _ Correction du bug d'affichage lors de l'annonce des scores à la fin de la phase de combat, les scores d'attribution de gain ou perte du round s'afficheront correctement. _ Changement d'attribution de points pour la victoire ou la défaite d'un round : Gagner donnera toujours 100 points, mais le perdre en donnera désormais 50 et finir sur un "Draw" attribuera 50 points aux 2 équipes. _ Les attaquants pouvaient poser des blocs de murailles dans la base des défenseurs, cela ne sera plus le cas ! _ Les enchantements "Sharpness 4 et 5", "Power 4 et 5" ainsi que "Flame" sont retirés du jeu à cause de leur puissance. _ Le tarif de la potion de Force a été augmenté (x3), il passe de 6 à 18 golds.

14-03-2017 : _ Désormais selon votre rôle dans la partie (Admin, Caster, Spec, Équipe Rouge ou Équipe Verte), ce dernier sera signalé lorsque vous parlerez dans le chat.

17-03-2017 : _ Le "End Game" a été ajouté au jeu, maintenant une fois qu'une équipe atteindra le score de 3, la partie se finira à la fin de la phase de fight et je vous laisse découvrir ce qu'il se passera alors ! ;)

_ Passage de la version Alpha de S.I.N.E en v0.3 !

18-03-2017 : _ Achat de l'hébergement et des domaines "sine-game.com" et "sine-game.fr" pour le futur site web de S.I.N.E.

06-06-2019 : _ Reprise du projet SINE.

09-06-2019 : _ Mise à jour en 1.14 mais beaucoup trop de changements de fonctionnalités et le temps personnel manque. _ Le projet repart de zéro suite à beaucoup de changements de fonctionnalités sur Minecraft et afin de se refaire la main en Java avant l'arrivée du futur support de SINE : Hytale. _ Changement du nom de projet pour SINE – Hytale.

16-11-2020 : _ Début de formation de Concepteur et Développeur d'Applications afin de renforcer et confirmer les bases présentes + augmenter ses compétences. _ Projet S.I.N.E toujours en veille mais pas abandonné !