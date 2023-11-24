/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import java.util.Vector;
import javax.ejb.EJB;
import javax.ejb.Local;

/**
 *
 * @author itu
 */
@Local
public interface BanqueLocal {
    /**
     *
     * @param nom
     * @return
     */
    public String getNom(String nom);
    public Vector<Banque> getListBanque();
    public abstract Banque getInfo();
}
