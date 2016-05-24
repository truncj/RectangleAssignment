import java.util.List;

/**
 * Created by jtruncale on 5/13/16.
 */
public class Main {


    public static void main(String[] args) {

        /**
         * JUnit Tests for the 3 methods (testIntersect,testContains,testAdjacent)
         * The evaluation method accepts parameters as follows:
         *      evaluation(
         *          Rectangle r1,              - Rectangle 1
         *          Rectangle r2,              - Rectangle 2
         *          double intersectParam,     - The number of expected intersections between the rectangles
         *          boolean containsParam,     - If the rectangle will be contained in the other
         *          boolean adjacencyParam     - If the rectangle will be adjacent to the other
         *       )
         *
         *       Asserts need "-ea" to be added the VM arguments
         */

        try {

        /* Intersection Coordinates */
            Rectangle r1 = new Rectangle(0, 0, 2, 1);
            Rectangle r2 = new Rectangle(1, 0.5, 3, 1.5);

            evaluation(r1, r2, 2, false, false);

        /* Contains Coordinates */
            r1 = new Rectangle(0, 0, 4, 3);
            r2 = new Rectangle(1, 1, 3, 2);

            evaluation(r1, r2, 0, true, false);

        /* Adjacent Coordinates */
            r1 = new Rectangle(0, 0, 4, 3);
            r2 = new Rectangle(0, 3, 4, 6);

            evaluation(r1, r2, 0, false, true);

        /* Adjacent Coordinates Reversed */
            r1 = new Rectangle(0, 3, 4, 6);
            r2 = new Rectangle(0, 0, 4, 3);

            evaluation(r1, r2, 0, false, true);

        /* Internally Adjacent Coordinates - NOT adjacent as per the definition */
            r1 = new Rectangle(0, 0, 4, 4);
            r2 = new Rectangle(0, 1, 2, 2);

            evaluation(r1, r2, 0, false, false);

        }catch(AssertionError e){
            System.out.println(e.toString());
            System.out.println("**** Assertion is incorrect ****");
        }
    }

    public static void evaluation(Rectangle r1, Rectangle r2, double intersectParam, boolean containsParam, boolean adjacencyParam){

        //Evaluate the 3 requirements and verify with JUnit tests

        System.out.println("Rectangle1 = " + r1.toString());
        System.out.println("Rectangle2 = " + r2.toString());

        List<Coordinate> testIntersectResult = testIntersect(r1,r2);
        assert testIntersectResult.size() == intersectParam;

        boolean contains = testContains(r1,r2);
        assert contains == containsParam;

        boolean adjacent = testAdjacent(r1,r2);
        assert adjacent == adjacencyParam;
    }

    public static List<Coordinate> testIntersect(Rectangle r1, Rectangle r2){

        List<Coordinate> intersections = r1.getIntersect(r2);
        System.out.print("Intersect: ");

        if(intersections.isEmpty()) {
            System.out.println("The given rectangles do not overlap.");
        }else{
            System.out.print("The rectangles overlap at points: ");
            for (Coordinate point: intersections)
            {
                System.out.print(point.toString());
            }
            System.out.println();
        }
        return intersections;
    }

    public static boolean testContains(Rectangle r1, Rectangle r2){

        boolean isContained = r1.isContained(r2);
        System.out.print("Contains: ");

        if (isContained){
            System.out.println("This rectangle is contained within the other rectangle");
        }else{
            System.out.println("This rectangle is not contained within the other rectangle");
        }
        return isContained;
    }

    public static boolean testAdjacent(Rectangle r1, Rectangle r2){

        boolean isAdjacent = r1.isAdjacent(r2);

        System.out.print("Adjacent: ");

        if (isAdjacent){
            System.out.println("This rectangle is adjacent with the other rectangle");
        }else{
            System.out.println("This rectangle is not adjacent with the other rectangle");
        }
        System.out.println();

        return isAdjacent;
    }

}
