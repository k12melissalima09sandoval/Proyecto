package Interprete.Graphik;

import Analizadores.Errores;
import Ast.Nodo;
import Interprete.Variable;
import Simbolos.TablaSimbolosGraphik;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class SegundaPasada {

    CrearVariables varsLocales = new CrearVariables();

    public Object Reconocer() {

        ArrayList<Als> als = TablaSimbolosGraphik.listaAls;
        for (int i = 0; i < als.size(); i++) {
            Boolean existe = als.get(i).existeMetodo("Inicio");
            if (existe) {
                MetodoGraphik inicio = als.get(i).obtenerMetodo("Inicio");
                Nodo cuerpo = inicio.cuerpo.hijos.get(0);
                ArrayList<Variable> Globales = als.get(i).VarsGlobales;

                ArrayList<ArrayList<Variable>> nueva;
                nueva = new ArrayList();
                nueva.add(Globales);
                int nivel = 0;
                Ejecucion(cuerpo, nueva, nivel, als.get(i));

            } else {
                Errores.ErrorSemantico("No existe el metodo Inicio", 0, 0);
            }
        }

        return null;
    }

    public Object Ejecucion(Nodo cuerpoAls, ArrayList<ArrayList<Variable>> variables, int nivel, Als als) {
        int cont = 0;
        for (Nodo raiz : cuerpoAls.hijos) {
            if (raiz.valor.toString().equals("DeclaraLocalVariable")
                    || raiz.valor.toString().equals("DeclaraLocalArreglo")
                    || raiz.valor.toString().equals("IntanciaLocal")) {
                cont++;
            }
        }
        if (cont > 0) {
            nivel = varsLocales.CrearVariablesLocales(cuerpoAls, variables, nivel, als);
        }
        
        for (Nodo raiz : cuerpoAls.hijos) {
            switch (raiz.valor.toString()) {
                case "Asignacion": {
                    String nombre = raiz.hijos.get(0).valor.toString();
                    
                }

            }
        }
        return null;
    }

}
