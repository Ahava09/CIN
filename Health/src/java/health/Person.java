/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import java.sql.Date;
import java.sql.Timestamp;
import javax.ejb.Stateless;
import util.reflection.ObjectDB;

/**
 *
 * @author itu
 */
public class Person extends ObjectDB{
    String nom;
    String prenom;
    Timestamp dtn;
    String lieu_n;
    String adresse;
    String profession;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Person(String nom, String prenom, Timestamp dtn, String lieu_n, String adresse, String profession) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.lieu_n = lieu_n;
        this.adresse = adresse;
        this.profession = profession;
    }

    public Person() {
        this.setPrimaryKeyName();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Timestamp getDtn() {
        return dtn;
    }

    public void setDtn(Timestamp dtn) {
        this.dtn = dtn;
    }

    public String getLieu_n() {
        return lieu_n;
    }

    public void setLieu_n(String lieu_n) {
        this.lieu_n = lieu_n;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public Person getById (String id ) throws Exception{
        Person personne = new Person();
        personne = (Person)personne.selectw(personne.getPrimaryKeyName(), id);
        return personne ; 
    }
    
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("PRS");
    }

    @Override
    public void setLongPK() {
        this.setLongPK(9);
    }

    @Override
    public void setFonction() {
    }

    @Override
    public void setSequence() {
        this.setSequence("SeqPerson");
    }

    
}
