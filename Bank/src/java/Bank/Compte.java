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
public class Compte extends ObjectDB implements CompteLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    String num_cin ;
    String id_banque ;
    String num_compte;
    String code;

    public Compte() {
        this.setPrimaryKeyName();
    }

    public Compte(String cin, String id_banque, String num_compte, String code) {
        super();
        this.setNum_cin(cin);
        this.setId_banque(id_banque);
        this.setNum_compte(num_compte);
        this.setCode(code);
    }

    public String getNum_cin() {
        return num_cin;
    }

    public void setNum_cin(String cin) {
        try {
            if (cin.length() == 12){
                this.num_cin = cin;
            }
        } catch(Exception e) {
            System.out.println("NUMERO CIN incorrect");
        }
    }

    public String getId_banque() {
        return id_banque;
    }

    public void setId_banque(String id_banque) {
        this.id_banque = id_banque;
    }

    public String getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(String num_compte) {
        this.num_compte = num_compte;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    
    public Compte getCompteByNumCin(String num_cin) {
        Compte c = new Compte ();
        try {
            c = (Compte)c.selectw("cin",num_cin);
        } catch (Exception e) {
            
        }
        return c;
    } 
    
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("CMPT");
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
        this.setSequence("SeqCompte");
    }
    
    
    
}
