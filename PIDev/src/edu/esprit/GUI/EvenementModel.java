/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import edu.esprit.DAO.evenementDAO;
import edu.esprit.entities.Evenement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author sarah
 */
public class EvenementModel extends AbstractTableModel {
    evenementDAO edao = new evenementDAO();
    List<Evenement> listE = new ArrayList<Evenement>();
    String [] header ={"","ID","Nom","Date","Lieu","Infos"};
    Boolean data[][] =new Boolean[30][30];
    public EvenementModel(){
    listE = edao.DisplayAllEvenement();
    for(int i=0 ;i<getRowCount();i++ ){
    data[i][0]=Boolean.FALSE;
    }
    }
    @Override
    public int getRowCount() {
return listE.size();
        }

    @Override
    public int getColumnCount() {
return header.length;
        }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
switch(columnIndex){
    case 0 : return data[rowIndex][0];
    case 1 : return listE.get(rowIndex).getID();
        case 2 : return listE.get(rowIndex).getNom();
    case 3 : return listE.get(rowIndex).getDate();
    case 4 : return listE.get(rowIndex).getLieu();
    case 5 : return listE.get(rowIndex).getInfos();
    default:return null ; 
}
    } 

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex==0)
            data[rowIndex][0]=(Boolean)aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==0)
            return Boolean.class;
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
    
        return header[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
return (columnIndex ==0);
        }
    
    
    
    
}

    
    
