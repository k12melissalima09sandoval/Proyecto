/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Dibujar.Nodo;
import Interprete.Valor;
import Interprete.Variable;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class Instancia {

    ExpresionGraphik exp = new ExpresionGraphik();
    static SegundaPasada ej = new SegundaPasada();
    CrearVariables varsLocales = new CrearVariables();

    public Object InstanciaAcceso(Nodo accesos, Als claseObjeto, Als claseAnterior, ArrayList<ArrayList<Variable>> anteriores) {
        Object valorTemp = "";
        String tipoTemp = "";
        Boolean bandera = false;

        for (Nodo nodo : accesos.hijos) {
            ArrayList metodos = (ArrayList) claseObjeto.Metodos;
            ArrayList<Variable> variables = (ArrayList) claseObjeto.VarsGlobales;
            if (nodo.hijos.size() == 1) {
                if (bandera) {
                    Errores.ErrorSemantico("No se puede acceder a un atributo "
                            + "-" + nodo.hijos.get(0).valor.toString() + "- ", 0, 0);
                    Valor v2 = new Valor("", "error");
                    return v2;
                } else {
                    bandera = true;
                    //cuando quiere acceder a un arreglo variable = a.arreglo[5]
                    if (nodo.hijos.get(0).valor.toString().equals("LlamaArreglo")) {
                        ArrayList nuevaVar = new ArrayList();
                        nuevaVar.add(variables);
                        Valor v = (Valor) exp.Expresion(nodo.hijos.get(0), claseObjeto, "", nuevaVar, false);
                        if (v != null) {
                            if (v.valor != "error") {
                                valorTemp = v.valor;
                                tipoTemp = v.tipo;
                            } else {
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        //cuando viene acceso a un atributo
                        String nombre = nodo.hijos.get(0).valor.toString();
                        Valor var = (Valor) buscarVariable(variables, nombre);

                        if (var.tipo.equals("true")) {
                            Variable acceso = (Variable) var.valor;
                            if (acceso.valor != null) {
                                if (acceso.instancia) {
                                    claseObjeto = (Als) acceso.valor;
                                    bandera = false;
                                }
                                valorTemp = acceso.valor;
                                tipoTemp = acceso.tipo;
                            } else {
                                Errores.ErrorSemantico("La variable -" + nombre + "- no tiene ningun valor"
                                        + " en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("La variable -" + nombre + "- no esta declarada en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }
                }
                //cuando viene una llamada a funcion trae dos hijos
            } else {
                bandera = false;
                ArrayList nueva = new ArrayList();
                nueva.add(variables);
                String nombre = nodo.hijos.get(0).valor.toString();

                ArrayList<Valor> valores = new ArrayList();
                if (nodo.hijos.get(1).hijos.isEmpty()) {
                    Valor v = (Valor) buscarMetodo(metodos, nombre, valores);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;

                        if (met.tipo.equals("vacio")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        } else {
                            //es de tipo diferente a vacio y tiene que traer retorno

                            Nodo cuerpo = met.cuerpo.hijos.get(0);
                            Valor retorno = (Valor) ej.Ejecucion(cuerpo, nueva, 0,
                                    claseObjeto, nombre, SegundaPasada.contTemp, nombre);
                            if (retorno != null) {
                                if (retorno.valor != null) {
                                    if (!retorno.tipo.equals("numero") && !retorno.tipo.equals("cadena") && !retorno.tipo.equals("caracter")
                                            && !retorno.tipo.equals("bool") && !retorno.tipo.equals("decimal")) {

                                        claseObjeto = (Als) retorno.valor;
                                        valorTemp = retorno.valor;
                                        tipoTemp = retorno.tipo;
                                    } else {
                                        bandera = true;
                                        valorTemp = retorno.valor;
                                        tipoTemp = retorno.tipo;
                                    }
                                    if (!SegundaPasada.pila.isEmpty()) {
                                        while (SegundaPasada.pila.peek().equals(nombre + SegundaPasada.contTemp)) {
                                            SegundaPasada.pila.pop();
                                            if (SegundaPasada.pila.isEmpty()) {
                                                break;
                                            }
                                        }
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

                    } else {
                        Errores.ErrorSemantico("El metodo -" + nombre + "- no existe en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                } else {

                    for (int i = 0; i < nodo.hijos.get(1).hijos.size(); i++) {
                        Valor v2 = (Valor) exp.Expresion(nodo.hijos.get(1).hijos.get(i), claseAnterior, nombre, anteriores, false);
                        if (v2 != null) {
                            if (v2.valor != null) {
                                if (!"error".equals(v2.tipo)) {
                                    Valor v3 = new Valor(v2.valor, v2.tipo);
                                    valores.add(v3);
                                }
                            } else {
                                Valor v3 = new Valor("", "error");
                                return v3;
                            }
                        } else {
                            Valor v3 = new Valor("", "error");
                            return v3;
                        }
                    }
                    Valor v = (Valor) buscarMetodo(metodos, nombre, valores);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;
                        if (met.tipo.equals("vacio")) {
                            Valor v4 = new Valor("", "error");
                            return v4;
                        } else {
                            Valor v2 = (Valor) varsLocales.CrearVariablesMetodos(met.listaParametros, valores, nueva,
                                    claseObjeto, nombre, SegundaPasada.contTemp);
                            // variables = nueva;
                            if (!"error".equals(v2.tipo)) {
                                Nodo cuerpometodo = met.cuerpo.hijos.get(0);
                                Valor retorno = (Valor) ej.Ejecucion(cuerpometodo, nueva, 0, claseObjeto, nombre, SegundaPasada.contTemp, nombre);
                                if (retorno != null) {
                                    if (retorno.valor != null) {
                                        if (!retorno.tipo.equals("numero") && !retorno.tipo.equals("cadena") && !retorno.tipo.equals("caracter")
                                                && !retorno.tipo.equals("bool") && !retorno.tipo.equals("decimal")) {
                                            claseObjeto = (Als) retorno.valor;
                                            valorTemp = retorno.valor;
                                            tipoTemp = retorno.tipo;
                                        } else {
                                            bandera = true;
                                            valorTemp = retorno.valor;
                                            tipoTemp = retorno.tipo;
                                        }
                                        if (!SegundaPasada.pila.isEmpty()) {
                                            while (SegundaPasada.pila.peek().equals(nombre + SegundaPasada.contTemp)) {
                                                SegundaPasada.pila.pop();
                                                if (SegundaPasada.pila.isEmpty()) {
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        Valor v3 = new Valor("", "error");
                                        return v3;
                                    }
                                } else {
                                    Valor v3 = new Valor("", "error");
                                    return v3;
                                }
                            }
                        }
                    } else {
                        Errores.ErrorSemantico("El metodo -" + nombre + "- no existe en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                }
            }

        }
        Valor vv = new Valor(valorTemp, tipoTemp);
        return vv;
    }

    public Object LlamaAcceso(Nodo accesos, Als claseObjeto, Als claseAnterior, ArrayList<ArrayList<Variable>> anteriores) {
        Object valorTemp = "";
        String tipoTemp = "";
        Boolean bandera = false;

        for (Nodo nodo : accesos.hijos) {
            ArrayList metodos = (ArrayList) claseObjeto.Metodos;
            ArrayList<Variable> variables = (ArrayList) claseObjeto.VarsGlobales;
            if (nodo.hijos.size() == 1) {
                if (bandera) {
                    Errores.ErrorSemantico("No se puede acceder a un atributo "
                            + "-" + nodo.hijos.get(0).valor.toString() + "- ", 0, 0);
                    Valor v2 = new Valor("", "error");
                    return v2;
                } else {
                    bandera = true;
                    //cuando quiere acceder a un arreglo variable = a.arreglo[5]
                    if (nodo.hijos.get(0).valor.toString().equals("LlamaArreglo")) {
                        ArrayList nuevaVar = new ArrayList();
                        nuevaVar.add(variables);
                        Valor v = (Valor) exp.Expresion(nodo.hijos.get(0), claseObjeto, "", nuevaVar, false);
                        if (v != null) {
                            if (v.valor != "error") {
                                valorTemp = v.valor;
                                tipoTemp = v.tipo;
                            } else {
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        //cuando viene acceso a un atributo
                        String nombre = nodo.hijos.get(0).valor.toString();
                        Valor var = (Valor) buscarVariable(variables, nombre);

                        if (var.tipo.equals("true")) {
                            Variable acceso = (Variable) var.valor;
                            if (acceso.valor != null) {
                                if (acceso.instancia) {
                                    claseObjeto = (Als) acceso.valor;
                                    bandera = false;
                                }
                                valorTemp = acceso.valor;
                                tipoTemp = acceso.tipo;
                            } else {
                                Errores.ErrorSemantico("La variable -" + nombre + "- no tiene ningun valor"
                                        + " en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("La variable -" + nombre + "- no esta declarada en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }
                }
                //cuando viene una llamada a funcion trae dos hijos
            } else {
                bandera = false;

                ArrayList nueva = new ArrayList();
                nueva.add(variables);

                String nombre = nodo.hijos.get(0).valor.toString();
                ArrayList<Valor> valores = new ArrayList();

                if (nodo.hijos.get(1).hijos.isEmpty()) {
                    //no trae parametros
                    //es de tipo diferente a vacio y trae retorno
                    Valor v = (Valor) buscarMetodo(metodos, nombre, valores);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;
                        Nodo cuerpo = met.cuerpo.hijos.get(0);
                        Valor retorno = (Valor) ej.Ejecucion(cuerpo, nueva, 0,
                                claseObjeto, nombre, SegundaPasada.contTemp, nombre);
                        if (retorno != null) {
                            if (retorno.valor != null) {
                                if (!retorno.tipo.equals("numero") && !retorno.tipo.equals("cadena") && !retorno.tipo.equals("caracter")
                                        && !retorno.tipo.equals("bool") && !retorno.tipo.equals("decimal")) {
                                    claseObjeto = (Als) retorno.valor;
                                    valorTemp = retorno.valor;
                                    tipoTemp = retorno.tipo;
                                } else {
                                    bandera = true;
                                    valorTemp = retorno.valor;
                                    tipoTemp = retorno.tipo;
                                }
                                if (!SegundaPasada.pila.isEmpty()) {
                                    while (SegundaPasada.pila.peek().equals(nombre + SegundaPasada.contTemp)) {
                                        SegundaPasada.pila.pop();
                                        if (SegundaPasada.pila.isEmpty()) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Errores.ErrorSemantico("El metodo -" + nombre + "- no existe en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                } else {
                    //trae parametros
                    //encuentro los valores que voy a mandarle en los parametros

                    for (int i = 0; i < nodo.hijos.get(1).hijos.size(); i++) {
                        Valor v2 = (Valor) exp.Expresion(nodo.hijos.get(1).hijos.get(i), claseAnterior, nombre, anteriores, false);
                        if (v2 != null) {
                            if (v2.valor != null) {
                                if (!"error".equals(v2.tipo)) {
                                    Valor v3 = new Valor(v2.valor, v2.tipo);
                                    valores.add(v3);
                                }
                            } else {
                                Valor v3 = new Valor("", "error");
                                return v3;
                            }
                        } else {
                            Valor v3 = new Valor("", "error");
                            return v3;
                        }
                    }
                    Valor v = (Valor) buscarMetodo(metodos, nombre, valores);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;
                        Valor v2 = (Valor) varsLocales.CrearVariablesMetodos(met.listaParametros, valores, nueva,
                                claseObjeto, nombre, SegundaPasada.contTemp);
                        // variables = nueva;
                        if (!"error".equals(v2.tipo)) {
                            Nodo cuerpometodo = met.cuerpo.hijos.get(0);
                            Valor retorno = (Valor) ej.Ejecucion(cuerpometodo, nueva, 0, claseObjeto, nombre, SegundaPasada.contTemp, nombre);
                            if (retorno != null) {
                                if (retorno.valor != null) {
                                    if (!retorno.tipo.equals("numero") && !retorno.tipo.equals("cadena") && !retorno.tipo.equals("caracter")
                                            && !retorno.tipo.equals("bool") && !retorno.tipo.equals("decimal")) {
                                        claseObjeto = (Als) retorno.valor;
                                        valorTemp = retorno.valor;
                                        tipoTemp = retorno.tipo;
                                    } else {
                                        bandera = true;
                                        valorTemp = retorno.valor;
                                        tipoTemp = retorno.tipo;
                                    }
                                    if (!SegundaPasada.pila.isEmpty()) {
                                        while (SegundaPasada.pila.peek().equals(nombre + SegundaPasada.contTemp)) {
                                            SegundaPasada.pila.pop();
                                            if (SegundaPasada.pila.isEmpty()) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Errores.ErrorSemantico("El metodo -" + nombre + "- no existe en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                }
            }
        }
        Valor vv = new Valor(valorTemp, tipoTemp);
        return vv;
    }

    public Object InstanciaReferencia(Nodo accesos, Als claseObjeto, Valor expresion, Als claseAnterior, ArrayList<ArrayList<Variable>> anteriores) {
        Object valorTemp = "";
        String tipoTemp = "";
        Variable varTemp = new Variable();
        Boolean bandera = false;

        for (Nodo nodo : accesos.hijos) {
            ArrayList metodos = (ArrayList) claseObjeto.Metodos;
            ArrayList<Variable> variables = (ArrayList) claseObjeto.VarsGlobales;
            if (nodo.hijos.size() == 1) {
                if (bandera) {
                    Errores.ErrorSemantico("No se puede acceder a un atributo "
                            + "-" + nodo.hijos.get(0).valor.toString() + "- ", 0, 0);
                    Valor v2 = new Valor("", "error");
                    return v2;
                } else {
                    bandera = true;
                    //cuando quiere acceder a un arreglo variable = a.arreglo[5]
                    if (nodo.hijos.get(0).valor.toString().equals("LlamaArreglo")) {
                        ArrayList nuevaVar = new ArrayList();
                        nuevaVar.add(variables);
                        Valor v = (Valor) exp.Expresion(nodo.hijos.get(0), claseObjeto, "", nuevaVar, false);
                        if (v != null) {
                            if (v.valor != "error") {
                                valorTemp = v.valor;
                                tipoTemp = v.tipo;
                            } else {
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        //cuando viene acceso a un atributo
                        String nombre = nodo.hijos.get(0).valor.toString();
                        Valor var = (Valor) buscarVariable(variables, nombre);

                        if (var.tipo.equals("true")) {
                            Variable acceso = (Variable) var.valor;
                            if (acceso.valor != null) {
                                if (acceso.instancia) {
                                    claseObjeto = (Als) acceso.valor;
                                    bandera = false;
                                }

                                valorTemp = acceso.valor;
                                tipoTemp = acceso.tipo;
                                varTemp = acceso;
                            } else {

                                valorTemp = acceso.valor;
                                tipoTemp = acceso.tipo;
                                varTemp = acceso;
                            }
                        } else {
                            Errores.ErrorSemantico("La variable -" + nombre + "- no esta declarada en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    }
                }
                //cuando viene una llamada a funcion trae dos hijos
            } else {
                bandera = false;

                ArrayList nueva = new ArrayList();
                nueva.add(variables);
                String nombre = nodo.hijos.get(0).valor.toString();

                ArrayList<Valor> valores = new ArrayList();
                if (nodo.hijos.get(1).hijos.isEmpty()) {
                    Valor v = (Valor) buscarMetodo(metodos, nombre, valores);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;
                        //no trae parametros
                        if (met.tipo.equals("vacio")) {
                            Valor v2 = new Valor("", "error");
                            return v2;
                        } else {
                            //es de tipo diferente a vacio y tiene que traer retorno

                            Nodo cuerpo = met.cuerpo.hijos.get(0);
                            Valor retorno = (Valor) ej.Ejecucion(cuerpo, nueva, 0,
                                    claseObjeto, nombre, SegundaPasada.contTemp, nombre);
                            if (retorno != null) {
                                if (retorno.valor != null) {
                                    if (!retorno.tipo.equals("numero") && !retorno.tipo.equals("cadena") && !retorno.tipo.equals("caracter")
                                            && !retorno.tipo.equals("bool") && !retorno.tipo.equals("decimal")) {
                                        claseObjeto = (Als) retorno.valor;
                                        valorTemp = retorno.valor;
                                        tipoTemp = retorno.tipo;
                                    } else {
                                        bandera = true;
                                        Variable acceso = (Variable) retorno.valor;
                                        valorTemp = retorno.valor;
                                        tipoTemp = retorno.tipo;
                                        varTemp = acceso;
                                    }
                                    if (!SegundaPasada.pila.isEmpty()) {
                                        while (SegundaPasada.pila.peek().equals(nombre + SegundaPasada.contTemp)) {
                                            SegundaPasada.pila.pop();
                                            if (SegundaPasada.pila.isEmpty()) {
                                                break;
                                            }
                                        }
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
                    } else {
                        Errores.ErrorSemantico("El metodo -" + nombre + "- no existe en el Als -" + claseObjeto.nombre + "- ", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                } else {
                    for (int i = 0; i < nodo.hijos.get(1).hijos.size(); i++) {
                        Valor v2 = (Valor) exp.Expresion(nodo.hijos.get(1).hijos.get(i), claseAnterior, nombre, anteriores, false);
                        if (v2 != null) {
                            if (v2.valor != null) {
                                if (!"error".equals(v2.tipo)) {
                                    Valor v3 = new Valor(v2.valor, v2.tipo);
                                    valores.add(v3);
                                }
                            } else {
                                Valor v3 = new Valor("", "error");
                                return v3;
                            }
                        } else {
                            Valor v3 = new Valor("", "error");
                            return v3;
                        }
                    }
                    Valor v = (Valor) buscarMetodo(metodos, nombre, valores);
                    if (v.tipo.equals("true")) {
                        MetodoGraphik met = (MetodoGraphik) v.valor;
                        if (met.tipo.equals("vacio")) {
                            Valor v4 = new Valor("", "error");
                            return v4;
                        } else {
                            Valor v2 = (Valor) varsLocales.CrearVariablesMetodos(met.listaParametros, valores, nueva,
                                    claseObjeto, nombre, SegundaPasada.contTemp);
                            if (!"error".equals(v2.tipo)) {
                                Nodo cuerpometodo = met.cuerpo.hijos.get(0);
                                Valor retorno = (Valor) ej.Ejecucion(cuerpometodo, nueva, 0, claseObjeto, nombre, SegundaPasada.contTemp, nombre);
                                if (retorno != null) {
                                    if (retorno.valor != null) {
                                        if (!retorno.tipo.equals("numero") && !retorno.tipo.equals("cadena") && !retorno.tipo.equals("caracter")
                                                && !retorno.tipo.equals("bool") && !retorno.tipo.equals("decimal")) {
                                            claseObjeto = (Als) retorno.valor;
                                            valorTemp = retorno.valor;
                                            tipoTemp = retorno.tipo;
                                        } else {
                                            try {
                                                bandera = true;
                                                Variable acceso = (Variable) retorno.valor;
                                                valorTemp = retorno.valor;
                                                tipoTemp = retorno.tipo;
                                                varTemp = acceso;
                                            } catch (Exception e) {
                                                Errores.ErrorSemantico("No se puede hacer referencia a un valor -" + retorno.valor + "-", 0, 0);
                                                Valor v3 = new Valor("", "error");
                                                return v3;
                                            }
                                        }
                                        if (!SegundaPasada.pila.isEmpty()) {
                                            while (SegundaPasada.pila.peek().equals(nombre + SegundaPasada.contTemp)) {
                                                SegundaPasada.pila.pop();
                                                if (SegundaPasada.pila.isEmpty()) {
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        Valor v3 = new Valor("", "error");
                                        return v3;
                                    }
                                } else {
                                    Valor v3 = new Valor("", "error");
                                    return v3;
                                }
                            }
                        }
                    }

                }
            }
        }

        if (tipoTemp.equals(expresion.tipo)) {
            varTemp.valor = expresion.valor;
        } else {
            Errores.ErrorSemantico("Error de casteo entre tipos (" + varTemp.tipo + "," + expresion.tipo + ")", 0, 0);
            Valor v2 = new Valor("", "error");
            return v2;
        }
        Valor vv = new Valor(varTemp, tipoTemp);
        return vv;
    }

    public Object buscarVariable(ArrayList<Variable> variables, String nombre) {

        for (int j = 0; j < variables.size(); j++) {
            if (variables.get(j).nombre.equals(nombre)) {
                Variable var = variables.get(j);
                Valor v = new Valor(var, "true");
                return v;
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
