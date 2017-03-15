/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import java.util.ArrayList;
import Interprete.FuncionHaskell;
import Interprete.Variable;
/**
 *
 * @author MishaPks
 */
public class RecorreHaskell {
    
    public static ArrayList<String> parametros;
    static FuncionHaskell nueva;
    static TablaSimbolosHaskell agrega = new TablaSimbolosHaskell();
    static Variable variable;
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
    
    public static void Consola(Nodo raiz){
        
            switch(raiz.valor.toString()){
                
                    case "D_Lista": //siempre trae dos hijos
                        String nombrelista = raiz.hijos.get(0).valor.toString();
                        if(raiz.hijos.get(1).hijos.size()==1){ //id, cadena, Lista, 2Niveles
                            if(raiz.hijos.get(1).hijos.get(0).valor.toString()=="id"){
                                String valor =  raiz.hijos.get(1).hijos.get(0).hijos.get(0).valor.toString();
                                variable = new Variable(nombrelista,valor);
                                agrega.AgregarVariable(nombrelista, variable);
                            }
                            else if(raiz.hijos.get(1).hijos.get(0).valor.toString()=="cadena"){
                                ArrayList<Object> cadena=new ArrayList();
                                String valor = raiz.hijos.get(1).hijos.get(0).hijos.get(0).valor.toString();
                                for (int i = 0; i < valor.length(); i++) {
                                    char letra = valor.charAt(i);
                                    cadena.add(letra);
                                }
                                variable = new Variable(nombrelista, cadena);
                                agrega.AgregarVariable(nombrelista, variable);
                            }
                            else if(raiz.hijos.get(1).hijos.get(0).valor.toString()=="Lista"){
                                ArrayList<Object> cadena = new ArrayList();
                                if(raiz.hijos.get(1).hijos.get(0).hijos.get(0).valor.toString()=="caracter"){
                                    for(Nodo c: raiz.hijos.get(1).hijos.get(0).hijos){
                                        //aqui tengo que llevar la lista de caracteres
                                        cadena.add(c.hijos.get(0).valor.toString());
                                        
                                    }
                                variable = new Variable(nombrelista, cadena);
                                agrega.AgregarVariable(nombrelista, variable);
                                } else if(raiz.hijos.get(1).hijos.get(0).hijos.get(0).valor.toString()=="Calcula"){
                                 //AQUI ME QUEDE   
                                }
                            }
                            else if(raiz.hijos.get(1).hijos.get(0).valor.toString()=="2Niveles"){
                                
                            }
                        }
                        System.out.println("entro a D_Lista");
                        break;
                        
                    case "Calcular":
                        System.out.println("entro a Calcular");
                        break;
        }
        
    }
}
