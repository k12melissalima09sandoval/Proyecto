/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import FunPropias.Concatena;
import java.util.ArrayList;
import Interprete.FuncionHaskell;
import FunPropias.FuncionesPropiasHaskell;
import Interprete.ExpresionHaskell;
import static Interprete.ExpresionHaskell.ultimoValor;
import Interprete.FuncionHaskellTemp;
import Interprete.Parametros;
import Interprete.Valor;
import Interprete.Variable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author MishaPks
 */
public class RecorreHaskell {

    public static ArrayList<Parametros> parametros;
    public static ArrayList<Parametros> parametrosTemp;
    static FuncionHaskell nueva;
    static FuncionHaskellTemp nuevaTemp;
    static FuncionesPropiasHaskell funPropias = new FuncionesPropiasHaskell();
    static ExpresionHaskell exp = new ExpresionHaskell();
    static Concatena concatena = new Concatena();
    static TablaSimbolosHaskell agrega = new TablaSimbolosHaskell();
    static Variable variable;
    public static String ambito = "consola";

    //-------------------------------------AGREGANDO FUNCIONES---------------------------------
    public static void Recorrido(Nodo raiz) {

        for (int i = 0; i < raiz.hijos.size(); i++) {
            //nombre de la funcion
            String nombreFunc = raiz.hijos.get(i).hijos.get(0).valor.toString();
            if (raiz.hijos.get(i).hijos.size() == 3) {
                //parametros que recibe la funcion
                parametros = new ArrayList();
                parametrosTemp = new ArrayList();
                Nodo param = raiz.hijos.get(i).hijos.get(1);
                for (int j = 0; j < param.hijos.size(); j++) {
                    Parametros p = new Parametros("", param.hijos.get(j).hijos.get(0).valor.toString());
                    p.setValor(null);
                    parametros.add(p);

                }
                Nodo cuerpo = raiz.hijos.get(i).hijos.get(2).hijos.get(0);
                nueva = new FuncionHaskell(nombreFunc, parametros, cuerpo);
                agrega.AgregarFuncion(nombreFunc, nueva);
            } else if (raiz.hijos.get(i).hijos.size() == 2) {
                //nodo del cuerpo de la funcion

                Nodo cuerpo = raiz.hijos.get(i).hijos.get(1).hijos.get(0);

                nueva = new FuncionHaskell(nombreFunc, cuerpo);
                agrega.AgregarFuncion(nombreFunc, nueva);
            }

        }
    }

