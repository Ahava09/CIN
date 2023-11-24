/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devises;

import javax.ejb.Local;

/**
 *
 * @author itu
 */
@Local
public interface CoursLocal {
    public float getSolde(String id_devise,float prix,boolean tf) throws Exception;
}
