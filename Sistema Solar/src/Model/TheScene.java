/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.AxisAngle4f;


class TheScene extends BranchGroup{
    TheScene(Camara camLuna, Camara camNave) {
        // Se crea la rama desde la que cuelga todo
        BranchGroup bg = new BranchGroup();
        // Se le dan permisos para poder intercambiar las figuras
        bg.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        bg.setCapability(Group.ALLOW_CHILDREN_WRITE);
        
        //Aniadir Posiciones
        Point3f[] posiciones = new Point3f[9];
        posiciones[0] = new Point3f(30f,0f,0f);
        posiciones[1]= new Point3f(15f,0f,15f);
        posiciones[2] = new Point3f(0f,0f,30f);
        posiciones[3] = new Point3f(-15f,0f,15f);
        posiciones[4] = new Point3f(-30f,0f,0f);
        posiciones[5] = new Point3f(-15f,0f,-15f);
        posiciones[6] = new Point3f(0f,0f,-30f);
        posiciones[7] = new Point3f(15f,0f,-15f);
        posiciones[8]= new Point3f(30f,0f,0f);
        
        //Aniadir Rotaciones
        Quat4f[] rotaciones =new Quat4f[9];
        for(int i=0;i<9;i++){
            rotaciones[i]=new Quat4f();
        }
        rotaciones[0].set (new AxisAngle4f (1.0f, 0.0f, 1.0f, -1.0f));
        rotaciones[1].set (new AxisAngle4f (1.0f, 0.0f, 1.0f,  1.0f));
        rotaciones[2].set (new AxisAngle4f (1.0f, 0.0f, 1.0f, -1.0f));
        rotaciones[3].set (new AxisAngle4f (1.0f, 0.0f, 1.0f,  1.0f));
        rotaciones[4].set (new AxisAngle4f (1.0f, 0.0f, 1.0f, -1.0f));
        rotaciones[5].set (new AxisAngle4f (1.0f, 0.0f, 1.0f,  1.0f));
        rotaciones[6].set (new AxisAngle4f (1.0f, 0.0f, 1.0f, -1.0f));
        rotaciones[7].set (new AxisAngle4f (1.0f, 0.0f, 1.0f,  1.0f));
        rotaciones[8].set (new AxisAngle4f (1.0f, 0.0f, 1.0f, -1.0f));
        
        //Punto de interpolación entre una posicion y otra
        float[] alphas={0.0f,0.12f,0.24f,0.36f,0.48f,0.6f,0.72f,0.84f,1f};
        
        Nave nave=new Nave("models/E-TIE-I/E-TIE-I.obj",100000,posiciones,rotaciones,alphas,camNave);
        bg.addChild(nave);
        
        Estrella sol=new Estrella();
        bg.addChild(sol);  
        
        
        Planeta mercurio=new Planeta(1.1f,22.0f,30000,195000,true,"imgs/mercurio.jpg");
        sol.addPlaneta(mercurio);
        
        Astro venus=new Planeta(2.8f,27.0f,40000,200000,true,"imgs/venus.jpg");
        sol.addPlaneta(venus);    
        
        Planeta tierra=new Planeta(3.0f,35.0f,50000,100000,true,"imgs/tierra.jpg");
        Satelite luna=new Satelite(0.7f, 4.9f, 160000/5, 100000/3, "imgs/luna.jpg");
        tierra.addSatelite(luna);
        sol.addPlaneta(tierra);
        luna.addCamara(camLuna);
        
        Planeta marte=new Planeta(1.4f,44.0f,60000,150000,true,"imgs/marte.jpg");
        Astro fobos=new Satelite(0.15f, 1.6f, 142000/5, 2000/3, "imgs/fobos.jpg");
        Astro deimos=new Satelite(0.1f, 2.0f, 153000/5, 8000/3, "imgs/deimos.jpg");
        marte.addSatelite(fobos);
        marte.addSatelite(deimos);
        sol.addPlaneta(marte);
        
        Planeta jupiter=new Planeta(8.0f,70.0f,90000,9000,true,"imgs/jupiter.jpg");
        Astro io=new Satelite(0.8f, 13.0f, 27000/5, 12000,"imgs/io.jpg");
        Astro europa= new Satelite(0.6f, 15.0f, 40000/5, 25000/3,"imgs/europa.jpg");
        Astro calisto= new Satelite(1.1f, 20.0f, 70000/5, 70000/3,"imgs/calisto.jpg");
        jupiter.addSatelite(io);
        jupiter.addSatelite(europa);
        jupiter.addSatelite(calisto);
        sol.addPlaneta(jupiter);
        
        Planeta saturno=new Planeta(7.0f,110.0f,100000,12000,true,"imgs/saturno.jpg");
        Anillo a=new Anillo(8.0f,9.5f,900,64,"imgs/anilloA.jpg");
        Anillo b=new Anillo(9.5f,12.5f,2000,64,"imgs/anilloB.jpg");
        Anillo c=new Anillo(12.5f,13.5f,1000,64,"imgs/anilloC.jpg");
        saturno.addAnillo(a);   
        saturno.addAnillo(b);
        saturno.addAnillo(c);
        sol.addPlaneta(saturno);
        
        
        Planeta urano=new Planeta(6.0f,139.0f,110000,40000,true,"imgs/urano.jpg");
        Astro titania=new Satelite(0.4f, 10.0f, 130000/5, 35000/3,"imgs/titania.jpg");
        Astro ariel= new Satelite(0.35f, 8.0f, 110000/5, 20000/3,"imgs/ariel.jpg");
        Astro miranda= new Satelite(0.2f, 7.0f, 95000/5, 10000/3,"imgs/miranda.jpg");
        urano.addSatelite(titania);
        urano.addSatelite(ariel);
        urano.addSatelite(miranda);
        sol.addPlaneta(urano);
        
        Planeta neptuno=new Planeta(5.8f,160.0f,115000,60000,true,"imgs/neptuno.jpg");
        Astro triton= new Satelite(0.9f, 9.6f, 115000/5, -30000/3,"imgs/triton.jpg");
        neptuno.addSatelite(triton);
        sol.addPlaneta(neptuno);

        // Se cuelga rotación de la escena
        this.addChild(bg);
    }
    
}
