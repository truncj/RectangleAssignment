import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by jtruncale on 5/13/16.
 */
public final class Edge {

    Coordinate p1,p2;
    Double minX,maxX,minY,maxY;

    //Edge Constructor
    public Edge(Coordinate p1, Coordinate p2){
        this.p1 = p1;
        this.p2 = p2;
        this.minX = min(p1.x,p2.x);
        this.maxX = max(p1.x,p2.x);
        this.minY = min(p1.y,p2.y);
        this.maxY = max(p1.y,p2.y);
    }

    public Coordinate getP1(){
        return this.p1;
    }

    public Coordinate getP2(){
        return this.p2;
    }

    //Iterate through all the edges of r2 and check to see if any are located on an edge of r1
    public boolean edgeOnEdge(List<Edge> r2Edge){

        for (Edge myEdge:r2Edge)
        {
            if (this.getP1().pointBetweenCoords(myEdge) && this.getP2().pointBetweenCoords(myEdge)) {
                return true;
            }
        }
        return false;
    }

    //Check to see if a single edge of r2 is adjacent to an edge of r1
    public boolean isEdgeOnEdge(Edge myEdge){

        if (this.getP1().pointBetweenCoords(myEdge) && this.getP2().pointBetweenCoords(myEdge)) {
            return true;
        }
        return false;
    }

}
