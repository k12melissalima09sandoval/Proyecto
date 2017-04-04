/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dibujar;

/**
 *
 * @author MishaPks
 */
import Interprete.Haskell.FuncionHaskell;
import Interprete.Parametros;
import Simbolos.TablaSimbolosHaskell;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.tree.*;

public class ArbolFunciones {
    static DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Funciones Haskell");
    static TablaSimbolosHaskell lista = new TablaSimbolosHaskell();
    
    
    public void CrearArbol(){
        Map<String, FuncionHaskell> listaFunciones = lista.ObtenerListaFunciones();
        
        for (int i = 0; i < listaFunciones.size(); i++) {
            String nombre;
            nombre = listaFunciones.get(i).getNombre();
            DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(nombre);
            ArrayList param = (ArrayList)listaFunciones.get(i).parametros;
            nuevo.add(nuevo);
            raiz.add(nuevo);
        }
        
        DefaultTreeModel arbol = new DefaultTreeModel(raiz);
        Funciones.jTree1.setModel(arbol);
    }
}