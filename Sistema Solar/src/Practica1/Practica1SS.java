/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import GUI.ControlWindow;
import Model.SistemaSolar;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

public class Practica1SS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
        Canvas3D canvasPlantaFija = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        Canvas3D canvasVariable = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        // Se le da el tamaño deseado
        canvasVariable.setSize(760, 710);
        canvasPlantaFija.setSize(610, 710);
        // Se crea el Sistema Solar, incluye una vista para ese canvas
        SistemaSolar universe = new SistemaSolar(canvasPlantaFija,canvasVariable);
        // Se crea la GUI a partir del Canvas3D y del Universo
        ControlWindow controlWindow = new ControlWindow (canvasPlantaFija, canvasVariable, universe);
        // Se muestra la ventana principal de la aplicación
        controlWindow.showWindow ();
    }
    
}
