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
    private int r1;
    private int r2;
    private int r3;
    private int r4;
    private int r5;
    private int r6;

    public procesosDAO(int id, String nombre, int tamaño, int numHilos, String estado, int r1, int r2, int r3, int r4, int r5, int r6) {
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

    public procesosDAO(int id, String nombre, int tamaño, int numHilos, int r1, int r2, int r3, int r4, int r5, int r6) {
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

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }

    public int getR4() {
        return r4;
    }

    public void setR4(int r4) {
        this.r4 = r4;
    }

    public int getR5() {
        return r5;
    }

    public void setR5(int r5) {
        this.r5 = r5;
    }

    public int getR6() {
        return r6;
    }

    public void setR6(int r6) {
        this.r6 = r6;
    }
}