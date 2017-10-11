/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.behaviors.mouse.MouseBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


public class Camara extends BranchGroup{
    Canvas3D canvas;
    ViewPlatform viewPlatform;
    TransformGroup viewTransformGroup;
    Point3d posicion,pMira;
    Vector3d up;
    double escang,distProx,distFin;
    View view;
    public Camara(Canvas3D canv,boolean movRaton, boolean paralela,Point3d pos,Point3d pM,Vector3d u,
            double esc,double distP,double distF){
        
        canvas=canv;
        posicion=pos;
        pMira=pM;
        up=u;
        escang=esc;
        distProx=distP;
        distFin=distF;
        
        // TransformGroup para posicionar y orientarla vista
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (posicion, pMira, up);
        viewTransform3D.invert();
        
        viewTransformGroup=new TransformGroup(viewTransform3D);
        viewTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        viewPlatform = new ViewPlatform();
        //viewPlatform.setActivationRadius(200f);
        viewTransformGroup.addChild(viewPlatform);


        if(movRaton){
            // El comportamiento, para mover la camara con el raton
            MouseRotate rotacion = new MouseRotate(MouseRotate.INVERT_INPUT);
            rotacion.setTransformGroup(viewTransformGroup);
            rotacion.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));
            rotacion.setFactor(0.005,0.005);            
            viewTransformGroup.addChild(rotacion);
            
            MouseTranslate traslacion = new MouseTranslate(MouseTranslate.INVERT_INPUT);
            traslacion.setTransformGroup(viewTransformGroup);
            traslacion.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));
            traslacion.setFactor(0.2,0.2);
            viewTransformGroup.addChild(traslacion);
            
            MouseWheelZoom zoom = new MouseWheelZoom(MouseWheelZoom.INVERT_INPUT);
            zoom.setTransformGroup(viewTransformGroup);
            zoom.setSchedulingBounds(new BoundingSphere(new Point3d(), 200.0));
            zoom.setFactor(1.5);
            viewTransformGroup.addChild(zoom);
        }
        
        view = new View();
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment(new PhysicalEnvironment());
        if(paralela){
            view.setProjectionPolicy(View.PARALLEL_PROJECTION);
            view.setScreenScalePolicy(View.SCALE_EXPLICIT);
            view.setScreenScale(escang);
            view.setFrontClipDistance (distProx);
            view.setBackClipDistance (distFin);
        
        }
        
        else{
            view.setProjectionPolicy (View.PERSPECTIVE_PROJECTION);
            view.setFieldOfView (escang);
            view.setFrontClipDistance (distProx);
            view.setBackClipDistance (distFin);
        }
        //Posicionar la vista
        view.attachViewPlatform(viewPlatform);
        this.addChild(viewTransformGroup);
    }
    
    public void activar() {
        view.addCanvas3D(canvas);
    }
    
    public void desactivar() {
        view.removeCanvas3D(canvas);
    }
}
