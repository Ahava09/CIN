<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire d'ajout de Borne et Tany</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1 {
            background-color: #3498db;
            color: #fff;
            text-align: center;
            padding: 20px 0;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
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
        /* Style CSS pour aligner les champs Latitude et Longitude côte à côte */
        .point-container {
            display: flex;
            flex-direction: row;
            align-items: center;
            margin-bottom: 10px;
        }

        .point-label {
            width: 100px; /* Largeur du label (ajustez selon vos besoins) */
        }

        .point-input {
            flex: 1; /* Remplit l'espace disponible */
            margin-right: 10px; /* Espace entre Latitude et Longitude */
        }
    </style>
</head>
<body>
    <h1>Formulaire d'ajout de Borne et Tany</h1>
    <form action="AjoutFoncier" method="post">
       
        <label for="nomPartiel">Nom Partiel :</label>
        <input type="text" name="nomPartiel" id="nom_partiel" required><br>

        <label for="heritage">Héritage :</label>
        <select name="heritage" id="heritage">
            <option value="OUI">Oui</option>
            <option value="NON">Non</option>
        </select><br>

        <label for="adresse">Adresse :</label>
        <input type="text" name="adresse" id="adresse" required><br>

        <label for="prix">Prix :</label>
        <input type="text" name="prix" id="prix" required><br>
        
        <label for="prix">Superficie :</label>
        <input type="text" name="superficie" id="superficie" required><br>
            <h2>Point 1</h2>
            <span class="point-label">Latitude :</span>
            <input type="text" name="latitude1" class="point-input" >
            <span class="point-label">Longitude :</span>
            <input type="text" name="longitude1" class="point-input" >
        </div>

        <div class="point-container">
            <h2>Point 2</h2>
            <span class="point-label">Latitude :</span>
            <input type="text" name="latitude2" class="point-input" >
            <span class "point-label">Longitude :</span>
            <input type="text" name="longitude2" class="point-input" >
        </div>

        <div class="point-container">
            <h2>Point 3</h2>
            <span class="point-label">Latitude :</span>
            <input type="text" name="latitude3" class="point-input" >
            <span class "point-label">Longitude :</span>
            <input type="text" name="longitude3" class="point-input" >
        </div>

        <input type="submit" value="Ajouter la borne">
    </form>
</body>
</html>