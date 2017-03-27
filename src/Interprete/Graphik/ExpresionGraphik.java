/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */
public class ExpresionGraphik {


    public Object Expresion(Nodo raiz, String nombreFuncion) {

        if (raiz.hijos.size() == 1) {

        } else if (raiz.hijos.size() == 2) {
            Valor izq;
            Valor der;
            Double resultado = 0.0;
            switch (raiz.valor.toString()) {

                case "+":

                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        
                    } else {
                        
                    }
            }
        }

        return null;
    }
}
