/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.Parametros;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class CrearMetodos {

    Errores error = new Errores();
    ExpresionGraphik exp = new ExpresionGraphik();

    public Object CrearMetodos(Nodo raiz, Als als) {

        for (Nodo nodo : raiz.hijos) {
            String valor = nodo.valor.toString();
            switch (valor) {

                case "Metodo":
                    String tipo="";
                    if (nodo.hijos.get(0).valor.toString().equals("Als")) {
                        tipo = nodo.hijos.get(0).hijos.get(0).valor.toString();
                    } else {
                        tipo = nodo.hijos.get(0).valor.toString();
                    }
                    String nombre = nodo.hijos.get(1).valor.toString();
                    String visible = nodo.hijos.get(3).valor.toString();
                    Boolean existe = als.existeMetodo(nombre);
                    Nodo cuerpo = nodo.hijos.get(4);
                    if (!existe) {
                        if (nodo.hijos.get(2).hijos.isEmpty()) {
                            MetodoGraphik met = new MetodoGraphik(tipo, nombre, visible, cuerpo);
                            als.addMetodo(met);

                        } else {
                            MetodoGraphik met = new MetodoGraphik(tipo, nombre, visible, cuerpo);
                            for (Nodo p : nodo.hijos.get(2).hijos) {
                                String tipoP = p.hijos.get(0).valor.toString();
                                String nombreP = p.hijos.get(1).valor.toString();

                                Parametros param = new Parametros(tipoP, nombreP);
                                met.setParametros(param);
                            }
                            als.addMetodo(met);
                        }
                    } else {
                        MetodoGraphik encontrado = als.obtenerMetodo(nombre);
                        if (encontrado != null) {
                            ArrayList<Parametros> pam = encontrado.getParametros();
                            ArrayList<Parametros> temp = new ArrayList();
                            for (Nodo p : nodo.hijos.get(2).hijos) {
                                String tipoP = p.hijos.get(0).valor.toString();
                                String nombreP = p.hijos.get(1).valor.toString();

                                Parametros param = new Parametros(tipoP, nombreP);
                                temp.add(param);
                            }

                            if (temp.size() == pam.size()) {
                                Boolean bandera = false;
                                for (int i = 0; i < pam.size(); i++) {
                                    Parametros p = (Parametros) temp.get(i);
                                    if (temp.get(i).tipo.equals(pam.get(i).tipo)) {
                                        bandera = true;
                                    } else {
                                        bandera = false;
                                    }
                                }
                                if (bandera) {
                                    Errores.ErrorSemantico("El metodo -" + nombre + "- ya esta definido "
                                            + "con los mismos parametros", 0, 0);
                                }

                            } else {
                                MetodoGraphik met = new MetodoGraphik(tipo, nombre, visible, cuerpo);
                                for (int i = 0; i < temp.size(); i++) {
                                    Parametros p = (Parametros) temp.get(i);
                                    met.setParametros(p);
                                }
                                als.addMetodo(met);
                            }
                        }
                    }

                    break;

                case "MetodoDatos":
                    String tipoD = nodo.hijos.get(0).valor.toString();
                    Nodo cuerpoD = nodo.hijos.get(1);
                    Boolean d = als.existeMetodo("Datos");
                    if (!d) {
                        MetodoGraphik met = new MetodoGraphik(tipoD, "Datos", "Privado", cuerpoD);
                        als.addMetodo(met);
                    } else {
                        Errores.ErrorSemantico("El metodo Datos ya esta definido", 0, 0);

                    }
                    break;

                case "MetodoInicio":
                    String tipoI = nodo.hijos.get(0).valor.toString();
                    Nodo cuerpoI = nodo.hijos.get(1);
                    Boolean e = als.existeMetodo("Inicio");
                    if (!e) {

                        MetodoGraphik met = new MetodoGraphik(tipoI, "Inicio", "Publico", cuerpoI);
                        als.addMetodo(met);

                    } else {
                        Errores.ErrorSemantico("El metodo Inicio ya esta definido", 0, 0);
                    }

                    break;
            }
        }

        return null;
    }
}
