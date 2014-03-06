/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.GUI;

import edu.esprit.DAO.AnimalTrouveDAO;
import edu.esprit.entities.AnimalTrouve;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Melek
 */
public class ListeAnimauxTrouvesModel extends AbstractTableModel {

    List<AnimalTrouve> listeAnimaux= new ArrayList<AnimalTrouve>();
    String[] header={"id","Type","Ville",};

    public ListeAnimauxTrouvesModel() {
        listeAnimaux=new AnimalTrouveDAO().DisplayAllAnimalsFound();
    }

    public int getRowCount() {
        return listeAnimaux.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return listeAnimaux.get(rowIndex).getId();
            case 1: return listeAnimaux.get(rowIndex).getType();
            case 2: return listeAnimaux.get(rowIndex).getVille();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }


}
