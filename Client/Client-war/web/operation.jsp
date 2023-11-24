<%-- 
    Document   : operation
    Created on : 16 oct. 2023, 11:33:12
    Author     : itu
--%>

<%@page import="Bank.Transaction"%>
<%@page import="java.util.Vector"%>
<%@page import="devises.Devise"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Vector<Devise> devises = (Vector<Devise>) request.getAttribute("devises") ; %>
<% Vector<Transaction> transactions = (Vector<Transaction>) request.getAttribute("transactions") ; %>
<% String erreur = (String) request.getAttribute("erreur") ; %>
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
            text-align: center;
        }

        h1 {
            color: #3498db;
        }

        form {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2077b3;
        }

    </style>
    <body>
        <h1>Transacation avec Banque</h1>
        <% if(erreur != null ) { %>
            <p style="color:red;"> <%= erreur %> </p>
        <% } %>
        <form action="Action" method="post" >
            <label> Numero cin(12) : </label> <input name="num_cin2" type="text" value="150000"/>
            <label> Type : </label> 
            <select name ="id_transaction" value=""> 
            <% 
                Transaction transaction = new Transaction();
                for (int i=0; i<transactions.size(); i++) {
                transaction = (Transaction)transactions.get(i);
            %>
            <option value="<%= transaction.getPrimaryKey()%>">  <%= transaction.getNom()%> </option>
            <% } %>
            </select>
            if
            <label> Prix : </label> <input name="prix" type="text" value="12.36"/>
            <label> Devise : </label> 
            <select name ="id_devise" > 
            <% 
                Devise devise = new Devise();
                for (int i=0; i<devises.size(); i++) {
                devise = (Devise)devises.get(i);
            %>
            <option value="<%= devise.getPrimaryKey()%>">  <%= devise.getNom()%> </option>
            <% } %>
            </select>
            <input type ="submit" value="OK"/>
        </form>
    </body>
</html>
