/* Toutes les tâches */
set T;

/* Temps d'exécution par tâche */
param tempsDExec{T} >= 0;

/* date de disponibilité par tâches */
param dateDispo{T} >= 0;

/* date de fin de disponibilité par tâche */
param dateFin{T} >= 0;

/* Vérification de la validité des données*/
check{i in T} : dateDispo[i] + tempsDExec[i] <= dateFin[i];

/* date de début de l'exécution par tâche */
var dateDebutExec{i in T} >= dateDispo[i], <= dateFin[i] - tempsDExec[i];

/* variable pour linéarisation*/
var finExecDerniere >= 0;

minimize
    FinExec : finExecDerniere;

/* linéarisation*/
subject to
    finExecDerniereContrainte{i in T} : finExecDerniere >= dateDebutExec[i] + tempsDExec[i];

/* variables binaire pour éviter chevauchement*/
var y{i in T, j in T} binary;

/* Contrainte de fin avec variable binaire*/
subject to
    avant0{i in T, j in T: i < j} : dateDebutExec[i] - dateDebutExec[j] + tempsDExec[i] <= dateFin[i]*(1 - y[i, j]);
    avant1{i in T, j in T: i < j} : dateDebutExec[j] - dateDebutExec[i] + tempsDExec[j] <= dateFin[j]*y[i, j];

solve;

printf{1..45} "="; printf "\n\n";
printf "Durée minimale d'exécution : %g\n\n", FinExec;
printf "Ordonnancement\n";
printf "--------------\n";
printf{i in T}: "Tâche %g : Disponibilité : %2g, échéance : %2g, durée : %2g, début : %2g, fin : %2g\n", i, dateDispo[i], dateFin[i], tempsDExec[i], dateDebutExec[i], dateDebutExec[i]+tempsDExec[i];
printf "\n"; printf{1..45} "="; printf "\n";

data;

param : T : dateDispo dateFin tempsDExec := 
  1   0   25  4
  2   3   25  9
  3   6   20  8;
 
############################################

# param : T : dateDispo dateFin tempsDExec := 
#   1   0   50  15
#   2   3   25  12
#   3   0   40   8
#   4   9   20   3
#   5  28   45   7;
 
end;