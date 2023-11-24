/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import util.connexion.ConnectBD;

/**
 *
 * @author itu
 */
public class Maladie {
    String id_cin;
    String nom;
    String heridite;
    String traitement;
    String geuris ;
    Date dateDebut ;
    Date dateFin ;

    public Maladie(String id_cin, String nom, String heridite, String traitement, String geuris, Date dateDebut, Date dateFin) {
        this.id_cin = id_cin;
        this.nom = nom;
        this.heridite = heridite;
        this.traitement = traitement;
        this.geuris = geuris;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Maladie() {
    }

    public String getId_cin() {
        return id_cin;
    }

    public void setId_cin(String id_cin) {
        this.id_cin = id_cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHeridite() {
        return heridite;
    }

    public void setHeridite(String heridite) {
        this.heridite = heridite;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getGeuris() {
        return geuris;
    }

    public void setGeuris(String geuris) {
        this.geuris = geuris;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public static String change01 (int nb) {
        if (nb == 0){
            return "Non";
        }
        return "Oui";
    }
    
    public static Vector<Maladie> getListMaladie(String id_cin) throws Exception{
        String req = "select m.nom nom_maladie, mp.heridite heredite, mp.traitement traitement, mp.geuris geuris, mp.dateDebut debut, mp.dateFin fin from  maladie_pers mp join maladie m on m.id = mp.id_maladie where id_cin ='"+id_cin+"'";
        System.out.println(req);
        ConnectBD c = new ConnectBD("postgres","root");
        Connection connection = c.getOnConnection();
        Statement stm = connection.createStatement();
        ResultSet result;
        Vector<Maladie> list = new Vector<Maladie> ();
//        PreparedStatement statement = connection.prepareStatement(req);
        try {
            result = stm.executeQuery(req);
            Maladie maladie = new Maladie();
            while (result.next()) {
                maladie.setId_cin(id_cin);
                maladie.setNom(result.getString(1));
                maladie.setHeridite(change01(result.getInt(2)));
                maladie.setTraitement(change01(result.getInt(3)));
                maladie.setGeuris(change01(result.getInt(4)));
                maladie.setDateDebut(result.getDate(5));
                maladie.setDateFin(result.getDate(6));
                list.add(maladie);
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return list;
    }

    Maladie getDateDebut(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
