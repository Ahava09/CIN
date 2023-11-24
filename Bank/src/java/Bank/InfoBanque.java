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
public class InfoBanque implements InfoBanqueLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    String nom;
    String num_compte;
    String cin;

    public InfoBanque(String nom, String num_compte, String cin) {
        this.nom = nom;
        this.num_compte = num_compte;
        this.cin = cin;
    }

    public InfoBanque() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(String num_compte) {
        this.num_compte = num_compte;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    
//    public Vector<InfoBanque> getListe (String num_cin) {
//        Vector<Banque> listBanque = new Vector(); 
//        try {
//            Vector<Object> list = this.find();
//            for(int i=0; i<list.size(); i++){
//                listBanque.add((Banque)list.get(i));
//            } 
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return listBanque;
//    }
    
    public InfoBanque getInfo (String num_cin) {
        InfoBanque info = new InfoBanque();
        Compte c = new Compte();
        Banque b = new Banque ();
        c = c.getCompteByNumCin(num_cin);
        b = b.getById(c.getId_banque());
        info.setNom(b.getNom());
        info.setNum_compte(c.getNum_compte());
        info.setCin(c.getNum_cin());
        return info;
    }
}
