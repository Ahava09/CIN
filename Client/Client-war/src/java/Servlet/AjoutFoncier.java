/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dotnet.Borne;
import dotnet.Foncier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AjoutFoncier", urlPatterns = {"/AjoutFoncier"})
public class AjoutFoncier extends HttpServlet {

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
            out.println("<title>Servlet AjoutFoncier</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjoutFoncier at " + request.getContextPath() + "</h1>");
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
        String nom_partielle = request.getParameter("nomPartiel");
        String heritage = request.getParameter("heritage");

        String adresse = request.getParameter("adresse");
        String prix = request.getParameter("prix");
        String latitude1 = request.getParameter("latitude1");
        String longitude1 = request.getParameter("longitude1");
        String latitude2 = request.getParameter("latitude2");
        String longitude2 = request.getParameter("longitude2");
        String latitude3 = request.getParameter("latitude3");
        String longitude3 = request.getParameter("longitude3");
        String superficie = request.getParameter("superficie");
        Borne borne = new Borne();
        
        String id_cin = (String) request.getSession().getAttribute("id_cin");
        
        Foncier foncier = new Foncier();
        foncier.setId_cin(id_cin);
        foncier.setHeritage(heritage);
        foncier.setPartielle(nom_partielle);
        foncier.setPrix(Foncier.getStringToDouble(prix));
        foncier.setSuperficie(Foncier.getStringToDouble(superficie));
        foncier.setLocalisation(adresse);
        foncier = sendJsonToWebService(foncier.transformFoncierToJson());
        String[] latitudes = {latitude1, latitude2, latitude3};
        String[] longitudes = {longitude1, longitude2, longitude3};
        out.print("Lattttttttttt"+latitude1);
        int id_foncier = foncier.getId();
        
        borne.setId_foncier(id_foncier);
        for (int i = 0; i < 3; i++) {
            if (latitudes[i] != null && !latitudes[i].isEmpty() && longitudes[i] != null && !longitudes[i].isEmpty()) {
                borne.setLatitude(Double.parseDouble(latitudes[i]));
                borne.setLongitude(Double.parseDouble(longitudes[i]));
                insertBorne(borne);
            }
        }
        DetailsTany details_tany = new DetailsTany();
        details_tany.sendBorne(request, response, Integer.toString(id_foncier) );
        RequestDispatcher dispat = request.getRequestDispatcher("/Union");
        dispat.forward(request,response);


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
    
    public void insertBorne(Borne borne) throws MalformedURLException, IOException{
        String borne_json = borne.transformFoncierToJson();
        URL url = new URL("http://localhost:5073/api/Foncier/ajout-borne");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = borne_json.getBytes("UTF-8");
            os.write(input, 0, input.length);
        } catch(IOException io) {

        }
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responses = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responses.append(line);
            }
            reader.close();
            String jsonResponse = responses.toString();
            ObjectMapper objectMapper = new ObjectMapper();
        }
        connection.disconnect();
    }
    
    public Foncier sendJsonToWebService(String json) {
        Foncier foncier = new Foncier();
        try {
            // Créez une URL pour votre service web
            URL url = new URL("http://localhost:5073/api/Foncier/ajout");

            // Ouvrez une connexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurez la connexion pour une requête POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Activez l'envoi de données
            connection.setDoOutput(true);

            // Obtenez le flux de sortie pour envoyer les données JSON
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes("UTF-8");
                os.write(input, 0, input.length);
            } catch(IOException io) {
                
            }

            // Lisez la réponse du serveur
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                foncier = objectMapper.readValue(jsonResponse, new TypeReference<Foncier>() {});
            }
            // Fermez la connexion
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }            
        return foncier;
    }

}
