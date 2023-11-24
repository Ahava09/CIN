/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import java.util.Vector;
import javax.ejb.Stateless;
import util.reflection.ObjectDB;

/**
 *
 * @author itu
 */
@Stateless
public class Sante implements SanteLocal {
     Vector<Maladie> maladie;
     Vector<Operation> operation;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Sante(Vector<Maladie> maladie, Vector<Operation> operation) {
        this.maladie = maladie;
        this.operation = operation;
    }

    public Sante() {
    }

    public Vector<Maladie> getMaladie() {
        return maladie;
    }

    public void setMaladie(Vector<Maladie> maladie) {
        this.maladie = maladie;
    }

    public Vector<Operation> getOperation() {
        return operation;
    }

    public void setOperation(Vector<Operation> operation) {
        this.operation = operation;
    }
     
    public Sante getInfoSante (String id_cin) throws Exception{
        Sante sante = new Sante();
        sante.setMaladie(Maladie.getListMaladie(id_cin));
        sante.setOperation(Operation.getListOperation(id_cin));
        System.out.println("Okkk"+sante.getOperation().size());

        return sante;
    }
     
}
