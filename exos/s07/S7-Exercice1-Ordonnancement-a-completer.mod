/*
 *  Modélisation du problème d'ordonnancement de tâches (Série 7 - Exercice 1, SIO, 2024-2025)
 */

/* Ensemble des tâches à exécuter */
set Jobs;

/* Dates de disponibilité des tâches */
param ReleaseDate{i in Jobs} >=0;

/* Dates d'échéance des tâches */
param DueDate{i in Jobs} >=0;

/* Temps d'exécution de chaque tâche */
param ProcessingTime{i in Jobs} >=0;

/* Vérification minimale de la cohérence des données */
check{i in Jobs}: ReleaseDate[i] + ProcessingTime[i] <= DueDate[i];

data;

param : Jobs : ReleaseDate DueDate ProcessingTime := 
  1   0   25  4
  2   3   25  9
  3   6   20  8;
 
############################################

# param : Jobs : ReleaseDate DueDate ProcessingTime := 
#   1   0   50  15
#   2   3   25  12
#   3   0   40   8
#   4   9   20   3
#   5  28   45   7;
 
end;
