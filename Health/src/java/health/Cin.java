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
public class Cin extends ObjectDB {
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    String id_person;
    String nom_pere ;
    String profession_pere;
    String nom_mere;
    String profession_mere;
    Timestamp date_cin;
    String num_cin;

    public Cin() {
        this.setPrimaryKeyName();
    }
    

    public Cin(String id_person, String nom_pere, String profession_pere, String nom_mere, String profession_mere, Timestamp date_cin, String num_cin) {
        super();
        this.id_person = id_person;
        this.nom_pere = nom_pere;
        this.profession_pere = profession_pere;
        this.nom_mere = nom_mere;
        this.profession_mere = profession_mere;
        this.date_cin = date_cin;
        this.num_cin = num_cin;
    }

    public String getId_person() {
        return id_person;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }

    public String getNom_pere() {
        return nom_pere;
    }

    public void setNom_pere(String nom_pere) {
        this.nom_pere = nom_pere;
    }

    public String getProfession_pere() {
        return profession_pere;
    }

    public void setProfession_pere(String profession_pere) {
        this.profession_pere = profession_pere;
    }

    public String getNom_mere() {
        return nom_mere;
    }
    

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getProfession_mere() {
        return profession_mere;
    }

    public void setProfession_mere(String profession_mere) {
        this.profession_mere = profession_mere;
    }

    public Timestamp getDate_cin() {
        return date_cin;
    }

    public void setDate_cin(Timestamp date_cin) {
        this.date_cin = date_cin;
    }

    public String getNum_cin() {
        return num_cin;
    }

    public void setNum_cin(String num_cin) {
        this.num_cin = num_cin;
    }
    
    public Cin getByNumCin (String cin ) throws Exception{
        Cin c = new Cin();
        c = (Cin)c.selectw("num_cin", cin); 
        System.out.print(c.getDate_cin());
        return c ;
    }
    
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("CIN");
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
        this.setSequence("SeqCin");
    }
    
}
