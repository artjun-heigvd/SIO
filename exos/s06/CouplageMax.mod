/* 
 * Mod�lisation du probl�me de la recherche d'un couplage de cardinal maximum dans un graphe simple non orient�, SIO 2024-2025 
 */

/* Nombre de sommets du graphe */
param n integer, >= 2;

/* Ensemble des sommets du graphe */
set V, default {1..n};

/* Ensemble des ar�tes du graphe (chaque ar�te est vue comme un couple de sommets, un �l�ment de VxV, c.-�-d. comme un arc) */
set E, within V cross V;

/* Le graphe doit �tre sans boucles */
check{(i,j) in E}: i != j;

/* Le graphe doit �tre simple : chaque ar�te est repr�sent�e par un seul couple de sommets */
check{(i,j) in E}: (j,i) not in E;

/* Variables de d�cision : x[i,j] = 1 si l'ar�te {i,j} est dans le couplage, 0 sinon */
var x{(i,j) in E} binary;

/* Pour chaque sommet, on ne peut s�lectionner qu'une ar�te incidente.
    Comme chaque ar�te est mod�lis�e par un couple, pour chaque sommet i 
    il faut sommer les variables des couples (j,i) et celles des couples (i,j) de E
 */
subject to sommet{i in V}: 
  sum{(j,i) in E} x[j,i] + sum{(i,j) in E} x[i,j] <= 1;

/* Fonction objectif : maximiser le cardinal du couplage, c.-�-d. le nombre d'ar�tes s�lectionn�es */
maximize cardCouplage: sum{(i,j) in E} x[i,j];

solve;

printf{1..56} "="; printf "\n\n";
printf "Taille du graphe : %g sommets et %g ar�tes.\n", n, card(E);
printf "Cardinal maximal d'un couplage : %g\n\n", cardCouplage;

printf "Ar�tes retenues\n";
printf "---------------\n";
printf{(i,j) in E : x[i,j]}: "{%2s,%2s}\n", i, j;
printf "\n";
printf{1..56} "="; printf "\n";


data;

/* Donn�es pour le graphe en exemple du cours (SIO 2024-2025), */

/* Le cardinal d'un couplage max est �gal � 2 */

/*Nombre de sommets */
param n := 5;

set E :=
 1 2
 1 3
 1 4
 2 3
 2 5
 3 4
 3 5
 4 5
;

# /* Graphe de l'exercice 1 de la s�rie 6 sur la PLNE (SIO 2024-2025), */

# /* Cardinal du couplage max = 4 */

# /*Nombre de sommets */
# param n := 8;

# set E :=
#  1 2
#  1 3
#  1 8
#  2 3
#  2 5
#  3 4
#  3 5
#  3 6
#  4 5
#  6 7
#  7 8
# ;

end;

