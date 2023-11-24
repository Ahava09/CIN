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
public interface TransactionLocal {
    public Vector<Transaction> getAll() throws Exception;
}
