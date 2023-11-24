<%-- 
    Document   : foncier
    Created on : 24 oct. 2023, 06:08:27
    Author     : itu
--%>

<%@page import="dotnet.Polygon"%>
<%@page import="dotnet.Borne"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    List<Borne> list_borne = (List<Borne>)request.getAttribute("borne"); 
    Polygon polygon = new Polygon(list_borne);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <title>Liste des Bornes</title>
    <style>
        /* Style pour le tableau */
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

        /* Style pour les boutons */
        button {
            padding: 10px 20px;
            background-color: #3498db;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #2774a3;
        }

        /* Style pour les champs d'entrée */
        input {
            padding: 10px;
        }
    </style>
</head>
<body>
    <h1>Liste des Bornes</h1>
    <h2> Superficie: <%= polygon.calculSuperficie() %> </h2>
    <h2> Perimetre: <%= polygon.calculPerimetre() %> </h2>
    <table>
        <tr>
            <th>ID Foncier</th>
            <th>Latitude</th>
            <th>Longitude</th>
        </tr>
        <% for (int i=0; i<list_borne.size(); i++) { %>
            <tr>
                <td><%= list_borne.get(i).getId_foncier() %></td>
                <td><%= list_borne.get(i).getLatitude() %></td>
                <td><%= list_borne.get(i).getLongitude() %></td>
            </tr>
        <% } %>
    </table>
    <div id="map" style="height: 400px; width: 100%;"></div>
    <div id="button-container">
        <button id="addBorneButton" onclick="addPoint()">Add Point</button>
        <button onclick="connectPoints()">Relier les Points</button>
    </div>
    
    <div id="input-container">
        <input type="text" id="latitudeInput" placeholder="Latitude" onclick="disableMapInteractions()">
        <input type="text" id="longitudeInput" placeholder="Longitude" onclick="disableMapInteractions()">
        <button onclick="updateMap()">Submit</button>
    </div>
    <script>
        var map = L.map('map').setView([-18.8792, 46.7969], 8); // Coordonnées du centre de Madagascar (latitude, longitude) et le niveau de zoom initial.

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '© <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        // Exemple de données de bornes (à remplacer par vos données réelles)
        var markerCoordinates = [];
        var lines = L.layerGroup();
        lines.addTo(map);
        var addedPoints = [];
        var bornes = [
            <%  for (int i=0; i<list_borne.size(); i++) { %>
                { latitude: <%= list_borne.get(i).getLatitude() %>, longitude: <%= list_borne.get(i).getLongitude() %>, nomPartiel: 'Borne 1', heritage: 'oui' },
            <% } %>
        ];
        for (var i = 0; i < bornes.length; i++) {
            var borne = bornes[i];
            var marker = L.marker([borne.latitude, borne.longitude]).addTo(map);
            marker.bindPopup("<b>Nom Partiel:</b> " + borne.nomPartiel + "<br><b>Héritage:</b> " + borne.heritage);
            markerCoordinates.push([borne.latitude, borne.longitude]);
        }
        // État de l'interface utilisateur
        var isAddingBorne = false;

        function addPoint() {
            // Activez l'écouteur de clic
            isAddingBorne = true;

            // Activez le bouton "Add Borne"
            document.getElementById("addBorneButton").disabled = true;

            // Affichez un message pour indiquer à l'utilisateur qu'il peut maintenant cliquer sur la carte
            alert("Cliquez sur la carte pour ajouter une borne.");

            // Activez l'écouteur de clic sur la carte
            map.on('click', function(e) {
                if (isAddingBorne) {
                    var latitude = e.latlng.lat;
                    var longitude = e.latlng.lng;

                    var marker = L.marker([latitude, longitude]).addTo(map);
                    markerCoordinates.push({ latitude: latitude, longitude: longitude });
                    bornes.push(marker);

                    // Effectuez une demande AJAX pour insérer la borne
                    $.ajax({
                        type: "POST",
                        url: "Borne", // Remplacez par l'URL de votre servlet
                        data: {
                            latitude: latitude,
                            longitude: longitude
                        },
                        success: function(response) {
                        },
                        error: function() {
                        }
                    });

                    // Désactivez l'écouteur de clic sur la carte
                    map.off('click');
                    isAddingBorne = false;

                    // Réactivez le bouton "Add Borne"
                    document.getElementById("addBorneButton").disabled = false;
                }
            });
        }

        function connectPoints() {
            if (bornes.length < 3) {
                alert("Vous devez ajouter au moins deux points pour les relier.");
                return;
            }

            var latlngs = bornes.map(function(borne) {
                return L.latLng(borne.latitude, borne.longitude);
            });
            // Relier le dernier point au premier point pour former un polygone fermé
            latlngs.push(latlngs[0]);
            var polygon = L.polygon(latlngs, { color: 'blue' }).addTo(map);
            // Rafraîchissez la carte (facultatif)
            map.fitBounds(polygon.getBounds());
        }

    
        // Désactiver les interactions de la carte pendant l'édition des champs d'entrée
        function disableMapInteractions() {
            map.off('click');
        }

        // Activer les interactions de la carte après avoir édité les champs d'entrée
        function enableMapInteractions() {
            map.on('click', function(e) {
                var latitude = e.latlng.lat;
                var longitude = e.latlng.lng;

                var marker = L.marker([latitude, longitude]).addTo(map);
                bornes.push(marker);
            });
        }

        // Mettre à jour la carte en fonction des coordonnées saisies
        function updateMap() {
            var latitude = parseFloat(document.getElementById('latitudeInput').value);
            var longitude = parseFloat(document.getElementById('longitudeInput').value);

            if (!isNaN(latitude) && !isNaN(longitude) && latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180) {
                var marker = L.marker([latitude, longitude]).addTo(map);
                map.setView([latitude, longitude], 14);
                bornes.push(marker);
            } else {
                alert('Veuillez saisir des coordonnées valides.');
            }
            // Effectuez une demande AJAX pour insérer la borne
                    $.ajax({
                        type: "POST",
                        url: "Borne", // Remplacez par l'URL de votre servlet
                        data: {
                            latitude: latitude,
                            longitude: longitude
                        },
                        success: function(response) {
                        },
                        error: function() {
                        }
                    });
            // Réactiver les interactions de la carte après avoir cliqué sur "Submit"
            enableMapInteractions();
        }
 
        // Créez un tableau pour stocker les coordonnées des marqueurs


    </script>

</body>
</html>

