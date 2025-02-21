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

/* Variables de d�cision : date de d�but d'ex�cution de chaque t�che */
var ProcessingDate{i in Jobs} >= ReleaseDate[i], <= DueDate[i] - ProcessingTime[i];

/* Variable auxiliaire : borne sup sur les dates de fin d'ex�cution des t�ches */ 
var MaxFinishDate >= 0;

/* Fonction objectif : minimiser la date de fin d'ex�cution de la derni�re t�che � terminer (makespan) */
minimize
  Makespan: MaxFinishDate;

/* Lin�arisation de la fonction objectif : max des dates de fin de traitement */
subject to
  MaxFinishDateConstraint{i in Jobs}: MaxFinishDate >= ProcessingDate[i] + ProcessingTime[i];

/* La fin de l'ex�cution d'une t�che nMaxFinishDatee doit avoir lieu apr�s sa date d'�ch�ance (mis comme borne sup. dans la d�finition des variables */
# subject to
#   DueDateConstraint{i in Jobs}: ProcessingDate[i] + ProcessingTime[i] <= DueDate[i];

/* Variables auxiliaires : Before[i,j] = 1 si la t�che i est ex�cut�e avant la t�che j, 0 sinon */
var Before{i in Jobs, j in Jobs} binary;

# La d�finition suivante est plus pr�cise mais ne change rien dans les faits car les variables inutiles ne sont pas g�n�r�es */
# var Before{i in Jobs, j in Jobs: i < j} binary;

# /* Constante utilis�e pour la lin�arisation des contraintes "i avant j" ou "j avant i" */
# param MaxDueDate := max{i in Jobs} DueDate[i];

# subject to
#   /* Pour chaque paire de t�ches diff�rentes {i,j}, 
#      * soit la t�che i est ex�cut�e en premier (et sa date de fin d'ex�cution est inf�rieure � la date de d�but d'ex�cution de j)
#      * soit c'est j qui est ex�cut�e en premier
#      */
#   BeforeCase1{i in Jobs, j in Jobs: i < j}: ProcessingDate[i] + ProcessingTime[i] - ProcessingDate[j] <= MaxDueDate*(1-Before[i,j]);
#   BeforeCase2{i in Jobs, j in Jobs: i < j}: ProcessingDate[j] + ProcessingTime[j] - ProcessingDate[i] <= MaxDueDate*Before[i,j];

# Variante : M = DueDate[i] pour la t�che i
subject to
  /* Pour chaque paire de t�ches diff�rentes {i,j}, 
     * soit la t�che i est ex�cut�e en premier (et sa date de fin d'ex�cution est inf�rieure � la date de d�but d'ex�cution de j)
     * soit c'est j qui est ex�cut�e en premier
     */
  BeforeCase1{i in Jobs, j in Jobs: i < j}: ProcessingDate[i] + ProcessingTime[i] - ProcessingDate[j] <= DueDate[i]*(1-Before[i,j]);
  BeforeCase2{i in Jobs, j in Jobs: i < j}: ProcessingDate[j] + ProcessingTime[j] - ProcessingDate[i] <= DueDate[j]*Before[i,j];


solve;

printf{1..45} "="; printf "\n\n";
printf "Dur�e minimale d'ex�cution : %g\n\n", Makespan;
printf "Ordonnancement\n";
printf "--------------\n";
printf{i in Jobs}: "T�che %g : Disponibilit� : %2g, �ch�ance : %2g, dur�e : %2g, d�but : %2g, fin : %2g\n", i, ReleaseDate[i], DueDate[i], ProcessingTime[i], ProcessingDate[i], ProcessingDate[i]+ProcessingTime[i];
printf "\n"; printf{1..45} "="; printf "\n";

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
