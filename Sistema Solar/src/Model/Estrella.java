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
import static javax.management.Query.value;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.SpotLight;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;


public class Estrella extends Astro{

    public Estrella(){

        
        this.setCapability(Node.ENABLE_PICK_REPORTING);

        //Crear textura y material
        Appearance ap=new Appearance();
        Texture aTexture = new TextureLoader("imgs/sol.jpg", null).getTexture();
        ap.setTexture(aTexture);
        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        ap.setTextureAttributes(ta);
        ap.setMaterial(new Material (
              new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
              new Color3f (1.00f,1.00f, 1.00f),   // Color emisivo
              new Color3f (1.0f, 1.0f, 1.0f),   // Color difuso
              new Color3f (1.0f, 1.0f, 1.0f),   // Color especular
              17.0f ));

        //Se crea la rotacion
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        TransformGroup transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        transform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        setValue(new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                100000, 0, 0, 0, 0, 0));
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotationInterpolator (getValue(), transform, yAxis,
            0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 200.0));
        rotator.setEnable(true);
        // Se cuelga del grupo de transformación y este se devuelve
        transform.addChild(rotator);
        //Se crea la escena
        // Se crea la rama desde la que cuelga todo
        BranchGroup bg = new BranchGroup ();

        // Se le dan permisos para poder intercambiar las figuras
        bg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        bg.setCapability(Group.ALLOW_CHILDREN_WRITE);

        // Y le ponemos una figura
         // Lo mismo para una esfera
        bg.addChild(new Sphere (14.0f, 
        Primitive.GENERATE_NORMALS | 
        Primitive.GENERATE_TEXTURE_COORDS |
        Primitive.ENABLE_APPEARANCE_MODIFY, 64, 
        ap));
        transform.addChild(bg);
        this.addChild(transform);
        
       
        
    }
    public void addPlaneta(Astro p){
        this.addChild(p);
    }

    public void activarRotacion() {
        rotator.setEnable(true);
    }

    public void desactivarRotacion() {
        rotator.setEnable(false);
    }
}
