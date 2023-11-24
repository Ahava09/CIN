/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.Vector;
import javax.ejb.Stateless;
import util.reflect.ObjectDB;

/**
 *
 * @author itu
 */
@Stateless
public class Transaction extends ObjectDB implements TransactionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String type) {
        this.nom = type;
    }

    public Transaction() {
        this.setPrimaryKeyName();
    }

    public Transaction(String type) {
        super();
        this.setNom(type);
    }
    
    public Vector<Transaction> getAll() throws Exception {
        Transaction t = new Transaction();
        Vector<Transaction> transactions = new Vector<Transaction>();
        Vector<Object> list = t.find();
        for (int i=0; i<list.size(); i++) {
            t = (Transaction)list.get(i);
            transactions.add(t);
        }
        return transactions;
    }
    
    public Transaction getTransaction(String id) throws Exception {
        Transaction t = new Transaction();
        Object trans = t.selectw(t.getPrimaryKeyName(), id);
        return (Transaction)trans;
    }
    
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("TRN");
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
        this.setSequence("SeqTransaction");
    }
}
