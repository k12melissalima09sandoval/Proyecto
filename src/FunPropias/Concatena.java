/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

import Ast.Nodo;
import Interprete.ExpresionHaskell;
import Interprete.Valor;
import Interprete.Variable;
import Simbolos.TablaSimbolosHaskell;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class Concatena {
    static ExpresionHaskell exp = new ExpresionHaskell();
    static TablaSimbolosHaskell lista = new TablaSimbolosHaskell();
    
    
    public Object Recorrer(Nodo raiz){
        String temp= raiz.valor.toString();
        if(temp.equals("id"))
        {
            String nombre =  raiz.hijos.get(0).valor.toString();
            Map<String, Variable> l = lista.ObtenerListaListas();
                    if(l!=null){
                        if(l.size()>0){
                            for (int i = 0; i < l.size(); i++) {
                                Boolean g = lista.getKey(nombre);
                                if(g.equals(true)){
                                    
                                    Object obtener = (Object)l.get(nombre).valor;
                                    Valor val = new Valor(obtener,l.get(nombre).tipo);
                                    return val;
                                }
                            }
                        }else {
                            System.out.println("no hay ninguna lista declarada");
                            return null;
                        }
                    }
            
        
        }else if(temp.equals("cadena")){
            
            ArrayList<Object> cadena=new ArrayList();
            String valor = raiz.hijos.get(0).valor.toString();
            for (int i = 0; i < valor.length(); i++) {
                char letra = valor.charAt(i);
                cadena.add(letra);
            }
            Valor v = new Valor(cadena,"cadena");
            return v;
            
        }else if(temp.equals("Lista")){
            //recorrer hijos para la concatenacion
            ArrayList<Object> cadena = new ArrayList();
            String tipo="";
            for(Nodo nodo: raiz.hijos){
                Valor valor = (Valor)exp.Expresion(nodo);
                cadena.add(valor.valor);
                tipo = valor.tipo;
            }
            Valor v=new Valor(cadena,tipo);
            return v;
        }
        return null;
    }
}
