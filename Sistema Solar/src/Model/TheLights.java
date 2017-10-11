/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingLeaf;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

class TheLights extends BranchGroup{

    TheLights() {
        Light aLight;
        PointLight luzSol;
        
        luzSol= new PointLight(new Color3f(1.0f,1.0f,1.0f),new Point3f(0.0f,0.0f,0.0f)
        ,new Point3f(1.0f,0.0f,0.0f));
        luzSol.setInfluencingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 200.0));
        luzSol.setCapability(PointLight.ALLOW_STATE_WRITE);
        luzSol.setEnable(true);
        this.addChild(luzSol);
        
        // Se crea la luz ambiente
        aLight = new AmbientLight(new Color3f (0.2f, 0.2f, 0.2f));
        aLight.setInfluencingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 500.0));
        aLight.setEnable(true);
        this.addChild(aLight);
    }
    
}
