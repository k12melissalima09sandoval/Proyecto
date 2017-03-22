/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Analizadores.Errores;
import Interprete.Graphik.Als;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author MishaPks
 */
public class TablaSimbolosGraphik {
    
    public static ArrayList<Errores> errorSemantico = new ArrayList();
    public static Map<String, Als> AlsPublico = new LinkedHashMap<>();
    public static Map<String, Als> AlsPrivado = new LinkedHashMap<>();
    public static Map<String, Als> AlsProtegido = new LinkedHashMap<>();
    public static Map<String, Als> listaAls = new LinkedHashMap<>();
    
    
    //-------------------------------------------------------ALS
    public static void addAlsPublico(String nombre,Als a){
        AlsPublico.put(nombre,a);
        listaAls.put(nombre,a);
    }
    public static void addAlsPrivado(String nombre,Als a){
        AlsPrivado.put(nombre,a);
        listaAls.put(nombre,a);
    }
    public static void addAlsProtegido(String nombre,Als a){
        AlsProtegido.put(nombre,a);
        listaAls.put(nombre,a);
    }
    
    public static Map<String,Als> getAlsPublicos(){
        return AlsPublico;
    }
    public static Map<String,Als> getAlsPrivados(){
        return AlsPrivado;
    }
    public static Map<String,Als> getAlsProtegidos(){
        return AlsProtegido;
    }
    public static Map<String,Als> getAls(){
        return listaAls;
    }
            
    public static Boolean getKeyAls(String val) {
        Boolean valor = listaAls.containsKey(val);
        return valor;
    }
    
    //--------------------------------------------------------ERRORES
    public static void AgregarErrores(Errores err){
        errorSemantico.add(err);
    }
    
    public ArrayList<Errores> getErrores(){
        return errorSemantico;
    }
    
    
    
}
