/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import java.util.Vector;

/**
 *
 * @author itu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Vector<Maladie> cin = new Vector<Maladie>();
        cin = Maladie.getListMaladie("CIN0001");
        System.out.println(cin.size());
    }
    
}
