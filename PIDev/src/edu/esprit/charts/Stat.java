package edu.esprit.charts;

import edu.esprit.DAO.AnimalAAdopterDAO;
import edu.esprit.DAO.AnimalPerduDAO;
import edu.esprit.DAO.AnimalTrouveDAO;
import edu.esprit.entities.AnimalAAdopter;
import edu.esprit.entities.AnimalPerdu;
import edu.esprit.entities.AnimalTrouve;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

public class Stat extends javax.swing.JFrame{
    DefaultPieDataset dataset;//Dataset qui va contenir les Données
    JFreeChart graphe;        //Graphe
    ChartPanel cp;            //Panel
    List<AnimalAAdopter> listeAnimalAAdopter= new ArrayList<AnimalAAdopter>();
    List<AnimalPerdu> listeAnimalPerdu= new ArrayList<AnimalPerdu>();
    List<AnimalTrouve> listeAnimalTrouve= new ArrayList<AnimalTrouve>();
    AnimalPerduDAO anDAOPerdu= new AnimalPerduDAO();
    AnimalTrouveDAO anDAOTrouve = new AnimalTrouveDAO();
    AnimalAAdopterDAO anDAOAAdopter = new AnimalAAdopterDAO();

    public Stat() {
        listeAnimalPerdu=anDAOPerdu.DisplayAllAnimalsLost();
        listeAnimalAAdopter= anDAOAAdopter.DisplayAllAnimalsForAdoption();
        listeAnimalTrouve = anDAOTrouve.DisplayAllAnimalsFound();
        dataset = new DefaultPieDataset();
//Statique
        dataset.setValue("Animal Perdu",new Double(listeAnimalPerdu.size()) );
        dataset.setValue("Animal_Trouvé",new Double(listeAnimalTrouve.size()) );
        dataset.setValue("Animal_A_Adopter",new Double(listeAnimalAAdopter.size()) );
graphe = ChartFactory.createPieChart3D("Statistiques Des Animaux", dataset,true ,true ,false);
        cp = new ChartPanel(graphe);
        this.add(cp);
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

public static void main(String[] arg) {
                new Stat().setVisible(true);

    }
}
