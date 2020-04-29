package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Astargraph {
    public ArrayList<Vertex> vertices;
    public Astargraph() {
        vertices=new ArrayList<Vertex>();
    }
    public void addvertex(Vertex v) {
        vertices.add(v);
    }
    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2,dist);
        v2.addOutEdge(v1,dist);
    }
    public boolean A_Star(Vertex start, Vertex destination)
    {   if (start==null || destination==null)
        return false;
        PriorityQueue<Vertex> Openlist = new PriorityQueue<Vertex>();
        ArrayList<Vertex> Closedlist = new ArrayList<Vertex>();
        Openlist.offer(start);
        Vertex Current;
        ArrayList<Vertex> CurrentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("choose number 1 or 2:");
        //int number = scanner.nextInt();
        for (int i =0; i<vertices.size();i++)
        {
            vertices.get(i).seth(Euclidean(vertices.get(i), destination));
            vertices.get(i).seth(Manhattan(vertices.get(i), destination));

        }
        Openlist.remove(start);
        start.setg(0.0);
        start.calculatef();
        Openlist.offer(start);
        //Start algorithm
        System.out.println("Start Algorithm");

        while (!Openlist.isEmpty()){
            Current = Openlist.remove();
            if (Current.equals(destination)){
                return true;
            }
            Closedlist.add(Current);
            CurrentNeighbors = Current.getNeighbours();
            for (int i = 0; i <CurrentNeighbors.size() ; i++) {
                Neighbor = Current.getNeighbours().get(i);

                double tempgofv = Current.getg() + Current.getNeighbourDistance().get(i);

                if (tempgofv<Neighbor.getg()){
                    Neighbor.setPrev(Current);
                    Neighbor.setg(tempgofv);
                    Neighbor.calculatef();


                    if (!Closedlist.contains(Neighbor) && !Openlist.contains(Neighbor)){
                        Openlist.offer(Neighbor);
                    }
                    else if (Closedlist.contains(Neighbor)&& Openlist.contains(Neighbor)){
                        Openlist.remove(Neighbor);
                        Openlist.offer(Neighbor);
                    }
                }


            }
        }


        return false;
    }
    public Double Manhattan(Vertex from,Vertex goal){
        double x = Math.abs(goal.getx()-from.getx());
        double y = Math.abs(goal.gety()-from.gety());
        double z = (x+y);
        return z;
    }


    public Double Euclidean( Vertex from,Vertex to){
        double x = Math.abs(to.getx()-from.getx());
        double y = Math.abs(to.gety()-from.gety());
        double z = Math.sqrt((x*x)+(y*y));
        return z;
    }
}

class Vertex implements Comparable<Vertex>{
    private String id;
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>();
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>();
    private Double f;
    private Double g;
    private Double h;
    private Integer x;
    private Integer y;
    private Vertex prev =null;
    public Vertex(String id, int x_cor,int y_cor){
        this.id=id;
        this.x=x_cor;
        this.y = y_cor;
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        h=0.0;
    }
    public void addOutEdge(Vertex toV, Double dist){
        Neighbours.add(toV);
        NeighbourDistance.add(dist);
    }
    public ArrayList<Vertex> getNeighbours(){
        return Neighbours;
    }
    public ArrayList<Double> getNeighbourDistance(){
        return NeighbourDistance;
    }
    public String getid(){ return id;};
    public Integer getx(){ return x; }
    public Integer gety(){return y; }
    public Double getf() { return f; }
    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }
    public Double geth(){ return h; }
    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}
    public void printVertex(){
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex o) {

        calculatef();
        if (this.getf() > o.getf())
            return 1;
        if (this.getf() < o.getf())
            return -1;
        return 0;
    }



}
