/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;
import Interprete.Operacion.Division;
import Interprete.Operacion.Multiplicacion;
import Interprete.Operacion.Potencia;
import Interprete.Operacion.Resta;
import Interprete.Operacion.Suma;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */
public class ExpresionGraphik {

    Suma suma = new Suma();
    Resta resta = new Resta();
    Multiplicacion mult = new Multiplicacion();
    Division div = new Division();
    Potencia pot = new Potencia();

    public Object Expresion(Nodo raiz, String nombreFuncion) {

        if (raiz.hijos.size() == 1) {
            Nodo exp = raiz.hijos.get(0);
            switch (raiz.valor.toString()) {
                case "Exp": {
                    Valor v = (Valor) Expresion(exp, nombreFuncion);
                    return v;
                }
                case "numero": {
                    int num = Integer.parseInt(raiz.hijos.get(0).valor.toString());
                    Valor v = new Valor(num, "numero");
                    return v;
                }
                case "decimal": {
                    Double num = Double.parseDouble(raiz.hijos.get(0).valor.toString());
                    Valor v = new Valor(num, "decimal");
                    return v;
                }
                case "cadena": {
                    String cad = raiz.hijos.get(0).valor.toString().replace("\"", "");
                    Valor v = new Valor(cad, "cadena");
                    return v;
                }
                case "caracter": {
                    String cad = raiz.hijos.get(0).valor.toString().replace("'", "");
                    Valor v = new Valor(cad, "caracter");
                    return v;
                }
                case "verdadero": {
                    Valor v = new Valor("verdadero", "bool");
                    return v;
                }
                case "falso": {
                    Valor v = new Valor("falso", "bool");
                    return v;
                }
            }

        } else if (raiz.hijos.size() == 2) {
            Valor izq;
            Valor der;
            switch (raiz.valor.toString()) {

                case "+":

                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        Valor v = (Valor) suma.Suma(izq, der);
                        return v;
                    } else {
                        Valor v = new Valor("", "error");
                        return v;
                    }

                case "-":

                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        Valor v = (Valor) resta.Resta(izq, der);
                        return v;
                    } else {
                        Valor v = new Valor("", "error");
                        return v;
                    }

                case "*":

                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        Valor v = (Valor) mult.Multiplicacion(izq, der);
                        return v;
                    } else {
                        Valor v = new Valor("", "error");
                        return v;
                    }

                case "/":

                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        Valor v = (Valor) div.Division(izq, der);
                        return v;
                    } else {
                        Valor v = new Valor("", "error");
                        return v;
                    }

                case "^":

                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        Valor v = (Valor) pot.Potencia(izq, der);
                        return v;
                    } else {
                        Valor v = new Valor("", "error");
                        return v;
                    }
            }
        }

        return null;
    }
}
