/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Brayan
 */
public class r1 {
    private String proceso;
    private boolean estado = true;
   

    public r1(String proceso, boolean estado) {
        this.proceso = proceso;
        this.estado = estado;
    }

    public r1() {
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
