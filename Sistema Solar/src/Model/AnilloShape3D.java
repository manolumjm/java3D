/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;


public class AnilloShape3D extends Shape3D{ 
    public AnilloShape3D(float radioInterior, float radioExterior, int res,String t){

        ArrayList<Point3f> vertices=new ArrayList<Point3f>();
        int[] indicesVertices;  
        this.setPickable(false);
        
        Transform3D trans = new Transform3D() ;
        
        Appearance appearance = new Appearance();

        for (int i=0;i<res;i++){
            Point3f puntoE = new Point3f(radioExterior, 0, 0) ;
            double ang = ((Math.PI*2.0f) / res) * i ;
            trans.rotY(ang) ;
            trans.transform(puntoE) ;
            vertices.add(puntoE) ;
        }
        
        trans = new Transform3D() ;
        
        for (int i=0;i<res;i++){
            Point3f puntoI = new Point3f(radioInterior, 0, 0) ;
            double ang = ((Math.PI*2.0f) / res) * i ;
            trans.rotY(ang) ;
            trans.transform(puntoI) ;
            vertices.add(puntoI) ;
        }
        
        indicesVertices=new int[res*12];
        for (int i = 0 ; i < res ; i++){
            indicesVertices[i*12] = i ;
            indicesVertices[i*12+1] = (i+1)%res ;
            indicesVertices[i*12+2] = (i+res) ;
            indicesVertices[i*12+3] = ((i+1)%res)+res ;
            indicesVertices[i*12+4] = i+res ;
            indicesVertices[i*12+5] = (i+1)%res ;
            
            indicesVertices[i*12+6] = (i+1)%res ;
            indicesVertices[i*12+7] = i+res ;
            indicesVertices[i*12+8] = ((i+1)%res)+res ;
            indicesVertices[i*12+9] = i ;
            indicesVertices[i*12+10] = i+res ;
            indicesVertices[i*12+11] = (i+1)%res ;
        }
        
        Point3f[] vectorvertices = new Point3f[res*2] ;
        Vector3f[] vectornormales = new Vector3f[res*2] ;
        
        for (int i = 0 ; i < res*2 ; i++){
            vectornormales[i] = new Vector3f(0.0f, 1.0f, 0.0f) ;
        }
        for (int i = 0 ; i < res*2 ; i++){
            vectornormales[i] = new Vector3f(0.0f, -1.0f, 0.0f) ;
        }
        IndexedGeometryArray iga = new IndexedTriangleArray(res*2, GeometryArray.COORDINATES | GeometryArray.NORMALS, res*12) ;
        
        iga.setCoordinates(0, vertices.toArray(vectorvertices)) ;
        iga.setCoordinateIndices(0, indicesVertices) ;
        iga.setNormals(0, vectornormales) ;
        iga.setNormalIndices(0, indicesVertices) ;
        
        TexCoordGeneration generadortexturas = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_2,
                new Vector4f(1/(2*radioExterior), 0.0f, 1/(2*radioInterior), 0), new Vector4f(0.0f, 0.0f, 1/(2*radioExterior),0)) ; 
        appearance.setTexCoordGeneration(generadortexturas);
        Texture aTexture = new TextureLoader (t, null).getTexture();
        appearance.setTexture(aTexture);
        TextureAttributes ta = new TextureAttributes();
            ta.setTextureMode(TextureAttributes.MODULATE);
        appearance.setTextureAttributes(ta);
        appearance.setMaterial(new Material (
              new Color3f (0.20f, 0.20f, 0.20f),   // Color ambiental
              new Color3f (0.10f, 0.10f, 0.10f),   // Color emisivo
              new Color3f (0.50f, 0.50f, 0.50f),   // Color difuso
              new Color3f (0.70f, 0.70f, 0.70f),   // Color especular
              17.0f ));
        this.setGeometry(iga);
        this.setAppearance(appearance) ;
    }
    
}
