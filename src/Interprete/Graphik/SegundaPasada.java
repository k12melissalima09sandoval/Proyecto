package Interprete.Graphik;

import Dibujar.Datos;
import Analizadores.Errores;
import Analizadores.Imprimir;
import Dibujar.DatosGraphik;
import Dibujar.Graficar;
import Dibujar.Nodo;
import Interprete.Arreglo;
import Interprete.AsignacionCasteo;
import Interprete.Haskell.FuncionHaskell;
import Interprete.Haskell.RecorreHaskell;
import Interprete.Parametros;
import Interprete.Valor;
import Interprete.Variable;
import Simbolos.TablaSimbolosGraphik;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author MishaPks
 */
public class SegundaPasada {

    ExpresionGraphik exp = new ExpresionGraphik();
    CrearVariables varsLocales = new CrearVariables();
    AsignacionCasteo asigna = new AsignacionCasteo();
    public static int contTemp = 0;
    public static Stack pila = new Stack();
    //static Graficar graf = new Graficar("Grafica");
    Boolean pasoGraf = false;
    static DatosGraphik datos = new DatosGraphik();
    public static ArrayList Resultados = new ArrayList();

    public Object Reconocer() {

        ArrayList<Als> als = TablaSimbolosGraphik.listaAls;
        for (int i = 0; i < als.size(); i++) {
            Boolean existe = als.get(i).existeMetodo("Inicio");
            if (existe) {
                MetodoGraphik inicio = als.get(i).obtenerMetodo("Inicio");
                Nodo cuerpo = inicio.cuerpo.hijos.get(0);
                ArrayList<Variable> Globales = als.get(i).VarsGlobales;

                ArrayList<ArrayList<Variable>> nueva;
                nueva = new ArrayList();
                nueva.add(Globales);
                int nivel = 0;
                Ejecucion(cuerpo, nueva, nivel, als.get(i), "Inicio", contTemp, "inicio");
                if (pasoGraf) {
                    //graf.mostrar();
                }
            } else {
                Errores.ErrorSemantico("No existe el metodo Inicio", 0, 0);
            }
        }

        return null;
    }

