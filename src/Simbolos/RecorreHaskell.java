/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import java.util.ArrayList;
import Interprete.FuncionHaskell;
import FunPropias.FuncionesPropiasHaskell;
import Interprete.Valor;
import Interprete.Variable;
/**
 *
 * @author MishaPks
 */
public class RecorreHaskell {
    
    public static ArrayList<String> parametros;
    static FuncionHaskell nueva;
    static FuncionesPropiasHaskell funPropias = new FuncionesPropiasHaskell();
    static TablaSimbolosHaskell agrega = new TablaSimbolosHaskell();
    static Variable variable;
    
    //-------------------------------------AGREGANDO FUNCIONES---------------------------------
    public static void Recorrido(Nodo raiz){
        
        for (int i = 0; i < raiz.hijos.size(); i++) {
            //nombre de la funcion
            String nombreFunc = raiz.hijos.get(i).hijos.get(0).valor.toString();
            
            //parametros que recibe la funcion
            parametros = new ArrayList();
            Nodo param=raiz.hijos.get(i).hijos.get(1);
            for (int j = 0; j < param.hijos.size(); j++) {
                parametros.add(param.hijos.get(j).valor.toString());
            }
            
            //nodo del cuerpo de la funcion
            Nodo cuerpo = raiz.hijos.get(i).hijos.get(2).hijos.get(0);
            
            nueva = new FuncionHaskell(nombreFunc,parametros,cuerpo);
            agrega.AgregarFuncion(nombreFunc, nueva); 
        }
        //Map<String, FuncionHaskell> l = agrega.ObtenerListaFunciones();
    }
    
    
    //------------------------------------OPERACIONES EN CONSOLA ------------------------------
    public Object Consola(Nodo raiz){
        
            switch(raiz.valor.toString()){
                
                    case "D_Lista":
                        String nombrelista = raiz.hijos.get(0).valor.toString();
                         //id, cadena, Lista, 2Niveles
                        Valor val = (Valor)funPropias.Recorrer(raiz.hijos.get(1));
                        if(val!=null){

                            variable = new Variable(nombrelista,val.valor,val.tipo);
                            agrega.AgregarVariable(nombrelista, variable);
                            String texto="";
                            ArrayList a = (ArrayList)val.valor;
                            if(a!=null){

                                if(val.tipo.equals("numero")){

                                    for (int i = 0; i < a.size(); i++) {
                                        texto += a.get(i).toString()+",";
                                    }

                                    if(texto.lastIndexOf(",")==texto.length()-1){
                                        texto = texto.substring(0,texto.length()-1);
                                    }
                                    texto = "["+texto+"]";
                                    Valor v= new Valor(texto,"");
                                    return v;
                                }else if(val.tipo.equals("cadena")){
                                    for (int i = 0; i < a.size(); i++) {
                                        texto += a.get(i).toString();
                                    }
                                    texto = "\""+texto+"\"";
                                    Valor v = new Valor(texto,"");
                                    return v;
                                }else if(val.tipo.equals("caracter")){
                                    for (int i = 0; i < a.size(); i++) {
                                        texto += a.get(i).toString();
                                    }
                                    texto = "\""+texto+"\"";
                                    Valor v = new Valor(texto,"");
                                    return v;
                                }
                            }
                        }
                        
                        
                    case "Calcular":
                        Valor valcalc=(Valor)funPropias.Recorrer(raiz);
                        return valcalc;
                        
                    case "Concatena":
                        Valor valconca = (Valor)funPropias.Recorrer(raiz);
                        String texto="";
                        ArrayList a = (ArrayList)valconca.valor;
                            if(a!=null){

                                if(valconca.tipo.equals("numero")){

                                    for (int i = 0; i < a.size(); i++) {
                                        texto += a.get(i).toString()+",";
                                    }

                                    if(texto.lastIndexOf(",")==texto.length()-1){
                                        texto = texto.substring(0,texto.length()-1);
                                    }
                                    texto = "["+texto+"]";
                                    Valor v= new Valor(texto,"");
                                    return v;
                                }else if(valconca.tipo.equals("cadena")){
                                    for (int i = 0; i < a.size(); i++) {
                                        texto += a.get(i).toString();
                                    }
                                    texto = "\""+texto+"\"";
                                    Valor v = new Valor(texto,"");
                                    return v;
                                }else if(valconca.tipo.equals("caracter")){
                                    for (int i = 0; i < a.size(); i++) {
                                        texto += a.get(i).toString();
                                    }
                                    texto = "\""+texto+"\"";
                                    Valor v = new Valor(texto,"");
                                    return v;
                                }
                            }
                        //return valconca;
        }
        
        return null;
    }
}
