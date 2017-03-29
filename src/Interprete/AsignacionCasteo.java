/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Analizadores.Errores;

/**
 *
 * @author MishaPks
 */
public class AsignacionCasteo {

    public Object Casteo(String tipoRecibe, Object val, String tipoAsigna) {

        switch (tipoRecibe) {
            case "numero": {
                switch (tipoAsigna) {
                    case "numero": {
                        Valor v = new Valor(val, "numero");
                        return v;
                    }
                    case "decimal": {
                        int num = (int) Math.floor(Double.parseDouble(val.toString()));
                        Valor v = new Valor(num, "numero");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error de casteo (numero <- cadena)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "caracter": {
                        int num = val.toString().codePointAt(0);
                        Valor v = new Valor(num, "numero");
                        return v;
                    }
                    case "bool": {
                        if (val.equals("verdadero")) {
                            Valor v = new Valor(1, "numero");
                            return v;
                        } else {
                            Valor v = new Valor(0, "numero");
                            return v;
                        }
                    }
                }
            }
            case "decimal": {
                switch (tipoAsigna) {
                    case "numero": {
                        Double num = Double.parseDouble(val.toString());
                        Valor v = new Valor(num, "decimal");
                        return v;
                    }
                    case "decimal": {
                        Valor v = new Valor(val, "decimal");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error de casteo (cadena <- bool)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "caracter": {
                        Errores.ErrorSemantico("Error de casteo (caracter <- bool)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        Errores.ErrorSemantico("Error de casteo (decimal <- bool)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                }
            }
            case "cadena": {
                switch (tipoAsigna) {
                    case "numero": {
                        String num = "\"" + val.toString().replace("\"","") + "\"";
                        Valor v = new Valor(num, "cadena");
                        return v;
                    }
                    case "decimal": {
                        String num = "\"" + val.toString().replace("\"", "") + "\"";
                        Valor v = new Valor(num, "cadena");
                        return v;
                    }
                    case "cadena": {
                        String num = "\"" + val.toString().replace("\"", "") + "\"";
                        Valor v = new Valor(num, "cadena");
                        return v;
                    }
                    case "caracter": {
                        String num = "\"" + val.toString().replace("\"","") + "\"";
                        Valor v = new Valor(num, "cadena");
                        return v;
                    }
                    case "bool": {
                        String num = "\"" + val.toString().replace("\"", "") + "\"";
                        Valor v = new Valor(num, "cadena");
                        return v;
                    }
                }
            }
            case "caracter": {
                switch (tipoAsigna) {
                    case "numero": {
                        char num = (char) Integer.parseInt(val.toString());
                        String r = "'" + num + "'";
                        Valor v = new Valor(r, "caracter");
                        return v;
                    }
                    case "decimal": {
                        Errores.ErrorSemantico("Error de casteo (caracter <- decimal)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error de casteo (caracter <- cadena)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "caracter": {
                        String num = "'" + val.toString().replace("'","") + "'";
                        Valor v = new Valor(num, "caracter");
                        return v;
                    }
                    case "bool": {
                        Errores.ErrorSemantico("Error de casteo (caracter <- bool)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                }
            }
            case "bool": {
                switch (tipoAsigna) {
                    case "numero": {
                        Errores.ErrorSemantico("Error de casteo (bool <- numero)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "decimal": {
                        Errores.ErrorSemantico("Error de casteo (bool <- decimal)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "cadena": {
                        Errores.ErrorSemantico("Error de casteo (bool <- cadena)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "caracter": {
                        Errores.ErrorSemantico("Error de casteo (bool <- caracter)", 0, 0);
                        Valor v = new Valor("", "error");
                        return v;
                    }
                    case "bool": {
                        Valor v = new Valor(val, "bool");
                        return v;
                    }
                }
            }
        }
        return null;
    }
}
