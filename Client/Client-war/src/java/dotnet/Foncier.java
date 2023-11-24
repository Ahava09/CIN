/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author itu
 */
public class Foncier {
    private int id;
    private String partielle;
    private String heritage;
    private String id_cin;
    private String localisation;
    private double prix;
    private double superficie;

    public Foncier() {}

    public Foncier(int id, String id_cin, String partielle, String localisation, double prix, String heritage, double superficie) {
        this.setId(id);
        this.setPartielle(partielle);
        this.setHeritage(heritage);
        this.setId_cin(id_cin);
        this.setLocalisation(localisation);
        this.setPrix(prix);
        this.setSuperficie(superficie);
    }
    public Foncier( String id_cin, String partielle, String localisation, double prix, String heritage, double superficie) {
        this.setPartielle(partielle);
        this.setHeritage(heritage);
        this.setId_cin(id_cin);
        this.setLocalisation(localisation);
        this.setPrix(prix);
        this.setSuperficie(superficie);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartielle() {
        return partielle;
    }

    public void setPartielle(String partielle) {
        this.partielle = partielle;
    }

    public String getHeritage() {
        return heritage;
    }

//    public void setHeritage(int heritag) {
//        this.heritage = (heritag == 1) ? "OUI" : "NON";
//    }
    public static double getStringToDouble(String d) {
        char decimalSeparator = DecimalFormatSymbols.getInstance(Locale.US).getDecimalSeparator();

        // Remplacez les virgules par des points si nécessaire
        d = d.replace(',', decimalSeparator);

        double doubleValue = Double.parseDouble(d); 
        return  doubleValue;
    }
    

    
    public void setHeritage(String heritage) {
        this.heritage = heritage;
    }

    public String getId_cin() {
        return id_cin;
    }

    public void setId_cin(String id_cin) {
        this.id_cin = id_cin;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

//    public void setPrix(String prixStr) {
//    try {
//        prix = Double.parseDouble(prixStr);
//        // Maintenant, "prix" contient la valeur en double
//    } catch (NumberFormatException e) {
//        // Gérer l'exception si la conversion échoue (par exemple, la chaîne n'est pas un nombre valide)
//        System.err.println("La conversion en double a échoué : " + e.getMessage());
//    }
//}

//    public void setSuperficie(String superficie) {
//        try {
//            prix = Double.parseDouble(superficie);
//            // Maintenant, "prix" contient la valeur en double
//        } catch (NumberFormatException e) {
//            // Gérer l'exception si la conversion échoue (par exemple, la chaîne n'est pas un nombre valide)
//            System.err.println("La conversion en double a échoué : " + e.getMessage());
//        }
//    }
    
    public String appel (String uri) {
        uri = uri+"?nom="+this.getPartielle()+"&heritage="+this.getHeritage()+"&localisation="+this.getLocalisation()+"&id_cin="+this.getId_cin()+"&prix="+this.getPrix()+"&superficie="+this.getSuperficie();
        System.out.println("dotnet.Foncier.appel()" + uri);
        return uri;
    }
    
    public String transformFoncierToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // Gérer les erreurs de sérialisation
        }
    }


}

