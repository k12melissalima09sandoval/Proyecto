/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Analizadores.Errores;
import Interprete.Graphik.Als;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class TablaSimbolosGraphik {
    
    public static ArrayList<Errores> errorSemantico = new ArrayList();
    public static ArrayList<Als> listaAls = new ArrayList();
    
    
    //-------------------------------------------------------ALS
    public static void addAls(Als a){
        
        listaAls.add(a);
    }
    
    
    public static ArrayList<Als> getAls(){
        return listaAls;
    }
    public Boolean existeAls(String val) {

        if (listaAls.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < listaAls.size(); i++) {
                if (val.equals(listaAls.get(i).nombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Als obtenerAls(String nombre) {
        for (int i = 0; i < listaAls.size(); i++) {
            if (nombre.equals(listaAls.get(i).nombre)) {
                Als encontrado = (Als) listaAls.get(i);
                return encontrado;
            }
        }
        return null;
    }    
    
    //--------------------------------------------------------ERRORES
    public static void AgregarErrores(Errores err){
        errorSemantico.add(err);
    }
    
    public ArrayList<Errores> getErrores(){
        return errorSemantico;
    }
    
    
    
}
