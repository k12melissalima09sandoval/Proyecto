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
import Analizadores.Errores;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graficar extends JFrame {

    public static Graficar graficar = null;
    public static XYSeriesCollection grafica;
    public static JFreeChart graf;
    public static ChartFrame frame;
    public static String titulo;
    static int contador = 1;

    public Graficar(String titulo) {
        super(titulo);
        grafica = new XYSeriesCollection();
    }

    public static void Inicializar() {
        grafica = new XYSeriesCollection();
        graf = ChartFactory.createXYLineChart("Compiladores 2, Melissa Lima", "Eje X", "Eje Y",
                grafica, PlotOrientation.VERTICAL, true, true, false);
        frame = new ChartFrame(titulo, graf);
    }

    public static void graficarL(ArrayList x, ArrayList y, String titulo) {
        final XYSeries serie = new XYSeries(titulo+contador);
        if (!x.isEmpty() && !y.isEmpty()) {

            if (x.size() == y.size()) {
                for (int i = 0; i < x.size(); i++) {
                    serie.add(Integer.parseInt(x.get(i).toString()), Integer.parseInt(y.get(i).toString()));
                }
                grafica.addSeries(serie);
            } else {
                Errores.ErrorSemantico("No se puede graficar porque las dimensiones en el ejeX y ejeY"
                        + " no coinciden", 0, 0);

            }
        }
        contador++;
        frame.pack();
        frame.setVisible(true);
    }

    public void mostrar() {
        graf = ChartFactory.createXYLineChart("Compiladores 2, Melissa Lima", "Eje X", "Eje Y",
                grafica, PlotOrientation.VERTICAL, true, true, false);
        //ChartFrame frame = new ChartFrame(titulo, graf);

        frame.pack();
        frame.setVisible(true);
    }
}
