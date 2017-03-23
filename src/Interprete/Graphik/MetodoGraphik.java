/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;
import Interprete.Parametros;
import Interprete.Variable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class MetodoGraphik {
    
    String tipo;
    String nombre;
    public static ArrayList<Parametros> listaParametros = new ArrayList();
    String visibilidad;
    Nodo cuerpo;
    
    public MetodoGraphik(String tipo, String nombre, String visible,Nodo cuerpo){
        this.tipo = tipo;
        this.nombre = nombre;
        this.visibilidad = visible;
        this.cuerpo = cuerpo;
    }   
    
    public void setParametros(Parametros p){
        listaParametros.add(p);
    }
    public ArrayList getParametros(){
        return listaParametros;
    }
    public Nodo getCuerpo(){
        return cuerpo;
    }
            
}
