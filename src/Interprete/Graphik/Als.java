/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Interprete.Variable;
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

    public static Map<String, Variable> VarsGlobales = new LinkedHashMap<>();
    
    public static Map<String, MetodoGraphik> Metodos = new LinkedHashMap<>();

    public Als() {

    }

    //-------------------------------------------INICIALIZANDO TODAS LAS VARIABLES POR CADA NUEVO ALS
    public void Inicializar() {
       
        VarsGlobales = new LinkedHashMap<>();
        Metodos = new LinkedHashMap<>();
    }

    //-----------------------------------------------------------AGREGANDO UN NUEVO ALS
    public Als(String name, String Visibilidad, Object Hereda) {
        nombre = name;
        visibilidad = Visibilidad;
        hereda = Hereda;
    }

    //---------------------------------------------------------------VARIABLES
    public  void addVarGlobal(String nombre, Variable var) {
        
        VarsGlobales.put(nombre, var);
    }

    public Map<String, Variable> getVarGlobales() {
        return VarsGlobales;
    }

    public  Boolean getKeyVarsGlobales(String val) {
        Boolean valor = VarsGlobales.containsKey(val);
        return valor;
    }
    
    //-----------------------------------------------------------------METODOS
    public void addMetodo(String nombre, MetodoGraphik fun) {
        Metodos.put(nombre, fun);
    }

    public Map<String, MetodoGraphik> getMetodos() {
        return Metodos;
    }

    public  Boolean getKeyMetodos(String val) {
        Boolean valor = Metodos.containsKey(val);
        return valor;
    }
}
