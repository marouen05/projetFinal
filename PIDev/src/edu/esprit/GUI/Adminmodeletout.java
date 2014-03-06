/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.GUI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import edu.esprit.DAO.AdminDAO;
import edu.esprit.entities.Admin;

/**
 *
 * @author Abd
 */
public class Adminmodeletout extends AbstractTableModel {
 List<Admin> maliste=new ArrayList<Admin>();
    String[] header={"Id_Admin","Nom","Prenom","Email","Login","Mdp"};
    public Adminmodeletout(){maliste=new AdminDAO().DisplayAllAdmin();}
    
    public int getRowCount() {return maliste.size();
        
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
                            case 3:
                        return maliste.get(rowIndex).getEmail();
                                case 4:
                        return maliste.get(rowIndex).getMdp();
                                    case 5:
                        return maliste.get(rowIndex).getLogin();
                        
                        default:
                               return null;
        }
    }
    
    
   public String getColumnName(int column) {
        return header[column];
    }
    
}
