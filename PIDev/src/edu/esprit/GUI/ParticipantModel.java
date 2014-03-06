/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.DAO.evenementDAO;
import edu.esprit.DAO.ParticipantDAO;
import edu.esprit.entities.Participant;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author sarah
 */
public class ParticipantModel extends AbstractTableModel{
     List<Participant> maliste = new ArrayList<Participant>();
    String[] header = {"Id_participant","Nom","Prenom","Id_evenement"};
      

    public  ParticipantModel() {
    maliste = new ParticipantDAO().DisplayClient();
    }


        public int getRowCount() {
        return maliste.size();
    }

        public int getColumnCount() {
        return header.length;
    }

        public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return maliste.get(rowIndex).getIdp();
            case 1: return maliste.get(rowIndex).getNomp();
            case 2: return maliste.get(rowIndex).getPrenom();
            case 3: return maliste.get(rowIndex).getIde();    
           
            default:
                return null;
        }
}
         public String getColumnName(int column) {
        return header[column];
    }
    
}
