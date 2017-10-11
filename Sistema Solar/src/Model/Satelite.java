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
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;


public class Satelite extends Astro{
    private int vel_tras;
    private float radio_tras;
    private TransformGroup transform,trorbita;
    RotationInterpolator rotatoreje;
    public Satelite(float r, float rt, int vt, int vr, String t){
        setRadio(r);
        radio_tras=rt;
        vel_tras=vt;
        setVelocidad(vr);
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
              new Color3f (0.20f, 0.20f, 0.20f),   // Color emisivo
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

        //Se mueve el planeta a la orbita del satelite
        TransformGroup torbita = new TransformGroup ();
        Transform3D tra=new Transform3D();
        tra.setTranslation(new Vector3f(radio_tras,0,0));
        torbita.setTransform(tra);
        
        //Nos movemos del eje de coordenadas a la distacia planeta-satelite y aplicamos la rotacion
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

        // Se le dan permisos para poder intercambiar las figuras
        bg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        bg.setCapability(Group.ALLOW_CHILDREN_WRITE);

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
    public void addCamara(Camara camL){
        trorbita.addChild(camL);
    }
    
    public void activarRotacion() {
        rotatoreje.setEnable(true);
    }

    public void desactivarRotacion() {
        rotatoreje.setEnable(false);
    }
}
