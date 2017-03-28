/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.Operacion.*;
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
    MenorQ menorq = new MenorQ();
    MayorQ mayorq = new MayorQ();
    MayorIgualQ mayorigualq = new MayorIgualQ();
    MenorIgualQ menorigualq = new MenorIgualQ();
    IgualIgual igualigual = new IgualIgual();
    Diferente diferente = new Diferente();

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
                case "Unario": {
                    Valor v = (Valor) Expresion(exp, nombreFuncion);
                    if (v != null) {
                        if (v.valor != null) {
                            if (v.tipo.equals("numero")) {
                                int num = Integer.parseInt(v.valor.toString());
                                int resultado = num * (-1);
                                Valor v2 = new Valor(resultado, "numero");
                                return v2;
                            } else if (v.tipo.equals("decimal")) {
                                Double num = Double.parseDouble(v.valor.toString());
                                Double resultado = num * (-1);
                                Valor v2 = new Valor(resultado, "decimal");
                                return v2;
                            } else {
                                Errores.ErrorSemantico("No se pude aplicar el Unario a un tipo diferente "
                                        + "a numerico", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                }
                case "!": {
                    Valor v = (Valor) Expresion(exp, nombreFuncion);
                    if (v != null) {
                        if (v.valor != null) {
                            if (v.tipo.equals("bool")) {
                                if (v.valor.equals("verdadero")) {
                                    Valor v2 = new Valor("falso", "bool");
                                    return v2;
                                } else {
                                    Valor v2 = new Valor("verdadero", "bool");
                                    return v2;
                                }
                            } else {
                                Errores.ErrorSemantico("En negacion Not(!) no es de tipo booleano", 0, 0);
                            }
                        } else {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                }
                case "Incremento":{
                    Valor v = (Valor) Expresion(exp, nombreFuncion);
                    if(v.tipo.equals("error")){
                        Valor v2 = new Valor("","error");
                        return v2;
                    }
                    if(v.tipo.equals("numero")){
                        int i = Integer.parseInt(v.valor.toString()) + 1;
                        Valor v2 = new Valor(i,"numero");
                        return v2;
                    }else if(v.tipo.equals("decimal")){
                        Double i = Double.parseDouble(v.valor.toString()) + 1;
                        Valor v2 = new Valor(i,"decimal");
                        return v2;
                    }else if(v.tipo.equals("caracter")){
                        int i = v.valor.toString().codePointAt(0) + 1;
                        Valor v2 = new Valor(i,"numero");
                        return v2;
                    }else{
                        Errores.ErrorSemantico("No se puede incrementar", 0, 0);
                        Valor v2 = new Valor("","error");
                        return v2;
                    }
                }
                case "Decremento":{
                    Valor v = (Valor) Expresion(exp, nombreFuncion);
                    if(v.tipo.equals("error")){
                        Valor v2 = new Valor("","error");
                        return v2;
                    }
                    if(v.tipo.equals("numero")){
                        int i = Integer.parseInt(v.valor.toString()) - 1;
                        Valor v2 = new Valor(i,"numero");
                        return v2;
                    }else if(v.tipo.equals("decimal")){
                        Double i = Double.parseDouble(v.valor.toString()) - 1;
                        Valor v2 = new Valor(i,"decimal");
                        return v2;
                    }else if(v.tipo.equals("caracter")){
                        int i = v.valor.toString().codePointAt(0) - 1;
                        Valor v2 = new Valor(i,"numero");
                        return v2;
                    }else{
                        Errores.ErrorSemantico("No se puede decrementar", 0, 0);
                        Valor v2 = new Valor("","error");
                        return v2;
                    }
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

                case "||":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("bool")) {
                                if (izq.valor.equals("verdadero") || der.valor.equals("verdadero")) {
                                    Valor v = new Valor("verdadero", "bool");
                                    return v;
                                } else {
                                    Valor v = new Valor("falso", "bool");
                                    return v;
                                }
                            } else {
                                Errores.ErrorSemantico("En comparacion (||) no son tipo booleano", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("En comparacion (||) no son tipo del mismo tipo", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }

                case "&|":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("bool")) {
                                if (izq.valor.equals(der.valor)) {
                                    Valor v = new Valor("falso", "bool");
                                    return v;
                                } else {
                                    Valor v = new Valor("verdadero", "bool");
                                    return v;
                                }
                            } else {
                                Errores.ErrorSemantico("En comparacion (&|) no son tipo booleano", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("En comparacion (&|) no son tipo del mismo tipo", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }

                case "&&":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("bool")) {
                                if (izq.valor.equals("verdadero") && der.valor.equals("verdadero")) {
                                    Valor v = new Valor("verdadero", "bool");
                                    return v;
                                } else {
                                    Valor v = new Valor("falso", "bool");
                                    return v;
                                }
                            } else {
                                Errores.ErrorSemantico("En comparacion (&&) no son tipo booleano", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("En comparacion (&&) no son tipo del mismo tipo", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }

                case "<":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v = (Valor) menorq.MenorQ(izq, der);
                        if (v.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v2 = new Valor(v.valor, v.tipo);
                        return v2;
                    }

                case ">":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v = (Valor) mayorq.MayorQ(izq, der);
                        if (v.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v2 = new Valor(v.valor, v.tipo);
                        return v2;
                    }

                case ">=":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v = (Valor) mayorigualq.MayorIgualQ(izq, der);
                        if (v.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v2 = new Valor(v.valor, v.tipo);
                        return v2;
                    }

                case "<=":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v = (Valor) menorigualq.MenorIgualQ(izq, der);
                        if (v.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v2 = new Valor(v.valor, v.tipo);
                        return v2;
                    }

                case "==":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v = (Valor) igualigual.IgualIgual(izq, der);
                        if (v.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v2 = new Valor(v.valor, v.tipo);
                        return v2;
                    }

                case "!=":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals("error") || der.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v = (Valor) diferente.Diferente(izq, der);
                        if (v.tipo.equals("error")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                        Valor v2 = new Valor(v.valor, v.tipo);
                        return v2;
                    }
            }
        }

        return null;
    }
}
