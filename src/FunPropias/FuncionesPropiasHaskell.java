/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

//import FunPropias.*;
import Ast.Nodo;

/**
 *
 * @author MishaPks
 */

public class FuncionesPropiasHaskell {
   
    static Concatena concatena= new Concatena();
    
    public Object Recorrer(Nodo raiz){
        switch(raiz.valor.toString()){
            
            case "Asc":
                break;
                
            case "Calcular":
                break;
                
            case "Concatena":
                Object val = concatena.Recorrer(raiz.hijos.get(0));
                return val;
                
            case "Decc":
                break;
                
            case "D_Lista":
                break;
                
            case "Desc":
                break;
                
            case "Impr":
                break;
                
            case "Indice":
                break;
                
            case "Length":
                break;
                
            case "LlamaFunc":
                break;
                
            case "Max":
                break;
                
            case "Min":
                break;
                
            case "Par":
                break;
                
            case "Product":
                break;
                
            case "Revers":
                break;
                
            case "Succ":
                break;
                
            case "Sum":
                break;
                
        }
        return null;
    }
}
