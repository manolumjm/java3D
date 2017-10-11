/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;


public class Planeta extends Astro{
    private float radio_orb;
    private int vel_tras;
    private TransformGroup transform,torbita,trorbita;
    RotationInterpolator rotatoreje;
    public Planeta(float r, float ro, int vt, int vr, boolean rot, String t){
        setRadio(r);
        radio_orb=ro;
        vel_tras=vt;
        setVelocidad(vr);
        rotacion=rot;
        setTextura(t);
        
        this.setCapability(Node.ENABLE_PICK_REPORTING);
        
        
        Appearance ap=new Appearance();
        Texture aTexture = new TextureLoader (t, null).getTexture();
        ap.setTexture(aTexture);
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
        ap.setMaterial(new Material (
              new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
              new Color3f (0.10f, 0.10f, 0.10f),   // Color emisivo
              new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
              new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
              17.0f ));
        //Se crea la rotacion
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        setValue(new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                getVelocidad(), 0, 0, 0, 0, 0));
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotatoreje = new RotationInterpolator (getValue(), transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotatoreje.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0));
        rotatoreje.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotatoreje);

        //Se mueve el planeta a la orbita
        torbita = new TransformGroup ();
        torbita.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D tra=new Transform3D();
        tra.setTranslation(new Vector3f(radio_orb,0,0));
        torbita.setTransform(tra);
        
        trorbita = new TransformGroup ();
        trorbita.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        setValue(new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                vel_tras, 0, 0, 0, 0, 0));
        Transform3D yAxis2=new Transform3D();
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotationInterpolator (getValue(), trorbita, yAxis2,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        trorbita.addChild(rotator);
        
        //Se crea la escena
        // Se crea la rama desde la que cuelga todo
        BranchGroup bg = new BranchGroup ();

        // Y le ponemos una figura
         // Lo mismo para una esfera
        bg.addChild(new Sphere (getRadio(), 
        Primitive.GENERATE_NORMALS | 
        Primitive.GENERATE_TEXTURE_COORDS |
        Primitive.ENABLE_APPEARANCE_MODIFY, 64, 
        ap));
        transform.addChild(bg);
        torbita.addChild(transform);
        trorbita.addChild(torbita);
        this.addChild(trorbita);
        
    }
    public void setRadioOrb(float r){
        radio_orb=r;
    }
    public float getRadioOrb(){
        return radio_orb;
    }
    public void setVelocidadTras(int v){
        vel_tras=v;
    }
    public int getVelocidadTras(){
        return vel_tras;
    }

    public void addSatelite(Astro s){
        torbita.addChild(s);
    }
    public void addAnillo(Anillo a){
        torbita.addChild(a);
    }
    
    public void activarRotacion() {
        rotatoreje.setEnable(true);
    }

    public void desactivarRotacion() {
        rotatoreje.setEnable(false);
    }
    

}
