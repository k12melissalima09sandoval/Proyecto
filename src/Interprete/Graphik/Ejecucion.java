/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Dibujar.Nodo;

/**
 *
 * @author MishaPks
 */
public class Ejecucion {

    PrimeraPasada primera = new PrimeraPasada();
    SegundaPasada segunda = new SegundaPasada();
    
    public void Ejecucion(Nodo raiz) {

        //primera pasada
        //creo los als
        //guardo las variables globales
        //guardo todos los metodos
        //verifico los hereda y valido si existen y los agrego
        //verifico los incluye y valido si existen y los agrego
        //verifico los importa y valido si existen y los agrego
        primera.Reconocer(raiz,false);

        //segunda pasada
        //ejecucion del metodo inicio
        //ejecucion de sentencias
        segunda.Reconocer();
        
    }

}
