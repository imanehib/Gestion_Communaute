README
-------------------
Ce projet Java a été développé pour résoudre des problèmes de gestion de communauté d'agglomération. Veuillez suivre les instructions ci-dessous pour configurer et utiliser l'application de manière optimale.

-------------------
# Prérequis
Java 17 et supérieur : Assurez-vous que Java 17 ou une version ultérieure est installée sur votre système.

JUnit : Les tests unitaires utilisent JUnit. Assurez-vous d'avoir configuré votre projet pour utiliser JUnit.

-------------------
# Projet d'Agglomération Java

Ce projet Java a été développé pour résoudre des problèmes de gestion de communauté d'agglomération. Suivez les instructions ci-dessous pour configurer et utiliser l'application de manière optimale.

-------------------
## Prérequis

- **Java 17 et supérieur :** Assurez-vous que Java 17 ou une version ultérieure est installée sur votre système.

- **JUnit 5 :** Les tests unitaires utilisent JUnit 5. Assurez-vous d'avoir configuré votre projet pour utiliser JUnit 5.

- **JavaFX :** L'application utilise JavaFX pour l'interface utilisateur. Assurez-vous d'avoir les bibliothèques JavaFX configurées dans votre projet.

-------------------
## Phase 1 :
-------------------
## Structure du package Phase1 
  Ce package contient les classes principales du projet phase 1 du projet. Il est structuré comme suit :
    - MainP1.java : Classe principale pour lancer l'application.
    - GestionCA.java : Classe gérant les opérations sur la communauté d'agglomération.
    - CommAgglomeration.java : Classe représentant la communauté d'agglomération avec ses villes et routes.
    - Ville.java : Classe représentant une ville dans la communauté d'agglomération.

-------------------
## Utilisation de l'application 
1. **Chargement depuis un fichier :** Lancement de l'Application : Exécutez la classe MainP1 pour lancer l'application.
2. **Ajout de Villes :** Suivez les instructions pour ajouter le nombre de villes souhaité.
3. **Ajout de Route :**  Utilisez le Menu 1 pour ajouter des routes entre les villes.
4. **Gestion des zones de Recharges :** Utilisez le Menu 2 pour ajouter ou retirer des zones de recharge.
5. **Affichage des Villes avec Zone de Recharge :** L'application affiche la liste des villes avec une zone de recharge après chaque action.
    
-------------------
## Implementation 
  Toutes les fonctions ont été bien implémentées et la gestion des erreurs a été prise en compte pour les méthodes de la phase 1.

-------------------
## Phase 2 :
-------------------
## Fonctionnalités

- **Chargement depuis un fichier :** Permet de représenter une communauté d'agglomération sous forme d'un fichier texte, évitant ainsi la saisie manuelle des villes et des routes à chaque utilisation.

- **Algorithmes d'Optimisation :**
    - *Algorithme Naïf :* Fournit une solution potentielle, mais non optimale, en ajoutant ou retirant aléatoirement des zones de recharge dans les villes.
    - *Algorithme Moins Naïf :* Amélioration de l'algorithme naïf en s'arrêtant lorsque le score (nombre de zones de recharge) ne s'améliore pas pendant plusieurs itérations.
    - *Algorithme Optimal :* Propose une solution optimale en ajoutant ou retirant des zones de recharge de manière stratégique, permettant de minimiser le nombre de zones nécessaires.

      Étapes de l'Algorithme:
        1. Lancer SolutionNaif : Avant d'optimiser, lancer une solution naïve pour établir un point de départ.
        2. Ajout Initial des Zones de Recharge : Ajouter des zones de recharge dans toutes les villes de la communauté.
        3. Tri des Villes selon le Nombre de Voisins :Classer les villes par nombre de voisins croissants dans un ensemble (Set) appelé VilleC. Cela permet de prioriser les villes avec moins de voisins.
        4. Optimisation des Zones de Recharge :Pour un nombre limité d'itérations (défini par iterationsMax), itérer à travers les villes de VilleC.
          Si une ville a actuellement une zone de recharge, la retirer temporairement et évaluer le nouveau score de la communauté.
          Si le nouveau score est inférieur au score courant, mettre à jour le score courant.
          Si le score ne s'améliore pas, rétablir la zone de recharge retirée.
          Répéter ce processus pour chaque ville de VilleC.
        5. Affichage du Résultat :Afficher le score de la solution inverse optimale.

      **Fonction Score
      La fonction score(communaute) évalue la qualité d'une configuration de zones de recharge dans la communauté d'agglomération. L'objectif est de minimiser ce score, ce qui signifie une meilleure configuration.



