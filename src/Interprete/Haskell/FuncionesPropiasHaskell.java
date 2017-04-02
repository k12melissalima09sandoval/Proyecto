/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Haskell;

//import FunPropias.*;
import Interprete.Haskell.Concatena;
import Dibujar.Nodo;
import Interprete.Haskell.ExpresionHaskell;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */

public class FuncionesPropiasHaskell {
  
    static Concatena concatena= new Concatena();
    static ExpresionHaskell exp = new ExpresionHaskell();
    
    
    public Object Recorrer(Nodo raiz,String nombreFuncion){
        switch(raiz.valor.toString()){

            case "Calcular":
                Valor obj2 = (Valor)exp.Expresion(raiz,nombreFuncion);
                return obj2;
                
            case "Concatena":
                Valor obj3 = (Valor)concatena.Recorrer(raiz,nombreFuncion);
                return obj3;
                
            case "Decc":
                Valor obj4=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj4;
                
            case "Indice":
                Valor obj8=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj8;
                
            case "Length":
                Valor obj9=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj9;
                
            case "LlamaFunc":
                Valor obj10=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj10;
                
            case "Max":
                Valor obj11=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj11;
                
            case "Min":
                Valor obj12=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj12;
                
            case "Product":
                Valor obj14=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj14;
                
            case "Succ":
                Valor obj16=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj16;
                
            case "Sum":
                Valor obj17=(Valor)exp.Expresion(raiz,nombreFuncion);
                return obj17;
                
        }
        return null;
    }
}
