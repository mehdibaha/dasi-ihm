/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gustatifihm;
import javax.servlet.http.HttpServletRequest;
import modele.Restaurant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Client;
import modele.Commande;

/**
 *
 * @author tthibault
 */
public class ListCommandesClientAction extends Action {
    @Override
    public void execute(HttpServletRequest request)
    {
        List<Commande> commandes = null;
        long idClient = (Long) request.getSession(true).getAttribute("id");
        
        try {
            Client client = serviceMetier.TrouverClientParId(idClient);
            commandes = this.serviceMetier.ListerCommandesClient(client);
        } catch (Throwable ex) {
            Logger.getLogger(ListeRestaurantsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("commandes", commandes);
    }
}