    //------------------------------------OPERACIONES EN CONSOLA ------------------------------
    public static Object Consola(Nodo raiz, String nombreFuncion) {

        System.out.println("ambito " + ambito);

        switch (raiz.valor.toString()) {

            case "Sentencias":
                ambito = nombreFuncion;
                Valor vSent = new Valor("", "");
                for (Nodo c : raiz.hijos) {
                    vSent = (Valor) Consola(c, nombreFuncion);
                }
                Valor vFinSent = new Valor(vSent.valor, vSent.tipo);
                //ExpresionHaskell.ultimoValor = vSent.valor;
                ambito = "consola";
                return vFinSent;

            case "If":
                Valor valif = (Valor) exp.Expresion(raiz.hijos.get(0), nombreFuncion);

                if (valif != null) {
                    if (valif.tipo.equals("")) {
                        Valor v = new Valor(valif.valor, valif.tipo);
                        return v;
                    } else if (valif.tipo.equals("bool")) {
                        if (valif.valor.equals(true)) {
                            Valor v = (Valor) Consola(raiz.hijos.get(1).hijos.get(0), nombreFuncion);
                            return v;
                        } else {
                            Valor v = (Valor) Consola(raiz.hijos.get(2).hijos.get(0), nombreFuncion);
                            return v;
                        }
                    } else {
                        Valor v = new Valor("no devuelve un bool la condicion if", "");
                        return v;
                    }

                } else {
                    Valor v = new Valor("la condicion if devuelve un nulo", "");
                    return v;
                }
                
            case "Case":
                Valor valcase = (Valor) exp.Expresion(raiz.hijos.get(0), nombreFuncion);
                
                
//----------------------------------------FUNCIONES PROPIAS---------------------------------------
            case "Revers":
                try {
                        Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                        ArrayList vals = (ArrayList) ob4.valor;
                        List va = (List)vals;
                        Collections.reverse(va);
                        ultimoValor = va;
                        Valor val4 = new Valor(va, "numero");
                        return val4;
                    } catch (Exception e) {
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }
                
                
            case "Impr":
                
            case "Par":
                
            case "Asc":
                try {
                        Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                        ArrayList vals = (ArrayList) ob4.valor;
                        List va = (List)vals;
                        Collections.sort(va);
                        ultimoValor = va;
                        Valor val4 = new Valor(va, "numero");
                        return val4;
                    } catch (Exception e) {
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }
            case "Desc":
            case "LlamaFunc":
                Valor valfun = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valfun;

            case "Calcular":
                Valor valcalc = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valcalc;

            case "Succ":
                Valor valsucc = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valsucc;

            case "Decc":
                Valor valdecc = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valdecc;

            case "Min":
                Valor valmin = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valmin;

            case "Max":
                Valor valmax = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valmax;

            case "Sum":
                Valor valsum = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valsum;

            case "Product":
                Valor valpro = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valpro;

            case "Length":
                Valor valtam = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valtam;

            case "Indice":
                Valor valindice = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                return valindice;

            case "D_Lista":
                String nombrelista = raiz.hijos.get(0).valor.toString();
                //id, cadena, Lista, 2Niveles
                Valor val = (Valor) funPropias.Recorrer(raiz.hijos.get(1), nombreFuncion);
                if (val != null) {

                    if (val.tipo.equals("")) {
                        Valor v = new Valor(val.valor, val.tipo);
                        return v;
                    }

                    variable = new Variable(nombrelista, val.valor, val.tipo);
                    agrega.AgregarVariable(nombrelista + "_" + ambito, variable);
                    ExpresionHaskell.ultimoValor = val.valor;
                    String texto = "";
                    ArrayList a = (ArrayList) val.valor;
                    if (a != null) {

                        if (val.tipo.equals("numero")) {
                            ExpresionHaskell.ultimoTipo = "numero";
                            for (int i = 0; i < a.size(); i++) {
                                texto += a.get(i).toString() + ",";
                            }

                            if (texto.lastIndexOf(",") == texto.length() - 1) {
                                texto = texto.substring(0, texto.length() - 1);
                            }
                            texto = "[" + texto + "]";
                            Valor v = new Valor(texto, "");
                            return v;
                        } else if (val.tipo.equals("cadena")) {
                            ExpresionHaskell.ultimoTipo = "cadena";
                            for (int i = 0; i < a.size(); i++) {
                                texto += a.get(i).toString();
                            }
                            texto = "\"" + texto + "\"";
                            Valor v = new Valor(texto, "");
                            return v;
                        } else if (val.tipo.equals("caracter")) {
                            ExpresionHaskell.ultimoTipo = "caracter";
                            for (int i = 0; i < a.size(); i++) {
                                texto += a.get(i).toString();
                            }
                            texto = "\"" + texto + "\"";
                            Valor v = new Valor(texto, "");
                            return v;
                        }
                    }
                }

            case "Concatena":
                Valor valconca = (Valor) funPropias.Recorrer(raiz, nombreFuncion);
                String texto = "";
                ArrayList a = (ArrayList) valconca.valor;
                ExpresionHaskell.ultimoValor = valconca.valor;
                if (a != null) {

                    if (valconca.tipo.equals("numero")) {

                        for (int i = 0; i < a.size(); i++) {
                            texto += a.get(i).toString() + ",";
                        }

                        if (texto.lastIndexOf(",") == texto.length() - 1) {
                            texto = texto.substring(0, texto.length() - 1);
                        }
                        texto = "[" + texto + "]";
                        Valor v = new Valor(texto, "");
                        return v;
                    } else if (valconca.tipo.equals("cadena")) {
                        for (int i = 0; i < a.size(); i++) {
                            texto += a.get(i).toString();
                        }
                        texto = "\"" + texto + "\"";
                        Valor v = new Valor(texto, "");
                        return v;
                    } else if (valconca.tipo.equals("caracter")) {
                        for (int i = 0; i < a.size(); i++) {
                            texto += a.get(i).toString();
                        }
                        texto = "\"" + texto + "\"";
                        Valor v = new Valor(texto, "");
                        return v;

                    } else {
                        Valor v = new Valor(a.get(0).toString(), "");
                        return v;
                    }
                }
        }

        return null;
    }
}
