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

    public Object Recorrer(Nodo raiz) {
        ArrayList<Object> paso = new ArrayList();
        String tipo = "inicia";
        for (Nodo c : raiz.hijos) {
            Valor val = (Valor) Listas(c);
            if (val != null) {
                if (!tipo.equals("inicia")) {
                    System.out.println("" + val.tipo + " " + tipo);
                    if (val.tipo.equals(tipo)) {

                        if (val.tipo.equals("cadena")) {
                            ArrayList a = (ArrayList) val.valor;
                            if (a.get(0) instanceof ArrayList) {
                                for (int i = 0; i < a.size(); i++) {
                                    ArrayList temp = (ArrayList) a.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        paso.add(temp.get(j).toString());
                                        tipo = val.tipo;
                                    }
                                }
                            } else {
                                for (int i = 0; i < a.size(); i++) {
                                    paso.add(a.get(i).toString());
                                    tipo = val.tipo;
                                }
                            }
                        } else {

                            ArrayList a = (ArrayList) val.valor;
                            if (a.size() > 1) {
                                for (int i = 0; i < a.size(); i++) {
                                    //ArrayList temp=(ArrayList)a.get(i);
                                    ArrayList temp = new ArrayList();
                                    temp.add(a.get(i));
                                    for (int j = 0; j < temp.size(); j++) {
                                        paso.add(temp.get(j));
                                        tipo = val.tipo;
                                    }
                                }
                            } else {
                                for (int i = 0; i < a.size(); i++) {
                                    paso.add(a.get(i));
                                    tipo = val.tipo;
                                }
                            }
                        }
                    } else {
                        System.out.println("Tipos diferentes para la concatenacion");
                        ArrayList temp = new ArrayList();
                        temp.add("tipos diferentes");
                        Valor v = new Valor(temp,"");
                        return v;
                    }
                } else if (val.tipo.equals("cadena")) {
                    ArrayList a = (ArrayList) val.valor;
                    if (a.get(0) instanceof ArrayList) {
                        for (int i = 0; i < a.size(); i++) {
                            ArrayList temp = (ArrayList) a.get(i);
                            for (int j = 0; j < temp.size(); j++) {
                                paso.add(temp.get(j).toString());
                                tipo = val.tipo;
                            }
                        }
                    } else {
                        for (int i = 0; i < a.size(); i++) {
                            paso.add(a.get(i).toString());
                            tipo = val.tipo;
                        }
                    }
                } else {

                    ArrayList a = (ArrayList) val.valor;
                    if (a.size() > 1) {
                        for (int i = 0; i < a.size(); i++) {
                            //ArrayList temp=(ArrayList)a.get(i);
                            ArrayList temp = new ArrayList();
                            temp.add(a.get(i));
                            for (int j = 0; j < temp.size(); j++) {
                                paso.add(temp.get(j));
                                tipo = val.tipo;
                            }
                        }
                    } else {
                        for (int i = 0; i < a.size(); i++) {
                            paso.add(a.get(i));
                            tipo = val.tipo;
                        }
                    }
                }
            }
        }
        Valor fin = new Valor(paso, tipo);
        return fin;

    }

    public Object Listas(Nodo raiz) {
        String temp = raiz.valor.toString();
        if (temp.equals("id")) {
            String nombre = raiz.hijos.get(0).valor.toString();
            Map<String, Variable> l = lista.ObtenerListaListas();
            if (l != null) {
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        Boolean g = lista.getKeyListas(nombre);
                        if (g.equals(true)) {

                            Object obtener = (Object) l.get(nombre).valor;
                            Valor val = new Valor(obtener, l.get(nombre).tipo);
                            return val;
                        }
                    }
                } else {
                    System.out.println("no hay ninguna lista declarada");
                    return null;
                }
            }

        } else if (temp.equals("porcentaje")) {
            Valor v = (Valor) exp.Expresion(raiz);
            return v;

        } else if (temp.equals("cadena")) {

            ArrayList<Object> cadena = new ArrayList();
            String valor = raiz.hijos.get(0).valor.toString();
            for (int i = 0; i < valor.length(); i++) {
                char letra = valor.charAt(i);
                cadena.add(letra);
            }
            Valor v = new Valor(cadena, "cadena");
            return v;

        } else if (temp.equals("Lista")) {
            //recorrer hijos para la concatenacion
            ArrayList<Object> cadena = new ArrayList();
            String tipo = "";
            for (Nodo nodo : raiz.hijos) {
                Valor valor = (Valor) exp.Expresion(nodo);
                cadena.add(valor.valor);
                tipo = valor.tipo;
            }
            Valor v = new Valor(cadena, tipo);
            return v;

        } else if (temp.equals("2Niveles")) {
            ArrayList<Object> nivel1 = new ArrayList();
            ArrayList<Object> nivel2 = new ArrayList();
            ArrayList<Object> juntos = new ArrayList();
            //voy a traer las dos listas
            Valor val = (Valor) Listas(raiz.hijos.get(0));
            Valor val2 = (Valor) Listas(raiz.hijos.get(1));

            nivel1 = (ArrayList) val.valor;
            nivel2 = (ArrayList) val2.valor;

            juntos.add(nivel1);
            juntos.add(nivel2);

            Valor v = new Valor(juntos, val.tipo);
            return v;

        }
        return null;
    }
}
