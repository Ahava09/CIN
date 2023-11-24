/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import javax.ejb.Stateless;

/**
 *
 * @author itu
 */
@Stateless
public class InfoCin implements InfoCinLocal {
    
    Person person ;
    Cin cin;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public InfoCin(Person person, Cin cin) {
        this.person = person;
        this.cin = cin;
    }

    public InfoCin() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Cin getCin() {
        return cin;
    }

    public void setCin(Cin cin) {
        this.cin = cin;
    }
    
    public InfoCin getInfo(String num_cin) throws Exception {
        Cin cin = new Cin();
        Person person = new Person();
        cin = cin.getByNumCin(num_cin);
        person = person.getById(cin.getId_person());
        
        return new InfoCin(person, cin);
    }
    
}
