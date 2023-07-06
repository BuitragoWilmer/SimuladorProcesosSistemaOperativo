/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author Brayan
 */
public class procesosDAO {
    private int id;
    private String nombre;
    private int tamaño;
    private int numHilos;
    private String estado;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private String r5;
    private String r6;

    public procesosDAO(int id, String nombre, int tamaño, int numHilos, String estado, String r1, String r2, String r3, String r4, String r5, String r6) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.numHilos = numHilos;
        this.estado = estado;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.r6 = r6;
    }

    public procesosDAO() {
    }

    public procesosDAO(int id, String nombre, int tamaño, int numHilos, String r1, String r2, String r3, String r4, String r5, String r6) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.numHilos = numHilos;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.r6 = r6;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getNumHilos() {
        return numHilos;
    }

    public void setNumHilos(int numHilos) {
        this.numHilos = numHilos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getR4() {
        return r4;
    }

    public void setR4(String r4) {
        this.r4 = r4;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    public String getR6() {
        return r6;
    }

    public void setR6(String r6) {
        this.r6 = r6;
    }

 
    
    

    
    
    
    
    
    
}
