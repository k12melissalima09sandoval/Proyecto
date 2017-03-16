/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Ast.Nodo;
import FunPropias.Concatena;
import Simbolos.TablaSimbolosHaskell;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class ExpresionHaskell {
    
    public Object ultimoValor;
    Boolean res;
    Boolean uno;
    Boolean dos;
    static Concatena concatena = new Concatena();
    static TablaSimbolosHaskell lista= new TablaSimbolosHaskell();
    public Object Expresion(Nodo raiz){
        if(raiz.hijos.size()==1){
            //Double temp;
            Valor tmp;
            Nodo exp = raiz.hijos.get(0);
            switch(raiz.valor.toString()){
                
                case "Exp":
                    Valor ob1 = (Valor)Expresion(exp);
                    return ob1;
                
                case "Calcular":
                    Valor ob2 = (Valor)Expresion(exp);
                    ultimoValor = ob2.valor.toString();
                    return ob2;
                    
                case "Decc":
                    Valor ob3 = (Valor)Expresion(exp);
                    if(ob3.tipo.equals("caracter")){
                        int num = ob3.valor.toString().codePointAt(0);
                        num = num-1;
                        ultimoValor = num;
                        System.out.println("decc: "+num);
                        tmp = new Valor(num,"numero");
                        return tmp;
                    }else {
                        Double ob18 = Double.parseDouble(ob3.valor.toString());
                        Double num = ob18-1;
                        ultimoValor = num;
                        System.out.println("decc: "+num);
                        tmp = new Valor(num,"numero");
                        return tmp;
                    }

                case "Length":
                    Valor ob4 = (Valor)concatena.Listas(exp.hijos.get(0));
                    ArrayList vals = (ArrayList)ob4.valor;
                    int tamaño = vals.size();
                    ultimoValor = tamaño;
                    Valor val4 = new Valor(tamaño,"numero");
                    return val4;

                case "Max":
                    Valor ob5 = (Valor)concatena.Listas(exp);
                    ArrayList a = (ArrayList)ob5.valor;
                    if(ob5.tipo.equals("caracter")){
                        
                        int max=0;
                        for (int i = 0; i <a.size(); i++) {
                            if(a.get(i).toString().codePointAt(0) > max){
                                max = a.get(i).toString().codePointAt(0);
                            }
                        }
                        ultimoValor=max;
                        char c = (char)max;
                        System.out.println("max "+c);
                        Valor v = new Valor(c,"caracter");
                        return v;
                    }else if(ob5.tipo.equals("cadena")){
                        
                        int max=0;
                        for (int i = 0; i <a.size(); i++) {
                            if(a.get(i).toString().codePointAt(0) > max){
                                max = a.get(i).toString().codePointAt(0);
                            }
                        }
                        ultimoValor=max;
                        char c = (char)max;
                        System.out.println("max "+c);
                        Valor v = new Valor(c,"caracter");
                        return v;
                    }else {
                        Double max=0.00;
                        for (int i = 0; i <a.size(); i++) {
                            if(Integer.parseInt(a.get(i).toString()) > max){
                                max = Double.parseDouble(a.get(i).toString());
                            }
                        }System.out.println("max "+max);
                        ultimoValor=max;
                        Valor v = new Valor(max,"numero");
                        return v;
                    }

                case "Min":
                    Valor ob6 = (Valor)concatena.Listas(exp);
                    ArrayList a8 = (ArrayList)ob6.valor;
                    if(ob6.tipo.equals("caracter")){
                        
                        int max=0;
                        for (int i = 0; i <a8.size(); i++) {
                            if(a8.get(i).toString().codePointAt(0) > max){
                                max = a8.get(i).toString().codePointAt(0);
                            }
                        }
                        int min=max;
                        for (int i = 0; i < a8.size(); i++) {
                            if(a8.get(i).toString().codePointAt(0) < min){
                                min = a8.get(i).toString().codePointAt(0);
                            }
                        }
                        ultimoValor = min;
                        char c = (char)min;
                        System.out.println("min "+c);
                        Valor v = new Valor(c,"caracter");
                        return v;
                    }else { //numero
                        Double max=0.00;
                        for (int i = 0; i <a8.size(); i++) {
                            if(Integer.parseInt(a8.get(i).toString()) > max){
                                max = Double.parseDouble(a8.get(i).toString());
                            }
                        }
                        Double min =max;
                        for (int i = 0; i < a8.size(); i++) {
                            if(Integer.parseInt(a8.get(i).toString()) < min){
                                min = Double.parseDouble(a8.get(i).toString());
                            }
                        }
                        ultimoValor = min;
                        System.out.println("min "+min);
                        Valor v = new Valor(min,"numero");
                        return v;
                    }

                case "Product":
                    Valor ob7 = (Valor)concatena.Listas(exp.hijos.get(0));
                    ArrayList a7 = (ArrayList)ob7.valor;
                    if(ob7.tipo.equals("caracter")){
                        
                        int multiplica=1;
                        for (int i = 0; i <a7.size(); i++) {
                            multiplica = multiplica * a7.get(i).toString().codePointAt(0);
                        }
                        ultimoValor = multiplica;
                        System.out.println("mult "+multiplica);
                        Valor v = new Valor(multiplica,"numero");
                        return v;
                    }else { //numero
                        int multiplica=1;
                        for (int i = 0; i <a7.size(); i++) {
                            multiplica = multiplica*Integer.parseInt(a7.get(i).toString());
                        }
                        ultimoValor = multiplica;
                        System.out.println("mult "+multiplica);
                        Valor v = new Valor(multiplica,"numero");
                        return v;
                    }

                case "Succ":
                    Valor ob8 = (Valor)Expresion(exp);
                    
                    if(ob8.tipo.equals("caracter")){
                        int num = ob8.valor.toString().codePointAt(0);
                        num =num+1;
                        ultimoValor = num;
                        System.out.println("succ: "+num);
                        tmp = new Valor(num,"numero");
                        return tmp;
                    }else {
                        Double ob18 = Double.parseDouble(ob8.valor.toString());
                        Double num = ob18+1;
                        ultimoValor = num;
                        System.out.println("succ: "+num);
                        tmp = new Valor(num,"numero");
                        return tmp;
                    }
                    
                case "Sum":
                    Valor ob9 = (Valor)concatena.Recorrer(exp.hijos.get(0));
                    ArrayList a9 = (ArrayList)ob9.valor;
                    if(ob9.tipo.equals("caracter")){
                        
                        int sum=0;
                        for (int i = 0; i <a9.size(); i++) {
                            sum = sum + a9.get(i).toString().codePointAt(0);
                        }
                        ultimoValor=sum;
                        System.out.println("sum "+sum);
                        Valor v = new Valor(sum,"numero");
                        return v;
                    }else { //numero
                        int sum=0;
                        for (int i = 0; i <a9.size(); i++) {
                            sum = sum+ Integer.parseInt(a9.get(i).toString());
                        }
                        ultimoValor = sum;
                        System.out.println("sum "+sum);
                        Valor v = new Valor(sum,"numero");
                        return v;
                    }
                    
                case "porcentaje":
                    Valor v4 = new Valor(ultimoValor,"numero");
                    return v4;
                    
                case "Unario":
                    Valor v5 = (Valor)Expresion(exp);
                    int mult = Integer.parseInt(v5.valor.toString())*(-1);
                    Valor v6= new Valor(mult,"numero");
                    return v6;
                    
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
                    
            }
        }else if(raiz.hijos.size()==2){
            Valor izq;
            Valor der;
            Double resultado = 0.0;
            switch(raiz.valor.toString()){
                
                case "LlamaFunc":
                    /////////////////////////////////////////////////////////////////////////////
                    break;
                    
                case "Indice":
                    String nombreLista = raiz.hijos.get(0).valor.toString();
                    Valor ob = (Valor)Expresion(raiz.hijos.get(1));
                    int indice = Integer.parseInt(ob.valor.toString());
                    //buscar en la lista de listas
                    
                    Map<String, Variable> l = lista.ObtenerListaListas();
                    if(l!=null){
                        if(l.size()>0){
                            for (int i = 0; i < l.size(); i++) {
                                Boolean g = lista.getKey(nombreLista);
                                if(g.equals(true)){
                                    ArrayList valores = (ArrayList)l.get(nombreLista).valor;
                                    String pos = valores.get(indice).toString();
                                    if(l.get(nombreLista).tipo.equals("numero"))
                                    {
                                        Valor val = new Valor(pos,"numero");
                                        return val;
                                    }else{
                                        Valor val = new Valor(pos,"caracter");
                                        return val;
                                    }
                                }
                            }
                        }else {
                            System.out.println("no hay ninguna lista declarada");
                        }
                    }
                    System.out.println("entro a indice "+nombreLista);
                    
                    //Valor val = new Valor();
                    return ob;
                    
                case "+":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
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
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                case "-":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = num1-num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = num1-num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                    
                case "*":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = num1*num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = num1*num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                    
                case "/":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = num1/num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = num1/num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                    
                case "mod":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = num1%num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = num1%num2;
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                    
                case "sqrt":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = Math.pow(num2,1/num1);
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = Math.pow(num2,1/num1);
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                   
                    
                case "pot":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null || der != null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                resultado = Math.pow(num1,num2);
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }
                            else if(izq.tipo.equals("caracter")){
                                Object o = izq.valor.toString().codePointAt(0);
                                Object m = der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(m.toString());
                                resultado = Math.pow(num1,num2);
                                Valor v = new Valor(resultado,"numero");
                                return v;
                            }else {
                                //error
                                System.out.println("no son del mismo tipo");
                            }
                        }
                    }else {
                        System.out.println("los valores tienen nulo");
                    }
                    
                case "||":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    uno = Boolean.parseBoolean(izq.valor.toString());
                    dos = Boolean.parseBoolean(der.valor.toString());
                    res = uno || dos;
                    Valor v = new Valor(res,"bool");
                    return v;
                    
                case "&&":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    uno = Boolean.parseBoolean(izq.valor.toString());
                    dos = Boolean.parseBoolean(der.valor.toString());
                    res = uno && dos;
                    Valor v2 = new Valor(res,"bool");
                    return v2;
                    
                case "<":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null && der!=null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if(num1<num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }else if(izq.tipo.equals("caracter")){
                                Object o = (Object)izq.valor.toString().codePointAt(0);
                                Object oo = (Object)der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if(num1<num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }
                        }
                    }
                    
                case ">":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null && der!=null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if(num1>num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }else if(izq.tipo.equals("caracter")){
                                Object o = (Object)izq.valor.toString().codePointAt(0);
                                Object oo = (Object)der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if(num1>num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }
                        }
                    }
                    
                case "<=":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null && der!=null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if(num1<=num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }else if(izq.tipo.equals("caracter")){
                                Object o = (Object)izq.valor.toString().codePointAt(0);
                                Object oo = (Object)der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if(num1<=num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }
                        }
                    }
                    
                case ">=":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null && der!=null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if(num1>=num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }else if(izq.tipo.equals("caracter")){
                                Object o = (Object)izq.valor.toString().codePointAt(0);
                                Object oo = (Object)der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if(num1>=num2){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }
                        }
                    }
                    
                case "==":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null && der!=null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if(num1.equals(num2)){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }else if(izq.tipo.equals("caracter")){
                                Object o = (Object)izq.valor.toString().codePointAt(0);
                                Object oo = (Object)der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if(num1.equals(num2)){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }
                        }
                    }
                case "!=":
                    izq = (Valor)Expresion(raiz.hijos.get(0)); 
                    der = (Valor)Expresion(raiz.hijos.get(1));
                    if(izq!=null && der!=null){
                        if(izq.tipo.equals(der.tipo)){
                            if(izq.tipo.equals("numero")){
                                Double num1 = Double.parseDouble(izq.valor.toString());
                                Double num2 = Double.parseDouble(der.valor.toString());
                                if(!num1.equals(num2)){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }else if(izq.tipo.equals("caracter")){
                                Object o = (Object)izq.valor.toString().codePointAt(0);
                                Object oo = (Object)der.valor.toString().codePointAt(0);
                                Double num1 = new Double(o.toString());
                                Double num2 = new Double(oo.toString());
                                if(!num1.equals(num2)){
                                    Valor val = new Valor(true,"bool");
                                    return val;
                                }else{
                                    Valor val = new Valor(false,"bool");
                                    return val;
                                }
                            }
                        }
                    }
            }
        }
        return null;
    }
}
