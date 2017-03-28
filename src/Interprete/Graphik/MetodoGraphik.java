/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;
import Interprete.Parametros;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class MetodoGraphik {
    
    public String tipo;
    public String nombre;
    Boolean hereda = false;
    public ArrayList<Parametros> listaParametros = new ArrayList();
    public String visibilidad;
    Nodo cuerpo;
    
    public MetodoGraphik(String tipo, String nombre, String visible,Nodo cuerpo){
        this.tipo = tipo;
        this.nombre = nombre;
        this.visibilidad = visible;
        this.cuerpo = cuerpo;
        this.hereda = false;
    }   
    
    public void setHereda(Boolean si ){
        this.hereda = true;
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
