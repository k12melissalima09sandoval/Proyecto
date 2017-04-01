/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.Valor;
import Interprete.Variable;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class Instancia {

    ExpresionGraphik exp = new ExpresionGraphik();

    public Object InstanciaAcceso(Nodo accesos, Als claseObjeto) {
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
                        Valor v = (Valor) exp.Expresion(nodo.hijos.get(0),claseObjeto, "", nuevaVar, false);
                        if (v != null) {
                            if (v.valor != "error") {
                                valorTemp=v.valor;
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
                

            }

        }

        Valor vv = new Valor(valorTemp, tipoTemp);
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

}
