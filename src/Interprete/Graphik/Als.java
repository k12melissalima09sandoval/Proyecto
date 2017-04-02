/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

import Interprete.Haskell.FuncionHaskell;
import Interprete.Variable;
import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class Als implements Cloneable {

    public String nombre;
    public String visibilidad;
    public ArrayList<Als> hereda = new ArrayList();
    public ArrayList<Als> importa = new ArrayList();
    public ArrayList<FuncionHaskell> incluye = new ArrayList();
    public ArrayList<Variable> VarsGlobales = new ArrayList();
    public ArrayList<MetodoGraphik> Metodos = new ArrayList();

    public Als() {

    }

    public Als copiar() {
        try {
            Als copia = new Als();
            ArrayList<Variable> varscopia = new ArrayList();
            for (int i = 0; i < this.VarsGlobales.size(); i++) {
                if (this.VarsGlobales.get(i).instancia) {
                    Als a = (Als)this.VarsGlobales.get(i).valor;
                    Als b = a.copiar();
                    Variable v = this.VarsGlobales.get(i).copiar();
                    v.valor = b;
                    varscopia.add(v);
                } else {
                    Variable v = this.VarsGlobales.get(i).copiar();
                    varscopia.add(v);
                }
            }
            ArrayList<MetodoGraphik> metodoscopia = new ArrayList();
            for (int i = 0; i < this.Metodos.size(); i++) {
                MetodoGraphik m = this.Metodos.get(i).copiar();
                metodoscopia.add(m);
            }
            ArrayList<FuncionHaskell> haskellcopia = new ArrayList();
            for (int i = 0; i < this.incluye.size(); i++) {
                FuncionHaskell f = this.incluye.get(i).copiar();
                haskellcopia.add(f);
            }
            ArrayList<Als> importacopia = new ArrayList();
            for (int i = 0; i < this.importa.size(); i++) {
                Als a = this.importa.get(i).copiar();
                importacopia.add(a);
            }
            ArrayList<Als> heredacopia = new ArrayList();
            for (int i = 0; i < this.hereda.size(); i++) {
                Als h = this.hereda.get(i).copiar();
                heredacopia.add(h);
            }
            copia.nombre = this.nombre;
            copia.visibilidad = this.visibilidad;
            copia.VarsGlobales = varscopia;
            copia.Metodos = metodoscopia;
            copia.incluye = haskellcopia;
            copia.importa = importacopia;
            copia.hereda = heredacopia;
            return copia;
        } catch (Exception e) {
            return null;
        }
    }

    //-------------------------------------------INICIALIZANDO TODAS LAS VARIABLES POR CADA NUEVO ALS
    public void Inicializar() {

        VarsGlobales = new ArrayList();
        Metodos = new ArrayList();
        importa = new ArrayList();
        incluye = new ArrayList();
        hereda = new ArrayList();
        nombre = "";
        visibilidad = "";
    }

    public void agregarImporta(Als nuevo) {
        importa.add(nuevo);
    }

    public void agregarHereda(Als nuevo) {
        hereda.add(nuevo);
    }

    public void agregarIncluye(FuncionHaskell funHas) {
        incluye.add(funHas);
    }

    //-----------------------------------------------------------AGREGANDO UN NUEVO ALS
    public Als(String name, String Visibilidad) {
        nombre = name;
        visibilidad = Visibilidad;
    }

    //---------------------------------------------------------------VARIABLES
    public void addVarGlobal(Variable var) {

        VarsGlobales.add(var);
    }

    public ArrayList getVarGlobales() {
        return VarsGlobales;
    }

    public Boolean existeVariableG(String val) {
        if (VarsGlobales.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < VarsGlobales.size(); i++) {
                if (val.equals(VarsGlobales.get(i).nombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Variable obtenerVariableG(String nombre) {
        for (int i = 0; i < VarsGlobales.size(); i++) {
            if (nombre.equals(VarsGlobales.get(i).nombre)) {
                Variable encontrada = (Variable) VarsGlobales.get(i);
                return encontrada;
            }
        }
        return null;
    }

    //-----------------------------------------------------------------METODOS
    public void addMetodo(MetodoGraphik fun) {
        Metodos.add(fun);
    }

    public ArrayList getMetodos() {
        return Metodos;
    }

    public Boolean existeMetodo(String val) {

        if (Metodos.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < Metodos.size(); i++) {
                if (val.equals(Metodos.get(i).nombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    public MetodoGraphik obtenerMetodo(String nombre) {
        for (int i = 0; i < Metodos.size(); i++) {
            if (nombre.equals(Metodos.get(i).nombre)) {
                MetodoGraphik encontrado = (MetodoGraphik) Metodos.get(i);
                return encontrado;
            }
        }
        return null;
    }
}
