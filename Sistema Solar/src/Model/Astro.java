/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;


public abstract class Astro extends BranchGroup{
    private float radio;
    private int vel_rot;
    private String textura;
    RotationInterpolator rotator;
    private Alpha value;
    boolean rotacion=true;
    public void setRadio(float r){
        radio=r;
    }
    public float getRadio(){
        return radio;
    }
    
    public void setVelocidad(int v){
        vel_rot=v;
    }
    public int getVelocidad(){
        return vel_rot;
    }
    public void setTextura(String text){
        textura=text;
    }
    public String getTextura(){
        return textura;
    }
    
    public Alpha getValue(){
        return value;
    }
    
    public void setValue(Alpha v){
        value=v;
    }
    
    public boolean getRotacion(){
        return rotacion;
    }
    
    public void setRotacion(boolean r){
        rotacion=r;
    }
    
    public abstract void activarRotacion();
    public abstract void desactivarRotacion();
   
}
