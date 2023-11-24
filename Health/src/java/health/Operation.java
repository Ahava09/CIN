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
import java.sql.Timestamp;
import java.util.Vector;
import util.connexion.ConnectBD;

/**
 *
 * @author itu
 */
public class Operation {
    String id_cin ;
    String nom;
    Timestamp date_operation;
    Float prix ;
    String docteur;
    String lieu ;

    public Operation(String id_cin, String nom, Timestamp date_operation, Float prix, String docteur, String lieu) {
        this.id_cin = id_cin;
        this.nom = nom;
        this.date_operation = date_operation;
        this.prix = prix;
        this.docteur = docteur;
        this.lieu = lieu;
    }

    public Operation() {
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

    public Timestamp getDate_operation() {
        return date_operation;
    }

    public void setDate_operation(Timestamp date_operation) {
        this.date_operation = date_operation;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDocteur() {
        return docteur;
    }

    public void setDocteur(String docteur) {
        this.docteur = docteur;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
    public static Vector<Operation> getListOperation(String id_cin) throws Exception{
        String req = "select o.nom nom_operation, op.date_operation date_operation, op.prix prix, op.docteur docteur, op.lieu lieu from  operation_pers op join operation o on o.id = op.id_operation where id_cin ='"+id_cin+"'";
        ConnectBD c = new ConnectBD("postgres","root");
        Connection connection = c.getOnConnection();
        Statement stm = connection.createStatement();
        ResultSet result;
        Vector<Operation> list = new Vector<Operation> ();
        result = stm.executeQuery(req);
        Operation operation = new Operation();
        while (result.next()) {
            operation.setId_cin(id_cin);
            operation.setNom(result.getString(1));
            operation.setDate_operation(result.getTimestamp(2));
            operation.setPrix(result.getFloat(3));
            operation.setDocteur(result.getString(4));
            operation.setLieu(result.getString(5));
            list.add(operation);        
            System.out.println(operation.getPrix());

        }
        System.out.println("Nombre:" + list.size());
        return list;
    }
    
    
}
