/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

import Ast.Nodo;
import Interprete.ExpresionHaskell;
import Interprete.FuncionHaskell;
import Interprete.Parametros;
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

    static TablaSimbolosHaskell lista = new TablaSimbolosHaskell();
    static ExpresionHaskell exp = new ExpresionHaskell();

    public Object Recorrer(Nodo raiz) {
        ArrayList<Object> paso = new ArrayList();
        String tipo = "inicia";
        for (Nodo c : raiz.hijos) {
            Valor val = (Valor) Listas(c);
            if (val != null) {
                if(val.tipo.equals("")){
                    Valor v= new Valor(val.valor,val.tipo);
                    return v;
                }
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
                        Valor v = new Valor(temp, "");
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

                    } else if(val.tipo.equals("")){
                        Valor v = new Valor("lista no declarada",val.tipo);
                        return v;
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
            Boolean encontrado= false;
            if (l != null) {
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        Boolean g = lista.getKeyListas(nombre);
                        if (g.equals(true)) {
                            encontrado = true;
                            Object obtener = (Object) l.get(nombre).valor;
                            Valor val = new Valor(obtener, l.get(nombre).tipo);
                            return val;
                        } else {
                            /// mando a traer los parametros y busco la variable
                            Map<String, FuncionHaskell> fun = lista.ObtenerListaFunciones();

                            if (fun != null) {
                                if (fun.size() > 0) {
                                    for (int j = 0; j < fun.size(); j++) {
                                        if (!"".equals(ExpresionHaskell.nombreFuncion)) {
                                            Boolean g2 = lista.getKeyFunciones(ExpresionHaskell.nombreFuncion);
                                            if (g2.equals(true)) {
                                                encontrado=true;
                                                ArrayList<Parametros> parametros = (ArrayList) fun.get(ExpresionHaskell.nombreFuncion).getParametros();

                                                for (int k = 0; k < parametros.size(); k++) {
                                                    if (nombre.equals(parametros.get(k).nombre)) {
                                                        Valor val = new Valor(parametros.get(k).valor, parametros.get(k).tipo);
                                                        return val;
                                                    }
                                                }
                                            } else {
                                                Valor vi = new Valor(ExpresionHaskell.nombreFuncion + " no declarada", "");
                                                return vi;
                                            }
                                        }
                                    }
                                } else {
                                    Valor v2 = new Valor(nombre, "id");
                                    return v2;
                                }
                            } else {
                                Valor v2 = new Valor("lista no declarada", "");
                                return v2;
                            }

                        }

                    }
                } else {
                    Map<String, FuncionHaskell> fun = lista.ObtenerListaFunciones();
                    if (fun != null) {
                        if (fun.size() > 0) {
                            for (int j = 0; j < fun.size(); j++) {
                                if (!"".equals(ExpresionHaskell.nombreFuncion)) {
                                    Boolean g3 = lista.getKeyFunciones(ExpresionHaskell.nombreFuncion);
                                    if (g3.equals(true)) {
                                        encontrado=true;
                                        ArrayList<Parametros> parametros = (ArrayList) fun.get(ExpresionHaskell.nombreFuncion).getParametros();

                                        for (int k = 0; k < parametros.size(); k++) {
                                            if (nombre.equals(parametros.get(k).nombre)) {
                                                Valor val = new Valor(parametros.get(k).valor, parametros.get(k).tipo);
                                                
                                                return val;
                                            }
                                        }
                                    } else {
                                        Valor vi = new Valor(ExpresionHaskell.nombreFuncion + " no declarada", "");
                                        return vi;
                                    }
                                }
                            }
                        } else {
                            Valor v2 = new Valor("lista no declarada", "");
                            return v2;
                        }
                    } else {
                        Valor v2 = new Valor(nombre, "id");
                        return v2;
                    }
                }
                
                if(encontrado.equals(false)){
                    Valor v2 = new Valor("lista no declarada", "");
                    return v2;
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
                if(valor.tipo.equals("")){
                    Valor v = new Valor(valor.valor,valor.tipo);
                    return v;
                }
                cadena.add(valor.valor);
                tipo = valor.tipo;
            }
            Valor v = new Valor(cadena, tipo);
            return v;

        } else if (temp.equals("2Niveles")) {
            Valor val = new Valor("", "");
            ArrayList<Object> nivel = new ArrayList();
            ArrayList<Object> juntos = new ArrayList();
            //voy a traer las dos listas
            for (Nodo c : raiz.hijos) {
                nivel = new ArrayList();
                val = (Valor) Listas(c);
                nivel = (ArrayList) val.valor;
                juntos.add(nivel);
            }
            /*Valor val = (Valor) Listas(raiz.hijos.get(0));
            Valor val2 = (Valor) Listas(raiz.hijos.get(1));

            nivel1 = (ArrayList) val.valor;
            nivel2 = (ArrayList) val2.valor;

            juntos.add(nivel1);
            juntos.add(nivel2);*/

            Valor v = new Valor(juntos, val.tipo);
            return v;

        }
        return null;
    }
}
