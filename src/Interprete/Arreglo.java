/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Analizadores.Errores;
import Dibujar.Nodo;
import Interprete.Graphik.Als;
import Interprete.Graphik.ExpresionGraphik;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MishaPks
 */
public class Arreglo {

    ExpresionGraphik exp = new ExpresionGraphik();

    public Object ValidarArreglo(ArrayList dim, Als als, Nodo posiciones, String nombreArreglo, String tipo, String nombreFuncion,
            ArrayList<ArrayList<Variable>> variables) {

        Valor v = (Valor) ExtraerPosiciones(posiciones, als,dim, nombreFuncion, nombreArreglo, variables, tipo);
        if (!"error".equals(v.tipo)) {
            ArrayList pos = (ArrayList) v.valor;
            if (pos.size() == Integer.parseInt(dim.get(0).toString())) {
                if (dim.size() == 1) {
                    Valor v2 = new Valor(pos, "");
                    return v2;
                } else {
                    Boolean que = ContarPosiciones(dim, pos, nombreArreglo);
                    if (que) {
                        Valor vv = (Valor) Posiciones(posiciones, als,dim, nombreFuncion, nombreArreglo, variables, tipo);
                        Valor v2 = new Valor(vv.valor, "");
                        return v2;
                    } else {
                        Errores.ErrorSemantico("Las posiciones del arreglo -" + nombreArreglo + "- no coinciden con sus dimensiones ", 0, 0);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                }
            } else {
                Errores.ErrorSemantico("Las posiciones del arreglo -" + nombreArreglo + "- no coinciden con sus dimensiones ", 0, 0);
                Valor v2 = new Valor("", "error");
                return v2;
            }
        } else {
            Errores.ErrorSemantico("Las posiciones del arreglo -" + nombreArreglo + "- no coinciden con sus dimensiones ", 0, 0);
            Valor v2 = new Valor("", "error");
            return v2;
        }

    }

    public Object ExtraerPosiciones(Nodo posiciones, Als als, ArrayList dim, String nombreFuncion, String nombreArreglo,
            ArrayList<ArrayList<Variable>> variables, String tipo) {
        ArrayList nuevo = new ArrayList();
        for (Nodo c : posiciones.hijos) {
            if (c.valor.toString().equals("Posiciones")) {
                Valor v = (Valor) ExtraerPosiciones(c, als,dim, nombreFuncion, nombreArreglo, variables, tipo);
                if (!"error".equals(v.tipo)) {
                    ArrayList a = (ArrayList) v.valor;
                    nuevo.add(a);
                } else {
                    Errores.ErrorSemantico("La asignacion no es del mismo tipo del arreglo", cont, cont);
                    Valor v2 = new Valor("", "error");
                    return v2;
                }
            } else {
                Valor v = (Valor) exp.Expresion(c, als, nombreFuncion, variables, false);
                if (v != null) {
                    if (v.valor != null) {
                        if (!"error".equals(v.tipo)) {
                            if (v.tipo.equals(tipo)) {
                                nuevo.add(v.valor);
                            } else {
                                Errores.ErrorSemantico("La asignacion no es del mismo tipo del arreglo", cont, cont);
                                Valor v2 = new Valor("", "error");
                                return v2;
                            }
                        } else {
                            Errores.ErrorSemantico("Errores en las posiciones del arreglo -" + nombreArreglo + "-", cont, cont);
                            Valor v2 = new Valor("", "error");
                            return v2;
                        }
                    } else {
                        Errores.ErrorSemantico("Errores en las posiciones del arreglo -" + nombreArreglo + "-", cont, cont);
                        Valor v2 = new Valor("", "error");
                        return v2;
                    }
                } else {
                    Errores.ErrorSemantico("Errores en las posiciones del arreglo -" + nombreArreglo + "-", cont, cont);
                    Valor v2 = new Valor("", "error");
                    return v2;
                }
            }
        }
        Valor v = new Valor(nuevo, "");
        return v;
    }
    ArrayList nuevoP = new ArrayList();

    public Object Posiciones(Nodo posiciones,Als als, ArrayList dim, String nombreFuncion, String nombreArreglo,
            ArrayList<ArrayList<Variable>> variables, String tipo) {

        for (Nodo c : posiciones.hijos) {
            if (c.valor.toString().equals("Posiciones")) {
                Posiciones(c,als, dim, nombreFuncion, nombreArreglo, variables, tipo);

            } else {
                Valor v = (Valor) exp.Expresion(c,als, nombreFuncion, variables, false);
                if (v != null) {
                    if (v.valor != null) {
                        if (!"error".equals(v.tipo)) {
                            if (v.tipo.equals(tipo)) {
                                nuevoP.add(v.valor);
                            }
                        }
                    }
                }
            }
        }
        Valor v = new Valor(nuevoP, "");
        return v;
    }

    public Object InicializarArreglo(ArrayList dim, int ii) {
        int mult = 1;
        for (int i = 0; i < dim.size(); i++) {
            mult = mult * Integer.parseInt(dim.get(i).toString());
        }
        ArrayList nuevo = new ArrayList();
        for (int i = 0; i < mult; i++) {
            nuevo.add("nulo");
        }
        Valor v = new Valor(nuevo, "");
        return v;
    }

    int cont = 1;
    Boolean vf = false;

    public Boolean ContarPosiciones(ArrayList dim, ArrayList pos, String nombre) {
        if (cont == dim.size()) {
            return null;
        }
        for (Object pp : pos) {
            try {
                ArrayList p = (ArrayList) pp;
                if (p.size() == Integer.parseInt(dim.get(cont).toString())) {
                    vf = true;
                    for (int i = 0; i < p.size(); i++) {
                        cont++;
                        try {
                            ArrayList a = (ArrayList) p.get(i);
                            ContarPosiciones(dim, a, nombre);
                            cont--;
                        } catch (Exception e) {
                            if (Integer.parseInt(dim.get(cont - 1).toString()) == p.size()) {
                                vf = true;
                            } else {
                                vf = false;
                            }
                        }
                    }
                } else {
                    vf = false;
                    break;
                }
            } catch (Exception e) {
                if (Integer.parseInt(dim.get(cont).toString()) == pos.size()) {
                    vf = true;
                } else {
                    vf = false;
                }

            }
        }

        return vf;
    }

    //devolvemos un valor en la posicion
    public Object BuscarPosicionYdevolverValor(ArrayList dim, ArrayList pos, ArrayList<ArrayList<Variable>> variables, String nombreA) {

        Valor v2 = (Valor) ValidarAcceso(dim, pos, nombreA);
        if (!"error".equals(v2.tipo)) {
            Valor existe = (Valor) buscarVariable(variables, nombreA);
            Variable var = (Variable) existe.valor;
            if (dim.size() == 1) {
                if (pos.size() == 1) {
                    ArrayList a = (ArrayList) var.valor;
                    int num = Integer.parseInt(a.get(Integer.parseInt(pos.get(0).toString())).toString());
                    Valor v = new Valor(num, var.tipo);
                    return v;
                } else {
                    Errores.ErrorSemantico("El acceso al arreglo -" + nombreA + "- no es valido", cont, cont);
                    Valor v = new Valor("", "error");
                    return v;
                }
            } else if (dim.size() == pos.size()) {
                int in1 = Integer.parseInt(pos.get(0).toString());
                for (int i = 0; i < dim.size() - 1; i++) {
                    int mult = in1 * Integer.parseInt(dim.get(i + 1).toString());
                    int in2 = Integer.parseInt(pos.get(i + 1).toString());
                    int suma = mult + in2;
                    in1 = suma;
                }
                ArrayList bb = (ArrayList) var.valor;
                Object ob = bb.get(in1);
                if (ob.toString().equals("nulo")) {
                    Errores.ErrorSemantico("El valor del arreglo-" + nombreA + "- en la posicion -"+in1+"- es nulo", cont, cont);
                    Valor v = new Valor("", "error");
                    return v;
                } else {
                    Valor v = new Valor(ob, "");
                    return v;
                }
            } else {
                Errores.ErrorSemantico("El acceso al arreglo -" + nombreA + "- no es valido", cont, cont);
                Valor v = new Valor("", "error");
                return v;
            }
        } else {
            Valor v = new Valor("", "error");
            return v;
        }
    }

    public Object ValidarAcceso(ArrayList dim, ArrayList pos, String nombre) {
        for (int i = 0; i < dim.size(); i++) {
            if (Integer.parseInt(pos.get(i).toString()) < Integer.parseInt(dim.get(i).toString())
                    && Integer.parseInt(pos.get(i).toString()) >= 0) {

            } else {
                Errores.ErrorSemantico("El acceso al arreglo -" + nombre + "- esta fuera de rango", i, cont);
                Valor v = new Valor("", "error");
                return v;
            }
        }
        Valor v = new Valor("", "");
        return v;
    }

    public Object BuscarPosicion(ArrayList dim, ArrayList pos, ArrayList<ArrayList<Variable>> variables, String nombreA) {

        Valor v2 = (Valor) ValidarAcceso(dim, pos, nombreA);
        if (!"error".equals(v2.tipo)) {

            Valor existe = (Valor) buscarVariable(variables, nombreA);
            Variable var = (Variable) existe.valor;
            if (dim.size() == 1) {
                if (pos.size() == 1) {
                    ArrayList a = (ArrayList) var.valor;
                    a.get(Integer.parseInt(pos.get(0).toString()));
                    Valor v = new Valor(pos.get(0), var.tipo);
                    return v;
                } else {
                    Errores.ErrorSemantico("El acceso al arreglo -" + nombreA + "- no es valido", cont, cont);
                    Valor v = new Valor("", "error");
                    return v;
                }
            } else if (dim.size() == pos.size()) {
                int in1 = Integer.parseInt(pos.get(0).toString());
                for (int i = 0; i < dim.size() - 1; i++) {
                    int mult = in1 * Integer.parseInt(dim.get(i + 1).toString());
                    int in2 = Integer.parseInt(pos.get(i + 1).toString());
                    int suma = mult + in2;
                    in1 = suma;
                }
                Valor v = new Valor(in1, "");
                return v;
            } else {
                Errores.ErrorSemantico("El acceso al arreglo -" + nombreA + "- no es valido", cont, cont);
                Valor v = new Valor("", "error");
                return v;
            }
        } else {
            Valor v = new Valor("", "error");
            return v;
        }
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
