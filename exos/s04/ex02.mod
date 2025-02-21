/* Ensembles */
set Produits;
set Etapes;

/* Params */
param PrixVente{i in Produits};
param NbMaxHeuresEtape{i in Etapes};
param DemandeProduit{i in Produits};
param NbHeuresEtapeProduit{i in Produits, j in Etapes};

/* Var de décision */
var NbProduit{i in Produits} >= 0, <= DemandeProduit[i];

/* Fonction objectif */
maximize
    Profit : sum{i in Produits}
        PrixVente[i]*NbProduit[i];

/* Contraintes */
subject to
    TempsTravail{i in Etapes} : sum{j in Produits} NbProduit[j]NbHeuresEtapeProduit[j, i] <= NbMaxHeuresEtape[i];

data;

set Produits := 1 2 3 4;
set Etapes := A B C;

param PrixVente := 1 12 2 18 3 15 4 20;
param NbMaxHeuresEtape := A 120 B 150 C 180;
param DemandeProduit := 1 60 2 55 3 80 4 70;
param NbHeuresEtapeProduit : A B C :=
    1 2 1 3
    2 2 2 5
    3 4 3 2
    4 3 2 6;

end;
