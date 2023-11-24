/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bank.CompteLocal;
import Bank.OperationLocal;
import Bank.Transaction_faite;
import Bank.Transaction_faiteLocal;
import devises.CoursDevise;
import devises.CoursLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author itu
 */
@WebServlet(name = "Action", urlPatterns = {"/Action"})
public class Action extends HttpServlet {
    @EJB
    Transaction_faiteLocal transactionfaitelocal;
    @EJB
    OperationLocal operationlocal;
    @EJB
    CoursLocal courslocal;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Action</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Action at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            throws ServletException, IOException{
        String num_cin = (String) request.getSession().getAttribute("num_cin");
        PrintWriter out = response.getWriter();
        try {
            Vector<Transaction_faite> transfert = operationlocal.getList(num_cin,"TRN001");
            Vector<Transaction_faite> depot = operationlocal.getList(num_cin,"TRN002");
            Vector<Transaction_faite> retrait = operationlocal.getList(num_cin,"TRN003");
            float solde = operationlocal.getSolde(depot, retrait,transfert);
            request.setAttribute("depots", depot);
            request.setAttribute("transfert", transfert);
            request.setAttribute("retrait", retrait);
            request.setAttribute("solde", solde);
            
            RequestDispatcher dispat = request.getRequestDispatcher("/transaction.jsp");
            dispat.forward(request,response);
        }
         catch (Exception ex) {
        }
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
        PrintWriter out = response.getWriter();
        String num_cin1 = (String) request.getSession().getAttribute("num_cin");
        String num_cin2 = request.getParameter("num_cin2");
        String id_transaction = request.getParameter("id_transaction");
        String id_devise = request.getParameter("id_devise");
        String prix = request.getParameter("prix");
        try {
            float p = courslocal.getSolde(id_devise, Float.parseFloat(prix),true) ;
            Transaction_faite ft = new Transaction_faite();
            ft.setPrix(p);
            ft.setId_compte1(num_cin1);
            ft.setId_compte2(num_cin2);
            java.util.Date utilDate = new java.util.Date(); // Obtenir la date et l'heure actuelles en java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convertir en java.sql.Date
            ft.setDate_transcription(sqlDate);
            ft.setId_transaction(id_transaction);
            Vector<Transaction_faite> transfert = operationlocal.getList(num_cin1,"TRN001");
            Vector<Transaction_faite> depot = operationlocal.getList(num_cin1,"TRN002");
            Vector<Transaction_faite> retrait = operationlocal.getList(num_cin1,"TRN003");
            float solde = operationlocal.getSolde(depot,retrait,transfert);
            out.println("Solde:"+p);
            if(ft.getId_transaction().equalsIgnoreCase("TRN001") ) {
                if(solde > p && p>0){
                    transactionfaitelocal.insert(ft,p);
                    ft.setId_compte1(num_cin2);
                    ft.setId_compte2(num_cin1);
                    ft.setId_transaction("TRN002");
                    p =  courslocal.getSolde(id_devise, Float.parseFloat(prix),false) ;
                    ft.setPrix(p);
                    transactionfaitelocal.insert(ft);
                    
                }
                else {
                    throw new Exception("Solde insuffisante");
                }
            } else {
                transactionfaitelocal.insert(ft,p);
            }
            
            doGet(request, response);
        } catch (Exception ex) {
            request.setAttribute("erreur", ex);
            RequestDispatcher dispat = request.getRequestDispatcher("/Client-war/Trasaction");
            dispat.forward(request,response);
        }
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
