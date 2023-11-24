/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devises;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.ejb.Stateless;
import util.reflection.ObjectDB;

/**
 *
 * @author itu
 */
@Stateless
public class Cours extends  ObjectDB implements CoursLocal {
    String id_devise;
    float valeur;
    float taux_achat;
    float taux_vente;
    Date dates;

    public String getId_devise() {
        return id_devise;
    }

    public void setId_devise(String id_devise) {
        this.id_devise = id_devise;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public float getTaux_achat() {
        return taux_achat;
    }

    public void setTaux_achat(float taux_achat) {
        this.taux_achat = taux_achat;
    }

    public float getTaux_vente() {
        return taux_vente;
    }

    public void setTaux_vente(float taux_vente) {
        this.taux_vente = taux_vente;
    }
    
    

    public Cours() {
        this.setPrimaryKeyName();
    }

    public Cours(String id_devise, float valeur, float taux_achat, float taux_vente, Date dates) {
        super();
        this.setId_devise(id_devise);
        this.setValeur(valeur);
        this.setTaux_achat(taux_achat);
        this.setTaux_vente(taux_vente);
        this.setDates(dates);
    }

    
    
    public Cours getLastCours(String id_devise) throws Exception {
        String req = "select * from  cours where id_devise='"+id_devise+"' order by dates desc";
        ResultSet result;
        Vector<Cours> list = new Vector<Cours> ();
        Statement stmt = this.createStatement();
//        PreparedStatement statement = connection.prepareStatement(req);
        try {
            result = stmt.executeQuery(req);
            Cours cours = new Cours();
            while (result.next()) {
                cours.setPrimaryKey(result.getString(1));
                cours.setId_devise(result.getString(2));
                System.out.println(id_devise);
                cours.setValeur(Float.parseFloat(result.getString(3)));
                System.out.println("++++++"+id_devise);
                cours.setTaux_achat(Float.parseFloat(result.getString(4)));
                cours.setTaux_vente(Float.parseFloat(result.getString(5)));
                cours.setDates(result.getDate(6));
                list.add(cours);
                
            }
            System.out.println(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return (Cours)list.get(0);
    }
    
    public float getSolde(String id_devise,float prix,boolean tf) throws Exception {
        Cours cours = this.getLastCours(id_devise);
        float solde = prix * cours.getTaux_vente();
        if(tf == true){
            solde = prix * cours.getTaux_achat();
        }
        return solde;
    }
     
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("DCR");
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
        this.setSequence("SeqCours");
    }
    
    
}
