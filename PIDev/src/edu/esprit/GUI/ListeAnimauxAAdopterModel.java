/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.GUI;

import edu.esprit.DAO.AnimalAAdopterDAO;
import edu.esprit.entities.AnimalAAdopter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Melek
 */
public class ListeAnimauxAAdopterModel extends AbstractTableModel {
     List<AnimalAAdopter> listeAnimaux= new ArrayList<AnimalAAdopter>();
    String[] header={"id","Type","Age"};

    public ListeAnimauxAAdopterModel() {
        listeAnimaux=new AnimalAAdopterDAO().DisplayAllAnimalsForAdoption();
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
            case 2: return listeAnimaux.get(rowIndex).getAge();
            default:return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }


}
