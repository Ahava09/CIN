/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devises;

import java.util.Vector;
import javax.ejb.Stateless;
import util.reflection.ObjectDB;

/**
 *
 * @author itu
 */
@Stateless
public class Devise extends ObjectDB implements DeviseLocal {
    String code;
    String nom;
    String pays;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Devise() {
        this.setPrimaryKeyName();
    }

    public Devise(String code, String nom, String pays) {
        super();
        this.setCode(code);
        this.setNom(nom);
        this.setPays(pays);
    }
    
    public Devise getDevise(String id) throws Exception {
        Devise c = new Devise();
        c = (Devise)c.selectw(c.getPrimaryKeyName(), id); 
        return c ;
    }
    
    public Vector<Devise> getAll () throws Exception {
        Devise d = new Devise();
        Vector<Object> list = d.find();
        Vector<Devise> listes = new Vector<Devise>();
        for (int i=0; i<list.size(); i++) {
            listes.add((Devise)list.get(i));
        }
        return listes;
    }
     
    @Override
    public void setPrimaryKeyName() {
        this.setPrimaryKeyName("id");
    }

    @Override
    public void setPrefix() {
        this.setPrefix("DVS");
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
        this.setSequence("SeqDevise");
    }
}
