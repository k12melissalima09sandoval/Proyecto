/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Interprete.Haskell.FuncionHaskell;
import Interprete.Variable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class TablaSimbolosHaskell {
    
    public static Map<String, FuncionHaskell> listaFunciones = new LinkedHashMap<>();
   
    public static Map<String, Variable> listaListasConsola = new LinkedHashMap<>();
      
    
    private static TablaSimbolosHaskell tabla;
    
    public TablaSimbolosHaskell(){
        
    }
    
    public Boolean getKeyListas(String val){
        Boolean valor = listaListasConsola.containsKey(val);
        return valor;
    }
    public Boolean getKeyFunciones(String val){
        Boolean valor = listaFunciones.containsKey(val);
        return valor;
    }
    
    
    public ArrayList<String> getValores(int i){
        ArrayList<String> a = (ArrayList)listaListasConsola.get("lista").valor;
        return a;
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

    
    public Map<String, Variable> ObtenerListaListas(){
        return listaListasConsola;
    }
    public static TablaSimbolosHaskell ObtenerTabla(){
        if(tabla==null){
            
            tabla = new TablaSimbolosHaskell();
        }
        return tabla;
    }
    
}
