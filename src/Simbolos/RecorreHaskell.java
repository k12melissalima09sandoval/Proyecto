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
import Interprete.Parametros;
import Interprete.Valor;
import Interprete.Variable;

/**
 *
 * @author MishaPks
 */
public class RecorreHaskell {

    public static ArrayList<Parametros> parametros;
    static FuncionHaskell nueva;
    static FuncionesPropiasHaskell funPropias = new FuncionesPropiasHaskell();
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
    public static Object Consola(Nodo raiz) {

        System.out.println("ambito " + ambito);
        
        switch (raiz.valor.toString()) {
            
            case "Sentencias":
                ambito=ExpresionHaskell.nombreFuncion;
                Valor vSent = new Valor("", "");
                for (Nodo c : raiz.hijos) {
                    vSent = (Valor) Consola(c);
                }
                Valor vFinSent = new Valor(vSent.valor, vSent.tipo);
                ExpresionHaskell.ultimoValor = vSent.valor;
                return vFinSent;

            case "Revers":
            case "Impr":
            case "Par":
            case "Asc":
            case "Desc":
            case "LlamaFunc":
                Valor valfun = (Valor) funPropias.Recorrer(raiz);
                return valfun;

            case "Calcular":
                Valor valcalc = (Valor) funPropias.Recorrer(raiz);
                return valcalc;

            case "Succ":
                Valor valsucc = (Valor) funPropias.Recorrer(raiz);
                return valsucc;

            case "Decc":
                Valor valdecc = (Valor) funPropias.Recorrer(raiz);
                return valdecc;

            case "Min":
                Valor valmin = (Valor) funPropias.Recorrer(raiz);
                return valmin;

            case "Max":
                Valor valmax = (Valor) funPropias.Recorrer(raiz);
                return valmax;

            case "Sum":
                Valor valsum = (Valor) funPropias.Recorrer(raiz);
                return valsum;

            case "Product":
                Valor valpro = (Valor) funPropias.Recorrer(raiz);
                return valpro;

            case "Length":
                Valor valtam = (Valor) funPropias.Recorrer(raiz);
                return valtam;

            case "Indice":
                Valor valindice = (Valor) funPropias.Recorrer(raiz);
                return valindice;

            case "D_Lista":
                String nombrelista = raiz.hijos.get(0).valor.toString();
                //id, cadena, Lista, 2Niveles
                Valor val = (Valor) funPropias.Recorrer(raiz.hijos.get(1));
                if (val != null) {

                    if (val.tipo.equals("")) {
                        Valor v = new Valor(val.valor, val.tipo);
                        return v;
                    }

                    variable = new Variable(nombrelista, val.valor, val.tipo,ambito);
                    agrega.AgregarVariable(nombrelista+"_"+ambito, variable);
                    ExpresionHaskell.ultimoValor = val.valor;
                    String texto = "";
                    ArrayList a = (ArrayList) val.valor;
                    if (a != null) {

                        if (val.tipo.equals("numero")) {

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
                            for (int i = 0; i < a.size(); i++) {
                                texto += a.get(i).toString();
                            }
                            texto = "\"" + texto + "\"";
                            Valor v = new Valor(texto, "");
                            return v;
                        } else if (val.tipo.equals("caracter")) {
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
                Valor valconca = (Valor) funPropias.Recorrer(raiz);
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
