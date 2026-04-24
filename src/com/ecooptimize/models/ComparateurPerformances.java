package com.ecooptimize.models;

import java.util.ArrayList;
import java.util.List;

public class ComparateurPerformances {
    private List<Benchmark> benchmarks;
    private double consommationReelle;
    private String secteurActuel;
    
    public ComparateurPerformances() {
        benchmarks = new ArrayList<>();
        benchmarks.add(new Benchmark("Industrie", 850));
        benchmarks.add(new Benchmark("Bureaux", 450));
        benchmarks.add(new Benchmark("Commerce", 620));
        benchmarks.add(new Benchmark("Santé", 780));
        benchmarks.add(new Benchmark("Éducation", 380));
        benchmarks.add(new Benchmark("Hôtellerie", 920));
    }
    
    private Benchmark trouverBenchmark(String secteur) {
        for (Benchmark b : benchmarks) {
            if (b.getSecteur().equalsIgnoreCase(secteur)) return b;
        }
        return null;
    }
    
    public String evaluerPerformance(double consoReelle, String secteur) {
        this.consommationReelle = consoReelle;
        this.secteurActuel = secteur;
        
        Benchmark b = trouverBenchmark(secteur);
        if (b == null) return "❌ Secteur non trouvé";
        
        double moyenne = b.getMoyenneSecteur();
        double rapport = consoReelle / moyenne;
        
        if (rapport <= 0.8) return "🏆 EXCELLENT - " + String.format("%.0f", (1-rapport)*100) + "% en dessous";
        if (rapport <= 0.95) return "✅ BON - " + String.format("%.0f", (1-rapport)*100) + "% en dessous";
        if (rapport <= 1.05) return "📊 MOYEN - Dans la moyenne";
        if (rapport <= 1.2) return "⚠️ À AMÉLIORER - " + String.format("%.0f", (rapport-1)*100) + "% au-dessus";
        return "💀 CRITIQUE - " + String.format("%.0f", (rapport-1)*100) + "% au-dessus";
    }
    
    public double getEcartPourcentage() {
        Benchmark b = trouverBenchmark(secteurActuel);
        if (b == null) return 0;
        return ((consommationReelle - b.getMoyenneSecteur()) / b.getMoyenneSecteur()) * 100;
    }
    
    public String getRecommandation() {
        double ecart = getEcartPourcentage();
        if (ecart <= -10) return "💡 Excellent ! Partagez vos bonnes pratiques.";
        if (ecart <= 0) return "💡 Bonne performance. Encore mieux possible.";
        if (ecart <= 20) return "💡 Audit énergétique recommandé, remplacement LED.";
        return "🚨 Actions urgentes : isolation, équipements efficaces, sensibilisation.";
    }
    
    public void afficherComparaison(double conso, String secteur) {
        System.out.println("\n📊 === COMPARAISON SECTORIELLE ===");
        System.out.println("📍 Votre conso : " + conso + " kWh/mois");
        System.out.println("🏭 Secteur : " + secteur);
        
        Benchmark b = trouverBenchmark(secteur);
        if (b == null) {
            System.out.println("❌ Secteur non reconnu");
            return;
        }
        
        System.out.println("📈 Moyenne : " + b.getMoyenneSecteur() + " kWh/mois");
        System.out.println("🎯 Performance : " + evaluerPerformance(conso, secteur));
        System.out.println("📊 Écart : " + String.format("%+.1f", getEcartPourcentage()) + "%");
        System.out.println(getRecommandation());
    }
    
    public void comparerAvecTousSecteurs(double conso) {
        System.out.println("\n📊 === MULTI-SECTEURS ===");
        System.out.println("🔍 Conso analysée : " + conso + " kWh/mois\n");
        for (Benchmark b : benchmarks) {
            String perf = evaluerPerformance(conso, b.getSecteur());
            System.out.println("• " + b.getSecteur() + " : " + perf);
        }
    }
}