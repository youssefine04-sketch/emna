package com.ecooptimize.models;

public class ObjectifEconomie {
    private static int compteurId = 1;
    private int id;
    private String site;
    private String mois;
    private double objectifKWh;
    private double consommationReelle;
    
    public ObjectifEconomie() {}
    
    public ObjectifEconomie(String site, String mois, double objectifKWh) {
        this.id = compteurId++;
        this.site = site;
        this.mois = mois;
        this.objectifKWh = objectifKWh;
        this.consommationReelle = 0;
    }
    
    public ObjectifEconomie(String site, String mois, double objectifKWh, double consommationReelle) {
        this.id = compteurId++;
        this.site = site;
        this.mois = mois;
        this.objectifKWh = objectifKWh;
        this.consommationReelle = consommationReelle;
    }
    
    public double calculerProgression() {
        if (objectifKWh == 0) return 0;
        return Math.min(100, (consommationReelle / objectifKWh) * 100);
    }
    
    public double calculerEcart() {
        return consommationReelle - objectifKWh;
    }
    
    public String getStatut() {
        if (consommationReelle <= objectifKWh) {
            return "🏆 Objectif atteint !";
        } else if (calculerProgression() <= 110) {
            return "⚠️ En retard (moins de 10% de dépassement)";
        } else if (calculerProgression() <= 125) {
            return "🔴 Dépassement modéré (10-25%)";
        } else {
            return "💀 Dépassement critique (>25%)";
        }
    }
    
    public void afficherObjectif() {
        System.out.println("\n🎯 === OBJECTIF ÉNERGÉTIQUE ===");
        System.out.println("📍 Site : " + site);
        System.out.println("📅 Mois : " + mois);
        System.out.println("🎯 Objectif : " + objectifKWh + " kWh");
        System.out.println("📊 Réel : " + consommationReelle + " kWh");
        System.out.println("📈 Progression : " + String.format("%.1f", calculerProgression()) + "%");
        
        double ecart = calculerEcart();
        if (ecart <= 0) {
            System.out.println("✅ Économie : " + (-ecart) + " kWh");
        } else {
            System.out.println("⚠️ Dépassement : " + ecart + " kWh");
        }
        System.out.println("📌 Statut : " + getStatut());
    }
    
    public void mettreAJourConsommation(double conso) {
        this.consommationReelle = conso;
        System.out.println("🔄 Consommation mise à jour : " + conso + " kWh");
    }
    
    // Getters/Setters
    public int getId() { return id; }
    public String getSite() { return site; }
    public String getMois() { return mois; }
    public double getObjectifKWh() { return objectifKWh; }
    public double getConsommationReelle() { return consommationReelle; }
    public void setConsommationReelle(double c) { this.consommationReelle = c; }
}