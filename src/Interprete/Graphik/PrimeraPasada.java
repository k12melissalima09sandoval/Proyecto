/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Simbolos.TablaSimbolosGraphik;
import java.net.URL;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class PrimeraPasada {

    SentenciasAls sentAls = new SentenciasAls();
    CrearVariables varsGlobales = new CrearVariables();
    CrearMetodos metodos = new CrearMetodos();
    public static Als nuevo = new Als();
    TablaSimbolosGraphik tabla = new TablaSimbolosGraphik();

    public Object Reconocer(Nodo raiz) {

        //IMPORTA
        if (raiz.hijos.get(0).hijos.size() != 0) {

            //VerificarIncluye("hola");
        }
        //INCLUYE
        if (raiz.hijos.get(1).hijos.size() != 0) {

        }

        //LOS ALS
        for (Nodo c : raiz.hijos.get(2).hijos) {

            nuevo.Inicializar();

            //nombre clase
            String nombreAls = c.hijos.get(0).valor.toString();
            Boolean key = TablaSimbolosGraphik.getKeyAls(nombreAls);
            if (key) {
                Errores.ErrorSemantico("El Als -" + nombreAls + "- ya esta definido", 0, 0);
            } else {

                //Hereda
                String hereda;
                if (!c.hijos.get(2).hijos.isEmpty()) {
                    hereda = c.hijos.get(2).hijos.get(0).toString();
                } else {
                    hereda = null;
                }
                //visibilidad de clase
                String visibilidad = c.hijos.get(1).valor.toString();
                if (visibilidad.equals("Publico")) {
                    Als nuevoAls = new Als(nombreAls, visibilidad, hereda);
                    TablaSimbolosGraphik.addAlsPublico(nombreAls, nuevoAls);

                } else if (visibilidad.equals("Privado")) {
                    Als nuevoAls = new Als(nombreAls, visibilidad, hereda);
                    TablaSimbolosGraphik.addAlsPrivado(nombreAls, nuevoAls);

                } else {
                    Als nuevoAls = new Als(nombreAls, visibilidad, hereda);
                    TablaSimbolosGraphik.addAlsProtegido(nombreAls, nuevoAls);
                }
                Nodo cuerpo = c.hijos.get(3);
                varsGlobales.CrearVariablesGlobales(cuerpo,nuevo);
                metodos.CrearMetodos(cuerpo,nuevo);
                Map<String,Als> ob = TablaSimbolosGraphik.getAls();
                
                
                //get el als para ver si hizo todo bien
            }

        }

        //ErrorSemantico("Semantico", "El Als "+nombreAls+" ya esta definido", 0, 0);
        return null;
    }

    public void VerificarIncluye(String nombre) {
        this.getClass().getClassLoader().getResourceAsStream("resources/fichero.txt");
        URL is = ClassLoader.getSystemResource("aritmetica.gk");
        //InputStream is = ClassLoader.getSystemResource("com/otropaquete/imagen1.jpg");
        if (is != null) {

        }
    }

}
