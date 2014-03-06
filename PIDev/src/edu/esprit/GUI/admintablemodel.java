/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.GUI;

import edu.esprit.DAO.AdminDAO;
import edu.esprit.entities.Admin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class admintablemodel extends AbstractTableModel {

    List<Admin> maliste=new ArrayList<Admin>();
    String[] header={"Id_Admin","Nom","Prenom"};
    public admintablemodel()
        {maliste=new AdminDAO().DisplayAllAdmin();}

    public int getRowCount() {
            return maliste.size();
    }

    public int getColumnCount() {
            return header.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
                    {case 0:
                        return maliste.get(rowIndex).getId_admin();
                        case 1:
                        return maliste.get(rowIndex).getNom();
                        case 2:
                        return maliste.get(rowIndex).getPrenom();
                        
                        default:
                               return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

}
