<%-- 
    Document   : affichage
    Created on : 10 oct. 2023, 08:21:18
    Author     : itu
--%>

<%@page import="Bank.Transaction_faite"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="dotnet.Foncier"%>
<%@page import="health.InfoCin"%>
<%@page import="Bank.InfoBanque"%>
<%@page import="health.Sante"%>
<%
    InfoBanque infoBanque = (InfoBanque)request.getAttribute("infoBanque");
    Sante infoSante = (Sante) request.getAttribute("infoSante");
    InfoCin infoCin = (InfoCin) request.getAttribute("infoCin");
    List<Foncier> infoFoncier = (List<Foncier>) request.getAttribute("infoFoncier");
    Vector<Transaction_faite> depot = (Vector<Transaction_faite>)request.getAttribute("depots");
    Vector<Transaction_faite> transfert = (Vector<Transaction_faite>)request.getAttribute("transfert");
    Vector<Transaction_faite> retrait = (Vector<Transaction_faite>)request.getAttribute("retrait");
    Float solde = (Float) request.getAttribute("solde");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            /* styles.css */
           /* styles.css */
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
                color: #333;
            }

            header {
                background-color: #3498db;
                color: #fff;
                text-align: center;
                padding: 20px 0;
            }

            .container {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            .info-box {
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
                margin: 10px;
                flex: 1;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .info-box h2 {
                font-size: 1.5rem;
                color: #333;
                margin-bottom: 10px;
            }

            ul {
                list-style: none;
                padding: 0;
            }

            ul li {
                margin: 5px 0;
                color: #555;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table, th, td {
                border: 1px solid #ccc;
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #3498db;
                color: #fff;
            }
            
            a {
                text-decoration: none;
                color: #3498db;
                padding: 10px 20px;
                background-color: #fff;
                border: 2px solid #3498db;
                margin: 10px;
                display: inline-block;
                border-radius: 5px;
                transition: background-color 0.3s, color 0.3s;
            }

            a:hover {
                background-color: #2077b3;
                color: #fff;
            }



        </style>

    </head>
    <body>
        <div class="info-container">
        <div class="info-box info-cin-box">
            <h2>Informations CIN</h2>
            <p>Nom: <%= infoCin.getPerson().getNom() %></p>
            <p>Prénom: <%= infoCin.getPerson().getPrenom() %></p>
            <p>Date de naissance: <%= infoCin.getPerson().getDtn() %></p>
            <p>Lieu: <%= infoCin.getPerson().getLieu_n()%></p>
            <p>Adresse: <%= infoCin.getPerson().getAdresse()%></p>
            <p>Profession: <%= infoCin.getPerson().getProfession()%></p>
            -------------------------------------------------------------
            <p>Numero CIN: <%= infoCin.getCin().getNum_cin() %></p>
            <!-- Ajoutez d'autres informations du modèle InfoCin ici -->
        </div>
        <div class="info-box">
            <h3>Maladie</h3>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Heridite</th>
                    <th>Geuris</th>
                    <th>Traitement</th>
                    <th>Debut</th>
                    <th>Fin</th>
                </tr>
                <% for (int i = 0; i < infoSante.getMaladie().size(); i++) { %>
                    <tr>
                        <td><%= infoSante.getMaladie().get(i).getNom() %></td>
                        <td><%= infoSante.getMaladie().get(i).getHeridite() %></td>
                        <td><%= infoSante.getMaladie().get(i).getGeuris() %></td>
                        <td><%= infoSante.getMaladie().get(i).getTraitement() %></td>
                        <td><%= infoSante.getMaladie().get(i).getDateDebut() %></td>
                        <td><%= infoSante.getMaladie().get(i).getDateFin() %></td>
                    </tr>
                <% } %>
            </table>

            <h3>Operation</h3>
            <% if (infoSante != null) { %>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Docteur</th>
                    <th>Lieu</th>
                    <th>Date</th>
                </tr>
                <% for (int i = 0; i < infoSante.getOperation().size(); i++) { %>
                    <tr>
                        <td><%= infoSante.getOperation().get(i).getPrix() %></td>
                        <td><%= infoSante.getOperation().get(i).getDocteur() %></td>
                        <td><%= infoSante.getOperation().get(i).getLieu() %></td>
                        <td><%= infoSante.getOperation().get(i).getDate_operation() %></td>
                    </tr>
                <% } %>
            </table>
            <% } else { %>
                <p>Les informations de santé ne sont pas disponibles.</p>
            <% } %>

            

        </div>
        
        <div class="info-box">
            <h2>Historique</h2>
            <h1>Solde: <%= solde %> </h1>
            <div class="info-box">
            <h3>Depots</h3>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Prix</th>
                </tr>
                <% for (int i = 0; i < depot.size(); i++) { %>
                    <tr>
                        <td><%= depot.get(i).getDate_transcription()%></td>
                        <td><%= depot.get(i).getPrix()%></td>
                    </tr>
                <% } %>
            </table>
        </div>
        <div class="info-box">
            <h3>Retraits</h3>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Prix</th>
                </tr>
                <% for (int i = 0; i < retrait.size(); i++) { %>
                    <tr>
                        <td><%= retrait.get(i).getDate_transcription()%></td>
                        <td><%= retrait.get(i).getPrix()%></td>
                    </tr>
                <% } %>
            </table>
        </div>
        <div class="info-box">
            <h3>Transfert</h3>
            <table>
                <tr>
                    <th>Date</th>
                    <th>Prix</th>
                </tr>
                <% for (int i = 0; i < transfert.size(); i++) { %>
                    <tr>
                        <td><%= transfert.get(i).getDate_transcription()%></td>
                        <td><%= transfert.get(i).getPrix()%></td>
                    </tr>
                <% } %>
            </table>
        </div>
            <!-- Ajoutez d'autres informations du modèle InfoBanque ici -->
        </div>
            
         <div class="info-box">
            <h2>Informations Foncier</h2>
            
            <a href="ajout.jsp" > <h2>+</h2> Ajout  </a>
            <table>
                <tr>
                    <th>Partielle</th>
                    <th>Héritage</th>
                    <th>Localisation</th>
                    <th>Prix</th>
                </tr>
                <% for (int i = 0; i < infoFoncier.size(); i++) { %>
                    <tr>
                        <td><%= infoFoncier.get(i).getPartielle()%></td>
                        <td><%= infoFoncier.get(i).getHeritage()%></td>
                        <td><%= infoFoncier.get(i).getLocalisation()%></td>
                        <td><%= infoFoncier.get(i).getPrix()%></td>
                        <td> <a href="DetailsTany?id_foncier=<%= infoFoncier.get(i).getId() %>">Borne & schema </a></td>
                    </tr>
                
                <% } %>
            </table>
        </div>

        </div>
        
    </body>
</html>

