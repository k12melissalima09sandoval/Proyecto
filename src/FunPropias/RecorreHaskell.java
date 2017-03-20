/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunPropias;

import Ast.Nodo;
import FunPropias.Concatena;
import java.util.ArrayList;
import Interprete.Haskell.FuncionHaskell;
import FunPropias.FuncionesPropiasHaskell;
import Interprete.Haskell.ExpresionHaskell;
import static Interprete.Haskell.ExpresionHaskell.ultimoValor;
import static Interprete.Haskell.ExpresionHaskell.ultimoTipo;
import Interprete.Parametros;
import Interprete.Valor;
import Interprete.Variable;
import Simbolos.TablaSimbolosHaskell;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author MishaPks
 */
public class RecorreHaskell {

    public static ArrayList<Parametros> parametros;
    public static ArrayList<Parametros> parametrosTemp;
    static FuncionHaskell nueva;
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

                if (valcase != null) {
                    if (valcase.valor != null) {
                        Nodo casos = raiz.hijos.get(1);

                        for (Nodo c : casos.hijos) {
                            Valor val = (Valor) exp.Expresion(c.hijos.get(0), nombreFuncion);
                            if (val != null) {
                                if (val.valor != null) {
                                    //coincide con el caso
                                    if (valcase.tipo.equals(val.tipo)) {
                                        if (valcase.valor.equals(val.valor)) {
                                            Valor v = (Valor) Consola(c.hijos.get(1), nombreFuncion);
                                            return v;
                                        }
                                    } else {
                                        Valor v = new Valor("en el case no coiciden los tipos", "");
                                        return v;
                                    }

                                }
                            }
                        }

                        if (valcase.tipo.equals("numero")) {

                        }
                    }
                }

//----------------------------------------FUNCIONES PROPIAS---------------------------------------
            case "Revers":
                try {
                    Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                   if (ob4.tipo.equals("cadena") || ob4.tipo.equals("caracter")) {
                    ArrayList vals = (ArrayList) ob4.valor;
                    List va = (List) vals;
                    Collections.reverse(va);
                    ultimoValor = va;
                    ultimoTipo = "cadena";
                    Valor val4 = new Valor(va, "cadena");
                    return val4;
                   }else{
                        ArrayList vals = (ArrayList) ob4.valor;
                    List va = (List) vals;
                    Collections.reverse(va);
                    ultimoValor = va;
                    ultimoTipo ="numero";
                    Valor val4 = new Valor(va, "numero");
                    return val4;
                   }
                } catch (Exception e) {
                    Valor v = new Valor("no es una lista", "");
                    return v;
                }

            case "Impr":

                try {
                    Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                    
                    if (ob4.tipo.equals("cadena") || ob4.tipo.equals("caracter")) {
                        ArrayList impar = new ArrayList();
                        ArrayList temp = (ArrayList) ob4.valor;
                        for (int i = 0; i < temp.size(); i++) {
                            String letra = temp.get(i).toString();
                            Boolean impr = esImpar(letra);
                            if (impr) {
                                impar.add(letra);
                            }

                        }
                        if (impar.isEmpty()) {
                            Valor v = new Valor("[ ]","caracter");
                            return v;
                            
                        } else {
                            ultimoValor=impar;
                            ultimoTipo="caracter";
                            Valor v = new Valor(impar, "caracter");
                            return v;
                        }
                    } else {
                        ArrayList impar = new ArrayList();
                        ArrayList temp = (ArrayList) ob4.valor;
                        for (int i = 0; i < temp.size(); i++) {
                            int num = Integer.parseInt(temp.get(i).toString());
                            Boolean impr = esImpar(num);
                            if (impr) {
                                impar.add(num);
                            }

                        }
                        if (impar.isEmpty()) {
                            Valor v = new Valor("[ ]","numero");
                            return v;
                            
                        } else {
                            ultimoValor=impar;
                            ultimoTipo="numero";
                            Valor v = new Valor(impar, "numero");
                            return v;
                        }
                    }
                } catch (Exception e) {
                    Valor v = new Valor("no es una lista", "");
                    return v;
                }
            case "Par":
                try {
                    Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                    
                    if (ob4.tipo.equals("cadena") || ob4.tipo.equals("caracter")) {
                        ArrayList par = new ArrayList();
                        ArrayList temp = (ArrayList) ob4.valor;
                        for (int i = 0; i < temp.size(); i++) {
                            String letra = temp.get(i).toString();
                            Boolean p = esPar(letra);
                            if (p) {
                                par.add(letra);
                            }

                        }
                        if (par.isEmpty()) {
                            Valor v = new Valor("[ ]","caracter");
                            return v;
                            
                        } else {
                            ultimoValor=par;
                            ultimoTipo="caracter";
                            Valor v = new Valor(par, "caracter");
                            return v;
                        }
                    } else {
                        ArrayList par = new ArrayList();
                        ArrayList temp = (ArrayList) ob4.valor;
                        for (int i = 0; i < temp.size(); i++) {
                            int num = Integer.parseInt(temp.get(i).toString());
                            Boolean p = esPar(num);
                            if (p) {
                                par.add(num);
                            }

                        }
                        if (par.isEmpty()) {
                            Valor v = new Valor("[ ]","numero");
                            return v;
                            
                        } else {
                            ultimoValor=par;
                            ultimoTipo="numero";
                            Valor v = new Valor(par, "numero");
                            return v;
                        }
                    }
                } catch (Exception e) {
                    Valor v = new Valor("no es una lista", "");
                    return v;
                }
                
