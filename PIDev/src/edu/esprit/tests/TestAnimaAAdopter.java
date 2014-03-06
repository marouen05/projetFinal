/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.tests;

import edu.esprit.DAO.AnimalAAdopterDAO;
import edu.esprit.entities.AnimalAAdopter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Melek
 */
public class TestAnimaAAdopter {

     public static void main(String[] args) {

         AnimalAAdopter animal= new AnimalAAdopter();
        AnimalAAdopterDAO animalDAO = new AnimalAAdopterDAO();
      
        //TEST AJOUT

      /*  animal.setType("chien");
        animal.setEspece("berger");
        animal.setCouleur("noir");
        animal.setSexe("male");
        animal.setPhoto("url");
        animal.setEtat("lebes");
        animal.setNom("chienloup");
        animal.setDescription("fi fourma");
        animalDAO.insertAnimal(animal,0);  */

        // TEST RECHERCHE ET MISE A JOUR

     /*   animal=animalDAO.findDepotById(14);
         System.out.println(animal);
         animal.setNom("loupchien");
         animalDAO.updateAnimal(animal); */

        //TEST AFFICHAGE

        List<AnimalAAdopter> maliste= new ArrayList<AnimalAAdopter>();
        maliste=animalDAO.DisplayAllAnimalsForAdoption();
        Iterator i=maliste.iterator();
        while(i.hasNext())
        {
            System.out.println(i.next());
        }

     }

}
