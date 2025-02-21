/*
 * Mod�lisation du probl�me d'assemblage (S�rie 2 - Exercice 2, partie a, SIO, 2024-2025)
 */

/* �tapes */
set E;

/* Produits */
set P;

/* Nombre d'heures disponibles par �tape et par semaine */
param NbHeuresDispo{E};

/* Temps de production par �tape et par produit */
param TempsProd{E,P};

/* Production maximale par produit et par semaine */
param ProductionMax{P};

/* Profit unitaire par produit */
param ProfitUnitaire{P};

/* Variables de d�cision : Quantit�s fabriqu�es de chaque produit pendant une semaine */
var nbUnitesProduites{j in P} >= 0, <= ProductionMax[j], integer;

var auMoins5UnitesProduites{i in P}, binary;

/* Contrainte sur les temps de production pour chaque �tape */
subject to 
  Etape{i in E}: sum{j in P} TempsProd[i,j]*nbUnitesProduites[j] <= NbHeuresDispo[i];
  
/* Fonction objectif */
maximize
  Profit: sum{j in P} ProfitUnitaire[j]*nbUnitesProduites[j];

solve;

printf{1..45} "="; printf "\n\n";
printf "Profit maximal par semaine : %g francs.\n\n", Profit;
printf "Plan de production\n";
printf "------------------\n";
printf{i in P}: "Fabriquer %8g unité(s) du produit %g\n", nbUnitesProduites[i], i;
printf "\n"; printf{1..45} "="; printf "\n";

data;

set E := A B C;

set P := 1 2 3 4;

param NbHeuresDispo := A 120 B 150 C 180;

param TempsProd : 1 2 3 4 :=
  A 2 2 4 3
  B 1 2 3 2
  C 3 5 1 6;

param ProductionMax := 1 60 2 55 3 80 4 70;

param ProfitUnitaire := 1 12 2 18 3 15 4 20;

end;
