package edu.esprit.GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.esprit.DAO.ExpertDAO;
import edu.esprit.entities.ExpertAnimalier;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DarkLord_666
 */
public class MyTableModelExpertcomplet extends AbstractTableModel{
    List<ExpertAnimalier> maliste = new ArrayList<ExpertAnimalier>();
    String[] header = {"NumExpert,","name","prenom","domain","adresse","numtel","email","login","mdp","cin"};

    public MyTableModelExpertcomplet() {
    maliste = new ExpertDAO().DisplayAllExpert();
    }


    @Override
    public int getRowCount() {
        return maliste.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return maliste.get(rowIndex).getId_Expert();
            case 1: return maliste.get(rowIndex).getNom();
            case 2: return maliste.get(rowIndex).getPrenom();
                    case 3: return maliste.get(rowIndex).getDomain();
                         case 4: return maliste.get(rowIndex).getAdresse();
            case 5: return maliste.get(rowIndex).getNumtel();
           
            case 6: return maliste.get(rowIndex).getEmail();
            case 7: return maliste.get(rowIndex).getLogin();
            case 8: return maliste.get(rowIndex).getMdp();
                case 9: return maliste.get(rowIndex).getCin();
        
            default:
                return null;
        }
}
     @Override
    public String getColumnName(int column) {
        return header[column];
    }
}