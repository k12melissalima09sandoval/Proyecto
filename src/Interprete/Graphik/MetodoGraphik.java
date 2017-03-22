/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;
import Interprete.Parametros;
import Interprete.Variable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class MetodoGraphik {
    
    String tipo;
    String nombre;
    public static Map<String, Parametros> listaParametros = new LinkedHashMap<>();
    public static Map<String, Variable> listaVariables = new LinkedHashMap<>();
    String visibilidad;
    Nodo cuerpo;
    
    public MetodoGraphik(String tipo, String nombre, String visible,Nodo cuerpo){
        this.tipo = tipo;
        this.nombre = nombre;
        this.visibilidad = visible;
        this.cuerpo = cuerpo;
    }
    
    public void setParametros(String nombre,Parametros p){
        listaParametros.put(nombre, p);
    }
    public Object getParametros(Object Parametros){
        return listaParametros;
    }
    public Nodo getCuerpo(){
        return cuerpo;
    }
            
}
