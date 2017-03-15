/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Ast.Nodo;

/**
 *
 * @author MishaPks
 */
public class ExpresionHaskell {
    
    public Object Expresion(Nodo raiz){
        if(raiz.hijos.size()==1){
            Nodo exp = raiz.hijos.get(0);
            switch(raiz.valor.toString()){
                
                case "Exp":
                    Valor ob1 = (Valor)Expresion(exp);
                    return ob1;
                
                case "Calcular":
                    Valor ob2 = (Valor)Expresion(exp);
                    return ob2;
                    
                case "Decc":
                break;
                
                case "Indice":
                    break;

                case "Length":
                    break;

                case "LlamaFunc":
                    break;

                case "Max":
                    break;

                case "Min":
                    break;

                case "Product":
                    break;

                case "Succ":
                    break;

                case "Sum":
                    break;
                    
                case "numero":
                    Valor v = new Valor(raiz.hijos.get(0).valor.toString(),"numero");
                    return v;
                    
                case "cadena":
                    Valor v1 = new Valor(raiz.hijos.get(0).valor.toString(),"cadena");
                    return v1;
                    
                case "id":
                    Valor v2 = new Valor(raiz.hijos.get(0).valor.toString(),"id");
                    return v2;
                    
                case "caracter":
                    Valor v3 = new Valor(raiz.hijos.get(0).valor.toString().replace("'", ""),"caracter");
                    return v3;
                    
                case "porcentaje":
                    break;
                    
                case "unario":
                    break;
                    
            }
        }else if(raiz.hijos.size()==2){
            Valor izq;
            Valor der;
            Double resultado = 0.0;
            switch(raiz.valor.toString()){
                
                case "+":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = (Double)izq.valor;
                                Double num2 = (Double)der.valor;
                                resultado = num1+num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = num1+num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                        }
                    }
                case "-":
                    
                    
                case "*":
                    
                    
                case "/":
                    
                    
                case "mod":
                    
                    
                case "sqrt":
                    
                    //resultado = Math.pow(der,1/izq);
                   
                    
                case "pot":
                   
                    //resultado = Math.pow(izq,der);
                    
                    
            }
        }
        return null;
    }
}
