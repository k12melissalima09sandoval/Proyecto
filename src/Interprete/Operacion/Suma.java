/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Operacion;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */

public class Suma {

    public Object Suma(Valor num1, Valor num2) {

        switch (num1.tipo) {
            case "numero": {
                int n1 = Integer.parseInt(num1.valor.toString());
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        int resultado = n1 + n2;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = n1 + n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().replace("'", "").codePointAt(0);
                        int resultado = n1 + n2;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "cadena": {
                        String n2 = num2.valor.toString().replace("\"", "");
                        String resultado = "\"" + n1 + n2 + "\"";
                        Valor v = new Valor(resultado, "cadena");
                        return v;

                    }
                    case "verdadero": {
                        int resultado = n1 + 1;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "falso": {
                        Valor v = new Valor(n1, "numero");
                        return v;
                    }
                }
            }
            case "decimal": {
                Double n1 = Double.parseDouble(num1.valor.toString());
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        Double resultado = n1 + n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = n1 + n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().replace("'", "").codePointAt(0);
                        Double resultado = n1 + n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "cadena": {
                        String n2 = num2.valor.toString().replace("\"", "");
                        String resultado = "\"" + n1 + n2 + "\"";
                        Valor v = new Valor(resultado, "cadena");
                        return v;
                    }
                    case "verdadero": {
                        Double resultado = n1 + 1;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }

                    case "falso": {
                        Valor v = new Valor(n1, "decimal");
                        return v;
                    }
                }
            }
            case "caracter": {
                String n1 = num1.valor.toString().replace("'", "");
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        int resultado = n1.codePointAt(0) + n2;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = n1.codePointAt(0) + n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        Valor v = new Valor("", "error");
                        return v;

                    }
                    case "cadena": {
                        Valor v = new Valor("", "cadena");
                        return v;
                    }
                    case "verdadero": {

                    }

                    case "falso": {

                        Valor v = new Valor("", "numero");
                        return v;
                    }

                }
            }
            case "cadena": {
                switch (num2.tipo) {
                    case "numero": {
                        Valor v = new Valor("", "cadena");
                        return v;
                    }
                    case "decimal": {
                        Valor v = new Valor("", "cadena");
                        return v;
                    }
                    case "caracter": {
                        Valor v = new Valor("", "cadena");
                        return v;
                    }
                    case "cadena": {
                        Valor v = new Valor("", "cadena");
                        return v;
                    }
                    case "verdadero":
                        break;
                    case "falso": {

                        Valor v = new Valor("", "numero");
                        return v;
                    }
                    default:
                        break;
                }
            }
            case "verdadero": {
                switch (num2.tipo) {
                    case "numero": {
                        Valor v = new Valor("", "numero");
                        return v;
                    }
                    case "decimal": {
                        Valor v = new Valor("", "decimal");
                        return v;
                    }
                    case "caracter": {
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "cadena": {
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "verdadero":
                        break;
                    case "falso": {

                        Valor v = new Valor("", "numero");
                        return v;
                    }
                    default:
                        break;
                }
            }
            case "falso": {
                switch (num2.tipo) {
                    case "numero": {
                        Valor v = new Valor("", "numero");
                        return v;
                    }
                    case "decimal": {
                        Valor v = new Valor("", "decimal");
                        return v;
                    }
                    case "caracter": {
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "cadena": {
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "verdadero":
                        break;
                    case "falso": {

                        Valor v = new Valor("", "numero");
                        return v;
                    }
                    default:
                        break;
                }
            }
        }

        return null;
    }

}
