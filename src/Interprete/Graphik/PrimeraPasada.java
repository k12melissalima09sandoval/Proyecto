/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Analizadores.Errores;
import Analizadores.Graphik.GraphikLexico;
import Analizadores.Graphik.GraphikSintactico;
import Ast.Nodo;
import Interprete.Haskell.FuncionHaskell;
import Interprete.Valor;
import Simbolos.TablaSimbolosGraphik;
import Simbolos.TablaSimbolosHaskell;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MishaPks
 */
public class PrimeraPasada {

    public static String ruta;
    SentenciasAls sentAls = new SentenciasAls();
    CrearVariables varsGlobales = new CrearVariables();
    CrearMetodos metodos = new CrearMetodos();
    TablaSimbolosGraphik tabla = new TablaSimbolosGraphik();
    TablaSimbolosHaskell tablaH = new TablaSimbolosHaskell();
    static int paso = 0;

    public Object Reconocer(Nodo raiz, Boolean bandera) {

        //LOS ALS
        for (Nodo c : raiz.hijos.get(2).hijos) {
            Nodo cuerpo = c.hijos.get(3);
            Als nuevo = new Als();
            nuevo.Inicializar();
            //nombre clase
            String nombreAls = c.hijos.get(0).valor.toString();
            Boolean key = tabla.existeAls(nombreAls);
            if (key) {
                Errores.ErrorSemantico("El Als -" + nombreAls + "- ya esta definido", 0, 0);
            } else {

                //Hereda
                String hereda;
                if (!c.hijos.get(2).hijos.isEmpty()) {
                    hereda = c.hijos.get(2).hijos.get(0).valor.toString();
                    Boolean existe = VerificarImporaciones(hereda + ".gk");
                    if (existe) {
                        try {
                            String texto = Abierto(ruta + "\\" + hereda + ".gk");
                            if (texto != null) {

                                GraphikLexico scan = new GraphikLexico(new BufferedReader(new StringReader(texto)));
                                GraphikSintactico parser = new GraphikSintactico(scan);
                                parser.parse();
                                Graficar(recorrido(GraphikSintactico.raiz), "AstHereda_" + hereda);

                                Valor v = (Valor) Reconocer(GraphikSintactico.raiz, true);
                                Als b = (Als) v.valor;
                                if (!b.visibilidad.equals("Privado")) {
                                    Als a = b.copiar();
                                    for (int i = 0; i < a.VarsGlobales.size(); i++) {
                                        if (a.VarsGlobales.get(i).visibilidad.equals("Publico")
                                                || a.VarsGlobales.get(i).visibilidad.equals("Protegido")) {
                                            a.VarsGlobales.get(i).setHereda(true);
                                            nuevo.addVarGlobal(a.VarsGlobales.get(i));

                                        }
                                    }
                                    for (int i = 0; i < a.Metodos.size(); i++) {
                                        if (a.Metodos.get(i).visibilidad.equals("Publico")
                                                || a.Metodos.get(i).visibilidad.equals("Protegido")) {
                                            a.Metodos.get(i).setHereda(true);
                                            nuevo.addMetodo(a.Metodos.get(i));
                                        }
                                    }
                                    nuevo.agregarHereda(a);
                                }else{
                                    Errores.ErrorSemantico("No se puede heredar un Als -" + hereda + "- privado", paso, paso);
                                }
                            } else {
                                Errores.ErrorSemantico("El Als -" + hereda + "- esta vacio", paso, paso);
                            }
                        } catch (Exception e) {
                            Logger.getLogger(PrimeraPasada.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } else {
                        Errores.ErrorSemantico("No se puede heredar del Als -" + hereda + "- porque no "
                                + "existe", 0, 0);
                    }
                } else {
                    hereda = null;
                }
                //visibilidad de clase
                String visibilidad = c.hijos.get(1).valor.toString();
                nuevo.nombre = nombreAls;
                nuevo.visibilidad = visibilidad;
                if (!bandera) {
                    TablaSimbolosGraphik.addAls(nuevo);
                }

                

            }

            //IMPORTA
            if (!raiz.hijos.get(0).hijos.isEmpty()) {

                for (Nodo c2 : raiz.hijos.get(0).hijos) {
                    String nombre = c2.valor.toString();
                    Boolean existe = VerificarImporaciones(nombre);
                    if (existe) {
                        try {
                            String texto = Abierto(ruta + "\\" + nombre);
                            if (texto != null) {

                                GraphikLexico scan = new GraphikLexico(new BufferedReader(new StringReader(texto)));
                                GraphikSintactico parser = new GraphikSintactico(scan);
                                parser.parse();
                                Graficar(recorrido(GraphikSintactico.raiz), "AstImporta_" + nombre);

                                Valor v = (Valor) Reconocer(GraphikSintactico.raiz, true);
                                Als b = (Als) v.valor;
                                Als a = b.copiar();
                                nuevo.agregarImporta(a);
                            } else {
                                Errores.ErrorSemantico("El Als -" + nombre + "- esta vacio", paso, paso);
                            }

                        } catch (Exception e) {
                            Logger.getLogger(PrimeraPasada.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } else {
                        Errores.ErrorSemantico("El archivo -" + nombre + "- no existe y no se puede importar", 0, 0);
                    }
                }
            }
            varsGlobales.CrearVariablesGlobales(cuerpo, nuevo);
            metodos.CrearMetodos(cuerpo, nuevo);
           // varsGlobales.CrearInstanciasGlobales(cuerpo, nuevo);
            //INCLUYE
            if (!raiz.hijos.get(1).hijos.isEmpty()) {
                Map<String, FuncionHaskell> funciones = tablaH.ObtenerListaFunciones();
                if (!funciones.isEmpty()) {
                    for (Nodo c3 : raiz.hijos.get(1).hijos) {
                        String nombre = c3.valor.toString();
                        Boolean existe = tablaH.getKeyFunciones(nombre);
                        if (!existe) {
                            nuevo.agregarIncluye(null);
                            Errores.ErrorSemantico("La funcion de Haskell -" + nombre + "- no existe", 0, 0);
                        } else {
                            FuncionHaskell a = funciones.get(nombre);
                            FuncionHaskell b = a.copiar();

                            nuevo.agregarIncluye(b);
                        }
                    }
                } else {
                    Errores.ErrorSemantico("No hay funciones cargadas, no se pude incluir", 0, 0);
                }

            }

            if (bandera) {
                Valor v = new Valor(nuevo, "");
                return v;
            }

        }
        return null;
    }

    private Boolean VerificarImporaciones(String incluye) {
        if (ruta == null) {
            JOptionPane.showMessageDialog(null,
                    "Se necesita tener una ruta, por favor abra algun archivo", "",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (paso > 0) {
            String files;
            File folder = new File(ruta);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    files = listOfFiles[i].getName();
                    if (incluye.equals(files)) {
                        return true;
                    }

                }
            }
        } else {
            int contador = 0;
            int m = 0;
            for (int i = 0; i < ruta.length(); i++) {
                String temp = ruta.substring(m, i + 1);
                m++;
                if (temp.equals("\\")) {
                    contador++;
                }
            }
            String r = "";
            int contTemp = 0;
            m = 0;
            for (int i = 0; i < ruta.length(); i++) {
                String temp = ruta.substring(m, i + 1);
                m++;
                if (temp.equals("\\")) {
                    contTemp++;
                    if (contador == contTemp) {
                        break;
                    } else {
                        r += temp;

                    }
                } else {
                    r += temp;
                }

            }

            ruta = r;
            String files;
            File folder = new File(ruta);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                if (listOfFiles[i].isFile()) {
                    files = listOfFiles[i].getName();
                    if (incluye.equals(files)) {
                        paso++;
                        return true;
                    }

                }
            }
        }

        return false;
    }

    public void Graficar(String cadena, String cad) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombre = "ImagenesAst/" + cad;
        String archivo = nombre + ".dot";
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println("digraph G {node[shape=box, style=filled]; edge[color=red]");
            pw.println(cadena);
            pw.println("\n}");
            fichero.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            String cmd = "dot.exe -Tpng " + nombre + ".dot -o " + nombre + ".png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    String recorrido(Nodo raiz) {

        String r = "";
        if (raiz != null) {
            r = "node" + raiz.hashCode() + "[label=\"" + raiz.valor + "\"];";
            for (int i = 0; i < raiz.hijos.size(); i++) {
                r += "\n node" + raiz.hashCode() + "->" + "node" + raiz.hijos.get(i).hashCode() + ";";
                r += recorrido(raiz.hijos.get(i));
            }
        }
        return r;
    }

    java.io.FileWriter escribir;
    java.io.PrintWriter pintar;
    java.io.BufferedReader leer;
    java.io.FileReader leerFichero;

    public String Abierto(String nombre) {
        try {
            String archivo = "", linea;
            leerFichero = new java.io.FileReader(nombre);
            leer = new java.io.BufferedReader(leerFichero);
            linea = leer.readLine();
            while (linea != null) {
                archivo += linea;
                archivo += System.getProperty("line.separator");
                linea = leer.readLine();
            }

            return archivo;

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (leerFichero != null) {
                try {
                    leerFichero.close();
                } catch (IOException ex) {
                    // Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
