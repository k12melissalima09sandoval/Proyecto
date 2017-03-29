package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.AsignacionCasteo;
import Interprete.Valor;
import Interprete.Variable;
import Simbolos.TablaSimbolosGraphik;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class SegundaPasada {

    ExpresionGraphik exp = new ExpresionGraphik();
    CrearVariables varsLocales = new CrearVariables();
    AsignacionCasteo asigna = new AsignacionCasteo();

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
                Ejecucion(cuerpo, nueva, nivel, als.get(i), "Inicio");

            } else {
                Errores.ErrorSemantico("No existe el metodo Inicio", 0, 0);
            }
        }

        return null;
    }

    public Object Ejecucion(Nodo cuerpoAls, ArrayList<ArrayList<Variable>> variables, int nivel, Als als, String nombreFun) {
        int cont = 0;
        for (Nodo raiz : cuerpoAls.hijos) {
            if (raiz.valor.toString().equals("DeclaraLocalVariable")
                    || raiz.valor.toString().equals("DeclaraLocalArreglo")
                    || raiz.valor.toString().equals("IntanciaLocal")) {
                cont++;
            }
        }
        if (cont > 0) {
            nivel = varsLocales.CrearVariablesLocales(cuerpoAls, variables, nivel, als);
        }

        for (Nodo raiz : cuerpoAls.hijos) {
            switch (raiz.valor.toString()) {

                case "Asignacion": {
                    String nombre = raiz.hijos.get(0).valor.toString();
                    Valor v = (Valor) buscarVariable(variables, nombre);
                    
                    if (v.tipo.equals("true")) {
                        Variable var = (Variable) v.valor;
                        //cuando viene un acceso desde la asignacion a.atributo = ----?
                        if (!raiz.hijos.get(1).hijos.isEmpty()) {
                            if (var.instancia) {
                                ///////////////////////////////////////////////////////////////////////////////////////
                            } else {
                                Errores.ErrorSemantico("La variable -" + nombre + "- no es de tipo objeto", 0, 0);
                            }
                        }
                        if (!raiz.hijos.get(2).valor.toString().equals("InstanciaLocal")) {
                            Valor ex = (Valor) exp.Expresion(raiz.hijos.get(2), nombreFun,variables);
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
                                            var.valor = null;
                                            Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                                    + "se le asigno null", 0, 0);
                                        }
                                    }
                                } else {
                                    var.valor = null;
                                    Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                            + "se le asigno null", 0, 0);
                                }
                            } else {
                                var.valor = null;
                                Errores.ErrorSemantico("A la variable -" + nombre + "- "
                                        + "se le asigno null", 0, 0);
                            }
                        } else { //viene una instancia en una asignacion;
                            String objeto = raiz.hijos.get(2).hijos.get(0).valor.toString();
                            Boolean ban = false;
                            if (var.instancia) {
                                if (var.tipo.equals(objeto)) {
                                    ArrayList<Als> im = als.importa;
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

                case "":

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

}
