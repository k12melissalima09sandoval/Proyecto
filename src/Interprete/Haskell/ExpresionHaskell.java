/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Haskell;

import Analizadores.Errores;
import Dibujar.Nodo;
import Interprete.Parametros;
import Interprete.Valor;
import Interprete.Variable;
import Simbolos.TablaSimbolosHaskell;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author MishaPks
 */
public class ExpresionHaskell {

    public static Object ultimoValor;
    public static Object ultimoTipo;
    Boolean res;
    Boolean uno;
    Boolean dos;
    static Concatena concatena = new Concatena();
    static TablaSimbolosHaskell lista = new TablaSimbolosHaskell();
    static Parametros param;
    public static Stack pila = new Stack();
    static int contador = 0;
    //static RecorreHaskell recorridocuerpo;
    //public String nombreFuncion = "";

    public Object Expresion(Nodo raiz, String nombreFuncion) {

        if (raiz.hijos.size() == 1) {
            //Double temp;
            Valor tmp;
            Nodo exp = raiz.hijos.get(0);
            switch (raiz.valor.toString()) {

                case "LlamaFunc":

                    String nombreFun = raiz.hijos.get(0).valor.toString();

                    Map<String, FuncionHaskell> fun2 = lista.ObtenerListaFunciones();

                    if (fun2 != null) {
                        if (fun2.size() > 0) {
                            for (int i = 0; i < fun2.size(); i++) {
                                Boolean g = lista.getKeyFunciones(nombreFun);
                                if (g.equals(true)) {
                                    ArrayList<Parametros> parametros = (ArrayList) fun2.get(nombreFun).getParametros();
                                    if (parametros == null) {

                                        Nodo cuerpo = (Nodo) fun2.get(nombreFun).getCuerpo();
                                        Valor v9 = (Valor) RecorreHaskell.Consola(cuerpo, nombreFun);

                                        contador++;
                                        RecorreHaskell.ambito = "consola";
                                        return v9;
                                    } else {
                                        System.out.println("parametros cantidad invalidos");
                                        Errores.ErrorSemantico("Haskell: cantidad de parametros no coincide", 0, 0);
                                        Valor v = new Valor("cantidad de parametros no coincide", "");
                                        return v;
                                    }
                                } else {
                                    Valor v = new Valor(nombreFun + " no declarada", "");
                                    return v;
                                }
                            }
                        } else {
                            System.out.println("no hay ninguna funcion declarada1");
                            Errores.ErrorSemantico("Haskell: no hay funciones cargadas", 0, 0);
                            Valor v = new Valor("no hay funciones cargadas", "");
                            return v;
                        }
                    } else {
                        Errores.ErrorSemantico("Haskell: no hay funciones cargadas", 0, 0);
                        Valor v = new Valor("no hay funciones cargadas", "");
                        return v;
                    }

                case "Exp":
                    Valor ob1 = (Valor) Expresion(exp, nombreFuncion);
                    return ob1;

                case "Calcular":
                    Valor ob2 = (Valor) Expresion(exp, nombreFuncion);
                    ultimoValor = ob2.valor.toString();
                    ultimoTipo = ob2.tipo;
                    return ob2;

                case "Decc":
                    Valor ob3 = (Valor) Expresion(exp, nombreFuncion);
                    if (ob3.tipo.equals("caracter") || ob3.tipo.equals("cadena")) {
                        int num = ob3.valor.toString().codePointAt(0);
                        num = num - 1;
                        ultimoValor = num;
                        ultimoTipo = "numero";
                        System.out.println("decc: " + num);
                        tmp = new Valor(num, "numero");
                        return tmp;
                    } else if (ob3.tipo.equals("numero")) {
                        Double ob18 = Double.parseDouble(ob3.valor.toString());
                        Double num = ob18 - 1;
                        ultimoValor = num;
                        ultimoTipo = "numero";
                        System.out.println("decc: " + num);
                        tmp = new Valor(num, "numero");
                        return tmp;
                    } else {
                        Errores.ErrorSemantico("Haskell: El ultimo valor es nulo", 0, 0);
                        Valor v = new Valor("el ultimo valor es nulo", "");
                        return v;
                    }

                case "Length":
                    try {
                        Valor ob4 = (Valor) concatena.Listas(exp.hijos.get(0), nombreFuncion);
                        ArrayList vals = (ArrayList) ob4.valor;
                        int tamaño = vals.size();
                        ultimoValor = tamaño;
                        ultimoTipo = "numero";
                        Valor val4 = new Valor(tamaño, "numero");
                        return val4;
                    } catch (Exception e) {
                        Errores.ErrorSemantico("", 0, 0);
                        Errores.ErrorSemantico("Haskell: no es una lista", 0, 0);
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }

                case "Max":
                    try {
                        Valor ob5 = (Valor) concatena.Listas(exp, nombreFuncion);

                        if (ob5.tipo.equals("")) {
                            Valor v = new Valor(ob5.valor, ob5.tipo);
                            return v;
                        }
                        ArrayList a = (ArrayList) ob5.valor;
                        if (a.get(0) instanceof ArrayList) {
                            //2 NIVELES
                            if (ob5.tipo.equals("caracter") || ob5.tipo.equals("cadena")) {
                                int max = 0;
                                for (int i = 0; i < a.size(); i++) {
                                    ArrayList temp = (ArrayList) a.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (temp.get(j).toString().codePointAt(0) > max) {
                                            max = temp.get(j).toString().codePointAt(0);
                                        }
                                    }
                                }
                                ultimoValor = max;
                                ultimoTipo = "numero";
                                char c = (char) max;
                                Valor v = new Valor(c, "numero");
                                return v;
                            } else {
                                Double max = 0.00;
                                for (int i = 0; i < a.size(); i++) {
                                    ArrayList temp = (ArrayList) a.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (Integer.parseInt(temp.get(j).toString()) > max) {
                                            max = Double.parseDouble(temp.get(j).toString());
                                        }
                                    }
                                }
                                ultimoValor = max;
                                ultimoTipo = "numero";
                                Valor v = new Valor(max, "numero");
                                return v;
                            }
                        } else if (ob5.tipo.equals("caracter") || ob5.tipo.equals("cadena")) {

                            int max = 0;
                            for (int i = 0; i < a.size(); i++) {
                                if (a.get(i).toString().codePointAt(0) > max) {
                                    max = a.get(i).toString().codePointAt(0);
                                }
                            }
                            ultimoValor = max;
                            ultimoTipo = "numero";
                            char c = (char) max;
                            Valor v = new Valor(c, "numero");
                            return v;
                        } else {
                            Double max = 0.00;
                            for (int i = 0; i < a.size(); i++) {
                                if (Double.parseDouble(a.get(i).toString()) > max) {
                                    max = Double.parseDouble(a.get(i).toString());
                                }
                            }
                            ultimoValor = max;
                            ultimoTipo = "numero";
                            Valor v = new Valor(max, "numero");
                            return v;
                        }
                    } catch (Exception e) {
                        Errores.ErrorSemantico("Haskell: no es una lista", 0, 0);
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }

                case "Min":
                    try {
                        Valor ob6 = (Valor) concatena.Listas(exp, nombreFuncion);
                        if (ob6.tipo.equals("")) {
                            Valor v = new Valor(ob6.valor, ob6.tipo);
                            return v;
                        }
                        ArrayList a8 = (ArrayList) ob6.valor;
                        if (a8.get(0) instanceof ArrayList) {
                            if (ob6.tipo.equals("caracter") || ob6.tipo.equals("cadena")) {

                                int max = 0;
                                for (int i = 0; i < a8.size(); i++) {
                                    ArrayList temp = (ArrayList) a8.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (temp.get(j).toString().codePointAt(0) > max) {
                                            max = temp.get(j).toString().codePointAt(0);
                                        }
                                    }
                                }
                                int min = max;
                                for (int i = 0; i < a8.size(); i++) {
                                    ArrayList temp = (ArrayList) a8.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (temp.get(j).toString().codePointAt(0) < min) {
                                            min = temp.get(j).toString().codePointAt(0);
                                        }
                                    }
                                }
                                ultimoValor = min;
                                ultimoTipo = "numero";
                                char c = (char) min;
                                System.out.println("min " + c);
                                Valor v = new Valor(c, "numero");
                                return v;
                            } else { //numero
                                Double max = 0.00;
                                for (int i = 0; i < a8.size(); i++) {
                                    ArrayList temp = (ArrayList) a8.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (Integer.parseInt(temp.get(j).toString()) > max) {
                                            max = Double.parseDouble(temp.get(j).toString());
                                        }
                                    }
                                }
                                Double min = max;
                                for (int i = 0; i < a8.size(); i++) {
                                    ArrayList temp = (ArrayList) a8.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        if (Integer.parseInt(temp.get(j).toString()) < min) {
                                            min = Double.parseDouble(temp.get(j).toString());
                                        }
                                    }
                                }
                                ultimoValor = min;
                                ultimoTipo = "numero";
                                System.out.println("min " + min);
                                Valor v = new Valor(min, "numero");
                                return v;
                            }
                        } else if (ob6.tipo.equals("caracter") || ob6.tipo.equals("cadena")) {

                            int max = 0;
                            for (int i = 0; i < a8.size(); i++) {
                                if (a8.get(i).toString().codePointAt(0) > max) {
                                    max = a8.get(i).toString().codePointAt(0);
                                }
                            }
                            int min = max;
                            for (int i = 0; i < a8.size(); i++) {
                                if (a8.get(i).toString().codePointAt(0) < min) {
                                    min = a8.get(i).toString().codePointAt(0);
                                }
                            }
                            ultimoValor = min;
                            ultimoTipo = "numero";
                            char c = (char) min;
                            System.out.println("min " + c);
                            Valor v = new Valor(c, "numero");
                            return v;
                        } else { //numero
                            Double max = 0.00;
                            for (int i = 0; i < a8.size(); i++) {
                                if (Double.parseDouble(a8.get(i).toString()) > max) {
                                    max = Double.parseDouble(a8.get(i).toString());
                                }
                            }
                            Double min = max;
                            for (int i = 0; i < a8.size(); i++) {
                                if (Double.parseDouble(a8.get(i).toString()) < min) {
                                    min = Double.parseDouble(a8.get(i).toString());
                                }
                            }
                            ultimoValor = min;
                            ultimoTipo = "numero";
                            System.out.println("min " + min);
                            Valor v = new Valor(min, "numero");
                            return v;
                        }
                    } catch (Exception e) {
                        Errores.ErrorSemantico("Haskell: no es una lista", 0, 0);
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }

                case "Product":
                    try {
                        Valor ob7 = (Valor) concatena.Listas(exp.hijos.get(0), nombreFuncion);
                        if (ob7.tipo.equals("")) {
                            Valor v = new Valor(ob7.valor, ob7.tipo);
                            return v;
                        }
                        ArrayList a7 = (ArrayList) ob7.valor;
                        if (a7.get(0) instanceof ArrayList) {
                            if (ob7.tipo.equals("caracter") || ob7.tipo.equals("cadena")) {

                                BigInteger multiplica = BigInteger.ONE;
                                for (int i = 0; i < a7.size(); i++) {
                                    ArrayList temp = (ArrayList) a7.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        BigInteger h2 = new BigInteger(temp.get(j).toString().codePointAt(0) + "");
                                        multiplica = multiplica.multiply(h2);
                                    }
                                }
                                ultimoValor = multiplica;
                                ultimoTipo = "numero";
                                System.out.println("mult " + multiplica);
                                Valor v = new Valor(multiplica, "numero");
                                return v;
                            } else { //numero
                                BigInteger multiplica = BigInteger.ONE;
                                for (int i = 0; i < a7.size(); i++) {
                                    ArrayList temp = (ArrayList) a7.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        BigInteger h2 = new BigInteger(temp.get(j).toString() + "");
                                        multiplica = multiplica.multiply(h2);

                                    }
                                }
                                ultimoValor = multiplica;
                                ultimoTipo = "numero";
                                System.out.println("mult " + multiplica);
                                Valor v = new Valor(multiplica, "numero");
                                return v;
                            }
                        } else if (ob7.tipo.equals("caracter") || ob7.tipo.equals("cadena")) {

                            BigInteger multiplica = BigInteger.ONE;
                            System.out.println("dd " + multiplica);
                            for (int i = 0; i < a7.size(); i++) {
                                BigInteger h2 = new BigInteger(a7.get(i).toString().codePointAt(0) + "");
                                System.out.println("h2 " + h2);
                                multiplica = multiplica.multiply(h2);
                                System.out.println("res " + multiplica);
                            }
                            ultimoValor = multiplica;
                            ultimoTipo = "numero";
                            System.out.println("mult " + multiplica);
                            Valor v = new Valor(multiplica, "numero");
                            return v;
                        } else { //numero
                            BigInteger multiplica = BigInteger.ONE;
                            for (int i = 0; i < a7.size(); i++) {
                                BigInteger h2 = new BigInteger(a7.get(i).toString() + "");
                                multiplica = multiplica.multiply(h2);
                            }
                            ultimoValor = multiplica;
                            ultimoTipo = "numero";
                            System.out.println("mult " + multiplica);
                            Valor v = new Valor(multiplica, "numero");
                            return v;
                        }
                    } catch (Exception e) {
                        Errores.ErrorSemantico("Haskell: no es una lista", 0, 0);
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }

                case "Succ":
                    Valor ob8 = (Valor) Expresion(exp, nombreFuncion);

                    if (ob8.tipo.equals("caracter") || ob8.tipo.equals("cadena")) {
                        int num = ob8.valor.toString().codePointAt(0);
                        num = num + 1;
                        ultimoValor = num;
                        ultimoTipo = "numero";
                        System.out.println("succ: " + num);
                        tmp = new Valor(num, "numero");
                        return tmp;
                    } else if (ob8.tipo.equals("numero")) {
                        Double ob18 = Double.parseDouble(ob8.valor.toString());
                        Double num = ob18 + 1;
                        ultimoValor = num;
                        ultimoTipo = "numero";
                        System.out.println("succ: " + num);
                        tmp = new Valor(num, "numero");
                        return tmp;
                    } else {
                        Errores.ErrorSemantico("Haskell: el ultimo valor es nulo", 0, 0);
                        Valor v = new Valor("el ultimo valor es nulo", "");
                        return v;
                    }

                case "Sum":
                    try {
                        Valor ob9 = (Valor) concatena.Listas(exp.hijos.get(0), nombreFuncion);
                        if (ob9.tipo.equals("")) {
                            Valor v = new Valor(ob9.valor, ob9.tipo);
                            return v;
                        }
                        ArrayList a9 = (ArrayList) ob9.valor;
                        if (a9.get(0) instanceof ArrayList) {
                            if (ob9.tipo.equals("caracter") || ob9.tipo.equals("cadena")) {

                                int sum = 0;
                                for (int i = 0; i < a9.size(); i++) {
                                    ArrayList temp = (ArrayList) a9.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        sum = sum + temp.get(j).toString().codePointAt(0);
                                    }
                                }
                                ultimoValor = sum;
                                ultimoTipo = "numero";
                                System.out.println("sum " + sum);
                                Valor v = new Valor(sum, "numero");
                                return v;
                            } else { //numero
                                int sum = 0;
                                for (int i = 0; i < a9.size(); i++) {
                                    ArrayList temp = (ArrayList) a9.get(i);
                                    for (int j = 0; j < temp.size(); j++) {
                                        sum = sum + Integer.parseInt(temp.get(j).toString());
                                    }
                                }
                                ultimoValor = sum;
                                ultimoTipo = "numero";
                                System.out.println("sum " + sum);
                                Valor v = new Valor(sum, "numero");
                                return v;
                            }
                        } else if (ob9.tipo.equals("caracter") || ob9.tipo.equals("cadena")) {

                            int sum = 0;
                            for (int i = 0; i < a9.size(); i++) {
                                sum = sum + a9.get(i).toString().codePointAt(0);
                            }
                            ultimoValor = sum;
                            ultimoTipo = "numero";
                            System.out.println("sum " + sum);
                            Valor v = new Valor(sum, "numero");
                            return v;
                        } else { //numero
                            int sum = 0;
                            for (int i = 0; i < a9.size(); i++) {
                                sum = sum + Integer.parseInt(a9.get(i).toString());
                            }
                            ultimoValor = sum;
                            ultimoTipo = "numero";
                            System.out.println("sum " + sum);
                            Valor v = new Valor(sum, "numero");
                            return v;
                        }
                    } catch (Exception e) {
                        Errores.ErrorSemantico("Haskell: no es una lista", 0, 0);
                        Valor v = new Valor("no es una lista", "");
                        return v;
                    }

                case "porcentaje":
                    if (ultimoValor != null) {
                        try {
                            ArrayList a = new ArrayList();
                            a = (ArrayList) ultimoValor;
                            if (ultimoTipo.equals("cadena")) {
                                Valor v4 = new Valor(ultimoValor, "cadena");
                                return v4;
                            } else if (ultimoTipo.equals("caracter")) {
                                Valor v4 = new Valor(ultimoValor, "cadena");
                                return v4;

                            } else if (ultimoTipo.equals("numero")) {
                                Valor v4 = new Valor(ultimoValor, "numero");
                                return v4;

                            }

                        } catch (Exception e) {
                            Valor v4 = new Valor(ultimoValor, "numero");
                            return v4;
                        }

                    } else {
                        Errores.ErrorSemantico("Haskell: el ultimo valor es nulo", 0, 0);
                        Valor v = new Valor("el ultimo valor es nulo", "");
                        return v;

                    }

                case "Unario":
                    Valor v5 = (Valor) Expresion(exp, nombreFuncion);
                    if (v5 != null) {
                        try {
                            int mult = Integer.parseInt(v5.valor.toString()) * (-1);
                            Valor v6 = new Valor(mult, "numero");
                            ultimoValor = mult;
                            ultimoTipo = "numero";
                            return v6;
                        } catch (Exception e) {
                            Errores.ErrorSemantico("Haskell: no aplica el unario", 0, 0);
                            Valor v = new Valor("no aplica el unario", "");
                            return v;
                        }
                    }
                case "numero":
                    Valor v = new Valor(raiz.hijos.get(0).valor.toString(), "numero");
                    ultimoValor = v.valor;
                    ultimoTipo = "numero";
                    return v;

                case "cadena":
                    Valor v1 = new Valor(raiz.hijos.get(0).valor.toString(), "cadena");
                    ultimoValor = v1.valor;
                    ultimoTipo = "cadena";
                    return v1;

                case "caracter":
                    Valor v3 = new Valor(raiz.hijos.get(0).valor.toString().replace("'", ""), "caracter");
                    ultimoValor = v3.valor;
                    ultimoTipo = "caracter";
                    return v3;

                case "id":
                    String parametro = raiz.hijos.get(0).valor.toString();
                    if (pila.empty()) {
                        Map<String, Variable> l = lista.ObtenerListaListas();
                        Boolean encontrado = false;
                        if (l != null) {
                            if (l.size() > 0) {
                                for (int i = 0; i < l.size(); i++) {
                                    Boolean g = lista.getKeyListas(parametro + "_" + RecorreHaskell.ambito);
                                    if (g.equals(true)) {
                                        Object obtener = (Object) l.get(parametro + "_" + RecorreHaskell.ambito).valor;
                                        Valor val = new Valor(obtener, l.get(parametro + "_" + RecorreHaskell.ambito).tipo);
                                        return val;
                                    } else {
                                        Boolean g2 = lista.getKeyListas(parametro + "_consola");
                                        if (g.equals(true)) {
                                            Object obtener = (Object) l.get(parametro + "_consola").valor;
                                            Valor val = new Valor(obtener, l.get(parametro + "_consola").tipo);
                                            return val;
                                        } else {
                                            Valor v2 = new Valor("lista no declarada", "");
                                            return v2;
                                        }
                                    }

                                }
                            } else {
                                ArrayList<Variable> variablesenpila = (ArrayList) pila.peek();
                                Boolean bandera = false;
                                for (int i = 0; i < variablesenpila.size(); i++) {
                                    if (parametro.equals(variablesenpila.get(i).nombre)) {
                                        Valor val = new Valor(variablesenpila.get(i).valor, variablesenpila.get(i).tipo);
                                        return val;
                                    }
                                }
                                if (bandera != true) {
                                    Valor v2 = new Valor("el parametro no existe", "");
                                    return v2;
                                }
                            }

                            if (encontrado.equals(false)) {
                                Valor v2 = new Valor("lista no declarada", "");
                                return v2;
                            }
                        }
                    } else {
                        ArrayList<Variable> variablesenpila = (ArrayList) pila.peek();
                        Boolean bandera = false;
                        for (int i = 0; i < variablesenpila.size(); i++) {
                            if (parametro.equals(variablesenpila.get(i).nombre)) {
                                Valor val = new Valor(variablesenpila.get(i).valor, variablesenpila.get(i).tipo);
                                return val;
                            }
                        }
                        if (bandera != true) {
                            Errores.ErrorSemantico("Haskell: el parametro no existe", 0, 0);
                            Valor v2 = new Valor("el parametro no existe", "");
                            return v2;
                        }

                    }

            }
        } else if (raiz.hijos.size() == 2) {
            Valor izq;
            Valor der;
            Double resultado = 0.0;
            switch (raiz.valor.toString()) {

                //////////////////////////////////////  FUNCION   //////////////////////////////////////////
                case "LlamaFunc":
                    String nombreFun = raiz.hijos.get(0).valor.toString();
                    ArrayList<Parametros> obtenerParam = new ArrayList();
                    Map<String, FuncionHaskell> fun = lista.ObtenerListaFunciones();

                    for (Nodo c : raiz.hijos.get(1).hijos) {
                        if (c.valor.equals("cadena") || c.valor.equals("Lista") || c.valor.equals("2Niveles")) {
                            Valor v = (Valor) concatena.Listas(c, nombreFun);

                            Parametros pp = new Parametros(v.valor, v.tipo);
                            obtenerParam.add(pp);
                        } else {
                            Valor v = (Valor) Expresion(c, nombreFuncion);
                            Parametros pp = new Parametros(v.valor, v.tipo);
                            obtenerParam.add(pp);
                        }
                    }

                    if (fun != null) {
                        if (fun.size() > 0) {
                            for (int i = 0; i < fun.size(); i++) {
                                Boolean g = lista.getKeyFunciones(nombreFun);
                                if (g.equals(true)) {

                                    ArrayList<Parametros> parametros = (ArrayList) fun.get(nombreFun).getParametros();
                                    if (parametros == null) {
                                        Errores.ErrorSemantico("Haskell, cantidad de parametros no coincide", i, contador);
                                        Valor v = new Valor("cantidad de parametros no coincide", "");
                                        return v;
                                    }
                                    if (parametros.size() == obtenerParam.size()) {
                                        Variable nueva = new Variable("", "", "");
                                        ArrayList temp = new ArrayList();
                                        for (int j = 0; j < parametros.size(); j++) {
                                            Parametros p = obtenerParam.get(j);
                                            nueva = new Variable(parametros.get(j).nombre, p.valor, p.tipo);
                                            temp.add(nueva);

                                        }
                                        pila.push(temp);
                                        break;
                                    } else {
                                        System.out.println("parametros cantidad invalidos");
                                        Errores.ErrorSemantico("Haskell: cantidad de parametros no coincide", 0, 0);
                                        Valor v = new Valor("cantidad de parametros no coincide", "");
                                        return v;

                                    }
                                } else {
                                    Valor v = new Valor(nombreFun + " no declarada", "");
                                    return v;
                                }
                            }
                        } else {
                            System.out.println("no hay ninguna funcion declarada2");
                            Errores.ErrorSemantico("Haskell: no hay funciones cargadas", 0, 0);
                            Valor v = new Valor("no hay funciones cargadas", "");
                            return v;
                        }
                    } else {
                        Errores.ErrorSemantico("Haskell: no hay funciones cargadas", 0, 0);
                        Valor v = new Valor("no hay funciones cargadas", "");
                        return v;
                    }

                    Nodo cuerpo = (Nodo) fun.get(nombreFun).getCuerpo();

                    Valor v9 = (Valor) RecorreHaskell.Consola(cuerpo, nombreFun);
                    pila.pop();
                    return v9;

                case "Indice":
                    String nombreLista = raiz.hijos.get(0).valor.toString();
                    Valor i1 = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    int j1 = Integer.parseInt(i1.valor.toString().replace(".0", ""));
                    if (pila.empty()) {

                        Map<String, Variable> l = lista.ObtenerListaListas();

                        if (l.size() > 0) {
                            for (int i = 0; i < l.size(); i++) {
                                Boolean g = lista.getKeyListas(nombreLista + "_" + RecorreHaskell.ambito);
                                if (g.equals(true)) {
                                    ArrayList valores = (ArrayList) l.get(nombreLista + "_" + RecorreHaskell.ambito).valor;
                                    String nivel1 = valores.get(j1).toString();
                                    if (l.get(nombreLista + "_" + RecorreHaskell.ambito).tipo.equals("numero")) {
                                        ultimoValor = nivel1;
                                        ultimoTipo = "numero";
                                        Valor val = new Valor(nivel1, "numero");
                                        return val;
                                    } else {
                                        ultimoValor = nivel1;
                                        ultimoTipo = "caracter";
                                        Valor val = new Valor(nivel1, "caracter");
                                        return val;
                                    }
                                } else {
                                    Boolean g2 = lista.getKeyListas(nombreLista + "_consola");
                                    if (g2.equals(true)) {
                                        ArrayList valores = (ArrayList) l.get(nombreLista + "_consola").valor;
                                        String nivel1 = valores.get(j1).toString();
                                        if (l.get(nombreLista + "_consola").tipo.equals("numero")) {
                                            ultimoValor = nivel1;
                                            ultimoTipo = "numero";
                                            Valor val = new Valor(nivel1, "numero");
                                            return val;
                                        } else {
                                            ultimoValor = nivel1;
                                            ultimoTipo = "caracter";
                                            Valor val = new Valor(nivel1, "caracter");
                                            return val;
                                        }
                                    } else {
                                        Errores.ErrorSemantico("Haskell: lista no declarada", 0, 0);
                                        Valor v2 = new Valor("lista no declarada", "");
                                        return v2;
                                    }
                                }
                            }
                        } else {
                            Errores.ErrorSemantico("Haskell: no hay listas declaradas", 0, 0);
                            Valor v = new Valor("no hay listas declaradas", "");
                            return v;
                        }

                    } else {
                        ArrayList<Variable> variablesenpila = (ArrayList) ExpresionHaskell.pila.peek();
                        Boolean bandera = false;
                        for (int i = 0; i < variablesenpila.size(); i++) {
                            if (nombreLista.equals(variablesenpila.get(i).nombre)) {
                                Valor val = new Valor(variablesenpila.get(i).valor, variablesenpila.get(i).tipo);
                                try {
                                    ArrayList valores = (ArrayList) val.valor;
                                    String pos = valores.get(j1).toString();
                                    if (val.tipo.equals("numero")) {
                                        ultimoValor = pos;
                                        ultimoTipo = "numero";
                                        Valor val1 = new Valor(pos, "numero");
                                        return val1;
                                    } else {
                                        ultimoValor = pos;
                                        ultimoTipo = "caracter";
                                        Valor val2 = new Valor(pos, "caracter");
                                        return val2;
                                    }
                                } catch (Exception e) {
                                    Valor v = new Valor("no es una lista", "");
                                    return v;
                                }
                            }
                        }
                        if (bandera != true) {
                            Errores.ErrorSemantico("Haskell: el parametro no existe", 0, 0);
                            Valor v2 = new Valor("el parametro no existe", "");
                            return v2;
                        }
                    }

                case "+":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {
                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = num1 + num2;
                                    Valor v = new Valor(resultado, "numero");
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = num1 + num2;

                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                    Errores.ErrorSemantico("Haskell: no son del mismo tipo", 0, 0);
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {
                                Errores.ErrorSemantico("Haskell: no son del mismo tipo", 0, 0);
                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Errores.ErrorSemantico("Haskell: el ultimo valor es nulo", 0, 0);
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                        Errores.ErrorSemantico("Haskell: en la suma hay valor nulo", 0, 0);
                       Valor v = new Valor("en la suma hay valor nulo", "");
                            return v;
                    }
                case "-":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {
                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = num1 - num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = num1 - num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {

                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                        Valor v = new Valor("en la resta hay valor nulo", "");
                        return v;
                    }

                case "*":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {
                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = num1 * num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = num1 * num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                   
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {
                                
                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                        Valor v = new Valor("en la multiplicacion hay valor nulo", "");
                        return v;
                    }

                case "/":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {
                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = num1 / num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = num1 / num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {
                                
                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                       Valor v = new Valor("en la division hay valor nulo", "");
                        return v;
                    }

                case "mod":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {
                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = num1 % num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = num1 % num2;
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {
                               
                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                        Valor v = new Valor("en el residuo hay valor nulo", "");
                        return v;
                    }

                case "sqrt":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {
                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = Math.pow(num2, 1 / num1);
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = Math.pow(num2, 1 / num1);
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {
                               
                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                       Valor v = new Valor("en la raiz hay valor nulo", "");
                        return v;
                    }

                case "pot":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null || der != null) {
                        if (izq.valor != "el ultimo valor es nulo" && der.valor != "el ultimo valor es nulo") {
                            if (izq.tipo.equals(der.tipo)) {

                                if (izq.tipo.equals("numero")) {
                                    Double num1 = Double.parseDouble(izq.valor.toString());
                                    Double num2 = Double.parseDouble(der.valor.toString());
                                    resultado = Math.pow(num1, num2);
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else if (izq.tipo.equals("caracter")) {
                                    Object o = izq.valor.toString().codePointAt(0);
                                    Object m = der.valor.toString().codePointAt(0);
                                    Double num1 = new Double(o.toString());
                                    Double num2 = new Double(m.toString());
                                    resultado = Math.pow(num1, num2);
                                    ultimoValor = resultado;
                                    ultimoTipo = "numero";
                                    Valor v = new Valor(resultado, "numero");
                                    return v;
                                } else {
                                    //error
                                    Valor v = new Valor("no son del mismo tipo", "");
                                    return v;
                                }
                            } else {
                               
                                Valor v = new Valor("no son del mismo tipo", "");
                                return v;
                            }
                        } else {
                            Valor v = new Valor("el ultimo valor es nulo", "");
                            return v;
                        }
                    } else {
                       Valor v = new Valor("en la potencia hay valor nulo", "");
                        return v;
                    }

                case "||":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("bool")) {
                                uno = Boolean.parseBoolean(izq.valor.toString());
                                dos = Boolean.parseBoolean(der.valor.toString());
                                res = uno || dos;
                                Valor v = new Valor(res, "bool");
                                return v;
                            } else {
                                Valor v2 = new Valor("no es tipo bool -> ||", "");
                                return v2;
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> ||", "");
                            return v2;
                        }
                    }
                case "&&":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("bool")) {
                                uno = Boolean.parseBoolean(izq.valor.toString());
                                dos = Boolean.parseBoolean(der.valor.toString());
                                res = uno && dos;
                                Valor v2 = new Valor(res, "bool");
                                return v2;
                            } else {
                                Valor v2 = new Valor("no es tipo bool -> &&", "");
                                return v2;
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> &&", "");
                            return v2;
                        }
                    }

                case "<":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if (num1 < num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = (Object) izq.valor.toString().codePointAt(0);
                                Object oo = (Object) der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if (num1 < num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> <", "");
                            return v2;
                        }
                    }

                case ">":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if (num1 > num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = (Object) izq.valor.toString().codePointAt(0);
                                Object oo = (Object) der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if (num1 > num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> >", "");
                            return v2;
                        }
                    }

                case "<=":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if (num1 <= num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = (Object) izq.valor.toString().codePointAt(0);
                                Object oo = (Object) der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if (num1 <= num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> <=", "");
                            return v2;
                        }
                    }

                case ">=":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if (num1 >= num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = (Object) izq.valor.toString().codePointAt(0);
                                Object oo = (Object) der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if (num1 >= num2) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> >=", "");
                            return v2;
                        }
                    }

                case "==":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if (num1.equals(num2)) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = (Object) izq.valor.toString().codePointAt(0);
                                Object oo = (Object) der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if (num1.equals(num2)) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> ==", "");
                            return v2;
                        }
                    }
                case "!=":
                    izq = (Valor) Expresion(raiz.hijos.get(0), nombreFuncion);
                    der = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    if (izq != null && der != null) {
                        if (izq.tipo.equals(der.tipo)) {
                            if (izq.tipo.equals("numero")) {
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if (!Objects.equals(num1, num2)) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            } else if (izq.tipo.equals("caracter")) {
                                Object o = (Object) izq.valor.toString().codePointAt(0);
                                Object oo = (Object) der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if (!Objects.equals(num1, num2)) {
                                    Valor val = new Valor(true, "bool");
                                    return val;
                                } else {
                                    Valor val = new Valor(false, "bool");
                                    return val;
                                }
                            }
                        } else {
                            Valor v2 = new Valor("diferentes tipos en comparacion -> !=", "");
                            return v2;
                        }
                    }
            }
        } else if (raiz.hijos.size() == 3) {
            Valor izq;
            Valor der;
            Double resultado = 0.0;
            switch (raiz.valor.toString()) {

                case "Indice":
                    String nombreLista = raiz.hijos.get(0).valor.toString();
                    Valor i1 = (Valor) Expresion(raiz.hijos.get(1), nombreFuncion);
                    Valor i2 = (Valor) Expresion(raiz.hijos.get(2), nombreFuncion);
                    int j1 = Integer.parseInt(i1.valor.toString().replace(".0", ""));
                    int j2 = Integer.parseInt(i2.valor.toString().replace(".0", ""));
                    if (pila.empty()) {
                        Map<String, Variable> l = lista.ObtenerListaListas();

                        if (l.size() > 0) {
                            for (int i = 0; i < l.size(); i++) {
                                Boolean g = lista.getKeyListas(nombreLista + "_" + RecorreHaskell.ambito);
                                if (g.equals(true)) {
                                    ArrayList valores = (ArrayList) l.get(nombreLista + "_" + RecorreHaskell.ambito).valor;
                                    ArrayList nivel1 = (ArrayList) valores.get(j1);
                                    String nivel2 = nivel1.get(j2).toString();
                                    if (l.get(nombreLista + "_" + RecorreHaskell.ambito).tipo.equals("numero")) {
                                        ultimoValor = nivel2;
                                        ultimoTipo = "numero";
                                        Valor val = new Valor(nivel2, "numero");
                                        return val;
                                    } else {
                                        ultimoValor = nivel2;
                                        ultimoTipo = "caracter";
                                        Valor val = new Valor(nivel2, "caracter");
                                        return val;
                                    }
                                } else {
                                    Boolean g2 = lista.getKeyListas(nombreLista + "_consola");
                                    if (g2.equals(true)) {
                                        ArrayList valores = (ArrayList) l.get(nombreLista + "_consola").valor;
                                        ArrayList nivel1 = (ArrayList) valores.get(j1);
                                        String nivel2 = nivel1.get(j2).toString();
                                        if (l.get(nombreLista + "_consola").tipo.equals("numero")) {
                                            ultimoValor = nivel2;
                                            ultimoTipo = "numero";
                                            Valor val = new Valor(nivel2, "numero");
                                            return val;
                                        } else {
                                            ultimoValor = nivel2;
                                            ultimoTipo = "caracter";
                                            Valor val = new Valor(nivel2, "caracter");
                                            return val;
                                        }
                                    } else {
                                        Valor v = new Valor("lista no declarada", "");
                                        return v;
                                    }
                                }
                            }
                        } else {
                            Valor v = new Valor("no hay listas declaradas", "");
                            return v;
                        }
                    } else {
                        ArrayList<Variable> variablesenpila = (ArrayList) ExpresionHaskell.pila.peek();
                        Boolean bandera = false;
                        for (int i = 0; i < variablesenpila.size(); i++) {
                            if (nombreLista.equals(variablesenpila.get(i).nombre)) {
                                Valor val = new Valor(variablesenpila.get(i).valor, variablesenpila.get(i).tipo);
                                try {
                                    ArrayList valores = (ArrayList) val.valor;
                                    ArrayList nivel1 = (ArrayList) valores.get(j1);
                                    String pos = nivel1.get(j2).toString();
                                    if (val.tipo.equals("numero")) {
                                        ultimoValor = pos;
                                        ultimoTipo = "numero";
                                        Valor val1 = new Valor(pos, "numero");
                                        return val1;
                                    } else {
                                        ultimoValor = pos;
                                        ultimoTipo = "caracter";
                                        Valor val2 = new Valor(pos, "caracter");
                                        return val2;
                                    }
                                } catch (Exception e) {
                                    Valor v = new Valor("no es una lista", "");
                                    return v;
                                }
                            }
                        }
                        if (bandera != true) {
                            Valor v2 = new Valor("el parametro no existe", "");
                            return v2;
                        }
                    }

            }
        }

        return null;
    }
}
