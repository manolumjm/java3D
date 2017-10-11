/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.File;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;


public class Nave extends BranchGroup{
    private int velocidad;
    private RotPosPathInterpolator rotator;
    private Point3f[] posiciones;
    private Quat4f[] rotaciones;
    private float[] alphas;
    private String nombre;

    public Nave(String n,int v, Point3f[] p, Quat4f[] rot, float[] a, Camara camN){
        nombre=n;
        velocidad=v;
        rotaciones=rot;
        posiciones=p;
        alphas=a;
        
        this.setPickable(false);
        
        //Se crea la rotacion
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        Alpha value=new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                velocidad, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotPosPathInterpolator(value, transform, yAxis,alphas, rotaciones, posiciones);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        
        /*
      Para incluir un modelo .obj en una escena se puede usar el siguiente código. 
      Se asume que ya se tiene creado el nodo, de nombre "nodo", de donde se quiere colgar el modelo importado
    */
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
          modelo = archivo.load(nombre);
        } catch (FileNotFoundException e) {
          System.err.println (e);
          System.exit(1);
        } catch (ParsingErrorException e) {
          System.err.println (e);
          System.exit(1);
        } catch (IncorrectFormatException e) {
          System.err.println (e);
          System.exit(1);
        }
        transform.addChild ( modelo.getSceneGroup() );
        
        transform.addChild(camN);
        
        this.addChild(transform);
        
    }
    
}
