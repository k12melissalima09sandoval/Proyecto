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

    public Object ValidarArreglo(Nodo dimensiones, Nodo posiciones) {
        int cont = 0;
        List<String> dim = new ArrayList<String>();
        // for(Nodo c: dimensiones.hijos){
        //Valor v=(Valor)exp.RecorrerExpresion(c);
        //if(v.tipo!="numero"){
        //  Errores.ErrorSemantico("Las dimensiones del arreglo deben ser enteros", 0, 0);
        //}else{
        dim.add("2");//v.valor);
        dim.add("2");//v.valor);
        dim.add("1");//v.valor);
        //}
        // }

        Valor v = (Valor) ExtraerPosiciones(posiciones);
        ArrayList pos = (ArrayList) v.valor;
        Boolean que = (Boolean) ContarPosiciones(dim, pos);

        if (que) {

        }
        return null;
    }

    public Object ExtraerPosiciones(Nodo posiciones) {
        ArrayList nuevo = new ArrayList();
        int contador = 0;
        for (Nodo c : posiciones.hijos) {

            if (c.valor.toString().equals("Posiciones")) {
                Valor v = (Valor) ExtraerPosiciones(c);
                ArrayList a = (ArrayList) v.valor;
                nuevo.add(a);
            } else {
                //Valor v = (Valor)exp.RecorrerExpresion(c);
                contador++;
                nuevo.add(contador);//valor
            }
        }
        Valor v = new Valor(nuevo, "");
        return v;
    }

    public Boolean ContarPosiciones(List<String> dim, ArrayList pos) {

        for (int i = 0; i < dim.size(); i++) {

            int num = Integer.parseInt(dim.get(i));
            if (num == pos.size()) {
                for (int j = 0; j < 10; j++) {

                    try {
                        ArrayList a = (ArrayList) pos.get(i);
                        ContarPosiciones(dim, a);
                    } catch (Exception e) {
                        System.out.println("ya llego al numero");
                    }
                }
            } else {
                System.out.println("error en arreglo");
                return false;
            }
        }

        return true;
    }
}
