import java.util.List;

/**
 * Created by jtruncale on 5/13/16.
 */
public final class Coordinate {

    double x,y;

    //Coordinate Constructor
    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("(").append(x).append(",").append(y).append(")");
        return output.toString();
    }

    //Check if a coordinate exists between the two points that define an edge
    public boolean pointBetweenCoords(Edge myEdge){

        double x = this.getX();
        double y = this.getY();
        double x1 = myEdge.minX;
        double y1 = myEdge.minY;
        double x2 = myEdge.maxX;
        double y2 = myEdge.maxY;

        if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
            return true;
        }else
            return false;

    }
 }
