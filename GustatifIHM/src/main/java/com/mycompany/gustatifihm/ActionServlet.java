/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gustatifihm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import modele.Client;
import modele.Produit;
import modele.Restaurant;
import service.ServiceMetier;

/**
 *
 * @author tthibault
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) 
        {
            HttpSession session = request.getSession(true); //Session   
            ServiceMetier sm = new ServiceMetier();
            MySerialiser ms = new MySerialiser();
            
            String todo = request.getParameter("todo");
            
            if(todo.equals("connexion")) //si demande de connexion
            {
                Action action = new ConnexionAction();
                action.setServiceMetier(sm);
                action.execute(request);
 
                Client client = (Client) request.getAttribute("client");
                if(client != null)
                {
                    session.setAttribute("id", client.getId());
                    RequestDispatcher rd = request.getRequestDispatcher("/home.html");
                    rd.forward(request, response);
                }
                else
                {
                    ms.printConnexionFail(out);
                }
            }
            else if(todo.equals("mdpOublie"))
            {
                Action action = new MdpOublieAction();
                action.setServiceMetier(sm);
                action.execute(request);
            }
            else if(todo.equals("inscription"))
            {
                Action action = new InscriptionAction();
                action.setServiceMetier(sm);
                action.execute(request);
                   
                //Client client = (Client) request.getAttribute("client");
                //ms.printCreationClient(out, client);
            }
            else
            {
                // Verif de la connexion
                Long sessionId = (Long) session.getAttribute("id"); 
                
                if(sessionId == null)
                {
                    //retourne vers une page de connexion
                    this.getServletContext().getRequestDispatcher("/login.html").forward(request, response);
                }
                else
                {
                    switch(todo)
                    {
                        case "listeRestaurants" : 
                        {
                            Action action = new ListeRestaurantsAction();
                            action.setServiceMetier(sm);
                            action.execute(request);

                            Object restaurants = request.getAttribute("restaurants");
                            ms.printListRestaurants(out, (List<Restaurant>) restaurants);
                            break;
                        }
                        case "restaurant" : 
                        {
                            Action action = new FicheRestaurantAction();
                            action.setServiceMetier(sm);
                            action.execute(request);

                            Object restaurant = request.getAttribute("restaurant");
                            ms.printFicheRestaurant(out, (Restaurant)restaurant);
                            break;
                        }
                        case "modificationInfos" :
                        {
                            Action action = new ModifInfosAction();
                            action.setServiceMetier(sm);
                            action.execute(request);

                            //Object restaurant = request.getAttribute("restaurant");
                            //ms.printFicheRestaurant(out, (Restaurant)restaurant);
                            break;
                        }
                        case "getPlats" :
                        {
                            Action action = new GetPlatsAction();
                            action.setServiceMetier(sm);
                            action.execute(request);

                            Object produits = request.getAttribute("plats");
                            ms.printPlats(out, (List<Produit>)produits);
                            break;
                        }
                        case "infosCommande" :
                        {
                            Action action = new InfosCommandeAction();
                            action.setServiceMetier(sm);
                            action.execute(request);

                            Object produits = request.getAttribute("plats");
                            ms.printPlats(out, (List<Produit>)produits);
                            break;
                        }
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}