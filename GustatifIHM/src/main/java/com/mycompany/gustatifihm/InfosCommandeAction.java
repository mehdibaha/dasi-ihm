/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gustatifihm;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Commande;

/**
 *
 * @author tthibault
 */
public class InfosCommandeAction extends Action {
    @Override
    public void execute(HttpServletRequest request)
    {
        Commande commande = null;
        long idCommande;
        try {
            idCommande = Long.parseLong(request.getParameter("idCommande"));
            commande = this.serviceMetier.TrouverCommandeParId(idCommande);
        } catch (Throwable ex) {
            Logger.getLogger(FicheRestaurantAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("commande", commande);
        //System.out.println(commande);
    }
}
