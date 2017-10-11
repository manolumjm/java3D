/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;


public class Anillo extends BranchGroup{
    private float radioInterior;
    private float radioExterior;
    private int vel_rot;
    private RotationInterpolator rotator;
    private ArrayList<Point3f> vertices=new ArrayList<Point3f>();
    public Anillo(float rI, float rE, int vr, int res,String t){
        radioInterior=rI;
        radioExterior=rE;
        vel_rot=vr;
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
        Alpha v=new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                vel_rot, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotationInterpolator (v, transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
       
        transform.addChild(new AnilloShape3D(radioInterior,radioExterior,res,t));
        this.addChild(transform);
    }
}
