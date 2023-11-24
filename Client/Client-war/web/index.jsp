<%-- 
    Document   : index
    Created on : 10 oct. 2023, 07:58:32
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                text-align: center;
                margin: 0;
                padding: 0;
                color: #333;
            }

            /* Styles pour le titre */
            h1 {
                color: #3498db;
            }

            /* Styles pour le formulaire */
            form {
                margin: 20px;
                padding: 20px;
                background-color: #fff;
                border: 2px solid #3498db;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }

            /* Styles pour les labels */
            label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
                color: #333;
            }

            /* Styles pour les champs de texte */
            input[type="text"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            /* Styles pour le bouton OK */
            input[type="submit"] {
                background-color: #3498db;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            /* Styles pour le bouton OK au survol */
            input[type="submit"]:hover {
                background-color: #2077b3;
            }
        </style>
    </head>
    <body>
        <h1>Entree</h1>
        <form action="Option" method="post" >
            <label> Numero CIN: </label> <input type="text" name="num_cin" />
            <input type ="submit" value="OK"/>
        </form>
    </body>
</html>
