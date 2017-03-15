/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

//import FunPropias.*;
import Ast.Nodo;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */

public class FuncionesPropiasHaskell {
   
    static Asc asc = new Asc();
    static Calcular calcular = new Calcular();
    static Concatena concatena= new Concatena();
    static Decc decc = new Decc();
    static Desc desc = new Desc();
    static Impr impr = new Impr();
    static Indice indice = new Indice();
    static Length length = new Length();
    static LlamaFuncion llamaF = new LlamaFuncion();
    static Max max = new Max();
    static Min min = new Min();
    static Par par = new Par();
    static Product product = new Product();
    static Revers revers = new Revers();
    static Succ succ = new Succ();
    static Sum sum = new Sum();
    
    public Object Recorrer(Nodo raiz){
        switch(raiz.valor.toString()){
            
            case "Asc":
                break;
                
            case "Calcular":
                break;
                
            case "Concatena":
                Valor obj3 = (Valor)concatena.Recorrer(raiz.hijos.get(0));
                return obj3;
                
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
