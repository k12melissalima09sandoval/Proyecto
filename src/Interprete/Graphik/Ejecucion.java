/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Ast.Nodo;

/**
 *
 * @author MishaPks
 */
public class Ejecucion {

    PrimeraPasada primera = new PrimeraPasada();

    public void Ejecucion(Nodo raiz) {

        //primera pasada
        //creo los als
        //guardo las variables globales
        //guardo todos los metodos
        //verifico los incluye y valido si existen
        //verifico los importa y valido si existen
        primera.Reconocer(raiz,false);

        //segunda pasada
        //busco los hereda de cada metodo y verifico si existe, si existe lo agrego al als
        //busco las instancias globales y si hay busco los als y verifico que existan
        //tercer pasada
        //ejecucion del metodo inicio
        //ejecucion de sentencias
        //creacion de variables locales
    }

}
