/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.Arreglo;
import Interprete.AsignacionCasteo;
import Interprete.Valor;
import Interprete.Variable;

/**
 *
 * @author MishaPks
 */
public class CrearVariables {

    Errores error = new Errores();
    ExpresionGraphik exp = new ExpresionGraphik();
    AsignacionCasteo asigna = new AsignacionCasteo();

    public Object CrearVariablesGlobales(Nodo raiz, Als als) {

        for (Nodo nodo : raiz.hijos) {
            String valor = nodo.valor.toString();
            switch (valor) {

                case "DeclaraGlobalVariable":

                    //DECLARACION LISTA DE VARIABLES
                    if (nodo.hijos.size() == 2) {
                        String tipo = nodo.hijos.get(0).valor.toString();
                        for (Nodo a : nodo.hijos.get(1).hijos) {
                            String nombre = a.hijos.get(0).valor.toString();
                            if (als.existeVariableG(nombre)) {
                                Errores.ErrorSemantico("La variable -" + nombre + "- ya esta declarada", 0, 0);
                            } else {
                                String visible = a.hijos.get(1).valor.toString();
                                Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                als.addVarGlobal(var);
                            }
                        }
                        //DECLARACION DE UNA VARIABLE CON O SIN ASIGNACION
                    } else //SOLAMENTE DECLARACION
                     if (nodo.hijos.get(2).hijos.isEmpty()) {

                            String tipo = nodo.hijos.get(0).valor.toString();

                            Nodo temp = nodo.hijos.get(1).hijos.get(0);
                            String nombre = temp.hijos.get(0).valor.toString();
                            if (als.existeVariableG(nombre)) {
                                Errores.ErrorSemantico("La variable -" + nombre + "- ya esta declarada", 0, 0);
                            } else {
                                String visible = temp.hijos.get(1).valor.toString();
                                Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                als.addVarGlobal(var);
                            }

                        } else { //VIENE CON UNA ASIGNACION
                            String tipo = nodo.hijos.get(0).valor.toString();

                            Nodo temp = nodo.hijos.get(1);
                            String nombre = temp.hijos.get(0).hijos.get(0).valor.toString();
                            if (als.existeVariableG(nombre)) {
                                Errores.ErrorSemantico("La variable -" + nombre + "- ya esta declarada", 0, 0);
                            } else {
                                Nodo expresion = nodo.hijos.get(2);
                                Valor v = (Valor) exp.Expresion(expresion.hijos.get(0), nombre);
                                if (v != null) {
                                    if (v.valor != null) {
                                        if (v.tipo.equals("error")) {
                                            String visible = temp.hijos.get(0).hijos.get(1).valor.toString();

                                            Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                            als.addVarGlobal(var);

                                            Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                                    + "se le asigno null", 0, 0);
                                        } else {
                                            String visible = temp.hijos.get(0).hijos.get(1).valor.toString();
                                            Valor val = (Valor) asigna.Casteo(tipo, v.valor, v.tipo);                 
                                            if (!val.tipo.equals("error")) {
                                                Variable var = new Variable(tipo, nombre, visible, val.valor, false, false);
                                                als.addVarGlobal(var);
                                            } else {
                                                Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                                als.addVarGlobal(var);
                                                Errores.ErrorSemantico("Error de casteo en asignacion de la "
                                                        + "variable -" + nombre + "- ",0, 0);
                                            }
                                        }
                                    } else {
                                        String visible = temp.hijos.get(0).hijos.get(1).valor.toString();

                                        Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                        als.addVarGlobal(var);

                                        Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                                + "se le asigno null", 0, 0);
                                    }
                                } else {
                                    String visible = temp.hijos.get(1).valor.toString();

                                    Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                    als.addVarGlobal(var);

                                    Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                            + "se le asigno null", 0, 0);
                                }
                            }
                        }
                    break;
                case "DeclaraGlobalArreglo":
//---------------------------------------------------------------------SIN TERMINAR
                    Arreglo arr = new Arreglo();
                    String tipo = nodo.hijos.get(0).valor.toString();

                    String nombre = nodo.hijos.get(1).hijos.get(0).valor.toString();
                    String visibilidad = nodo.hijos.get(1).hijos.get(1).valor.toString();
                    Nodo dimensiones = nodo.hijos.get(2);
                    Nodo posiciones = nodo.hijos.get(3);

                    Valor v = (Valor) arr.ValidarArreglo(dimensiones, posiciones);

                    Variable var = new Variable(tipo, nombre, visibilidad, null, true, false);
                    als.addVarGlobal(var);

                    break;
//----------------------------------------------------------------------
                case "InstanciaGlobal":

                    String tipoObjeto = nodo.hijos.get(0).hijos.get(0).valor.toString();
                    String nombreN = nodo.hijos.get(1).hijos.get(0).valor.toString();
                    String visible = nodo.hijos.get(1).hijos.get(1).valor.toString();

                    if (nodo.hijos.get(2).hijos.isEmpty()) {
                        Variable v2 = new Variable(tipoObjeto, nombreN, visible, null, false, true);
                        als.addVarGlobal(v2);
                    } else {
                        String NameObject = nodo.hijos.get(2).hijos.get(0).valor.toString();
                        if (nodo.hijos.get(2).valor.equals("Objeto")) {
                            if (tipoObjeto.equals(NameObject)) {
                                Variable v3 = new Variable(tipoObjeto, nombreN, visible, NameObject, false, true);
                                als.addVarGlobal(v3);
                            } else {
                                Errores.ErrorSemantico("La instancia de -" + nombreN + "- es de "
                                        + "tipos diferentes (" + tipoObjeto + "," + NameObject + ")", 0, 0);

                            }
                        } else {
                            Errores.ErrorSemantico("Asignacion erronea en -" + nombreN + "- ", 0, 0);
                        }
                    }

                    break;

            }
        }

        return null;
    }

    public Object CrearVariablesLocales(Nodo raiz) {

        return null;
    }
}
