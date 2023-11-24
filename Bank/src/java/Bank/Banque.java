/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Vector;
import javax.ejb.Stateless;
import util.connection.ConnectBD;
import util.reflect.ObjectDB;

/**
 *
 * @author itu
 */
@Stateless
public class Banque extends ObjectDB implements BanqueLocal,Serializable  {
    String nom;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public String getNom() {
        return nom;
    }
    public String getNom(String nom) {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Banque(String nom) {
        super();
        this.nom = nom;
    }

    public Banque() {
        this.setPrimaryKeyName();
    }
    
    public Vector<Banque> getListBanque(){
        Vector<Banque> listBanque = new Vector(); 
        try {
            Vector<Object> list = this.find();
            for(int i=0; i<list.size(); i++){
                listBanque.add((Banque)list.get(i));
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        return listBanque;
    }
    
    public Banque getById (String id) {
        Banque b = new Banque();
        try{
            b = (Banque)b.selectw(b.getPrimaryKeyName(), id);     
        } catch (Exception e) {
            
        }
        return b;
    }
    
    public Banque getInfo(){
        Banque b = new Banque();
        b.setNom("Minooo");
        return b;
    }
    
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("BNK");
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
        this.setSequence("SeqBanque");
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Banque banque = new Banque();
        Vector<Banque> list = banque.getListBanque();
        System.out.println(list.size());
    }

}
