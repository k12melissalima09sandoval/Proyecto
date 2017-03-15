/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

import Ast.Nodo;
import Interprete.ExpresionHaskell;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class Concatena {
    static ExpresionHaskell exp = new ExpresionHaskell();
    
    public Object Recorrer(Nodo raiz){
        String temp= raiz.valor.toString();
        if(temp.equals("id"))
        {
            String valor =  raiz.hijos.get(0).valor.toString();
            return valor;
            
        }else if(temp.equals("cadena")){
            
            ArrayList<Object> cadena=new ArrayList();
            String valor = raiz.hijos.get(0).valor.toString();
            for (int i = 0; i < valor.length(); i++) {
                char letra = valor.charAt(i);
                cadena.add(letra);
            }
            return cadena;
            
        }else if(temp.equals("Lista")){
            
            ArrayList<Object> cadena = new ArrayList();
            
            for(Nodo nodo: raiz.hijos){
                Object valor = (Object)exp.Expresion(nodo);
                cadena.add(valor);
            }
            return cadena;
        }
        return null;
    }
}
