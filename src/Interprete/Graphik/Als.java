/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Interprete.Variable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class Als {

    public static String nombre;
    public static String visibilidad;
    public static Object hereda;

    public static ArrayList<Variable> VarsGlobales = new ArrayList();

    public static ArrayList<MetodoGraphik> Metodos = new ArrayList();

    public Als() {

    }

    //-------------------------------------------INICIALIZANDO TODAS LAS VARIABLES POR CADA NUEVO ALS
    public void Inicializar() {

        VarsGlobales = new ArrayList();
        Metodos = new ArrayList();
    }

    //-----------------------------------------------------------AGREGANDO UN NUEVO ALS
    public Als(String name, String Visibilidad, Object Hereda) {
        nombre = name;
        visibilidad = Visibilidad;
        hereda = Hereda;
    }

    //---------------------------------------------------------------VARIABLES
    public void addVarGlobal(Variable var) {

        VarsGlobales.add(var);
    }

    public ArrayList getVarGlobales() {
        return VarsGlobales;
    }

    public Boolean existeVariableG(String val) {
        if (VarsGlobales.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < VarsGlobales.size(); i++) {
                if (val.equals(VarsGlobales.get(i).nombre)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Variable obtenerVariableG(String nombre) {
        for (int i = 0; i < VarsGlobales.size(); i++) {
            if (nombre.equals(VarsGlobales.get(i).nombre)) {
                Variable encontrada = (Variable) VarsGlobales.get(i);
                return encontrada;
            }
        }
        return null;
    }

    //-----------------------------------------------------------------METODOS
    public void addMetodo(MetodoGraphik fun) {
        Metodos.add(fun);
    }

    public ArrayList getMetodos() {
        return Metodos;
    }

    public Boolean existeMetodo(String val) {

        if (Metodos.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < Metodos.size(); i++) {
                if (val.equals(Metodos.get(i).nombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    public MetodoGraphik obtenerMetodo(String nombre) {
        for (int i = 0; i < Metodos.size(); i++) {
            if (nombre.equals(Metodos.get(i).nombre)) {
                MetodoGraphik encontrado = (MetodoGraphik) Metodos.get(i);
                return encontrado;
            }
        }
        return null;
    }
}
