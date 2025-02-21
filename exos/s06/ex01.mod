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

var x{v in V} binary;

subject to
    

