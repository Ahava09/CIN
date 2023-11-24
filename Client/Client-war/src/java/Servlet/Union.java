/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bank.Banque;
import Bank.BanqueLocal;
import Bank.InfoBanque;
import Bank.InfoBanqueLocal;
import Bank.OperationLocal;
import Bank.Transaction_faite;
import health.InfoCin;
import health.InfoCinLocal;
import health.Sante;
import health.SanteLocal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dotnet.Foncier;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author itu
 */
@WebServlet(name = "Union", urlPatterns = {"/Union"})
public class Union extends HttpServlet {
    @EJB
    SanteLocal sante  ;
    @EJB
    InfoBanqueLocal info  ;
    @EJB
    InfoCinLocal cin  ;
    @EJB
    OperationLocal operationlocal;
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
            out.println("<title>Servlet Union</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Union at " + "</h1>");
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
            throws ServletException, IOException {
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
            InfoBanque infoBanque = info.getInfo(num_cin);
            InfoCin infoCin = cin.getInfo(num_cin);
            request.setAttribute("infoCin",infoCin);
            request.setAttribute("infoBanque",infoBanque); 
            Sante infoSante = sante.getInfoSante(infoCin.getCin().getPrimaryKey());
            request.setAttribute("infoSante",infoSante);
            request.getSession().setAttribute("id_cin", infoCin.getCin().getPrimaryKey());
            servletReceive(request,response,infoCin.getCin().getPrimaryKey());
            RequestDispatcher dispat = request.getRequestDispatcher("/affichage.jsp");
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
        try {
            doGet(request,response);
        } catch (Exception ex) {
            ex.printStackTrace();
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

    
    private void servletReceive(HttpServletRequest request, HttpServletResponse response, String id_cin) throws IOException {
    PrintWriter out = response.getWriter();
    try {
        String apiUrl = "http://localhost:5073/api/Test/send-acte?cin="+id_cin; // L'URL de votre API
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        out.println(responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responses = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responses.append(line);
            }
            reader.close();
            String jsonResponse = responses.toString();
            // Maintenant, vous pouvez travailler avec la réponse JSON de l'API.
            // Vous pouvez utiliser Jackson pour désérialiser la réponse en objets.
            // Par exemple, si la réponse est une liste d'objets ActeVente :
            ObjectMapper objectMapper = new ObjectMapper();
            List<Foncier> foncier = objectMapper.readValue(jsonResponse, new TypeReference<List<Foncier>>() {});
            out.println(foncier.size());
            // Faites ce que vous devez avec les données.

            // Vous pouvez stocker les données dans l'attribut de la requête, si nécessaire.
            request.setAttribute("infoFoncier", foncier);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
