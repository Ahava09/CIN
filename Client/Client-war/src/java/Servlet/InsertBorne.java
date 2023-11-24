/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import dotnet.Borne;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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
@WebServlet(name = "Borne", urlPatterns = {"/Borne"})
public class InsertBorne extends HttpServlet {

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
            out.println("<title>Servlet Borne</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Borne at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérez les données de la requête AJAX (latitude et longitude)
        PrintWriter out = response.getWriter();
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        out.print(latitude);
        AjoutFoncier ajout_ = new AjoutFoncier();
        String id_foncier = (String) request.getSession().getAttribute("id_foncier");
        Borne borne = new Borne();
        borne.setId_foncier(Integer.valueOf(id_foncier));
        borne.setLatitude(latitude);
        borne.setLongitude(longitude);
        ajout_.insertBorne(borne);
        DetailsTany details_tany = new DetailsTany();
        details_tany.sendBorne(request, response, id_foncier );
        RequestDispatcher dispat = request.getRequestDispatcher("/foncier.jsp");
        dispat.forward(request,response);
    }

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    
    
    }
}

