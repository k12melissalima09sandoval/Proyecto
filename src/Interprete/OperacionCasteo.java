/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

/**
 *
 * @author MishaPks
 */
public class OperacionCasteo {

    public Object OpCasteo(String tipo1, String tipo2) {

        if (tipo1.equals("numero")) {
            if (tipo2.equals("numero")) {

                Valor v = new Valor("", "numero");
                return v;

            } else if (tipo2.equals("decimal")) {

                Valor v = new Valor("", "decimal");
                return v;

            } else if (tipo2.equals("caracter")) {

                Valor v = new Valor("", "numero");
                return v;

            } else if (tipo2.equals("cadena")) {

                Valor v = new Valor("", "cadena");
                return v;

            } else if (tipo2.equals("verdadero") || tipo2.equals("falso")) {

                Valor v = new Valor("", "numero");
                return v;
            }
        } else if (tipo1.equals("decimal")) {
            if (tipo2.equals("numero")) {
                Valor v = new Valor("", "decimal");
                return v;
            } else if (tipo2.equals("decimal")) {
                Valor v = new Valor("", "decimal");
                return v;
            } else if (tipo2.equals("caracter")) {
                Valor v = new Valor("", "decimal");
                return v;
            } else if (tipo2.equals("cadena")) {
                Valor v = new Valor("", "cadena");
                return v;
            } else if (tipo2.equals("verdadero") || tipo2.equals("falso")) {
                Valor v = new Valor("", "decimal");
                return v;
            }

        } else if (tipo1.equals("caracter")) {
            if (tipo2.equals("numero")) {
                Valor v = new Valor("", "numero");
                return v;
            } else if (tipo2.equals("decimal")) {

                Valor v = new Valor("", "decimal");
                return v;
            } else if (tipo2.equals("caracter")) {
                Valor v = new Valor("", "error");
                return v;

            } else if (tipo2.equals("cadena")) {
                Valor v = new Valor("", "cadena");
                return v;
            } else if (tipo2.equals("verdadero") || tipo2.equals("falso")) {
                Valor v = new Valor("", "error");
                return v;
            }

        } else if (tipo1.equals("cadena")) {
            if (tipo2.equals("numero")) {
                Valor v = new Valor("", "cadena");
                return v;
            } else if (tipo2.equals("decimal")) {
                Valor v = new Valor("", "cadena");
                return v;
            } else if (tipo2.equals("caracter")) {
                Valor v = new Valor("", "cadena");
                return v;
            } else if (tipo2.equals("cadena")) {
                Valor v = new Valor("", "cadena");
                return v;
            } else if (tipo2.equals("verdadero") || tipo2.equals("falso")) {
                Valor v = new Valor("", "error");
                return v;
            }

        } else if (tipo1.equals("verdadero") || tipo1.equals("falso")) {
            if (tipo2.equals("numero")) {
                Valor v = new Valor("", "numero");
                return v;
            } else if (tipo2.equals("decimal")) {
                Valor v = new Valor("", "decimal");
                return v;
            } else if (tipo2.equals("caracter")) {
                Valor v = new Valor("", "error");
                return v;
            } else if (tipo2.equals("cadena")) {
                Valor v = new Valor("", "error");
                return v;
            } else if (tipo2.equals("verdadero") || tipo2.equals("falso")) {
                Valor v = new Valor("", "verdadero");
                return v;
            }

        }
        return null;
    }

}
