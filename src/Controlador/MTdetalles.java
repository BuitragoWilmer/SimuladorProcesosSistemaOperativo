/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.procesosDAO;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Brayan
 */
public class MTdetalles implements TableModel{
private ArrayList<procesosDAO> proceso;
public MTdetalles(ArrayList<procesosDAO> lista){
    proceso= lista;
}
    @Override
    public int getRowCount() {
       return proceso.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
         String titulo = null;
        switch(columnIndex){
           
            case 0:{
                 titulo = "ID";
                break;
            }
            case 1:{
                titulo =  "Nombre";
                break;
            }
            case 2:{
                titulo =  "Tamaño";
                break;
            }
            case 3:{
                titulo =  "N° de Hilos";
                break;
            }
            case 4:{
                titulo =  "Recursos";
                break;
            }
            case 5:{
                titulo =  "Estado";
                break;
            }
            
        }
        return titulo;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        procesosDAO p = proceso.get(rowIndex);
        int id;
        String titulo = null;
        switch(columnIndex){
           
            case 0:{
                 id = p.getId();
                 return id;
            }
            case 1:{
                titulo =  p.getNombre();
                break;
            }
            case 2:{
               id = p.getTamaño();
                return id;
            }
            case 3:{
               id = p.getNumHilos();
                return id;
            }
            case 4:{
                String rec="";
                for(int i=0; i<6; i++){
                    if(i==0){
                        if(p.getR1()==1)
                            rec="R1 ";                      
                    }
                    if(i==1){
                        if(p.getR2()==1)
                            rec=rec+"R2 ";                      
                    }if(i==2){
                        if(p.getR3()==1)
                            rec=rec+"R3 ";                      
                    }if(i==3){
                        if(p.getR4()==1)
                            rec=rec+"R4 ";                      
                    }if(i==4){
                        if(p.getR5()==1)
                            rec=rec+"R5 ";                     
                    }if(i==5){
                        if(p.getR6()==1)
                            rec=rec+"R6 ";                      
                    }
                }
                return rec;
            }
            case 5:{
                titulo = p.getEstado();
                break;
            }
            
        }
        return titulo;
        
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        procesosDAO p = proceso.get(rowIndex);

        switch(columnIndex){
           
            case 0:{
                p.setId(Integer.parseInt(aValue.toString()));
                break;
            }
            case 1:{
                p.setNombre(aValue.toString());
                break;
            }
            case 2:{
                p.setTamaño(Integer.parseInt(aValue.toString()));
                break;
            }
            case 3:{
                p.setNumHilos(Integer.parseInt(aValue.toString()));
                break;
            }
            case 4:{
                
                break;
            }
            case 5:{
               
                break;
            }
            
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
       
    }
    
}