    public Object Ejecucion(Nodo cuerpoAls, ArrayList<ArrayList<Variable>> variables, int nivel,
            Als als, String nombreFun, int contTemp, String ambito) {
        int cont = 0;

        for (Nodo raiz : cuerpoAls.hijos) {
            switch (raiz.valor.toString()) {

                //Asignacion a=exp?
                case "DeclaraLocalVariable": {
                    varsLocales.CrearVariableLocal(raiz, variables, als);
                    pila.push(ambito + contTemp);
                }
                break;
                case "DeclaraLocalArreglo": {
                    varsLocales.CrearLocalArreglo(raiz, variables, als);
                    pila.push(ambito + contTemp);
                }
                break;
                case "InstanciaLocal": {
                    varsLocales.CrearInstanciaLocal(raiz, variables, als);
                    pila.push(ambito + contTemp);
                }
                break;
                case "Asignacion": {
                    String nombre = raiz.hijos.get(0).valor.toString();
                    Valor v = (Valor) buscarVariable(variables, nombre);
                    //encontro la variable
                    if (v.tipo.equals("true")) {
                        Variable var = (Variable) v.valor;
                        if (var.arreglo) {
                            Arreglo arreglo = new Arreglo();
                            Nodo posiciones = raiz.hijos.get(1).hijos.get(0);
                            Valor v2 = (Valor) arreglo.ValidarArreglo(var.dimensiones, als, posiciones, nombre, var.tipo, "", variables);
                            if (v2 != null) {
                                if (!"error".equals(v2.tipo)) {
                                    var.valor = v2.valor;
                                } else {
                                    Errores.ErrorSemantico("Al arreglo -" + nombre + "- no se le asigno el valor", 0, 0);
                                }
                            }
                        } //cuando no viene una instancia a=exp?
                        else if (!raiz.hijos.get(1).valor.toString().equals("InstanciaLocal")) {
                            Valor ex = (Valor) exp.Expresion(raiz.hijos.get(1), als, nombreFun, variables, false);
                            if (ex != null) {
                                if (ex.valor != null) {
                                    if (ex.tipo.equals("error")) {
                                        var.valor = null;
                                        Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                                + "se le asigno null", 0, 0);
                                    } else {
                                        Valor val = (Valor) asigna.Casteo(var.tipo, ex.valor, ex.tipo);
                                        if (!val.tipo.equals("error")) {
                                            var.valor = val.valor;

                                        } else {
                                            Errores.ErrorSemantico("El valor de la variable -" + nombre + "- "
                                                    + "no cambio", 0, 0);
                                        }
                                    }
                                } else {
                                    Errores.ErrorSemantico("El valor de la variable -" + nombre + "- "
                                            + "no cambio", 0, 0);
                                }
                            } else {
                                Errores.ErrorSemantico("El valor de la variable -" + nombre + "- "
                                        + "no cambio", 0, 0);
                            }
                        } else { //viene una instancia en una asignacion a=nuevo instancia()?
                            String objeto = raiz.hijos.get(1).hijos.get(0).valor.toString();
                            Boolean ban = false;
                            if (var.instancia) {
                                if (var.tipo.equals(objeto)) {
                                    ArrayList<Als> im = new ArrayList();
                                    if (!als.importa.isEmpty()) {
                                        for (int i = 0; i < als.importa.size(); i++) {
                                            if (objeto.equals(als.importa.get(i).nombre)) {
                                                im = als.importa;
                                            }
                                        }
                                        if (im.isEmpty()) {
                                            im = TablaSimbolosGraphik.listaAls;
                                        }
                                    } else {
                                        im = TablaSimbolosGraphik.listaAls;
                                    }
                                    //busco el importa y se lo asigno a la instancia

                                    for (int i = 0; i < im.size(); i++) {
                                        if (im.get(i).nombre.equals(objeto)) {
                                            ban = true;
                                            Als a = im.get(i);
                                            Als b = a.copiar();
                                            var.valor = b;
                                            break;
                                        }
                                    }
                                    if (!ban) {
                                        Errores.ErrorSemantico("No se puede Instanciar -" + nombre + "- porque no existe la importacion", 0, 0);
                                    }
                                } else {
                                    Errores.ErrorSemantico("No se puede instanciar -" + nombre + "- porque no son del mismo tipo", 0, 0);
                                }
                            } else {
                                Errores.ErrorSemantico("La variable -" + nombre + "- no es de tipo objeto", 0, 0);
                            }
                        }
                    } else {
                        Errores.ErrorSemantico("La variable -" + nombre + "- no esta declarada", 0, 0);
                    }
                }
                break;

                //Asignacion con Acceso -> a.acceso = exp?
                case "AsignacionAcceso": {
                    Instancia ins = new Instancia();
                    String nombre = raiz.hijos.get(0).valor.toString();
                    Valor v = (Valor) buscarVariable(variables, nombre);
                    if (v.tipo.equals("true")) {
                        Variable var = (Variable) v.valor;
                        Nodo accesos = raiz.hijos.get(1);
                        if (var.instancia) {
                            Nodo expresion = raiz.hijos.get(2);
                            Valor expAsigna = (Valor) exp.Expresion(expresion, als, nombreFun, variables, false);
                            if (expAsigna != null) {
                                if (!"error".equals(expAsigna.tipo)) {
                                    if (var.valor != null) {
                                        Als a = (Als) var.valor;
                                        ins.InstanciaReferencia(accesos, a, expAsigna, als, variables);
                                    } else {
                                        Errores.ErrorSemantico("El objeto -" + nombre + "- no esta inicializado", 0, 0);
                                    }
                                }
                            }
                        } else {
                            Errores.ErrorSemantico("La variable -" + nombre + "- a la que se quiere acceder no es de tipo objeto", 0, 0);
                        }
                    } else {
                        Errores.ErrorSemantico("La variable -" + nombre + "- no existe", 0, 0);
                    }
                }
                break;
                case "SentenciaSi": {
                    contTemp++;
                    Nodo condicion = raiz.hijos.get(0);
                    Nodo si = raiz.hijos.get(1).hijos.get(0);

                    Valor v = (Valor) exp.Expresion(condicion, als, nombreFun, variables, false);
                    if (v != null) {
                        if (!"error".equals(v.tipo)) {
                            if ("bool".equals(v.tipo)) {
                                if (v.valor.equals("verdadero")) {
                                    //se ejecutan las sentencias del si

                                    Valor sentsi = (Valor) Ejecucion(si, variables, nivel, als, nombreFun, contTemp, "si");
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals("si" + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                    if (sentsi != null && sentsi.valor != null) {
                                        return sentsi;
                                    }
                                    break;
                                } else if (!raiz.hijos.get(2).hijos.isEmpty()) {
                                    // se ejecutan las sentencias del sino cuando viene
                                    Nodo sino = raiz.hijos.get(2).hijos.get(0);
                                    Valor sentsino = (Valor) Ejecucion(sino, variables, nivel, als, nombreFun, contTemp, "sino");
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals("sino" + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }

                                    if (sentsino != null && sentsino.valor != null) {
                                        return sentsino;
                                    }
                                    break;
                                }
                            } else {
                                Errores.ErrorSemantico("La condicion del Si no devolvio un booleano", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("La condicion del Si devolvio un nulo", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }
                    break;
                }
                case "SentenciaSeleccion": {
                    contTemp++;
                    Nodo cond = raiz.hijos.get(0);
                    Valor v = (Valor) exp.Expresion(cond, als, nombreFun, variables, false);
                    if (v != null && v.valor != null) {
                        if (!v.tipo.equals("error")) {
                            Boolean tiene = false;
                            for (Nodo casos : raiz.hijos.get(1).hijos) {
                                Nodo e = casos.hijos.get(0);
                                Valor delcaso = (Valor) exp.Expresion(e, als, nombreFun, variables, false);
                                if (delcaso != null && delcaso.valor != null) {
                                    if (!delcaso.tipo.equals("error")) {
                                        if (v.valor.equals(delcaso.valor)) {
                                            tiene = true;
                                            Nodo sentcaso = casos.hijos.get(1).hijos.get(0);
                                            Valor vdelcaso = (Valor) Ejecucion(sentcaso, variables, nivel, als, nombreFun, contTemp, "seleccion");
                                            if (!pila.isEmpty()) {
                                                while (pila.peek().equals("seleccion" + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                    if (pila.isEmpty()) {
                                                        break;
                                                    }
                                                }
                                            }

                                            if (vdelcaso != null && vdelcaso.valor != null) {
                                                if (vdelcaso.valor.equals("Terminar")) {
                                                    break;
                                                } else if (!vdelcaso.valor.equals("Continuar")) {
                                                    Valor vc = new Valor(vdelcaso.valor, vdelcaso.tipo);
                                                    return vc;
                                                }
                                            }
                                        }
                                    } else {
                                        Errores.ErrorSemantico("La expresion del caso de Seleccion devolvio un nulo", 0, 0);
                                        Valor v2 = new Valor("", "error");
                                        return v2;
                                    }
                                }
                            }
                            if (!tiene) {
                                if (!raiz.hijos.get(2).hijos.isEmpty()) {
                                    Nodo casoDefecto = raiz.hijos.get(2).hijos.get(0);
                                    Ejecucion(casoDefecto, variables, nivel, als, nombreFun, contTemp, "defecto");
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals("si" + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }

                                    break;
                                }
                            }
                        } else {
                            Errores.ErrorSemantico("El valor a evaluar de Seleccion devolvio un nulo", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        Errores.ErrorSemantico("El valor a evaluar de Seleccion devolvio un nulo", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                    break;
                }
                case "SentenciaPara": {
                    Boolean bandera = false;
                    contTemp++;
                    Nodo var = raiz.hijos.get(0);
                    if (var.valor.toString().equals("DeclaraLocalVariable")) {
                        varsLocales.CrearVariableLocal(var, variables, als);
                        bandera = true;
                        pila.push("para" + contTemp);
                    } else {
                        Ejecucion(raiz, variables, nivel, als, nombreFun, contTemp, "para");
                    }

                    Nodo cond = raiz.hijos.get(1);
                    Nodo inc = raiz.hijos.get(2);
                    Nodo sentpara = raiz.hijos.get(3).hijos.get(0);

                    Valor v = (Valor) exp.Expresion(cond, als, nombreFun, variables, false);
                    if (v != null && v.valor != null) {
                        if (!v.valor.equals("error")) {
                            if (v.tipo.equals("bool")) {
                                while (v.valor.equals("verdadero")) {
                                    Valor sent = (Valor) Ejecucion(sentpara, variables, nivel, als, nombreFun, contTemp, "para");
                                    if (sent != null && sent.valor != null) {
                                        if (sent.valor.equals("Terminar")) {
                                            if (bandera) {
                                                if (!pila.isEmpty()) {
                                                    while (pila.peek().equals("para" + contTemp)) {
                                                        variables.remove(variables.size() - 1);
                                                        pila.pop();
                                                        if (pila.isEmpty()) {
                                                            break;
                                                        }
                                                    }
                                                }
                                                break;
                                            }

                                        } else if (sent.valor.equals("Continuar")) {
                                            if (!pila.isEmpty()) {
                                                if (pila.peek().equals("para" + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                }
                                            }
                                            exp.Expresion(inc, als, nombreFun, variables, false);
                                            Valor evalua = (Valor) exp.Expresion(cond, als, nombreFun, variables, false);
                                            if (evalua != null && evalua.valor != null) {
                                                if (!evalua.valor.equals("error")) {
                                                    if (evalua.tipo.equals("bool")) {
                                                        v.valor = evalua.valor;
                                                    } else {
                                                        Errores.ErrorSemantico("La condicion del Para no devolvio un bool", nivel, cont);
                                                    }
                                                } else {
                                                    Errores.ErrorSemantico("La condicion del Para devolvio un nulo", nivel, cont);
                                                }
                                            } else {
                                                Errores.ErrorSemantico("La condicion del Para devolvio un nulo", nivel, cont);
                                            }
                                        }
                                    } else {
                                        if (!pila.isEmpty()) {
                                            if (pila.peek().equals("para" + contTemp)) {
                                                variables.remove(variables.size() - 1);
                                                pila.pop();
                                            }
                                        }
                                        exp.Expresion(inc, als, nombreFun, variables, false);
                                        Valor evalua = (Valor) exp.Expresion(cond, als, nombreFun, variables, false);
                                        if (evalua != null && evalua.valor != null) {
                                            if (!evalua.valor.equals("error")) {
                                                if (evalua.tipo.equals("bool")) {
                                                    v.valor = evalua.valor;
                                                } else {
                                                    Errores.ErrorSemantico("La condicion del Para no devolvio un bool", nivel, cont);
                                                }
                                            } else {
                                                Errores.ErrorSemantico("La condicion del Para devolvio un nulo", nivel, cont);
                                            }
                                        } else {
                                            Errores.ErrorSemantico("La condicion del Para devolvio un nulo", nivel, cont);
                                        }
                                    }
                                }
                                if (bandera) {
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals("para" + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;

                            } else {
                                Errores.ErrorSemantico("La condicion del Para no devolvio un bool", nivel, cont);
                            }
                        } else {
                            Errores.ErrorSemantico("La condicion del Para devolvio un nulo", nivel, cont);
                        }
                    } else {
                        Errores.ErrorSemantico("La condicion del Para devolvio un nulo", nivel, cont);
                    }

                }
                case "SentenciaHacer": {
                    contTemp++;
                    Nodo sent1 = raiz.hijos.get(0).hijos.get(0);
                    Valor v = (Valor) Ejecucion(sent1, variables, nivel, als, nombreFun, contTemp, "hacer");
                    if (v != null && v.valor != null) {
                        if (v.valor.equals("Terminar")) {
                            break;
                        }
                    }
                    Nodo e = raiz.hijos.get(1);
                    Valor cond = (Valor) exp.Expresion(e, als, nombreFun, variables, false);
                    if (cond != null && cond.valor != null) {
                        if (!"error".equals(cond.tipo)) {
                            if (cond.tipo.equals("bool")) {
                                while (cond.valor.equals("verdadero")) {
                                    Valor sent = (Valor) Ejecucion(sent1, variables, nivel, als, nombreFun, contTemp, "hacer");
                                    if (sent != null && sent.valor != null) {
                                        if (sent.valor.equals("Terminar")) {
                                            if (!pila.isEmpty()) {
                                                while (pila.peek().equals("hacer" + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                    if (pila.isEmpty()) {
                                                        break;
                                                    }
                                                }
                                                break;
                                            }

                                        } else if (sent.valor.equals("Continuar")) {
                                            if (!pila.isEmpty()) {
                                                if (pila.peek().equals("hacer" + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                }
                                            }
                                            Valor evalua = (Valor) exp.Expresion(e, als, nombreFun, variables, false);
                                            if (evalua != null && evalua.valor != null) {
                                                if (!evalua.valor.equals("error")) {
                                                    if (evalua.tipo.equals("bool")) {
                                                        cond.valor = evalua.valor;
                                                    } else {
                                                        Errores.ErrorSemantico("La condicion del Hacer-Mientras no devolvio un bool", nivel, cont);
                                                    }
                                                } else {
                                                    Errores.ErrorSemantico("La condicion del Hacer-Mientras devolvio un nulo", nivel, cont);
                                                }
                                            } else {
                                                Errores.ErrorSemantico("La condicion del Hacer-Mientras devolvio un nulo", nivel, cont);
                                            }
                                        }
                                    } else {
                                        if (!pila.isEmpty()) {
                                            if (pila.peek().equals("hacer" + contTemp)) {
                                                variables.remove(variables.size() - 1);
                                                pila.pop();
                                            }
                                        }
                                        Valor evalua = (Valor) exp.Expresion(e, als, nombreFun, variables, false);
                                        if (evalua != null && evalua.valor != null) {
                                            if (!evalua.valor.equals("error")) {
                                                if (evalua.tipo.equals("bool")) {
                                                    cond.valor = evalua.valor;
                                                } else {
                                                    Errores.ErrorSemantico("La condicion del Hacer-Mientras no devolvio un bool", nivel, cont);
                                                }
                                            } else {
                                                Errores.ErrorSemantico("La condicion del Hacer-Mientras devolvio un nulo", nivel, cont);
                                            }
                                        } else {
                                            Errores.ErrorSemantico("La condicion del Hacer-Mientras devolvio un nulo", nivel, cont);
                                        }
                                    }
                                }
                            } else {
                                Errores.ErrorSemantico("La condicion del Hacer-Mientras no devolvio un booleano", nivel, cont);
                            }
                        } else {
                            Errores.ErrorSemantico("La condicion del Hacer-Mientras devolvio un nulo", nivel, cont);
                        }
                    } else {
                        Errores.ErrorSemantico("La condicion del Hacer-Mientras devolvio un nulo", nivel, cont);
                    }
                }
                break;
                case "SentenciaMientras": {
                    contTemp++;
                    Nodo sent1 = raiz.hijos.get(1).hijos.get(0);
                    Nodo c = raiz.hijos.get(0);
                    Valor cond = (Valor) exp.Expresion(c, als, nombreFun, variables, false);
                    if (cond != null && cond.valor != null) {
                        if (!"error".equals(cond.tipo)) {
                            if (cond.tipo.equals("bool")) {
                                while (cond.valor.equals("verdadero")) {
                                    Valor sent = (Valor) Ejecucion(sent1, variables, nivel, als, nombreFun, contTemp, "mientras");
                                    if (sent != null && sent.valor != null) {
                                        if (sent.valor.equals("Terminar")) {
                                            if (!pila.isEmpty()) {
                                                while (pila.peek().equals("mientras" + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                    if (pila.isEmpty()) {
                                                        break;
                                                    }
                                                }
                                                break;
                                            }

                                        } else if (sent.valor.equals("Continuar")) {
                                            if (!pila.isEmpty()) {
                                                if (pila.peek().equals("mientras" + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                }
                                            }
                                            Valor evalua = (Valor) exp.Expresion(c, als, nombreFun, variables, false);
                                            if (evalua != null && evalua.valor != null) {
                                                if (!evalua.valor.equals("error")) {
                                                    if (evalua.tipo.equals("bool")) {
                                                        cond.valor = evalua.valor;
                                                    } else {
                                                        Errores.ErrorSemantico("La condicion del Mientras no devolvio un bool", nivel, cont);
                                                    }
                                                } else {
                                                    Errores.ErrorSemantico("La condicion del Mientras devolvio un nulo", nivel, cont);
                                                }
                                            } else {
                                                Errores.ErrorSemantico("La condicion del Mientras devolvio un nulo", nivel, cont);
                                            }
                                        }
                                    } else {
                                        if (!pila.isEmpty()) {
                                            if (pila.peek().equals("mientras" + contTemp)) {
                                                variables.remove(variables.size() - 1);
                                                pila.pop();
                                            }
                                        }
                                        Valor evalua = (Valor) exp.Expresion(c, als, nombreFun, variables, false);
                                        if (evalua != null && evalua.valor != null) {
                                            if (!evalua.valor.equals("error")) {
                                                if (evalua.tipo.equals("bool")) {
                                                    cond.valor = evalua.valor;
                                                } else {
                                                    Errores.ErrorSemantico("La condicion del Mientras no devolvio un bool", nivel, cont);
                                                }
                                            } else {
                                                Errores.ErrorSemantico("La condicion del Mientras devolvio un nulo", nivel, cont);
                                            }
                                        } else {
                                            Errores.ErrorSemantico("La condicion del Mientras devolvio un nulo", nivel, cont);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
                case "AsignaPosicion": {
                    Arreglo arreglo = new Arreglo();
                    String nombre = raiz.hijos.get(0).hijos.get(0).valor.toString();
                    Valor existe = (Valor) buscarVariable(variables, nombre);
                    if (existe.tipo.equals("true")) {
                        Variable var = (Variable) existe.valor;
                        if (var.arreglo) {
                            Nodo posicion = raiz.hijos.get(0).hijos.get(1);

                            ArrayList pos = new ArrayList();
                            for (Nodo c : posicion.hijos) {
                                Valor v = (Valor) exp.Expresion(c, als, nombreFun, variables, false);
                                if (v != null) {
                                    if (v.valor != null) {
                                        if (!"error".equals(v.tipo)) {

                                            if (!"numero".equals(v.tipo)) {
                                                Errores.ErrorSemantico("Las dimensiones del arreglo deben ser enteros", 0, 0);
                                                Valor v2 = new Valor("", "error");
                                                return v2;
                                            } else {
                                                pos.add(v.valor);
                                            }

                                        } else {
                                            Errores.ErrorSemantico("Error en las dimensiones del arreglo -" + nombre + "- ", 0, 0);
                                            Valor v2 = new Valor("", "error");
                                            return v2;
                                        }
                                    } else {
                                        Errores.ErrorSemantico("Error en las dimensiones del arreglo -" + nombre + "- ", 0, 0);
                                        Valor v2 = new Valor("", "error");
                                        return v2;
                                    }
                                } else {
                                    Errores.ErrorSemantico("Error en las dimensiones del arreglo -" + nombre + "- ", 0, 0);
                                    Valor v2 = new Valor("", "error");
                                    return v2;
                                }
                            }

                            Nodo expresion = raiz.hijos.get(1);
                            Valor exx = (Valor) exp.Expresion(expresion, als, nombreFun, variables, false);

                            //voy a obtener la posicion
                            Valor val = (Valor) arreglo.BuscarPosicion(var.dimensiones, pos, variables, nombre);
                            if (val != null) {
                                if (!"error".equals(val.tipo)) {
                                    if (exx.valor != null) {
                                        if (exx.tipo.equals(var.tipo)) {
                                            ArrayList a = (ArrayList) var.valor;
                                            a.set(Integer.parseInt(val.valor.toString()), exx.valor.toString());
                                            break;
                                        } else {
                                            Errores.ErrorSemantico("La expresion a asignar al arreglo "
                                                    + "-" + nombre + "- no es del mismo tipo", 0, 0);
                                            Valor v2 = new Valor("", "error");
                                            return v2;
                                        }
                                    } else {
                                        Errores.ErrorSemantico("La expresion a asignar al arreglo "
                                                + "-" + nombre + "- trae nulo, no se le asigno", 0, 0);
                                        Valor v2 = new Valor("", "error");
                                        return v2;
                                    }
                                } else {
                                    Valor v2 = new Valor("", "error");
                                    return v2;
                                }
                            } else {
                                Errores.ErrorSemantico("El acceso en el arreglo -" + nombre + "- esta fuera de rango", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }

                        } else {
                            Errores.ErrorSemantico("La variable -" + nombre + "- no es de tipo arreglo", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                    } else {
                        Errores.ErrorSemantico("La variable -" + nombre + "- no existe", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }

                }
                case "Acceso": {
                    Instancia ins = new Instancia();
                    String nombre = raiz.hijos.get(0).valor.toString();
                    Valor v = (Valor) buscarVariable(variables, nombre);
                    if (v.tipo.equals("true")) {
                        Variable var = (Variable) v.valor;
                        Nodo accesos = raiz.hijos.get(1);
                        if (var.instancia) {
                            if (var.valor != null) {
                                Als a = (Als) var.valor;
                                ins.LlamaAcceso(accesos, a, als, variables);
                            } else {
                                Errores.ErrorSemantico("El objeto -" + nombre + "- no esta inicializado", 0, 0);
                            }

                        } else {
                            Errores.ErrorSemantico("La variable -" + nombre + "- a la que se quiere acceder no es de tipo objeto", 0, 0);
                        }
                    } else {
                        Errores.ErrorSemantico("La variable -" + nombre + "- no existe", 0, 0);
                    }
                }
                break;
                case "LlamaFun": {
                    contTemp++;
                    ArrayList nueva = new ArrayList();
                    nueva.add(als.VarsGlobales);
                    String nombre = raiz.hijos.get(0).valor.toString();
                    ArrayList<Valor> valores = new ArrayList();
                    if (raiz.hijos.get(1).hijos.isEmpty()) {
                        Valor existe = (Valor) buscarMetodo(als.Metodos, nombre, valores);
                        if (existe.tipo.equals("true")) {
                            MetodoGraphik met = (MetodoGraphik) existe.valor;
                            Nodo cuerpometodo = met.cuerpo.hijos.get(0);

                            Valor v = (Valor) Ejecucion(cuerpometodo, nueva, nivel, als, nombre, contTemp, nombre);
                            variables = nueva;
                            if (met.tipo.equals("vacio")) {
                                if (v != null) {
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals(nombre + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                    Errores.ErrorSemantico("El metodo -" + nombre + "- no puede retornar un valor", nivel, cont);
                                    Valor v2 = new Valor("", "error");
                                    return v2;
                                }
                                //saco variables
                                if (!pila.isEmpty()) {
                                    while (pila.peek().equals(nombre + contTemp)) {
                                        variables.remove(variables.size() - 1);
                                        pila.pop();
                                        if (pila.isEmpty()) {
                                            break;
                                        }
                                    }
                                }
                            } else if (v != null) {
                                if (v.tipo != null) {
                                    if (!"error".equals(v.tipo)) {
                                        if (met.tipo.equals(v.tipo)) {
                                            //saco variables
                                            if (!pila.isEmpty()) {
                                                while (pila.peek().equals(nombre + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                    if (pila.isEmpty()) {
                                                        break;
                                                    }
                                                }
                                            }
                                        } else {
                                            if (!pila.isEmpty()) {
                                                while (pila.peek().equals(nombre + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                    if (pila.isEmpty()) {
                                                        break;
                                                    }
                                                }
                                            }
                                            Errores.ErrorSemantico("El metodo -" + nombre + "- retorna un tipo diferente", nivel, cont);
                                            Valor v2 = new Valor("", "error");
                                            return v2;
                                        }
                                    } else {
                                        if (!pila.isEmpty()) {
                                            while (pila.peek().equals(nombre + contTemp)) {
                                                variables.remove(variables.size() - 1);
                                                pila.pop();
                                                if (pila.isEmpty()) {
                                                    break;
                                                }
                                            }
                                        }
                                        Valor v2 = new Valor("", "error");
                                        return v2;
                                    }
                                } else {
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals(nombre + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                    Valor v2 = new Valor("", "error");
                                    return v2;
                                }
                            } else {
                                if (!pila.isEmpty()) {
                                    while (pila.peek().equals(nombre + contTemp)) {
                                        variables.remove(variables.size() - 1);
                                        pila.pop();
                                        if (pila.isEmpty()) {
                                            break;
                                        }
                                    }
                                }
                                Errores.ErrorSemantico("El metodo -" + nombre + "- no tiene retorno", nivel, cont);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("El metodo -" + nombre + "- no existe", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                    } else {
                        for (int i = 0; i < raiz.hijos.get(1).hijos.size(); i++) {
                            Valor v = (Valor) exp.Expresion(raiz.hijos.get(1).hijos.get(i), als, nombreFun, variables, false);
                            if (v != null) {
                                if (v.valor != null) {
                                    if (!"error".equals(v.tipo)) {
                                        Valor v2 = new Valor(v.valor, v.tipo);
                                        valores.add(v2);
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
                        Valor existe = (Valor) buscarMetodo(als.Metodos, nombre, valores);
                        if (existe.tipo.equals("true")) {
                            MetodoGraphik met = (MetodoGraphik) existe.valor;
                            //metodo con parametros
                            //envio a crear los parametros como variables
                            Valor v2 = (Valor) varsLocales.CrearVariablesMetodos(met.listaParametros, valores, nueva, als, nombre, contTemp);
                            variables = nueva;
                            if (!"error".equals(v2.tipo)) {
                                Nodo cuerpometodo = met.cuerpo.hijos.get(0);
                                Valor v = (Valor) Ejecucion(cuerpometodo, nueva, nivel, als, nombre, contTemp, nombre);
                                if (met.tipo.equals("vacio")) {
                                    if (v != null) {
                                        if (!pila.isEmpty()) {
                                            while (pila.peek().equals(nombre + contTemp)) {
                                                variables.remove(variables.size() - 1);
                                                pila.pop();
                                                if (pila.isEmpty()) {
                                                    break;
                                                }
                                            }
                                        }
                                        Errores.ErrorSemantico("El metodo -" + nombre + "- no puede retornar un valor", 0, 0);
                                        Valor v3 = new Valor("", "error");
                                        return v3;
                                    }
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals(nombre + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                } else if (v != null) {
                                    if (v.tipo != null) {
                                        if (!"error".equals(v.tipo)) {
                                            if (met.tipo.equals(v.tipo)) {
                                                //saco variables
                                                if (!pila.isEmpty()) {
                                                    while (pila.peek().equals(nombre + contTemp)) {
                                                        variables.remove(variables.size() - 1);
                                                        pila.pop();
                                                        if (pila.isEmpty()) {
                                                            break;
                                                        }
                                                    }
                                                }
                                                //Valor v3 = new Valor(v.valor, v.tipo);
                                                //return v3;
                                            } else {
                                                if (!pila.isEmpty()) {
                                                    while (pila.peek().equals(nombre + contTemp)) {
                                                        variables.remove(variables.size() - 1);
                                                        pila.pop();
                                                        if (pila.isEmpty()) {
                                                            break;
                                                        }
                                                    }
                                                }
                                                Errores.ErrorSemantico("El metodo -" + nombre + "- retorna un tipo diferente", 0, 0);
                                                Valor v3 = new Valor("", "error");
                                                return v3;
                                            }
                                        } else {
                                            if (!pila.isEmpty()) {
                                                while (pila.peek().equals(nombre + contTemp)) {
                                                    variables.remove(variables.size() - 1);
                                                    pila.pop();
                                                    if (pila.isEmpty()) {
                                                        break;
                                                    }
                                                }
                                            }
                                            Valor v3 = new Valor("", "error");
                                            return v3;
                                        }
                                    } else {
                                        if (!pila.isEmpty()) {
                                            while (pila.peek().equals(nombre + contTemp)) {
                                                variables.remove(variables.size() - 1);
                                                pila.pop();
                                                if (pila.isEmpty()) {
                                                    break;
                                                }
                                            }
                                        }
                                        Valor v3 = new Valor("", "error");
                                        return v3;
                                    }
                                } else {
                                    if (!pila.isEmpty()) {
                                        while (pila.peek().equals(nombre + contTemp)) {
                                            variables.remove(variables.size() - 1);
                                            pila.pop();
                                            if (pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                    Errores.ErrorSemantico("El metodo -" + nombre + "- no tiene retorno", 0, 0);
                                    Valor v3 = new Valor("", "error");
                                    return v3;
                                }
                            } else {
                                if (!pila.isEmpty()) {
                                    while (pila.peek().equals(nombre + contTemp)) {
                                        variables.remove(variables.size() - 1);
                                        pila.pop();
                                        if (pila.isEmpty()) {
                                            break;
                                        }
                                    }
                                }
                                Valor v = new Valor("", "error");
                                return v;
                            }
                        } else {
                            Errores.ErrorSemantico("El metodo -" + nombre + "- no existe", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                    }

                    break;
                }

                case "LlamarHK": {

                    String nombre = raiz.hijos.get(0).valor.toString();
                    FuncionHaskell fun = new FuncionHaskell();
                    Boolean tt = false;
                    if (!als.incluye.isEmpty()) {
                        for (int i = 0; i < als.incluye.size(); i++) {
                            if (nombre.equals(als.incluye.get(i).getNombre())) {
                                fun = (FuncionHaskell) als.incluye.get(i);
                                tt = true;
                                break;
                            }
                        }
                        if (tt) {
                            ArrayList<Parametros> param = (ArrayList<Parametros>) fun.parametros;
                            if (param.isEmpty()) {
                                //no trae parametros
                                if (raiz.hijos.get(1).hijos.isEmpty()) {
                                    Nodo cuerpo = fun.getCuerpo();
                                    ArrayList nueva = new ArrayList();
                                    RecorreHaskell.Graphika(nombre, cuerpo, nueva);
                                } else {
                                    Errores.ErrorSemantico("La cantidad de parametros "
                                            + "para la funcion -" + nombre + "- no coinciden", nivel, cont);
                                }
                            } else if (!raiz.hijos.get(1).hijos.isEmpty()) {
                                //trae parametros
                                if (param.size() == raiz.hijos.get(1).hijos.size()) {
                                    ArrayList nueva = new ArrayList();
                                    for (int i = 0; i < param.size(); i++) {
                                        Valor v = (Valor) exp.Expresion(raiz.hijos.get(1).hijos.get(i), als, nombreFun, variables, false);
                                        if (v != null) {
                                            if (!"error".equals(v.tipo)) {
                                                if (v.tipo.equals("decimal")) {
                                                    v.tipo = "numero";
                                                } else if (v.tipo.equals("cadena") || v.tipo.equals("caracter")) {
                                                    String cad = v.valor.toString();
                                                    ArrayList caracteres = new ArrayList();
                                                    for (int j = 0; j < cad.length(); j++) {
                                                        caracteres.add(cad.charAt(j));
                                                    }
                                                    v.valor = caracteres;
                                                }
                                                Variable var = new Variable(param.get(i).nombre, v.valor, v.tipo);
                                                nueva.add(var);
                                            } else {
                                                break;
                                            }
                                        } else {
                                            Errores.ErrorSemantico("Error en los parametros", nivel, cont);
                                            break;
                                        }
                                    }

                                    Nodo cuerpo = fun.getCuerpo();
                                    RecorreHaskell.Graphika(nombre, cuerpo, nueva);

                                }
                            } else {
                                Errores.ErrorSemantico("La cantidad de parametros "
                                        + "para la funcion -" + nombre + "- no coinciden", nivel, cont);
                            }
                        } else {
                            Errores.ErrorSemantico("La funcion Haskell -" + nombre + "- no existe", nivel, cont);
                        }
                    } else {
                        Errores.ErrorSemantico("No hay funciones Incluidas de Haskell", nivel, cont);
                    }
                    break;
                }

                case "Imprimir": {
                    Valor v = (Valor) exp.Expresion(raiz.hijos.get(0), als, nombreFun, variables, true);
                    if (v != null) {
                        if (v.valor != null) {
                            Imprimir.Imprimir(v.valor.toString().replace("\"", ""));
                        } else {
                            Imprimir.Imprimir("");
                        }
                    } else {
                        Imprimir.Imprimir("");
                    }

                }
                break;

                case "LlamaDatos": {
                    ArrayList<Valor> p = new ArrayList();
                    Valor v = (Valor) buscarMetodo(als.Metodos, "Datos", p);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;
                        Nodo cuerpo = met.cuerpo;
                        Nodo procesar = new Nodo();
                        for (Nodo c : cuerpo.hijos) {
                            if (c.valor.toString().equals("Procesar")) {
                                procesar = c.hijos.get(0);
                            }
                            if (!c.valor.toString().equals("Procesar")) {
                                Valor columna = (Valor) exp.Expresion(c.hijos.get(0), als, nombreFun, variables, false);

                                //DONDE
                                if (c.valor.equals("Donde")) {
                                    Valor filtro = (Valor) exp.Expresion(c.hijos.get(1), als, nombreFun, variables, false);
                                    if (columna != null && filtro != null) {
                                        if (!"error".equals(columna.tipo) && !"error".equals(filtro.tipo)) {
                                            ArrayList<Vector> resultado = new ArrayList();
                                            //busqueda donde
                                            resultado = (ArrayList) datos.filtroDonde(Datos.jTable1,
                                                    Integer.parseInt(columna.valor.toString()), filtro.valor.toString());
                                            if (!resultado.isEmpty()) {
                                                for (int i = 0; i < resultado.size(); i++) {
                                                    ExpresionGraphik.Columnas.clear();
                                                    Resultados.clear();
                                                    for (int j = 0; j < resultado.get(i).size(); j++) {
                                                        String cad = resultado.get(i).get(j).toString().replace("\"", "");
                                                        String clase;
                                                        if (cad.contains(".")) {
                                                            Double num = Double.parseDouble(cad);
                                                            Valor val = new Valor(num, "decimal");
                                                            ExpresionGraphik.Columnas.add(val);
                                                        } else {
                                                            try {
                                                                int valor = Integer.parseInt(cad);
                                                                Valor val = new Valor(valor, "numero");
                                                                ExpresionGraphik.Columnas.add(val);

                                                            } catch (Exception e) {
                                                                Valor val = new Valor(cad, "cadena");
                                                                ExpresionGraphik.Columnas.add(val);

                                                            }
                                                        }

                                                    }
                                                    Valor res = (Valor) exp.Expresion(procesar, als, "Datos", variables, false);
                                                    if (res != null) {
                                                        if (res.tipo != "error") {
                                                            Resultados.add(res.valor);
                                                        }
                                                    }
                                                }
                                                //tabla donde
                                                datos.resultadoDonde(Datos.jTable1, Resultados, filtro.valor.toString());

                                            }
                                        }
                                    }

                                    //DONDE CADA
                                } else if (c.valor.equals("DondeCada")) {
                                    if (columna != null) {
                                        if (!"error".equals(columna.tipo)) {
                                            ArrayList<Vector> resultado = new ArrayList();
                                            //busqueda donde cada
                                            resultado = (ArrayList) datos.filtroDondeCada(Datos.jTable1);
                                            if (!resultado.isEmpty()) {
                                                Resultados.clear();
                                                for (int i = 0; i < resultado.size(); i++) {
                                                    ExpresionGraphik.Columnas.clear();

                                                    for (int j = 0; j < resultado.get(i).size(); j++) {
                                                        String cad = resultado.get(i).get(j).toString().replace("\"", "");
                                                        String clase;
                                                        if (cad.contains(".")) {
                                                            Double num = Double.parseDouble(cad);
                                                            Valor val = new Valor(num, "decimal");
                                                            ExpresionGraphik.Columnas.add(val);
                                                        } else {
                                                            try {
                                                                int valor = Integer.parseInt(cad);
                                                                Valor val = new Valor(valor, "numero");
                                                                ExpresionGraphik.Columnas.add(val);

                                                            } catch (Exception e) {
                                                                Valor val = new Valor(cad, "cadena");
                                                                ExpresionGraphik.Columnas.add(val);

                                                            }
                                                        }

                                                    }
                                                    Valor res = (Valor) exp.Expresion(procesar, als, "Datos", variables, false);
                                                    if (res != null) {
                                                        if (res.tipo != "error") {
                                                            Resultados.add(res.valor);
                                                        }
                                                    }
                                                }
                                                //tabla donde cada
                                                datos.resultadoDondeCada(Datos.jTable1, Resultados,
                                                        Integer.parseInt(columna.valor.toString()), resultado);

                                            }
                                        }
                                    }

                                    //DONDE TODO
                                } else if (c.valor.equals("DondeTodo")) {
                                    if (columna != null) {
                                        if (!"error".equals(columna.tipo)) {
                                            ArrayList<Vector> resultado = new ArrayList();
                                            //busqueda
                                            resultado = (ArrayList) datos.filtroDondeTodo(Datos.jTable1);
                                            if (!resultado.isEmpty()) {
                                                Resultados.clear();
                                                for (int i = 0; i < resultado.size(); i++) {
                                                    ExpresionGraphik.Columnas.clear();

                                                    for (int j = 0; j < resultado.get(i).size(); j++) {
                                                        String cad = resultado.get(i).get(j).toString().replace("\"", "");
                                                        String clase;
                                                        if (cad.contains(".")) {
                                                            Double num = Double.parseDouble(cad);
                                                            Valor val = new Valor(num, "decimal");
                                                            ExpresionGraphik.Columnas.add(val);
                                                        } else {
                                                            try {
                                                                int valor = Integer.parseInt(cad);
                                                                Valor val = new Valor(valor, "numero");
                                                                ExpresionGraphik.Columnas.add(val);

                                                            } catch (Exception e) {
                                                                Valor val = new Valor(cad, "cadena");
                                                                ExpresionGraphik.Columnas.add(val);

                                                            }
                                                        }

                                                    }
                                                    Valor res = (Valor) exp.Expresion(procesar, als, "Datos", variables, false);
                                                    if (res != null) {
                                                        if (res.tipo != "error") {
                                                            Resultados.add(res.valor);
                                                        }
                                                    }
                                                }
                                                //tabla donde todo
                                                datos.resultadoDondeTodo(Datos.jTable1, Resultados,
                                                        Integer.parseInt(columna.valor.toString()), resultado);

                                            }
                                        }
                                    }
                                } else {
                                    Errores.ErrorSemantico("El Metodo Datos necesita un Donde", nivel, cont);
                                }
                            }
                        }
                    } else {
                        Errores.ErrorSemantico("El Metodo Datos no existe", nivel, cont);
                    }
                    break;
                }

                case "Graphikar": {
                    
                    ArrayList valoresX = new ArrayList();
                    ArrayList valoresY = new ArrayList();
                    Nodo exp1 = raiz.hijos.get(0);
                    Nodo exp2 = raiz.hijos.get(1);
                    Valor v1 = new Valor("", "");
                    Valor v2 = new Valor("", "");
                    if (exp1.hijos.get(0).valor.equals("Posiciones")) {
                        ArrayList val = new ArrayList();
                        for (Nodo c : exp1.hijos.get(0).hijos) {
                            val.add(Integer.parseInt(c.hijos.get(0).valor.toString()));
                        }
                        valoresX = val;
                    } else {
                        v1 = (Valor) exp.Expresion(exp1, als, nombreFun, variables, false);
                        if (v1 != null) {
                            if (!v1.tipo.equals("error")) {
                                if (v1.valor != null) {
                                    valoresX = (ArrayList) v1.valor;
                                }
                            }
                        }
                    }
                    if (exp2.hijos.get(0).valor.equals("Posiciones")) {
                        ArrayList val = new ArrayList();
                        for (Nodo c : exp2.hijos.get(0).hijos) {
                            val.add(Integer.parseInt(c.hijos.get(0).valor.toString()));
                        }
                        valoresY = val;
                    } else {
                        v2 = (Valor) exp.Expresion(exp2, als, nombreFun, variables, false);
                        if (v2 != null) {
                            if (!v2.tipo.equals("error")) {
                                if (v2.valor != null) {
                                    valoresY = (ArrayList) v2.valor;
                                }
                            }
                        } else {

                        }
                    }
                    //Graficar nueva = Graficar.Inicializar();
                    Graficar.graficarL(valoresX, valoresY, "Grafica");
                    pasoGraf = true;
                }
                break;
                case "Incremento": {
                    exp.Expresion(raiz, als, nombreFun, variables, false);
                }
                break;
                case "Decremento": {
                    exp.Expresion(raiz, als, nombreFun, variables, false);
                }
                break;

                case "Terminar": {
                    Valor v = new Valor("Terminar", "");
                    return v;
                }
                case "Continuar": {
                    Valor v = new Valor("Continuar", "");
                    return v;
                }
                case "Retorno": {
                    Valor v = (Valor) exp.Expresion(raiz.hijos.get(0), als, nombreFun, variables, false);
                    Valor v2 = new Valor(v.valor, v.tipo);
                    return v2;
                }

            }
        }
        return null;
    }

    public Object buscarVariable(ArrayList<ArrayList<Variable>> variables, String nombre) {

        for (int i = 0; i < variables.size(); i++) {
            for (int j = 0; j < variables.get(i).size(); j++) {
                if (variables.get(i).get(j).nombre.equals(nombre)) {
                    Variable var = variables.get(i).get(j);
                    Valor v = new Valor(var, "true");
                    return v;
                }
            }
        }
        Valor v = new Valor("", "false");
        return v;
    }

    public Object buscarMetodo(ArrayList<MetodoGraphik> metodos, String nombre, ArrayList<Valor> parametros) {
        Boolean existe = false;
        for (int i = 0; i < metodos.size(); i++) {
            if (metodos.get(i).nombre.equals(nombre)) {
                MetodoGraphik met = metodos.get(i);
                if (met.listaParametros.isEmpty()) {
                    if (parametros.isEmpty()) {
                        Valor v = new Valor(met, "true");
                        return v;
                    }
                } else if (!parametros.isEmpty()) {
                    if (met.listaParametros.size() == parametros.size()) {
                        for (int j = 0; j < parametros.size(); j++) {
                            if (met.listaParametros.get(j).tipo.equals(parametros.get(j).tipo)) {
                                existe = true;
                            } else {
                                existe = false;
                                break;
                            }
                        }
                        if (existe) {
                            Valor v = new Valor(met, "true");
                            return v;
                        }
                    }
                }

            }

        }
        Valor v = new Valor("", "false");
        return v;
    }

}
