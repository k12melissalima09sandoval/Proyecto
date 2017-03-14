/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Ast.Nodo;
import Interprete.Parametros;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class TablaSimbolosHaskell {
    
    String nombre;
    public Object parametros;
    Nodo cuerpo;
    
    public TablaSimbolosHaskell(String nombre, ArrayList<String> param, Nodo cuerpofun){
        this.nombre = nombre;
        this.parametros=param;
        this.cuerpo = cuerpofun;
        
    }
    
}
