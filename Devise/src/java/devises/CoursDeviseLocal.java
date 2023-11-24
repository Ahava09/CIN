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
public interface CoursDeviseLocal {
    public CoursDevise getCoursDevise(String id_devise) throws Exception;
}
