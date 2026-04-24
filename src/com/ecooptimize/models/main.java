// À AJOUTER dans Main.java (après le code des autres membres)
System.out.println("\n-- Objectifs & Benchmarks (Membre 4) --");

ObjectifEconomie objectif = new ObjectifEconomie("Site A", "2026-04", 800, 720);
objectif.afficherObjectif();

ComparateurPerformances cp = new ComparateurPerformances();
cp.afficherComparaison(720, "Industrie");
cp.comparerAvecTousSecteurs(720);