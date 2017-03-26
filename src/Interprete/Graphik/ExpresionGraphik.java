/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;
import Interprete.OperacionCasteo;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */
public class ExpresionGraphik {

    OperacionCasteo casteo = new OperacionCasteo();

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
                        Valor vmas = (Valor) casteo.OpCasteo(izq.tipo, der.tipo);
                        if (izq.tipo.equals("numero")) {
                            if (der.tipo.equals("numero")) {

                                Valor v = new Valor("", "numero");
                                return v;

                            } else if (der.tipo.equals("decimal")) {

                                Valor v = new Valor("", "decimal");
                                return v;

                            } else if (der.tipo.equals("caracter")) {

                                Valor v = new Valor("", "numero");
                                return v;

                            } else if (der.tipo.equals("cadena")) {

                                Valor v = new Valor("", "cadena");
                                return v;

                            } else if (der.tipo.equals("verdadero")) {
                                
                            } else if (der.tipo.equals("falso")) {

                                Valor v = new Valor("", "numero");
                                return v;
                            }
                        } else if (izq.tipo.equals("decimal")) {
                            if (der.tipo.equals("numero")) {
                                Valor v = new Valor("", "decimal");
                                return v;
                            } else if (der.tipo.equals("decimal")) {
                                Valor v = new Valor("", "decimal");
                                return v;
                            } else if (der.tipo.equals("caracter")) {
                                Valor v = new Valor("", "decimal");
                                return v;
                            } else if (der.tipo.equals("cadena")) {
                                Valor v = new Valor("", "cadena");
                                return v;
                           } else if (der.tipo.equals("verdadero")) {
                                
                            } else if (der.tipo.equals("falso")) {

                                Valor v = new Valor("", "numero");
                                return v;
                            }

                        } else if (izq.tipo.equals("caracter")) {
                            if (der.tipo.equals("numero")) {
                                Valor v = new Valor("", "numero");
                                return v;
                            } else if (der.tipo.equals("decimal")) {

                                Valor v = new Valor("", "decimal");
                                return v;
                            } else if (der.tipo.equals("caracter")) {
                                Valor v = new Valor("", "error");
                                return v;

                            } else if (der.tipo.equals("cadena")) {
                                Valor v = new Valor("", "cadena");
                                return v;
                            } else if (der.tipo.equals("verdadero") || der.tipo.equals("falso")) {
                                Valor v = new Valor("", "error");
                                return v;
                            }

                        } else if (izq.tipo.equals("cadena")) {
                            if (der.tipo.equals("numero")) {
                                Valor v = new Valor("", "cadena");
                                return v;
                            } else if (der.tipo.equals("decimal")) {
                                Valor v = new Valor("", "cadena");
                                return v;
                            } else if (der.tipo.equals("caracter")) {
                                Valor v = new Valor("", "cadena");
                                return v;
                            } else if (der.tipo.equals("cadena")) {
                                Valor v = new Valor("", "cadena");
                                return v;
                            } else if (der.tipo.equals("verdadero")) {
                                
                            } else if (der.tipo.equals("falso")) {

                                Valor v = new Valor("", "numero");
                                return v;
                            }

                        } else if (izq.tipo.equals("verdadero")) {
                            if (der.tipo.equals("numero")) {
                                Valor v = new Valor("", "numero");
                                return v;
                            } else if (der.tipo.equals("decimal")) {
                                Valor v = new Valor("", "decimal");
                                return v;
                            } else if (der.tipo.equals("caracter")) {
                                Valor v = new Valor("", "error");
                                return v;
                            } else if (der.tipo.equals("cadena")) {
                                Valor v = new Valor("", "error");
                                return v;
                            } else if (der.tipo.equals("verdadero")) {
                                
                            } else if (der.tipo.equals("falso")) {

                                Valor v = new Valor("", "numero");
                                return v;
                            }

                        }else if (izq.tipo.equals("falso")) {
                            if (der.tipo.equals("numero")) {
                                Valor v = new Valor("", "numero");
                                return v;
                            } else if (der.tipo.equals("decimal")) {
                                Valor v = new Valor("", "decimal");
                                return v;
                            } else if (der.tipo.equals("caracter")) {
                                Valor v = new Valor("", "error");
                                return v;
                            } else if (der.tipo.equals("cadena")) {
                                Valor v = new Valor("", "error");
                                return v;
                            } else if (der.tipo.equals("verdadero")) {
                                
                            } else if (der.tipo.equals("falso")) {

                                Valor v = new Valor("", "numero");
                                return v;
                            }

                        }

                        if (vmas.tipo != "error") {
                            if (vmas.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = num1 + num2;
                                Valor v = new Valor(resultado, "numero");

                                return v;
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = num1 + num2;

                                Valor v = new Valor(resultado, "numero");
                                return v;
                            } else {
                                //error
                                Valor v = new Valor("el ultimo valor es nulo", "");
                                return v;
                            }

                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                        System.out.println("los valores tienen nulo");
                    }
            }
        }

        return null;
    }
}
