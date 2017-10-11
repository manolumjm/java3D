/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javafx.scene.effect.Light;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Locale;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.VirtualUniverse;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


public class SistemaSolar {
    // Atributos de relación
    private TheBackground background;
    private TheLights lights;
    private TheScene scene;
    //private TheAxes axes;
    private Pick pick;
    private VirtualUniverse universe;
    private Camara camFija,camPers,camLuna,camNave;
    private Locale root;
    public SistemaSolar(Canvas3D canvasF,Canvas3D canvasV) {
    // Todo cuelga de un nodo raiz
    universe=new VirtualUniverse();
    root = new Locale(universe);
    
    // Se crea y se añade el fondo
    background = new TheBackground();
    root.addBranchGraph(background);

    // Se crean las luces y se añaden
    lights = new TheLights();
    root.addBranchGraph(lights);
    
    camFija= new Camara(canvasF,false,true,new Point3d (0,200,0), new Point3d (0,0,0), new Vector3d (0,0,-1),Math.toRadians(0.08),0.2,100.0); 
    camFija.compile();
    root.addBranchGraph(camFija);
    camFija.activar();
    
    camPers= new Camara(canvasV,true,false,new Point3d (80,80,80), new Point3d (0,0,0), new Vector3d (0,1,0),Math.toRadians(50),1.0,60.0); 
    camPers.compile();
    root.addBranchGraph(camPers);
    camPers.activar();
    
    camLuna= new Camara(canvasV,false,false,new Point3d (4.9f,0,0), new Point3d (-0.8,0,0), new Vector3d (0,1,0),Math.toRadians(90), 0.01, 100.0);
    //camLuna.compile();
    
    camNave= new Camara(canvasV,false,false,new Point3d (0,0,0), new Point3d (-1.0,0,0), new Vector3d (0,1,0),Math.toRadians(50),1.0,60.0);
    //camNave.compile();
    
    // Se crea y se añade la escena al universo
    scene = new TheScene(camLuna,camNave); 
    pick= new Pick(canvasF);
    pick.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0), 200.0));
    scene.addChild(pick);
    scene.compile();
    root.addBranchGraph(scene);
    
    pick.initSearch(scene);
    }
    
    // ******* Public
  
    public void activarCamPers(){
        camPers.activar();
    }
    public void activarCamNave(){
        camNave.activar();
    }
    public void activarCamLuna(){
        camLuna.activar();
    }
    public void desactivarCamPers(){
        camPers.desactivar();
    }
    public void desactivarCamNave(){
        camNave.desactivar();
    }
    
    public void desactivarCamLuna(){
        camLuna.desactivar();
    }
    public void closeApp (int code) {
      System.exit (code);
    }
}
