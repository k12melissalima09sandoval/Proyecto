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
public class Multiplicacion {
    
    public Object Multiplicacion(Valor num1, Valor num2) {
        if (num1.tipo.equals("error") || num2.tipo.equals("error")) {
            Valor v = new Valor("", "error");
            return v;
        }
        switch (num1.tipo) {
            case "numero": {
                int n1 = Integer.parseInt(num1.valor.toString());
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        int resultado = n1 * n2;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = n1 * n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        int resultado = n1 * n2;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en multiplicacion de entero y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;

                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            int resultado = n1 * 1;
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        } else {
                            Valor v = new Valor(0, "numero");
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
                        Double resultado = n1 * n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = n1 * n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        Double resultado = n1 * n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en multiplicacion de decimal y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            Double resultado = n1 * 1;
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        } else {
                            Valor v = new Valor(0.0, "decimal");
                            return v;
                        }
                    }
                }
            }

            case "caracter": {
                String n1 = num1.valor.toString();
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        int resultado = n1.codePointAt(0) * n2;
                        Valor v = new Valor(resultado, "numero");
                        return v;
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        Double resultado = n1.codePointAt(0) * n2;
                        Valor v = new Valor(resultado, "decimal");
                        return v;
                    }
                    case "caracter": {
                        Errores.ErrorSemantico("Error en multiplicacion de caracter y caracter", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;

                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en multiplicacion de cadena y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        Errores.ErrorSemantico("Error en multiplicacion de caracter y booleano", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                }
            }
            case "cadena": {
                String n1 = num1.valor.toString();
                switch (num2.tipo) {
                    case "numero": {
                        Errores.ErrorSemantico("Error en multiplicacion de cadena y entero", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "decimal": {
                        Errores.ErrorSemantico("Error en multiplicacion de cadena y decimal", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "caracter": {
                        Errores.ErrorSemantico("Error en multiplicacion de cadena y caracter", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error en multiplicacion de cadena y cadena", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        Errores.ErrorSemantico("Error en multiplicacion de cadena y booleano", 0, 0);
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
                            int resultado = 1 * n2;
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        }
                        case "decimal": {
                            Double n2 = Double.parseDouble(num2.valor.toString());
                            Double resultado = 1 * n2;
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        }
                        case "caracter": {
                            Errores.ErrorSemantico("Error en multiplicacion de booleano y caracter", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "cadena": {
                            Errores.ErrorSemantico("Error en multiplicacion de booleano y cadena", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "bool": {
                            if(num2.valor.equals("verdadero")){
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                            }else{
                                Valor v = new Valor("falso", "bool");
                            return v;
                            }
                        }
                    }
                } else {
                    switch (num2.tipo) {
                        case "numero": {
                            int n2 = Integer.parseInt(num2.valor.toString());
                            int resultado = 0 * n2;
                            Valor v = new Valor(resultado, "numero");
                            return v;
                        }
                        case "decimal": {
                            Double n2 = Double.parseDouble(num2.valor.toString());
                            Double resultado = 0 * n2;
                            Valor v = new Valor(resultado, "decimal");
                            return v;
                        }
                        case "caracter": {
                            Errores.ErrorSemantico("Error en multiplicacion de booleano y caracter", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "cadena": {
                            Errores.ErrorSemantico("Error en multiplicacion de booleano y cadena", 0, 0);
                            Valor v = new Valor("", "error");
                            return v;
                        }
                        case "bool": {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                }
            }
        }

        return null;
    }
}
