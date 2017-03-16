/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

//import FunPropias.*;
import Ast.Nodo;
import Interprete.ExpresionHaskell;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */

public class FuncionesPropiasHaskell {
  
    static Concatena concatena= new Concatena();
    static ExpresionHaskell exp = new ExpresionHaskell();
    static LlamaFuncion llamaF = new LlamaFuncion();
    
    
    public Object Recorrer(Nodo raiz){
        switch(raiz.valor.toString()){
            
            case "Asc":
                break;
                
            case "Calcular":
                Valor obj2 = (Valor)exp.Expresion(raiz);
                return obj2;
                
            case "Concatena":
                Valor obj3 = (Valor)concatena.Recorrer(raiz);
                return obj3;
                
            case "Decc":
                Valor obj4=(Valor)exp.Expresion(raiz);
                return obj4;
                
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
                Valor obj11=(Valor)exp.Expresion(raiz);
                return obj11;
                
            case "Min":
                Valor obj12=(Valor)exp.Expresion(raiz);
                return obj12;
                
            case "Par":
                break;
                
            case "Product":
                break;
                
            case "Revers":
                break;
                
            case "Succ":
                Valor obj16=(Valor)exp.Expresion(raiz);
                return obj16;
                
            case "Sum":
                break;
                
        }
        return null;
    }
}
