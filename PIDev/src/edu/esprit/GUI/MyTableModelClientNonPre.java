package edu.esprit.GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import edu.esprit.DAO.ClientDAO;
import edu.esprit.entities.Client;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DarkLord_666
 */
public class MyTableModelClientNonPre extends AbstractTableModel{

   List<Client> maliste = new ArrayList<Client>();
    String[] header = {"Id_Client","Nom","Prenom"};
      

    public MyTableModelClientNonPre() {
    maliste = new ClientDAO().DisplayClient();
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
            case 0: return maliste.get(rowIndex).getId_Client();
            case 1: return maliste.get(rowIndex).getNom();
            case 2: return maliste.get(rowIndex).getPrenom();
           
            default:
                return null;
        }
}
     @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
}
