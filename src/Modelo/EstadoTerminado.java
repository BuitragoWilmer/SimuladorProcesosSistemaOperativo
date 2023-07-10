/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Brayan
 */
public class EstadoTerminado implements IEstadoEstrategy {

    @Override
    public void mostrar(DefaultListModel lista, ArrayList<procesosDAO> procesos) {
        lista.clear();
        for (procesosDAO i : procesos) {
            lista.addElement(i.getNombre());
        }
        if (procesos.size() > 0) {
            procesos.get(0).setEstado("TERMINADO");
        }
    }
    
}
