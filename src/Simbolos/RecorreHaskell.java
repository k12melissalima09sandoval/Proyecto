/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import java.util.ArrayList;
import Interprete.FuncionHaskell;
import Simbolos.TablaSimbolosHaskell;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class RecorreHaskell {
    
    public static ArrayList<String> parametros;
    static FuncionHaskell nueva;
    static TablaSimbolosHaskell agrega = new TablaSimbolosHaskell();
    
    public static void Recorrido(Nodo raiz){
        
        for (int i = 0; i < raiz.hijos.size(); i++) {
            //nombre de la funcion
            String nombreFunc = raiz.hijos.get(i).hijos.get(0).valor.toString();
            
            //parametros que recibe la funcion
            parametros = new ArrayList();
            Nodo param=raiz.hijos.get(i).hijos.get(1);
            for (int j = 0; j < param.hijos.size(); j++) {
                parametros.add(param.hijos.get(j).valor.toString());
            }
            
            //nodo del cuerpo de la funcion
            Nodo cuerpo = raiz.hijos.get(i).hijos.get(2).hijos.get(0);
            
            nueva = new FuncionHaskell(nombreFunc,parametros,cuerpo);
            agrega.AgregarFuncion(nombreFunc, nueva); 
        }
        //Map<String, FuncionHaskell> l = agrega.ObtenerListaFunciones();
    }
}
