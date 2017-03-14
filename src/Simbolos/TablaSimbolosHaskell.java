/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import Interprete.FuncionHaskell;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class TablaSimbolosHaskell {
    
    Map<String, FuncionHaskell> lista = new LinkedHashMap<>();
      
    
    private static TablaSimbolosHaskell tabla;
    
    public TablaSimbolosHaskell(){
        
    }
    
    public void AgregarFuncion(String nombre, FuncionHaskell fun){
        lista.put(nombre, fun);
    }
    
    public Map<String, FuncionHaskell> ObtenerListaFunciones(){
        return lista;
    }
    public static TablaSimbolosHaskell ObtenerTabla(){
        if(tabla==null){
            
            tabla = new TablaSimbolosHaskell();
        }
        return tabla;
    }
    
}
