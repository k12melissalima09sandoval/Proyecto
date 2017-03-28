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
public class MenorQ {

    public Object MenorQ(Valor num1, Valor num2) {
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
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "cadena": {
                        int n2 = Cadena(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            if (n1 < 1) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        } else if (n1 < 0) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
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
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "cadena": {
                        int n2 = Cadena(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            if (n1 < 1) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        } else if (n1 < 0) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
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
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "cadena": {
                        int n2 = (int) Cadena(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            if (n1 < 1) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        } else if (n1 < 0) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                }
            }
            case "cadena": {
                int n1 = Cadena(num1.valor.toString());
                switch (num2.tipo) {
                    case "numero": {
                        int n2 = Integer.parseInt(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "decimal": {
                        Double n2 = Double.parseDouble(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "caracter": {
                        int n2 = num2.valor.toString().codePointAt(0);
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "cadena": {
                        int n2 = (int) Cadena(num2.valor.toString());
                        if (n1 < n2) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                    case "bool": {
                        if (num2.valor.equals("verdadero")) {
                            if (n1 < 1) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        } else if (n1 < 0) {
                            Valor v = new Valor("verdadero", "bool");
                            return v;
                        } else {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                }
            }
            case "bool": {
                if (num2.valor.equals("verdadero")) {
                    switch (num2.tipo) {
                        case "numero": {
                            int n2 = Integer.parseInt(num2.valor.toString());
                            if (1 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "decimal": {
                            Double n2 = Double.parseDouble(num2.valor.toString());
                            if (1 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "caracter": {
                            int n2 = num2.valor.toString().codePointAt(0);
                            if (1 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "cadena": {
                            int n2 = (int) Cadena(num2.valor.toString());
                            if (1 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "bool": {
                            Valor v = new Valor("falso", "bool");
                            return v;
                        }
                    }
                } else {
                    switch (num2.tipo) {
                        case "numero": {
                            int n2 = Integer.parseInt(num2.valor.toString());
                            if (0 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "decimal": {
                            Double n2 = Double.parseDouble(num2.valor.toString());
                            if (0 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "caracter": {
                            int n2 = num2.valor.toString().codePointAt(0);
                            if (0 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
                        }
                        case "cadena": {
                            int n2 = (int) Cadena(num2.valor.toString());
                            if (0 < n2) {
                                Valor v = new Valor("verdadero", "bool");
                                return v;
                            } else {
                                Valor v = new Valor("falso", "bool");
                                return v;
                            }
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

    private int Cadena(String cad) {
        int valor = 0;
        char c;
        for (int i = 0; i < cad.length(); i++) {
            c = cad.charAt(i);
            valor += c;
        }
        return valor;
    }
}
