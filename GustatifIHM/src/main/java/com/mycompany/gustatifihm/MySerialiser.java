package com.mycompany.gustatifihm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.List;
import modele.Client;
import modele.Commande;
import modele.Produit;
import modele.ProduitCommande;
import modele.Restaurant;

public class MySerialiser {
    public void printListRestaurants(PrintWriter out, List<Restaurant> restaurants)
    {
        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        for (Restaurant r : restaurants)
        {
            JsonObject jsonRestaurant = new JsonObject();
            
            jsonRestaurant.addProperty("id", r.getId());
            jsonRestaurant.addProperty("denomination", r.getDenomination());
            jsonRestaurant.addProperty("description", r.getDescription());
            
            jsonListe.add(jsonRestaurant);
        }
        
        JsonObject container = new JsonObject();
        container.add("restaurants", jsonListe);
        String json = gson.toJson(container);
        out.println(json);
    }
    
    public void printFicheRestaurant(PrintWriter out, Restaurant r)
    {
        JsonObject jsonRestaurant = new JsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        jsonRestaurant.addProperty("denomination", r.getDenomination());
        jsonRestaurant.addProperty("adresse", r.getAdresse());
        jsonRestaurant.addProperty("description", r.getDescription());
        
        String json = gson.toJson(jsonRestaurant);      
        out.println(json);
    }
    
    public void printConnexionFail(PrintWriter out)
    {
        JsonObject jsonFail = new JsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        jsonFail.addProperty("state", false);
        String json = gson.toJson(jsonFail);      
        out.println(json);
    }
    
    public void printConnexion(PrintWriter out, Client c)
    {
        JsonObject jsonConnexion = new JsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        jsonConnexion.addProperty("id", c.getId());
        String json = gson.toJson(jsonConnexion);      
        out.println(json);
    }
    
    public void printPlats(PrintWriter out, List<Produit> produits)
    {
        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        for (Produit p : produits)
        {
            JsonObject jsonProduit = new JsonObject();
            
            jsonProduit.addProperty("id", p.getId());
            jsonProduit.addProperty("denomination", p.getDenomination());
            jsonProduit.addProperty("description", p.getDescription());
            jsonProduit.addProperty("prix", p.getPrix());
            jsonProduit.addProperty("poids", p.getPoids());
            
            jsonListe.add(jsonProduit);
        }
        
        JsonObject container = new JsonObject();
        container.add("produits", jsonListe);
        String json = gson.toJson(container);
        out.println(json);
    }
    
    public void printInfosCommande(PrintWriter out, Commande commande)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonCommande = new JsonObject();

        jsonCommande.addProperty("id", commande.getId());
        jsonCommande.addProperty("dateDebut", commande.getDateDebut().toString());
        jsonCommande.addProperty("dateFin", commande.getDateFin().toString());
        List<ProduitCommande> produits = commande.getProduits();
        
        JsonArray jsonListeProduits = new JsonArray();
        for (ProduitCommande p : produits)
        {
            JsonObject jsonProduit = new JsonObject();
            
            jsonProduit.addProperty("id", p.getProduit().getId());
            jsonProduit.addProperty("denomination", p.getProduit().getDenomination());
            jsonProduit.addProperty("prix", p.getProduit().getPrix());
            jsonProduit.addProperty("quantite", p.getQuantitee());
            
            jsonListeProduits.add(jsonProduit);
        }

        JsonObject containerProduits = new JsonObject();
        containerProduits.add("produits", jsonListeProduits);
        
        jsonCommande.add("produits", jsonListeProduits);

        JsonObject container = new JsonObject();
        container.add("commande", jsonCommande);
        String json = gson.toJson(container);
        out.println(json);
    }
    
    public void printListCommandes(PrintWriter out, List<Commande> commandes)
    {
        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        for (Commande c : commandes)
        {
            JsonObject jsonCommande = new JsonObject();
            
            jsonCommande.addProperty("id", c.getId());
            jsonCommande.addProperty("restaurant", c.getRestaurant().getDenomination());
            jsonCommande.addProperty("date", c.getDateDebut().toString());
            jsonCommande.addProperty("etat", c.getStatus().toString());
            
            jsonListe.add(jsonCommande);
        }
        
        JsonObject container = new JsonObject();
        container.add("commandes", jsonListe);
        String json = gson.toJson(container);
        out.println(json);
    }
}
