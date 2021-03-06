/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import dao.JpaUtil;
import java.util.Arrays;
import modele.*;
import static service.ServiceMetier.*;

/**
 *
 * @author hdav
 */
public class CreationLivraisonDur {
    
    public static void main(String[] args) throws Throwable {
        JpaUtil.creerEntityManager();
        Restaurant r1 = TrouverRestaurantParId(new Long(1));
        Client c1 = TrouverClientParId(new Long(140));
        
        Produit p1 = r1.getProduits().get(0);
        ProduitCommande pc1 = new ProduitCommande(2, p1);
        Produit p2 = r1.getProduits().get(1);
        ProduitCommande pc2 = new ProduitCommande(1, p2);
        Produit p3 = r1.getProduits().get(2);
        ProduitCommande pc3 = new ProduitCommande(1, p3);
        
        ProduitCommande[] plist = {pc1, pc2, pc3};
        
        Commande com1 = CreerCommande(c1, r1, Arrays.asList(plist));
        ValiderCommande(com1);
        System.out.println(com1);
        
        JpaUtil.fermerEntityManager();
    }
}
