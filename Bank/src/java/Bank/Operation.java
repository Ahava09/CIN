/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.Vector;
import javax.ejb.Stateless;

/**
 *
 * @author itu
 */
@Stateless
public class Operation implements OperationLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    float solde;
    Vector<Transaction_faite> depots;
    Vector<Transaction_faite> retraits;
    Vector<Transaction_faite> transferts;

    public float getSolde() {
        return solde;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    public Vector<Transaction_faite> getDepots() {
        return depots;
    }

    public void setDepots(Vector<Transaction_faite> depots) {
        this.depots = depots;
    }

    public Vector<Transaction_faite> getRetraits() {
        return retraits;
    }

    public void setRetraits(Vector<Transaction_faite> retraits) {
        this.retraits = retraits;
    }

    public Vector<Transaction_faite> getTransferts() {
        return transferts;
    }

    public void setTransferts(Vector<Transaction_faite> transferts) {
        this.transferts = transferts;
    }
    
    

    public Operation() {
    }

    public Operation( Vector<Transaction_faite> depots, Vector<Transaction_faite> retraits,Vector<Transaction_faite> transferts) {
        this.setSolde(getSolde(depots, retraits,transferts));
        this.setDepots(depots);
        this.setRetraits(retraits);
    }
    
    public Vector<Transaction_faite> getList(String id_compte1,String id_transaction) throws Exception {
        Transaction_faite t = new Transaction_faite();
        Vector<Transaction_faite> list = t.getOperation(id_compte1);
        Vector<Transaction_faite> depot = new Vector<Transaction_faite>();
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getId_transaction().equalsIgnoreCase(id_transaction)) {
                depot.add((Transaction_faite)list.get(i));            
            }
        }
        return depot;
    }
    
    public float calcul ( Vector<Transaction_faite> t) {
        float prix = 0;
        for(int i=0; i<t.size(); i++) {
            prix = prix+t.get(i).getPrix();
        }
        return  prix;
    }
    
    public float getSolde( Vector<Transaction_faite> depots, Vector<Transaction_faite> retraits,Vector<Transaction_faite> transferts) {
        float solde = calcul(depots) - calcul(retraits) - calcul(transferts);
        return solde;
    }
    
}
