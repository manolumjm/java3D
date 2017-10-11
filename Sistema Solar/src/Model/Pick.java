/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.Shape3D;
import javax.media.j3d.WakeupOnAWTEvent;


public class Pick extends Behavior {
    private WakeupOnAWTEvent condition ;
    private PickCanvas pickCanvas ;
    private Canvas3D canvas ;
    public Pick (Canvas3D aCanvas ) {
    canvas = aCanvas ;
    condition = new WakeupOnAWTEvent (MouseEvent .MOUSE_CLICKED) ;
    }
    public void initSearch (BranchGroup bg ) {
    pickCanvas = new PickCanvas ( canvas , bg ) ;
    pickCanvas.setTolerance(0.0f) ;
    pickCanvas.setMode ( PickInfo .PICK_GEOMETRY) ;
    pickCanvas.setFlags ( PickInfo .SCENEGRAPHPATH | PickInfo .CLOSEST_INTERSECTION_POINT) ;
    setEnable ( true ) ;
    }
    @Override
    public void initialize(){
        setEnable(false);
        wakeupOn(condition);
    }
    @Override
    public void processStimulus ( Enumeration cond ) {
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent [] e = c.getAWTEvent();
        MouseEvent mouse = (MouseEvent ) e [0] ;
        pickCanvas.setShapeLocation (mouse) ;
        PickInfo pi = pickCanvas.pickClosest();
        if(pi!=null){
            SceneGraphPath sgp = pi.getSceneGraphPath();//Devuelve el camino de nodos del grafo
            Astro astro = (Astro)sgp.getNode(sgp.nodeCount()-2);//nodeCount es el numero de nodos del camino
            if(astro.getRotacion()==false){
                astro.activarRotacion();
                astro.setRotacion(true);
            }
            else{
                astro.desactivarRotacion();
                astro.setRotacion(false);
            }
        }
        wakeupOn ( condition ) ;
    }
}
