<%-- 
    Document   : transaction
    Created on : 16 oct. 2023, 16:46:08
    Author     : itu
--%>

<%@page import="Bank.Transaction_faite"%>
<%@page import="java.util.Vector"%>
<%@page import="java.lang.Float" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
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
    </head>
    <style>
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        </style>
    <body>
        <h1>TRANSACTION compte <%= request.getSession().getAttribute("num_cin")%> </h1>
        <h2> Solde :  <%= solde %> AR</h2>
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
        
    </body>
</html>
