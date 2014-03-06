/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.tests;

import edu.esprit.DAO.AnimalDAO;
import edu.esprit.entities.Animal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Melek
 */
public class TestAnimal {

    public static void main(String[] args) {
        Animal animal= new Animal();
        AnimalDAO animalDAO = new AnimalDAO();

      // TEST AJOUT
        /* animal.setType("cheval");
        animal.setCouleur("noir");
        animal.setRace("pur sang");
        animal.setRegion("megrine");
        animalDAO.insertAnimal(animal); */

      // TEST RECHERCHER
        animal=animalDAO.findDepotById(1);
        System.out.println(animal.getEspece());

     // TEST MISE A JOUR
     /*   animal.setCouleur("marron");
        animalDAO.updateAnimal(animal); */

     // TEST AFFICHAGE
        List<Animal> listeAnimaux = new ArrayList<Animal>();
        listeAnimaux=animalDAO.DisplayAllAnimals() ;
        Iterator i=listeAnimaux.iterator();
        while(i.hasNext())
        {
            System.out.println(i.next());
        }


    }

}
