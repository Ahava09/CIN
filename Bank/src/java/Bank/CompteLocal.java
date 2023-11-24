/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import javax.ejb.Local;

/**
 *
 * @author itu
 */
@Local
public interface CompteLocal {
    public  Compte getCompteByNumCin(String num_cin);
}