- **Interface Utilisateur JavaFX :** Propose un menu interactif permettant à l'utilisateur de résoudre manuellement, résoudre automatiquement, sauvegarder la solution ou quitter le programme.
-------------------
## Structure du package Phase2 
  Ce package contient les classes principales du projet phase 2 du projet. Il est structuré comme suit :
    - MainP2.java : Classe principale pour lancer l'application.
    - GestionV2.java : Classe gérant les opérations de traitement de ficher et les autres algorithmes pour repondre au critere d'accessibilite ( solution Naive, resolution manuelle et resolution automatique).
    - GestionCA.java : Classe gérant les opérations sur la communauté d'agglomération.
    - CommAgglomeration.java : Classe représentant la communauté d'agglomération avec ses villes et routes.
    - Ville.java : Classe représentant une ville dans la communauté d'agglomération.
    - Route.java : Classe représentant une route dans la communauté d'agglomération.

-------------------
## Utilisation de l'application

1. **Exécution du programme :** Lancez l'application en exécutant la classe `MainP2`. Assurez-vous d'entrer le chemin absolu d'un fichier texte représentant la communauté d'agglomération lorsqu'il vous est demandé.

2. **Chargement depuis un fichier :** Indiquez le chemin du fichier texte représentant la communauté d'agglomération lors de l'exécution du programme.

   Exemple : `votrechemin/PAA_HIBAOUI_NAGOUDI_ARROUDJ/src/test.txt`

   Chaque ligne doit être écrite sous la forme proposée dans le document PDF du projet :
   ville(A).
   ville(B).
   route(A,B).
   recharge(B).

3. **Menu Principal :** Une fois l'application démarrée, le menu principal s'affichera. Utilisez les options numérotées pour effectuer différentes actions.

  - *Option 1 : Résoudre Manuellement : Permet à l'utilisateur d'ajouter ou de retirer des zones de recharge.
  - *Option 2 : Résoudre Automatiquement : Utilise l'algorithme d'optimisation pour minimiser le nombre de zones de recharge.
  - *Option 3 : Sauvegarder : Enregistre la solution dans un fichier.
  - *Option 4 : Fin : Quitte le programme.

4. **Résolution Automatique :** Lors de la résolution automatique, l'algorithme optimal sera utilisé. Vous pouvez ajuster la variable iterationsMax dans le code source de la fonction algorithmeOptimale selon vos besoins.
  int iterationsMax = 4; //a ajuster selon vos besoin "Apres plusieur test sur differents ville, on a constate que le maximum d'iterations=4 nous permet d'avoir le score le plus optimale"

5. **Résolution Manuelle :** Si vous choisissez l'option "Résoudre manuellement", suivez les instructions à l'écran pour ajouter ou retirer des zones de recharge.

6. **Exécution des Tests**
   Assurez-vous d'avoir JUnit correctement configuré dans votre projet. Exécutez les tests en utilisant votre IDE ou en utilisant des commandes de ligne de commande spécifiques à votre environnement de développement.



-------------------
## Détails Additionnels
Le code source contient des commentaires explicatifs pour chaque méthode. Assurez-vous de consulter ces commentaires pour comprendre la logique et le fonctionnement de chaque fonction.

-------------------
## Auteurs:
Nada NAGOUDI
Imane HIBAOUI
Lyna ARROUDJ