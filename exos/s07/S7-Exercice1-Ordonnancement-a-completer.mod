/*
 *  Mod�lisation du probl�me d'ordonnancement de t�ches (S�rie 7 - Exercice 1, SIO, 2024-2025)
 */

/* Ensemble des t�ches � ex�cuter */
set Jobs;

/* Dates de disponibilit� des t�ches */
param ReleaseDate{i in Jobs} >=0;

/* Dates d'�ch�ance des t�ches */
param DueDate{i in Jobs} >=0;

/* Temps d'ex�cution de chaque t�che */
param ProcessingTime{i in Jobs} >=0;

/* V�rification minimale de la coh�rence des donn�es */
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
