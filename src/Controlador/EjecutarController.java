/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EstadoEjecucion;
import Modelo.IEstadoEstrategy;
import Modelo.procesosDAO;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import Modelo.r1;
import Modelo.r2;
import Modelo.r3;
import Modelo.r4;
import Modelo.r5;
import Modelo.r6;
import static java.lang.Thread.sleep;



/**
 *
 * @author Brayan
 */
public class EjecutarController extends Thread {

    private IEstadoEstrategy estrategia;
    public boolean iteral;
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
    DefaultListModel R1;
    DefaultListModel R2;
    DefaultListModel R3;
    DefaultListModel R4;
    DefaultListModel R5;
    DefaultListModel R6;
    DefaultListModel proR1;
    DefaultListModel proR2;
    DefaultListModel proR3;
    DefaultListModel proR4;
    DefaultListModel proR5;
    DefaultListModel proR6;
    
   r1 r1= new r1();
   r2 r2= new r2();
   r3 r3= new r3();
   r4 r4= new r4();
   r5 r5= new r5();
   r6 r6= new r6();
   
     boolean br1 = true , br2= true , br3= true, br4= true, br5= true, br6= true;
     int x1=0, x2=0, x3=0, x4=0, x5=0, x6=0;

    public EjecutarController(ArrayList<procesosDAO> Llistoo, ArrayList<procesosDAO> Lnuevo, DefaultListModel listones, ArrayList<procesosDAO> Lejecucion, DefaultListModel ejecucion, DefaultListModel nuevo, ArrayList<procesosDAO> Lterminado, DefaultListModel terminado, ArrayList<procesosDAO> Lbloqueado, DefaultListModel bloqueado, DefaultListModel R1, DefaultListModel R2, DefaultListModel R3, DefaultListModel R4, DefaultListModel R5, DefaultListModel R6, DefaultListModel proR1, DefaultListModel proR2, DefaultListModel proR3, DefaultListModel proR4, DefaultListModel proR5, DefaultListModel proR6) {
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
        this.R1 = R1;
        this.R2 = R2;
        this.R3 = R3;
        this.R4 = R4;
        this.R5 = R5;
        this.R6 = R6;
        this.proR1 = proR1;
        this.proR2 = proR2;
        this.proR3 = proR3;
        this.proR4 = proR4;
        this.proR5 = proR5;
        this.proR6 = proR6;
    }

    public synchronized void suspeder() {
        iteral = true;
    }

    public synchronized void reandar() {
        iteral = false;
        notifyAll();
    }

    public synchronized void ensuspension() {
        while (iteral) {
            try {
                wait();
            } catch (InterruptedException ex) {
                interrupt();
            }
        }
    }

    @Override
    public void run() {
        r1.setProceso("null");
        r2.setProceso("null");
        r3.setProceso("null");
        r4.setProceso("null");
        r5.setProceso("null");
        r6.setProceso("null");
           
        while (!isInterrupted()) {
            ensuspension();
            listones.clear();
            R1.addElement("LIBRE");
            R2.addElement("LIBRE");
            R3.addElement("LIBRE");
            R4.addElement("LIBRE");
            R5.addElement("LIBRE");
            R6.addElement("LIBRE");
            pausa();
            for (procesosDAO i : Llistoo) {
                if (Lnuevo.size() >= 1) {
                    Lnuevo.remove(0);
                    nuevo.remove(0);
                }
                listones.addElement(i.getNombre());
                pausa();
            }
            
            ejecucionProcesos();
            nuevo.clear();
        }
    }

 

    public void mostrarListo() {
        listones.clear();
        for (procesosDAO i : Llistoo) {
            listones.addElement(i.getNombre());
        }
        if (Llistoo.size() > 0) {
            Llistoo.get(0).setEstado("LISTO");
        }

    }

    public void mostrarEjecucion() {
        ejecucion.clear();
        for (procesosDAO i : Lejecucion) {
            ejecucion.addElement(i.getNombre());
        }
        if (Lejecucion.size() > 0) {
            Lejecucion.get(0).setEstado("EJECUCION");
        }
    }

