/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import Interprete.FuncionHaskell;
import Interprete.Variable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class TablaSimbolosHaskell {
    
    Map<String, FuncionHaskell> listaFunciones = new LinkedHashMap<>();
    Map<String, Variable> listaListasConsola = new LinkedHashMap<>();
      
    
    private static TablaSimbolosHaskell tabla;
    
    public TablaSimbolosHaskell(){
        
    }
    
    public void AgregarVariable(String nombre,Variable var){
        listaListasConsola.put(nombre,var);
    }
    
    public void AgregarFuncion(String nombre, FuncionHaskell fun){
        listaFunciones.put(nombre, fun);
    }
    
    public Map<String, FuncionHaskell> ObtenerListaFunciones(){
        return listaFunciones;
    }
    public static TablaSimbolosHaskell ObtenerTabla(){
        if(tabla==null){
            
            tabla = new TablaSimbolosHaskell();
        }
        return tabla;
    }
    
}
