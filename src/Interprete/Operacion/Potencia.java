/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Operacion;

import Analizadores.Errores;
import Interprete.Valor;

/**
 *
 * @author MishaPks
 */
public class Potencia {

    public Object Potencia(Valor num1, Valor num2) {
        if (num1.tipo.equals("error") || num2.tipo.equals("error")) {
            Valor v = new Valor("", "error");
            return v;
        }
        switch (num1.tipo) {
            case "numero": {
                Double n1 = Double.parseDouble(num1.valor.toString());
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        Double n = Math.pow(n1, n2);
                        int resultado = (int) Math.floor(Double.parseDouble(n.toString()));
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double n = Math.pow(n1, n2);
                        int resultado = (int) Math.floor(Double.parseDouble(n.toString()));
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        Double n = Math.pow(n1, n2);
                        int resultado = (int) Math.floor(Double.parseDouble(n.toString()));
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en potencia de entero y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;

                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            Double n = Math.pow(n1, 1);
                            int resultado = (int) Math.floor(Double.parseDouble(n.toString()));
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        } else {
                            Double n = Math.pow(n1, 0);
                            int resultado = (int) Math.floor(Double.parseDouble(n.toString()));
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        }
                    }
                }
            }
            case "decimal": {
                Double n1 = Double.parseDouble(num1.valor.toString());
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        Double resultado = Math.pow(n1, n2);
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = Math.pow(n1, n2);
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        Double resultado = Math.pow(n1, n2);
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en potencia de decimal y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            Double resultado = Math.pow(n1, 1);
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        } else {
                            Double resultado = Math.pow(n1, 0);
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        }
                    }
                }
            }

            case "caracter": {
                int n1 = num1.valor.toString().codePointAt(0);
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        Double n = Math.pow(n1, n2);
                        int resultado = (int) Math.floor(Double.parseDouble(n.toString()));
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = Math.pow(n1, n2);
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        Errores.ErrorSemantico("Error en potencia de caracter y caracter", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;

                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en potencia de cadena y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        Errores.ErrorSemantico("Error en potencia de caracter y booleano", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                }
            }
            case "cadena": {
                String n1 = num1.valor.toString();
                switch (num2.tipo) {
                    case "numero": {
                        Errores.ErrorSemantico("Error en potencia de cadena y entero", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "decimal": {
                        Errores.ErrorSemantico("Error en potencia de cadena y decimal", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "caracter": {
                        Errores.ErrorSemantico("Error en potencia de cadena y caracter", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en potencia de cadena y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        Errores.ErrorSemantico("Error en potencia de cadena y booleano", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                }
            }
            case "bool": {
                if (num1.valor.equals("verdadero")) {
                    switch (num2.tipo) {
                        case "numero": {
                            int n2 = Integer.parseInt(num2.valor.toString());
                            Double resultado = Math.pow(1, n2);
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        }
                        case "decimal": {
                            Double n2 = Double.parseDouble(num2.valor.toString());
                            Double resultado = Math.pow(1, n2);
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        }
                        case "caracter": {
                            Errores.ErrorSemantico("Error en potencia de booleano y caracter", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "cadena": {
                            Errores.ErrorSemantico("Error en potencia de booleano y cadena", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "bool": {
                            Errores.ErrorSemantico("Error en potencia de booleano y booleano", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                    }
                } else {
                    switch (num2.tipo) {
                        case "numero": {
                            int n2 = Integer.parseInt(num2.valor.toString());
                            Double resultado = Math.pow(0, n2);
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        }
                        case "decimal": {
                            Double n2 = Double.parseDouble(num2.valor.toString());
                           Double resultado = Math.pow(0, n2);
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        }
                        case "caracter": {
                            Errores.ErrorSemantico("Error en potencia de booleano y caracter", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "cadena": {
                            Errores.ErrorSemantico("Error en potencia de booleano y cadena", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "bool": {
                           Errores.ErrorSemantico("Error en potencia de booleano y booleano", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                    }
                }
            }
        }

        return null;
    }
}
