/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.Graphik.ExpresionGraphik;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MishaPks
 */
public class Arreglo {

    ExpresionGraphik exp = new ExpresionGraphik();

    public Object ValidarArreglo(ArrayList dim, Nodo posiciones, String nombreArreglo, String tipo, String nombreFuncion,
            ArrayList<ArrayList<Variable>> variables) {

        Valor v = (Valor) ExtraerPosiciones(posiciones, dim, nombreFuncion, nombreArreglo, variables, tipo);
        if(!"error".equals(v.tipo)){
        ArrayList pos = (ArrayList) v.valor;
        if (pos.size() == Integer.parseInt(dim.get(0).toString())) {
            Boolean que = ContarPosiciones(dim, pos, nombreArreglo);
            if (que) {
                Valor v2 = new Valor(pos, "");
                return v2;
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
        }else{
            Errores.ErrorSemantico("Las posiciones del arreglo -" + nombreArreglo + "- no coinciden con sus dimensiones ", 0, 0);
            Valor v2 = new Valor("", "error");
            return v2;
        }

    }

    public Object ExtraerPosiciones(Nodo posiciones, ArrayList dim, String nombreFuncion, String nombreArreglo,
            ArrayList<ArrayList<Variable>> variables, String tipo) {
        ArrayList nuevo = new ArrayList();
        for (Nodo c : posiciones.hijos) {
            if (c.valor.toString().equals("Posiciones")) {
                Valor v = (Valor) ExtraerPosiciones(c, dim, nombreFuncion, nombreArreglo, variables, tipo);
                if (!"error".equals(v.tipo)) {
                    ArrayList a = (ArrayList) v.valor;
                    nuevo.add(a);
                } else {
                    Errores.ErrorSemantico("La asignacion no es del mismo tipo del arreglo", cont, cont);
                    Valor v2 = new Valor("", "error");
                    return v2;
                }
            } else {
                Valor v = (Valor) exp.Expresion(c, nombreFuncion, variables, false);
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
                    Valor v2 = new Valor(null,"");
                    nuevo.add(v2.valor);
                    System.out.println("paso por aqui");
                }
            }
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
                    vf=false;
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
}
