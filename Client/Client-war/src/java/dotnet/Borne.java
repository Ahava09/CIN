/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotnet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author itu
 */
public class Borne {
    private int id;
    private int id_foncier;
    private double latitude;
    private double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_foncier() {
        return id_foncier;
    }

    public void setId_foncier(int id_foncier) {
        this.id_foncier = id_foncier;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    

    public Borne() {
    }

    public Borne(int id, int id_foncier, double latitude, double longitude) {
        this.setId(id);
        this.setId_foncier(id_foncier);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }
    
    public Borne( int id_foncier, double latitude, double longitude) {
        this.setId_foncier(id_foncier);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
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
