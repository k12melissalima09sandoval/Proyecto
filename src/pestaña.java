/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.ArrayList;
import javax.swing.JEditorPane;

public class pestaña {
    
    public JEditorPane textPane;
    public String nombre;
    public File archivo;
    public File archivoTexto;
    public int esta_guardado = 0;
    public int esta_guardadoTexto = 0;
    /* bndera que toma 1 si al crear un nuevo archivo hay uno abierto actualmente */
    public int abierto = 0;
    public int abiertoTexto = 0;
    
    public ArrayList<String> texto = new ArrayList();
    
    
    public void marcar(){
    
        textPane.setDebugGraphicsOptions(10);
    }
    
    public String getTextoLinea(int i){
    
      
            String text []= textPane.getText().split("\n");
            
            if(text.length>i){
                return text[i];
            }
        
        
            return "";
    }
}
