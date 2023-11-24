/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.Vector;
import javax.ejb.Local;

/**
 *
 * @author itu
 */
@Local
public interface OperationLocal {
    public Vector<Transaction_faite> getList(String id_compte1,String id_transaction) throws Exception;
    public float getSolde( Vector<Transaction_faite> depots, Vector<Transaction_faite> retraits, Vector<Transaction_faite> transferts);
}
