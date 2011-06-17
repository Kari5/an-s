/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ambient_network_simulation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karcsi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Property a=new Property("tibi", 50, 60);
//        Property b=new Property("kari", 40, 70);
//        Property c=new Property("csaba", 30, 60);
//        Property d=new Property("kari", 40, 50);
//        Property e=new Property("tibi", 50, 50);
//        Property f=new Property("csaba", 40, 70);
//
//        List<Property> pla=new ArrayList<Property>();
//        List<Property> plb=new ArrayList<Property>();
//
//        pla.add(a);
//        pla.add(b);
//        pla.add(c);
//
//        plb.add(d);
//        plb.add(e);
//        plb.add(f);
//
//        Policy x=new Policy(pla);
//        Policy y=new Policy(plb);
//
//        x.checkPolicy(y);
//        System.out.println("---------------------------");
//        y.checkPolicy(x);
//        System.out.println("---------------------------");
//        x.evaluatePolicy(y);
//        System.out.println("---------------------------");
        

        Vertice v1=new Vertice(null);
        Vertice v2=new Vertice(null);
        Vertice v3=new Vertice(null);
        Vertice v4=new Vertice(null);
        Vertice v5=new Vertice(null);

        Edge e1= new Edge(v1, v5);
        Edge e2= new Edge(v2, v5);
        Edge e3= new Edge(v3, v5);
        Edge e4= new Edge(v4, v5);
        Edge e5= new Edge(v1, v3);
        Edge e6= new Edge(v3, v2);

        List<Edge> el= new ArrayList<Edge>();
        el.add(e1);
        el.add(e2);
        el.add(e3);
        el.add(e4);
        el.add(e5);
        el.add(e6);

        List<Vertice> vl=new ArrayList<Vertice>();
        vl.add(v1);
        vl.add(v2);
        vl.add(v3);
        vl.add(v4);
        vl.add(v5);

        System. out.println("-------------------------------------------");
        Graph g= new Graph(vl, el);
        e1.counterplus(1);
        System. out.println("1-------------------------------------------");
        g.unio(v1, v3);
        System. out.println("2-------------------------------------------");
        g.unio(v4, v5);
        System. out.println("3-------------------------------------------");
        g.removeVertice(v2);


        
        
       

        // TODO code application logic here
    }

}
