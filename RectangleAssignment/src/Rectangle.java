
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtruncale on 5/13/16.
 */
public final class Rectangle {

    /** Overview
    * A rectangle is defined as by a lower left coordinate and upper right coordinate
    * ie. (0,0,1,1) would be a rectangle on the origin and an upper right coordinate of 1,1
    */

    private double x1, y1, x2, y2;
    private Coordinate leftBottom,leftTop,rightBottom,rightTop;
    private Edge leftEdge, rightEdge, topEdge, bottomEdge;

    // Rectangle Constructor
    public Rectangle( double x1, double y1, double x2, double y2 ){

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.leftBottom = new Coordinate(x1,y1);
        this.leftTop = new Coordinate(x1,y2);
        this.rightBottom = new Coordinate(x2,y1);
        this.rightTop = new Coordinate(x2,y2);

        this.leftEdge = new Edge(leftBottom,leftTop);
        this.rightEdge = new Edge(rightBottom,rightTop);
        this.topEdge = new Edge(leftTop,rightTop);
        this.bottomEdge = new Edge(leftBottom,rightBottom);
    }

    public List<Coordinate> getCoordinates(){

        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(this.leftBottom);
        coordinates.add(this.rightTop);

        return coordinates;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(this.getCoordinates());
        return output.toString();
    }

     /** Scenario Algorithms
     * getIntersect() - Check if the two rectangles overlap, and if so, get the coordinates of the interesection
     * isContained() - Check if Rectangle2 exists within the bounds of Rectangle1
     * isAdjacent()  - Check if Rectangle2 is adjacent (externally) to Rectangle1
     * */

    public List<Coordinate> getIntersect(Rectangle r2){

        List<Coordinate> intersections = new ArrayList<Coordinate>();

        //A rectangle cannot be intersecting if it is adjacent
        //Then check isInternallyAdjacent() to check if the the rectangles
        //are either contained or internally adjacent and therefore not intersecting

        if(this.isAdjacent(r2) || this.isInternallyAdjacent(r2)){
            return intersections;
        }

        double leftBottomX = max(this.x1,r2.x1);
        double leftBottomY = max(this.y1,r2.y1);
        double rightTopX = min(this.x2,r2.x2);
        double rightTopY = min(this.y2,r2.y2);

        //Check if the rectangles cannot overlap
        if(leftBottomX > rightTopX || leftBottomY > rightTopY)
            return intersections;

        //The overlapping rectangles form a 3rd rectangle r3
        Rectangle r3 = new Rectangle(leftBottomX,leftBottomY,rightTopX,rightTopY);

        List<Coordinate> r3Coords = new ArrayList<Coordinate>();

        r3Coords.add(r3.leftBottom);
        r3Coords.add(r3.leftTop);
        r3Coords.add(r3.rightBottom);
        r3Coords.add(r3.rightTop);

        //Iterates through the coordinates of the intersected rectangle (r3)
        //and check to see which of the coordinates exist on the edges of both
        //rectangles r1 and r2
        for (Coordinate point:r3Coords) {
            if (this.onEdge(point) && r2.onEdge(point)) {
                intersections.add(point);
            }
        }

        return intersections;
    }

    public boolean isContained(Rectangle r2){

        double x1 = this.x1;
        double y1 = this.y1;
        double x2 = this.x2;
        double y2 = this.y2;

        double x3 = r2.x1;
        double y3 = r2.y1;
        double x4 = r2.x2;
        double y4 = r2.y2;

        //Check if the coordinates of Rectangle B are contained with the coordinates of Rectangle A
        if((x3 > x1 && y3 > y1) && (x4 < x2 && y4 < y2)){
            return true;
        }
        return false;
    }

    public boolean isInternallyAdjacent(Rectangle r2) {

        double x1 = this.x1;
        double y1 = this.y1;
        double x2 = this.x2;
        double y2 = this.y2;

        double x3 = r2.x1;
        double y3 = r2.y1;
        double x4 = r2.x2;
        double y4 = r2.y2;

        //Variation of isContained function (ie. changed to "less/greater than or EQUAL to")
        // that we use to help getIntersection() exclude rectangles that are internally adjacent
        if ((x3 >= x1 && y3 >= y1) && (x4 <= x2 && y4 <= y2))
            return true;
        else
            return false;
    }

    public boolean isAdjacent(Rectangle r2){
        /**
         * if two coordinates of a rectangle are located on the same edge of a second
         * rectangle, those two rectangles are considered adjacent
         */

        // If a rectangle is contained within the other rectangle, they cannot be adjacent
        // and in that case will return false
        if (this.isContained(r2)){
            return false;
        }

        // Creates a list of edges of Rectangle A (rEdges) and Rectangle B (r2Edges)
        List<Edge> rEdges = getEdges(this);

        List<Edge> r2Edges = getEdges(r2);


        // If the adjacent sides are the same side, they must be inscribed in one another
        // and therefore not externally adjacent
       if (this.topEdge.isEdgeOnEdge(r2.topEdge)){
            return false;
        }else if (this.bottomEdge.isEdgeOnEdge(r2.bottomEdge)){
            return false;
        }else if (this.leftEdge.isEdgeOnEdge(r2.leftEdge)){
            return false;
        }else if (this.rightEdge.isEdgeOnEdge(r2.rightEdge)){
            return false;
        }

        // Iterates through Rectangle A's edges and checks to see if the Rectangle B
        // has an edge that is a subset or equal
        for (Edge myEdge:rEdges) {

            if (myEdge.edgeOnEdge(r2Edges)) {
                return true;
            }
        }

        /**
         * Currently the requirement is to check for adjacency when Rectangle A's edge is larger or equal to
         * the size of Rectangle B's edge. If there is a requirement to check for adjacency when Rectangle B's
         * edge is larger than Rectangle A's edge, you can uncomment the code below
         */

        /*for (Edge myEdge:r2Edges) {

            if (myEdge.edgeOnEdge(rEdges)) {
                return true;
            }
        }*/

        return false;
    }

    //Function to return an array of edges for a specified rectangle
    public List<Edge> getEdges(Rectangle r){

        List<Edge> rEdges = new ArrayList<Edge>();

        rEdges.add(r.leftEdge);
        rEdges.add(r.rightEdge);
        rEdges.add(r.topEdge);
        rEdges.add(r.bottomEdge);

        return rEdges;
    }

    //Get the edges of the rectangle and check to see if
    //the point exists between the coordinates of an edge
    public boolean onEdge(Coordinate point){

        List<Edge> rEdges = getEdges(this);

        for (Edge myEdge:rEdges)
        {
            if (point.pointBetweenCoords(myEdge)) {
                return true;
            }
        }
        return false;
    }



}
