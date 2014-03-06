/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.tests;

import edu.esprit.DAO.AnimalTrouveDAO;
import edu.esprit.entities.AnimalTrouve;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Melek
 */
public class TestAnimalTrouve {


    public static void main(String[] args) {

         AnimalTrouve animal= new AnimalTrouve();
        AnimalTrouveDAO animalDAO = new AnimalTrouveDAO();

        //TEST AJOUT

     /*   animal.setType("rat");
        animal.setEspece("hamster");
        animal.setCouleur("marron");
        animal.setSexe("male");
        animal.setPhoto("url3");
        animal.setEtat("ma7leh");
        animal.setDescription("cute");
        animal.setDate("17 mars 1045");
        animal.setVille("tounes");
        animal.setRegion("ariana");
        animalDAO.insertAnimal(animal,0); */

        // TEST RECHERCHE ET MISE A JOUR

       /*  animal=animalDAO.findDepotById(17);
         System.out.println(animal);
         animal.setRegion("Carthage");
         animalDAO.updateAnimal(animal);  */

        //TEST AFFICHAGE

        List<AnimalTrouve> maliste= new ArrayList<AnimalTrouve>();
        maliste=animalDAO.DisplayAllAnimalsFound();
        Iterator i=maliste.iterator();
        while(i.hasNext())
        {
            System.out.println(i.next());
        }

     }

}