            case "Asc":
                try {
                    Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                    if (ob4.tipo.equals("cadena") || ob4.tipo.equals("caracter")) {
                        ArrayList vals = (ArrayList) ob4.valor;
                        List va = (List) vals;
                        Collections.sort(va);
                        ultimoValor = va;
                        ultimoTipo = "caracter";
                        Valor val4 = new Valor(va, "caracter");
                        return val4;
                    } else {
                        ArrayList vals = (ArrayList) ob4.valor;

                        ArrayList v = burbuja(vals);
                        List vv = (List) v;
                        Collections.reverse(vv);
                        ArrayList nuevo = (ArrayList) vv;
                        ultimoValor = nuevo;
                        ultimoTipo= "numero";
                        Valor vvv = new Valor(nuevo, "numero");
                        return vvv;
                    }

                } catch (Exception e) {
                    Valor v = new Valor("no es una lista", "");
                    return v;
                }

            case "Desc":
                try {
                    Valor ob4 = (Valor) concatena.Listas(raiz.hijos.get(0).hijos.get(0), nombreFuncion);
                    if (ob4.tipo.equals("cadena") || ob4.tipo.equals("caracter")) {
                        ArrayList vals = (ArrayList) ob4.valor;

                        List va = (List) vals;
                        Comparator<Integer> comparador = Collections.reverseOrder();
                        Collections.sort(va, comparador);
                        ultimoValor = va;
                        ultimoTipo = "cadena";
                        Valor val4 = new Valor(va, "cadena");
                        return val4;
                    } else {
                        ArrayList vals = (ArrayList) ob4.valor;

                        ArrayList v = burbuja(vals);

                        ultimoValor = v;
                        ultimoTipo = "numero";
                        Valor vvv = new Valor(v, "numero");
                        return vvv;
                    }
                } catch (Exception e) {
                    Valor v = new Valor("no es una lista", "");
                    return v;
                }

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
                        ultimoTipo="numero";
                        texto = "[" + texto + "]";
                        Valor v = new Valor(texto, "");
                        return v;
                    } else if (valconca.tipo.equals("cadena")) {
                        for (int i = 0; i < a.size(); i++) {
                            texto += a.get(i).toString();
                        }
                        ultimoTipo = "cadena";
                        texto = "\"" + texto + "\"";
                        Valor v = new Valor(texto, "");
                        return v;
                    } else if (valconca.tipo.equals("caracter")) {
                        for (int i = 0; i < a.size(); i++) {
                            texto += a.get(i).toString();
                        }
                        ultimoTipo = "caracter";
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

    static ArrayList burbuja(ArrayList arreglo) {
        ArrayList a = new ArrayList();
        for (int i = 0; i < arreglo.size() - 1; i++) {
            for (int j = 0; j < arreglo.size() - 1; j++) {
                if (Integer.parseInt(arreglo.get(j).toString()) < Integer.parseInt(arreglo.get(j + 1).toString())) {
                    int tmp = Integer.parseInt(arreglo.get(j + 1).toString());
                    arreglo.set(j + 1, arreglo.get(j).toString());
                    arreglo.set(j, tmp);
                }
            }

        }

        return arreglo;
    }

    static boolean esImpar(int iNumero) {
        return iNumero % 2 != 0;
    }

    static boolean esImpar(String letra) {
        int l = letra.codePointAt(0);
        return l % 2 != 0;
    }
    static boolean esPar(int iNumero) {
        return iNumero % 2 == 0;
    }

    static boolean esPar(String letra) {
        int l = letra.codePointAt(0);
        return l % 2 == 0;
    }
}
