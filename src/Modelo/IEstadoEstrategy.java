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
public interface IEstadoEstrategy {
    void mostrar(DefaultListModel lista, ArrayList<procesosDAO> procesos);
}