    public void mostrarterminado() {
        terminado.clear();
        for (procesosDAO i : Lterminado) {
            terminado.addElement(i.getNombre());
        }
        if (Lterminado.size() > 0) {
            Lterminado.get(0).setEstado("TERMINADO");
        }

    }

    public void mostrarbloqueado() {
        bloqueado.clear();
        for (procesosDAO i : Lbloqueado) {
            bloqueado.addElement(i.getNombre());
        }
        if (Lbloqueado.size() > 0) {
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
    
    public void ejecucionProcesos(){
        while (!isInterrupted()){
            ensuspension();
             if (Llistoo.size() >= 1) {// EJECUCION si hay mas de un proceso disponible en listo
                    Random aleatorio = new Random();
                    int n = aleatorio.nextInt(2);
                    if(Lbloqueado.size()>0 && n!=0){//verifica si puede liberar recursos
                       desbloquearRecursos();//mueve el ramdom para ver si ya se puede utilizar los recursos                                    
                       sacardeBloqueo();//Revisa si los recursos estan libre para sacar de bloqueo
                       ejecucionProcesos();
                 }  
                 
                pasarListoaEjecucion();
                System.out.println("el error");
                
                EstadoEjecucion();//DETERMINA QUE UN PROCESO TENGA LOS RECURSOS LIBRES PARA EJECUTARSE o SINO BLOQUEAR
                
            } else {//ejecucion 2
                if (Lejecucion.size() > 0) {
                    PasarEjecucionaListo();
                    System.out.println("entra");
                    ejecucionProcesos();
                }
                
            }       
        }
    }
    
    public void EstadoEjecucion() {

        if (Validarrecursoslibres() == true) {
            Lejecucion.get(0).setTamaño(Lejecucion.get(0).getTamaño() - 1);
            bloquearRecursos();
            terminarpro();
            if (Llistoo.size() > 0 || Lbloqueado.size() > 0 || Lejecucion.size() > 0) {//Si en el programa hay otro proceso
                if (Lejecucion.size() > 0) {//si el proceso sigue en ejecucion
                    PasarEjecucionaListo();
                    if (Lbloqueado.size() > 0) {
                        sacardeBloqueo();//Revisa si los recursos estan libre para sacar de bloqueo
                    }
                    ejecucionProcesos();
                } else {
                    if (Lbloqueado.size() >= 1) {
                        sacardeBloqueo();//Revisa si los recursos estan libre para sacar de bloqueo
                    }
                    ejecucionProcesos();
                }
            }
        } else {// PASA A BLOQUEADO
            if (verificarProconRec() == true) {//si el proceso fue quien bloqueo los recursos
                System.out.println("2 nombre del proceso que ocupa el recurso r1 " + r1.getProceso());
                Lejecucion.get(0).setTamaño(Lejecucion.get(0).getTamaño() - 1);
                LiberarporSegEjecucion();
                terminarpro();              
                if (Llistoo.size() > 0 || Lbloqueado.size() > 0 || Lejecucion.size() > 0) {//Si en el programa hay otro proceso
                    if (Lejecucion.size() > 0) {
                        PasarEjecucionaListo();
                    }
                    if (Lbloqueado.size() >= 1) {
                        sacardeBloqueo();
                       
                    }
                    liberar();
                    ejecucionProcesos();
                }
            }
            if (Llistoo.size() > 0) {
                Lbloqueado.add(Lejecucion.get(0));
                Lejecucion.remove(0);
                pausa();
                estrategia = new EstadoEjecucion();
                estrategia.mostrar(ejecucion, Lejecucion);
                mostrarbloqueado();
                pausa();
                pasarListoaEjecucion();
                EstadoEjecucion();
            }
            if (Lejecucion.size() > 0) {//si la lista de listo esta vacio se ejecuta el proceso
                Lejecucion.get(0).setTamaño(Lejecucion.get(0).getTamaño() - 1);
                liberarRecursos();
                terminarpro();
                System.out.println("nombre del proceso que ocupa el recurso r1" + r1.getProceso());
                if (Llistoo.size() > 0 || Lbloqueado.size() > 0 || Lejecucion.size() > 0) {//Si en el programa hay otro proceso
                    if (Lejecucion.size() > 0) {
                        PasarEjecucionaListo();

                    }
                    if (Lbloqueado.size() > 0) {
                        sacardeBloqueo();
                    }
                    ejecucionProcesos();
                }
            }
        }
    }
public void liberar(){
    System.out.println("libera");
    r1.setEstado(true);
    r2.setEstado(true);
    r3.setEstado(true);
    r4.setEstado(true);
    r5.setEstado(true);
    r6.setEstado(true);
    
}
    public void sacardeBloqueo() {
        if (ValidarRecLibresdeBloqueo() == true) {//si el recurso esta libre pasa lo de bloqueado a listo
            Llistoo.add(Lbloqueado.get(0));
            Lbloqueado.remove(0);
            pausa();
            mostrarbloqueado();
            mostrarListo();
            if (Llistoo.size() > 0) {
                pasarListoaEjecucion();
                EstadoEjecucion();
            }
        }
    }
    public boolean verificarProconRec(){
        boolean valor = false;
        if (Lejecucion.get(0).getNombre().equals(r1.getProceso()) && Lejecucion.get(0).getR1()==1) {
            valor= true;
        }
         if (Lejecucion.get(0).getNombre().equals(r2.getProceso())&& Lejecucion.get(0).getR2()==1) {
           valor= true;
        }
        if (Lejecucion.get(0).getNombre().equals(r3.getProceso())&& Lejecucion.get(0).getR3()==1) {
            valor= true;
        }
         if (Lejecucion.get(0).getNombre().equals(r4.getProceso())&& Lejecucion.get(0).getR4()==1) {       
            valor= true;;
        }
         if (Lejecucion.get(0).getNombre().equals(r5.getProceso())&& Lejecucion.get(0).getR5()==1) {       
            valor= true;
        }
         if (Lejecucion.get(0).getNombre().equals(r6.getProceso())&& Lejecucion.get(0).getR6()==1) {   
            valor= true;
        }
         return valor;
    }
    
     public void LiberarporSegEjecucion(){
         if (Lejecucion.get(0).getNombre().equals(r1.getProceso())) {
            r1.setEstado(true);
        }
         if (Lejecucion.get(0).getNombre().equals(r2.getProceso())) {
           r2.setEstado(true);
        }
        if (Lejecucion.get(0).getNombre().equals(r3.getProceso())) {
            r3.setEstado(true);
        }
         if (Lejecucion.get(0).getNombre().equals(r4.getProceso())) {       
             r4.setEstado(true);
        }
         if (Lejecucion.get(0).getNombre().equals(r5.getProceso())) {       
             r5.setEstado(true);
        }
         if (Lejecucion.get(0).getNombre().equals(r6.getProceso())) {   
             r6.setEstado(true);
        }
    }
    
    public void liberarRecursos(){
         if (Lejecucion.get(0).getR1() == 1) {
            r1.setEstado(true);
        }
        if (Lejecucion.get(0).getR2() == 1) {
           r2.setEstado(true);
        }
        if (Lejecucion.get(0).getR3() == 1) {
            r3.setEstado(true);
        }
        if (Lejecucion.get(0).getR4() == 1) {           
             r4.setEstado(true);
        }
        if (Lejecucion.get(0).getR5() == 1) {          
             r5.setEstado(true);
        }
        if (Lejecucion.get(0).getR6() == 1) {        
             r6.setEstado(true);
        }
    }
    public void liberarRecursosTerminado(){
         if (Lterminado.get(Lterminado.size()-1).getR1() == 1) {
            r1.setEstado(true);
        }
        if (Lterminado.get(Lterminado.size()-1).getR2() == 1) {
           r2.setEstado(true);
        }
        if (Lterminado.get(Lterminado.size()-1).getR3() == 1) {
            r3.setEstado(true);
        }
        if (Lterminado.get(Lterminado.size()-1).getR4() == 1) {           
             r4.setEstado(true);
        }
        if (Lterminado.get(Lterminado.size()-1).getR5() == 1) {          
             r5.setEstado(true);
        }
        if (Lterminado.get(Lterminado.size()-1).getR6() == 1) {        
             r6.setEstado(true);
        }
    }
    public void pasarListoaEjecucion() {
        Lejecucion.add(Llistoo.get(0));
        Llistoo.remove(0);
        pausa();
        mostrarListo();
        mostrarEjecucion();
    }
    
    public void PasarEjecucionaListo() {
        Llistoo.add(Lejecucion.get(0));
        Lejecucion.remove(0);
        pausa();
        mostrarListo();
        mostrarEjecucion();              
    }
   
    public boolean ValidarRecLibresdeBloqueo(){
            boolean valor = true;
          int verificador=0;
          int contador=0;
        if (Lbloqueado.get(0).getR1() == 1) {
            contador++;
            if(r1.isEstado()==true)
                verificador++;
        }
        if (Lbloqueado.get(0).getR2() == 1) {
            contador++;
             if(r2.isEstado()==true)
                verificador++;
        }
        if (Lbloqueado.get(0).getR3() == 1) {
            contador++;
              if(r3.isEstado()==true)
                verificador++;
        }
        if (Lbloqueado.get(0).getR4() == 1) {  
             contador++;
             if(r4.isEstado()==true)
                verificador++;
        }
        if (Lbloqueado.get(0).getR5() == 1) {   
             contador++;
             if(r5.isEstado()==true)
                verificador++;
        }
        if (Lbloqueado.get(0).getR6() == 1) {    
             contador++;
             if(r6.isEstado()==true)
                verificador++;
        }
        if(verificador==contador){
            return true;
        }else{
            return false;
        }
    }
    
    public void terminarpro(){
        if (Lejecucion.size() >= 1) {//Termina el proceso
            if (Lejecucion.get(0).getTamaño() == 0) {
                Lterminado.add(Lejecucion.get(0));
                Lejecucion.remove(0);
                pausa();
                mostrarEjecucion();
                mostrarterminado();
                liberarRecursosTerminado();
            }           
        }
    }

    public void mostrarVacion() {
        pausa();
        proR1.clear();
        R1.clear();
        R1.addElement("LIBRE");

        proR2.clear();
        R2.clear();
        R2.addElement("LIBRE");

        proR3.clear();
        R3.clear();
        R3.addElement("LIBRE");

        proR4.clear();
        R4.clear();
        R4.addElement("LIBRE");

        proR5.clear();
        R5.clear();
        R5.addElement("LIBRE");

        proR6.clear();
        R6.clear();
        R6.addElement("LIBRE");

    }

    public boolean RamdomTrueoFalse(){
       Random aleatorio = new Random();
       boolean bloquear=true;      
       int n = aleatorio.nextInt(2);
            if(n==0)
                bloquear = false;             
        return bloquear;
    }
    
   
    public void desbloquearRecursos(){//mirar si se utiliza
        
        if (Lbloqueado.get(0).getR1() == 1) {
           r1.setEstado(RamdomTrueoFalse());
        }
        if (Lbloqueado.get(0).getR2() == 1) {
            r2.setEstado(RamdomTrueoFalse());
        }
        if (Lbloqueado.get(0).getR3() == 1) {         
            r3.setEstado(RamdomTrueoFalse());
        }
        if (Lbloqueado.get(0).getR4() == 1) {          
            r4.setEstado(RamdomTrueoFalse());
        }
        if (Lbloqueado.get(0).getR5() == 1) {
             r5.setEstado(RamdomTrueoFalse());
        }
        if (Lbloqueado.get(0).getR6() == 1) {            
            r6.setEstado(RamdomTrueoFalse());
        }
    }
    
    
     public void bloquearRecursos(){
          
        if (Lejecucion.get(0).getR1() == 1) {
            boolean valor = RamdomTrueoFalse();
            r1.setEstado(valor);
            if(valor==false)
            r1.setProceso(Lejecucion.get(0).getNombre());
        }
        if (Lejecucion.get(0).getR2() == 1) {
           boolean valor = RamdomTrueoFalse();
            r2.setEstado(valor);
            if(valor==false)
            r2.setProceso(Lejecucion.get(0).getNombre());
        }
        if (Lejecucion.get(0).getR3() == 1) {
           boolean valor = RamdomTrueoFalse();
            r3.setEstado(valor);
            if(valor==false)
            r3.setProceso(Lejecucion.get(0).getNombre());
        }
        if (Lejecucion.get(0).getR4() == 1) {           
            boolean valor = RamdomTrueoFalse();
            r4.setEstado(valor);
            if(valor==false)
            r4.setProceso(Lejecucion.get(0).getNombre());
        }
        if (Lejecucion.get(0).getR5() == 1) {          
             boolean valor = RamdomTrueoFalse();
            r5.setEstado(valor);
            if(valor==false)
            r5.setProceso(Lejecucion.get(0).getNombre());
        }
        if (Lejecucion.get(0).getR6() == 1) {        
             boolean valor = RamdomTrueoFalse();
            r6.setEstado(valor);
            if(valor==false)
            r6.setProceso(Lejecucion.get(0).getNombre());
        }
    }
     
    public boolean Validarrecursoslibres(){
          int verificador=0;
          int contador=0;
        if (Lejecucion.get(0).getR1() == 1) {
            contador++;
            if(r1.isEstado()==true)
                verificador++;
        }
        if (Lejecucion.get(0).getR2() == 1) {
            contador++;
             if(r2.isEstado()==true)
                verificador++;
        }
        if (Lejecucion.get(0).getR3() == 1) {
            contador++;
              if(r3.isEstado()==true)
                verificador++;
        }
        if (Lejecucion.get(0).getR4() == 1) {  
             contador++;
             if(r1.isEstado()==true)
                verificador++;
        }
        if (Lejecucion.get(0).getR5() == 1) {   
             contador++;
             if(r1.isEstado()==true)
                verificador++;
        }
        if (Lejecucion.get(0).getR6() == 1) {    
             contador++;
             if(r1.isEstado()==true)
                verificador++;
        }
        if(verificador==contador){
            return true;
        }
        return false;
    } 
     
    public void pausa() {
        try {
            sleep(1000);
        } catch (Exception e) {
            System.out.println("Error en cargar listo");
        }
    }

    public void mostarr1() {
        R1.clear();
        R1.addElement("OCUPADO");
        proR1.addElement(Lejecucion.get(0).getNombre());

    }

    public void mostrarr2() {
        R2.clear();
        R2.addElement("OCUPADO");
        proR2.addElement(Lejecucion.get(0).getNombre());

    }
     
  public void mostrarRecursos() {
          
        if (Lejecucion.get(0).getR1() == 1) {
            mostarr1();
 
        }
        if (Lejecucion.get(0).getR2() == 1) {
            mostrarr2();
  
        }
        if (Lejecucion.get(0).getR3() == 1) {
            R3.clear();
            R3.addElement("OCUPADO");
            proR3.addElement(Lejecucion.get(0).getNombre());
      
        }
        if (Lejecucion.get(0).getR4() == 1) {
            R4.clear();
            R4.addElement("OCUPADO");
            proR4.addElement(Lejecucion.get(0).getNombre());
     
        }
        if (Lejecucion.get(0).getR5() == 1) {
            R5.clear();
            R5.addElement("OCUPADO");
            proR5.addElement(Lejecucion.get(0).getNombre());
      
        }
        if (Lejecucion.get(0).getR6() == 1) {
            R6.clear();
            R6.addElement("OCUPADO");
            proR6.addElement(Lejecucion.get(0).getNombre());
        }
    }
}
