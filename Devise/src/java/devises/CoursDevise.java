/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devises;

import javax.ejb.Stateless;

/**
 *
 * @author itu
 */
@Stateless
public class CoursDevise implements CoursDeviseLocal {
    Devise devise;
    Cours cours;

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public CoursDevise() {
    }
    
    public CoursDevise getCoursDevise (String id_devise) throws Exception {
        Devise devise = new Devise();
        Cours cours = new Cours();
        CoursDevise cd = new CoursDevise();
        cd.setCours(cours.getLastCours(id_devise));
        cd.setDevise(devise.getDevise(id_devise));
        return cd;
    }
    
}
