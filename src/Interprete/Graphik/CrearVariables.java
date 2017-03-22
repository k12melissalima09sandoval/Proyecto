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
import Simbolos.TablaSimbolosGraphik;

/**
 *
 * @author MishaPks
 */
public class CrearVariables {

    Errores error = new Errores();
    ExpresionGraphik exp = new ExpresionGraphik();

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
                            if (als.getKeyVarsGlobales(nombre)) {
                                Errores.ErrorSemantico("La variable <" + nombre + "> ya esta declarada", 0, 0);
                            } else {
                                String visible = a.hijos.get(1).valor.toString();
                                Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                als.addVarGlobal(nombre, var);
                            }
                        }
                        //DECLARACION DE UNA VARIABLE CON O SIN ASIGNACION
                    } else //SOLAMENTE DECLARACION
                     if (nodo.hijos.get(2).hijos.size() == 0) {

                            String tipo = nodo.hijos.get(0).valor.toString();

                            Nodo temp = nodo.hijos.get(1);
                            String nombre = temp.hijos.get(0).valor.toString();
                            if (als.getKeyVarsGlobales(nombre)) {
                                Errores.ErrorSemantico("La variable <" + nombre + "> ya esta declarada", 0, 0);
                            } else {
                                String visible = temp.hijos.get(1).valor.toString();
                                Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                als.addVarGlobal(nombre, var);
                            }

                        } else { //VIENE CON UNA ASIGNACION
                            String tipo = nodo.hijos.get(0).valor.toString();

                            Nodo temp = nodo.hijos.get(1);
                            String nombre = temp.hijos.get(0).valor.toString();
                            if (als.getKeyVarsGlobales(nombre)) {
                                Errores.ErrorSemantico("La variable <" + nombre + "> ya esta declarada", 0, 0);
                            } else {
                                Nodo expresion = nodo.hijos.get(2);
                                Valor v = (Valor) exp.RecorrerExpresion(expresion);
                                if (v != null) {
                                    if (v.valor != null) {
                                        String visible = temp.hijos.get(1).valor.toString();
//---------------------------CASTEO                                  
                                        Variable var = new Variable(tipo, nombre, visible, v.valor, false, false);
                                        als.addVarGlobal(nombre, var);

                                    } else {
                                        String visible = temp.hijos.get(1).valor.toString();

                                        Variable var = new Variable(tipo, nombre, visible, null, false, false);
                                        als.addVarGlobal(nombre, var);

                                        Errores.ErrorSemantico("El valor en la asignacion <" + nombre + "> "
                                                + "devolvio un nulo", 0, 0);
                                    }
                                } else {
                                    String visible = temp.hijos.get(1).valor.toString();

                                    Variable var = new Variable(tipo, nombre, visible, null,false,false);
                                    als.addVarGlobal(nombre, var);

                                    Errores.ErrorSemantico("El valor en la asignacion <" + nombre + "> "
                                            + "devolvio un nulo", 0, 0);
                                }
                            }
                        }

                    
                    
                case "DeclaraGlobalArreglo":

                    String tipo=nodo.hijos.get(0).valor.toString();
                    
                    String nombre = nodo.hijos.get(1).hijos.get(0).valor.toString();
                    String visibilidad = nodo.hijos.get(1).hijos.get(1).valor.toString();
                    
                    int dimensiones = nodo.hijos.get(2).hijos.size();
                    
                    
                    
                    
                case "InstanciaGlobal":

            }
        }

        return null;
    }
}
