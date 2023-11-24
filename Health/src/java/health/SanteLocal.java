/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import javax.ejb.Local;

/**
 *
 * @author itu
 */
@Local
public interface SanteLocal {
    public Sante getInfoSante(String id_cin) throws Exception;
}
