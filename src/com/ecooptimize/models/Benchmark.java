package com.ecooptimize.models;

public class Benchmark {
    private String secteur;
    private double moyenneKWhParMois;
    private String source;
    
    public Benchmark() {}
    
    public Benchmark(String secteur, double moyenneKWhParMois) {
        this.secteur = secteur;
        this.moyenneKWhParMois = moyenneKWhParMois;
        this.source = "ANME Tunisie - Benchmark énergétique 2025";
    }
    
    public double getMoyenneSecteur() { return moyenneKWhParMois; }
    public String getSecteur() { return secteur; }
    public String getSource() { return source; }
    
    public void afficherBenchmark() {
        System.out.println("📊 " + secteur + " : " + moyenneKWhParMois + " kWh/mois");
    }
}