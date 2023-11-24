/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import util.reflect.ObjectDB;

/**
 *
 * @author itu
 */
@Stateless
public class Transaction_faite extends ObjectDB implements Transaction_faiteLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    String id_transaction;
//    String transaction;
    String id_compte1;
    String id_compte2;
    float prix;
    Date date_transcription;

    public String getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(String id_transaction) {
        this.id_transaction = id_transaction;
    }

    public String getId_compte1() {
        return id_compte1;
    }

    public void setId_compte1(String id_compte1) {
        this.id_compte1 = id_compte1;
    }

    public String getId_compte2() {
        return id_compte2;
    }

    public void setId_compte2(String id_compte2) {
        this.id_compte2 = id_compte2;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) throws Exception {
        if(prix > 0){
            this.prix = prix;
        } else {
            throw new Exception("Prix invalide");
        }
    }

    public Date getDate_transcription() {
        return date_transcription;
    }

    public void setDate_transcription(Date date_transcription) {
        this.date_transcription = date_transcription;
    }

//    public String getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(String transaction) {
//        this.transaction = transaction;
//    }
    
    public Transaction_faite() {
        this.setPrimaryKeyName();
    }

    public Transaction_faite(String id_transaction, String id_compte1, String id_compte2, float prix, Date date_transcription) {
        super();
        this.setId_transaction(id_transaction);
//        this.setTransaction(transaction);
        this.setId_compte1(id_compte1);
        this.setId_compte2(id_compte2);
        this.setDate_transcription(date_transcription);
        try {
            this.setPrix(prix);
        } catch (Exception ex) {
        }
    }
    
    public Vector<Transaction_faite> getOperation(String id_compte1) throws Exception {
        Transaction_faite tf = new Transaction_faite();
        Vector<Transaction_faite> transactionfaite = new Vector<Transaction_faite>();
        Vector<Object> depot = tf.select("id_compte1", id_compte1);
        for (int i=0; i<depot.size(); i++) {
            transactionfaite.add((Transaction_faite)depot.get(i));
        }
        return transactionfaite;
    }
    
    public void insert(Transaction_faite t) throws Exception {
        t.save();
    }
    
    public boolean check(float valeur,float solde) throws Exception {
        if(solde >= valeur && valeur>0) {
            return true;
        }else {
            throw new Exception("Votre solde est insuffisant");
        }
    }
    
    public void insert(Transaction_faite t,float solde) {
        try {
            System.out.println("Bank.Transaction_faite.insert()-------"+solde+" -***- "+t.getId_transaction());
            if(t.getId_transaction().equalsIgnoreCase("TRN001") || t.getId_transaction().equalsIgnoreCase("TRN003")) {
                System.out.println(t.getPrix()+"Bank.Transaction_faite.insert()"+solde);
                boolean tf = check(t.getPrix(), solde);
                t.save();
            } else if(t.getId_transaction().equalsIgnoreCase("TRN002")) {
                t.save();
            }
        } catch (Exception e) {
            System.out.println("-------------++++++++++++++++++"+e);
        }
        
    }
    
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("TRF");
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
        this.setSequence("SeqTransaction_faite");
    }
    
    
    
}

