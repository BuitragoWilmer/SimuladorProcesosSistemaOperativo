/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.procesosDAO;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;

/**
 *
 * @author Brayan
 */
public class nuevo_Listo extends Thread{
    
    ArrayList<procesosDAO> Llistoo;
    ArrayList<procesosDAO> Lnuevo;
    DefaultListModel listones;
    ArrayList<procesosDAO> Lejecucion;
    DefaultListModel ejecucion;
    DefaultListModel nuevo;
    ArrayList<procesosDAO> Lterminado;
    DefaultListModel terminado;
    ArrayList<procesosDAO> Lbloqueado;
    DefaultListModel bloqueado;

    public nuevo_Listo(ArrayList<procesosDAO> Llistoo, ArrayList<procesosDAO> Lnuevo, DefaultListModel listones, ArrayList<procesosDAO> Lejecucion, DefaultListModel ejecucion, DefaultListModel nuevo, ArrayList<procesosDAO> Lterminado, DefaultListModel terminado, ArrayList<procesosDAO> Lbloqueado, DefaultListModel bloqueado) {
        this.Llistoo = Llistoo;
        this.Lnuevo = Lnuevo;
        this.listones = listones;
        this.Lejecucion = Lejecucion;
        this.ejecucion = ejecucion;
        this.nuevo = nuevo;
        this.Lterminado = Lterminado;
        this.terminado = terminado;
        this.Lbloqueado = Lbloqueado;
        this.bloqueado = bloqueado;
    }


    @Override
    public void run(){
         
        listones.clear();
        for(procesosDAO i : Llistoo){
                if(Lnuevo.size()>=1){
                    Lnuevo.remove(0); 
                    nuevo.remove(0);
                }                 
                listones.addElement(i.getNombre());            
                 pausa();
        }

        System.out.println(Llistoo.size() + " - " + Lejecucion.size());
        
        ejecucion();
        nuevo.clear();//Limpia Jlist de nuevo    

       // Lnuevo.clear();//Limpia lista de nuevo        
    }  
    
    public int comparacionRec(){
        
        int cont=0;
        if(Llistoo.get(0).getR1()==1 && Lejecucion.get(0).getR1()==1){
            cont++; 
        }
                   
        if(Llistoo.get(0).getR2()==1 && Lejecucion.get(0).getR2()==1){
              cont++;
        }
                  
        if(Llistoo.get(0).getR3()==1 && Lejecucion.get(0).getR3()==1){
            cont++;
        }
                    
        if(Llistoo.get(0).getR4()==1 && Lejecucion.get(0).getR4()==1){
             cont++;
        }
                   
        if(Llistoo.get(0).getR5()==1 && Lejecucion.get(0).getR5()==1){
                    cont++;
                }
                    
      if(Llistoo.get(0).getR6()==1 && Lejecucion.get(0).getR6()==1){
                    cont++;
                }
                    
         return cont;
    }
    
    public void mostrarListo() {
        listones.clear();
        for (procesosDAO proceso : Llistoo) {
            listones.addElement(proceso.getNombre());            
        }
        if(Llistoo.size()>0){
            Llistoo.get(0).setEstado("LISTO");
        }
       
    }

    public void mostrarEjecucion() {
        ejecucion.clear();
        Lejecucion.forEach((i) -> {
            ejecucion.addElement(i.getNombre());
        });
        if(Lejecucion.size()>0){
             Lejecucion.get(0).setEstado("EJECUCION");
        }
    }

    public void mostrarterminado() {
        terminado.clear();
        for (procesosDAO i : Lterminado) {
            terminado.addElement(i.getNombre());            
        }
        if(Lterminado.size()>0){
            Lterminado.get(0).setEstado("TERMINADO");
        }
        
    }
     public void mostrarbloqueado(){
        bloqueado.clear();
         for(procesosDAO i : Lbloqueado){
                    bloqueado.addElement(i.getNombre());                                 
               }
         if(Lbloqueado.size()>0){
            Lbloqueado.get(0).setEstado("BLOQUEADO");
        }
   
    }
    public void terminar() {
        Lterminado.add(Lejecucion.get(0));
        Lejecucion.remove(0);
        pausa();
        mostrarEjecucion();
        mostrarterminado();
    }
     
    public void ejecucion() {
        if (Llistoo.size() > 1) {// EJECUCION
            SacarsinRamdon();
            Lejecucion.add(Llistoo.get(0));
            Llistoo.remove(0);
            mostrarListo();
            mostrarEjecucion();
            Lejecucion.get(0).setTamaño(Lejecucion.get(0).getTamaño() - 1);

            if (Lejecucion.get(0).getTamaño() == 0) {//TERMINAR
                terminar();
                ejecucion();
                SacarsinRamdon();

            } else {
                if (comparacionRec() == 0) {//Pasar a listo despues de ejecucion
                    Llistoo.add(Lejecucion.get(0));
                    Lejecucion.remove(0);
                    pausa();
                    mostrarEjecucion();
                    mostrarListo();
                    if (Lbloqueado.size() > 0) {//SAcar de bloqueado
                        Random aleatorio = new Random();
                        int n = aleatorio.nextInt(2);
                        if (n == 1) {
                            Llistoo.add(Lbloqueado.get(0));
                            Lbloqueado.remove(0);
                            pausa();
                            mostrarbloqueado();
                            mostrarListo();
                            ejecucion();
                        }
                        
                        ejecucion();
                    }
                    ejecucion();
                } else {//BLOQUEADO
                    Lbloqueado.add(Lejecucion.get(0));
                    Lejecucion.remove(0);
                    pausa();
                    mostrarEjecucion();
                    mostrarbloqueado();
                    ejecucion();
                }
            }
        }else{
            System.out.println("entra");
            SacarsinRamdon();
            Lejecucion.add(Llistoo.get(0));
            Llistoo.remove(0);
            pausa();
            mostrarListo();
            mostrarEjecucion();
            Lejecucion.get(0).setTamaño(Lejecucion.get(0).getTamaño() - 1);
            
            if (Lejecucion.get(0).getTamaño() == 0) {//TERMINAR
                terminar();
                ejecucion();
                SacarsinRamdon();
            } else {     
                    Llistoo.add(Lejecucion.get(0));
                    Lejecucion.remove(0);
                    pausa();
                    mostrarEjecucion();
                    mostrarListo();
                    if (Lbloqueado.size()>0) {//SAcar de bloqueado
                        Random aleatorio = new Random();
                        int n = aleatorio.nextInt(2);
                        if (n == 1) {
                            Llistoo.add(Lbloqueado.get(0));
                            Lbloqueado.remove(0);
                            pausa();
                            mostrarbloqueado(); 
                            mostrarListo();
                            ejecucion();
                        }
                        ejecucion();
                        SacarsinRamdon();
                    }   
                      ejecucion();
            }
             SacarsinRamdon();
        }
    }
   
    public void pausa(){
        try {
                sleep(500);
            } catch (Exception e) {
                System.out.println("Error en cargar listo");
            } 
    }
    
    public void SacarsinRamdon() {
        if (Lejecucion.size() < 1 && Llistoo.size() <1 && Lbloqueado.size() > 0) {
            Llistoo.add(Lbloqueado.get(0));
            Lbloqueado.remove(0);
            pausa();
            mostrarbloqueado();
            mostrarListo();
            ejecucion();

        }
    }
    
}
